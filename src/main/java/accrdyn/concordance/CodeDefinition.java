package accrdyn.concordance;

import java.util.Objects;

public class CodeDefinition {
    private String code;
    private String description;

    public CodeDefinition( String code, String description ) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    @Override
    public boolean equals( Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        CodeDefinition that = (CodeDefinition) o;
        return Objects.equals( code, that.code ) && Objects.equals( description, that.description );
    }

    @Override
    public int hashCode() {
        return Objects.hash( code, description );
    }
}
