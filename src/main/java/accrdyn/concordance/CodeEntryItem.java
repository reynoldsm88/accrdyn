package accrdyn.concordance;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude()
public class CodeEntryItem {

    private String id;
    private String system;
    private String code;
    private String version;
    private String description;
    private float[] embedding;


    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
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

    public float[] getEmbedding() {
        return embedding;
    }

    public void setEmbedding( float[] embedding ) {
        this.embedding = embedding;
    }
}
