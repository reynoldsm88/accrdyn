package accrdyn.concordance;

import accrdyn.exceptions.InitializationException;
import accrdyn.utils.ClasspathResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CodeSystemRegistry {

    private static final Logger LOG = LoggerFactory.getLogger( CodeSystemRegistry.class );

    private Set<CodeSystem> codeSystems = new HashSet<>();

    public CodeSystemRegistry() {
        super();
    }

    public CodeSystem byName( String name ) {
        List<CodeSystem> systems = this.codeSystems.stream().filter( system -> system.getName() == name ).toList();

        if ( systems.isEmpty() ) {
            return null;
        } else if ( systems.size() > 1 ) {
            systems.sort( Comparator.comparing( CodeSystem::getVersion ).reversed() );
            return systems.get( 0 );
        } else {
            return systems.get( 0 );
        }
    }

    public CodeSystem byNameAndVersion( String name, String version ) {
        throw new RuntimeException( "VERSIONING IS NOT IMPLEMENTED YET...." );
    }

    public void init() throws InitializationException {
        if ( this.codeSystems.isEmpty() ) {
            try {
                this.loadCodeSystemDefinitions();
            } catch ( IOException e ) {
                //@formatter:off
                throw new InitializationException(
                        String.format( "there was an error loading the code system registry... %s", e.getMessage() ),
                        e,
                        this.getClass(),
                        "config_file"
                );
                //@formatter:on
            }
        } else {
            LOG.warn( "code concordance service is already initialized..." );
        }
    }

    private void loadCodeSystemDefinitions() throws IOException {
        Resource[] codeSystemFiles = ClasspathResources.allFrom( "classpath:code_systems/*.csv" );

        for ( Resource codeSystemCsv : codeSystemFiles ) {
            if ( codeSystemCsv.isFile() ) {
                this.codeSystems.add( this.loadCodeSystemDefinition( codeSystemCsv.getFile() ) );
            }
        }
    }

    private CodeSystem loadCodeSystemDefinition( File csvFile ) throws IOException {
        //@formatter:off
        CsvReadOptions options =
                CsvReadOptions
                    .builder( csvFile )
                    .columnTypes( new ColumnType[]{
                            ColumnType.STRING,
                            ColumnType.STRING,
                            ColumnType.STRING,
                            ColumnType.STRING,
                    } )
                    .build();
        //@formatter:on

        Table codeTable = Table.read().csv( options );

        Column<String> systemCol = codeTable.column( "system" ).unique().asStringColumn();
        Column<String> versionCol = codeTable.column( "version" ).unique().asStringColumn();

        String name = null;
        if ( systemCol.size() == 1 ) {
            name = systemCol.get( 0 );
        } else {
            throw new RuntimeException( "fix this!!!" );
        }

        String version = null;
        if ( versionCol.size() == 1 ) {
            version = versionCol.get( 0 );
        } else {
            throw new RuntimeException( "fix this!!!" );
        }

        List<CodeDefinition> codes = codeTable.stream().map( row -> {
            String code = row.getString( "code" );
            String description = row.getString( "description" );
            return new CodeDefinition( code, description );
        } ).toList();

        return new CodeSystem( name, version, codes );
    }

    public Set<CodeSystem> getCodeSystems() {
        return codeSystems;
    }

    public void setCodeSystems( Set<CodeSystem> codeSystems ) {
        this.codeSystems = codeSystems;
    }
}
