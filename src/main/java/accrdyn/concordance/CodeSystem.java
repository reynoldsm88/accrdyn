package accrdyn.concordance;

import java.util.List;
import java.util.Objects;

public class CodeSystem {
    private String name;
    private String version;
    private List<CodeDefinition> codes;


    public CodeSystem( String name, String version, List<CodeDefinition> codes ) {
        this.name = name;
        this.version = version;
        this.codes = codes;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion( String version ) {
        this.version = version;
    }

    public List<CodeDefinition> getCodes() {
        return codes;
    }

    public void setCodes( List<CodeDefinition> codes ) {
        this.codes = codes;
    }

    @Override
    public boolean equals( Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        CodeSystem that = (CodeSystem) o;
        return Objects.equals( name, that.name ) && Objects.equals( version, that.version ) && Objects.equals( codes, that.codes );
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, version, codes );
    }
}
