package accrdyn.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude()
public class CodeInfo {

    @JsonProperty( "system" )
    private String system;

    @JsonProperty( "code" )
    private String code;

    @JsonProperty( "version" )
    private String version;

    @JsonProperty( "description" )
    private String description;

    public CodeInfo() {
    }

    public CodeInfo( String system, String code, String version, String description ) {
        this.system = system;
        this.code = code;
        this.version = version;
        this.description = description;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem( String system ) {
        this.system = system;
    }

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion( String version ) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }
}

