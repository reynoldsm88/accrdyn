package accrdyn.exceptions;

/**
 * A generic exception that can be thrown during various stages of starting up the
 * application.
 */
public class InitializationException extends Exception {

    private final String component;
    private final String integration;

    public InitializationException( String message, Exception cause, Class<?> component, String integration ) {
        this( message, cause, component.getCanonicalName(), integration );
    }

    public InitializationException( String message, Exception cause, String component, String integration ) {
        super( message, cause );
        this.component = component;
        this.integration = integration;
    }

    public String getComponent() {
        return component;
    }
}
