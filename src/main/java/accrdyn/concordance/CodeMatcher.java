package accrdyn.concordance;


import accrdyn.datasource.elasticsearch.ESClient;
import accrdyn.exceptions.InitializationException;
import accrdyn.nlp.TextEmbedder;
import accrdyn.utils.ClasspathResources;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Component
public class CodeMatcher {

    private static final Logger LOG = LoggerFactory.getLogger( CodeMatcher.class );

    private static final String INDEX = "code_entries";

    @Value( "classpath:elasticsearch_schema/code_entries.json" )
    private Resource codeEntryMappings;

    @Autowired
    private ESClient esClient;

    @Autowired
    private TextEmbedder embedder;

    @Value( "${accrdyn.semantic.search.seed.on.startup}" )
    private boolean seedDataOnStartup;


    @PostConstruct
    public void init() throws IOException {
        ElasticsearchClient client = this.esClient.getClient();
        try {
            this.initializeIndex( client );
            if ( seedDataOnStartup ) this.seedSemanticSearchData( client );
        } catch ( Exception e ) {

        }
    }

    private void seedSemanticSearchData( ElasticsearchClient client ) throws InitializationException {
        try {
            for ( Resource seedFile : ClasspathResources.allFrom( "classpath:semantic_search_seed/*.csv" ) ) {
                System.out.println( "TODO..." );
            }

        } catch ( IOException e ) {
            //TODO - finish this...
        }
    }

    private void initializeIndex( ElasticsearchClient client ) throws InitializationException {
        try {
            String mappingsJson = codeEntryMappings.getContentAsString( StandardCharsets.UTF_8 );

            CreateIndexRequest createReq = CreateIndexRequest.of( builder -> {
                builder.index( CodeMatcher.INDEX );
                builder.withJson( new StringReader( mappingsJson ) );
                return builder;
            } );

            CreateIndexResponse createResp = client.indices().create( createReq );

        } catch ( ElasticsearchException e ) {
            LOG.error( String.format( "encountered error while initializing index '%s'", CodeMatcher.INDEX ) );
            throw new InitializationException( String.format( "error creating elasitcsearch index '%s'", CodeMatcher.INDEX ), e, this.getClass(), "elasticsearch" );
        } catch ( IOException ioe ) {
            LOG.error( "encountered error when trying to read index mappings for index = " + CodeMatcher.INDEX );
            throw new InitializationException( "unable to locate or read mappings file for index = " + CodeMatcher.INDEX, ioe, this.getClass(), "config_file" );
        }
    }

    public ESClient getEsClient() {
        return esClient;
    }

    public void setEsClient( ESClient esClient ) {
        this.esClient = esClient;
    }

    public Resource getCodeEntryMappings() {
        return codeEntryMappings;
    }

    public void setCodeEntryMappings( Resource codeEntryMappings ) {
        this.codeEntryMappings = codeEntryMappings;
    }

    public TextEmbedder getEmbedder() {
        return embedder;
    }

    public void setEmbedder( TextEmbedder embedder ) {
        this.embedder = embedder;
    }

    private List<CodeEntryItem> loadExamplesFile( File file ) {
        //@formatter:off
        CsvReadOptions options =
                CsvReadOptions
                        .builder( file )
                        .columnTypes( new ColumnType[]{
                                ColumnType.STRING,
                                ColumnType.STRING,
                                ColumnType.STRING,
                                ColumnType.STRING,
                        } )
                        .build();
        //@formatter:on

        Table examplesTable = Table.read().csv( options );

        //@formatter:off
        return examplesTable.stream().map( row -> {
                    CodeEntryItem item = new CodeEntryItem();

                    item.setId( UUID.randomUUID().toString() );
                    item.setCode( row.getString( "code" ) );
                    item.setSystem( row.getString( "system" ) );
                    item.setVersion( row.getString( "version" ) );
                    item.setDescription( row.getString( "description" ) );
                    item.setEmbedding( this.embedder.embed( row.getString( "description" ) ) );

                    return item;

                } )
                .toList();
        //@formatter:on

    }

    private void bulkIndexDocuments( ElasticsearchClient esClient, List<CodeEntryItem> items ) {

    }
}
