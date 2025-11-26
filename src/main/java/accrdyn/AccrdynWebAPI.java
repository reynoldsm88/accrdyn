package accrdyn;

import accrdyn.api.CodeInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class AccrdynWebAPI {

    /**
     * MAIN ENTRYPOINT...
     */
    public static void main( String[] args ) {
        SpringApplication.run( AccrdynWebAPI.class, args );
    }

    public ResponseEntity<CodeInfo> codeInfo( String system, String code ) {
        return ResponseEntity.ok( new CodeInfo() );
    }

    public ResponseEntity<String> concord( String fromSystem, String toSystem ) {
        return ResponseEntity.ok( "" );
    }

    @RequestMapping( "/info" )
    public String home() {
        return "Hello World!";
    }
}
