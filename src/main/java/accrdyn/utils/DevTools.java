package accrdyn.utils;

import java.util.Collection;
import java.util.Map;

public class DevTools {

    public static void printDebug( Object o ) {
        System.out.println( "###########################################################################" );
        if ( o != null ) {
            if ( o instanceof Map ) {
                Map<Object, Object> map = (Map<Object, Object>) o;
                map.keySet().forEach( key -> System.out.printf( "%s #> %s", key.toString(), map.get( key ).toString() ) );
            } else if ( o instanceof Collection ) {
                Collection<?> collection = (Collection<?>) o;
                collection.forEach( System.out::println );
            } else {
                System.out.println( o );
            }
        }
        System.out.println( "###########################################################################" );
    }
}
