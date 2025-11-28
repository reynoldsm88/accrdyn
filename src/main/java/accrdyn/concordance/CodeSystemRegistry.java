package accrdyn.concordance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class CodeSystemRegistry {

    private static final Logger LOG = LoggerFactory.getLogger( CodeSystemRegistry.class );

    private Set<CodeSystem> codeSystems;

    public CodeSystemRegistry() {

    }

    public void addCodeSystem( CodeSystem system ) {
        this.codeSystems.add( system );
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

        return null;
    }

    public void init() {
        if ( this.codeSystems == null ) {
            try {
                this.loadCodeSystemDefinitions();
            } catch ( IOException e ) {
                throw new RuntimeException( e );
            }
        } else {
            LOG.warn( "code concordance service is already initialized..." );
        }
    }

    private void loadCodeSystemDefinitions() throws IOException {
    }

    private void loadCodeSystemDefinition( File csvFile ) throws IOException{

    }
}
