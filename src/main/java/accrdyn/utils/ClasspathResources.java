package accrdyn.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class ClasspathResources {

    public static Resource[] allFrom( String pattern ) throws IOException {
        ClassLoader classLoader = MethodHandles.lookup().getClass().getClassLoader();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver( classLoader );
        return resolver.getResources( pattern );
    }

    public static Resource fromPath( String path ) throws IOException {
        Resource[] resources = allFrom( path );

        if ( resources.length == 1 ) {
            return resources[ 0 ];
        } else {
            throw new IOException( "ClasspathResource.fromPath returns a single resource, found multiple. Use ClasspathResources.allFrom or update the path to point to a specific file" );
        }
    }


}
