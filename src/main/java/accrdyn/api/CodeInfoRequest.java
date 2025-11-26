package accrdyn.api;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude()
public class CodeInfoRequest {

    private String code;
    private String system;
    private String version;

    public CodeInfoRequest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem( String system ) {
        this.system = system;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion( String version ) {
        this.version = version;
    }
}
