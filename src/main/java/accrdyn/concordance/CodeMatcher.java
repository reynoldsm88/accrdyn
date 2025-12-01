package accrdyn.concordance;


import accrdyn.datasource.elasticsearch.ESClient;
import accrdyn.exceptions.InitializationException;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.cat.ElasticsearchCatClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexRequest;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
public class CodeMatcher {

    private static final Logger LOG = LoggerFactory.getLogger( CodeMatcher.class );

    private static final String INDEX = "code_entries";

    @Value( "classpath:elasticsearch_schema/code_entries.json" )
    Resource codeEntryMappings;

    @Autowired
    public ESClient esClient;

    public ESClient getEsClient() {
        return esClient;
    }

    public void setEsClient( ESClient esClient ) {
        this.esClient = esClient;
    }

    @PostConstruct
    public void init() throws IOException {
        ElasticsearchClient client = this.esClient.getClient();

        try {
            GetIndexRequest indexReq = GetIndexRequest.of( builder -> {
                return builder.index( CodeMatcher.INDEX );
            } );

            GetIndexResponse indexResp = client.indices().get( indexReq );
        } catch ( ElasticsearchException e ) {
            if ( e.getMessage().contains( "no such index" ) ) {
                LOG.info( "semantic search index not initialized, creating now..." );
                try {
                    this.initializeIndex( client );
                } catch ( Exception ex ) {
                    throw new RuntimeException( ex );
                }
            }
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

        } catch ( IOException ioe ) {
            LOG.error( "encountered error when trying to read index mappings for index = " + CodeMatcher.INDEX );
            throw new InitializationException( "unable to locate or read mappings file for index = " + CodeMatcher.INDEX, ioe, this.getClass(), "config_file" );
        }
    }
}
