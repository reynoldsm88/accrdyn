package accrdyn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication( scanBasePackages = "accrdyn" )
public class AccrdynWebAPI {

    public static void main( String[] args ) {
        SpringApplication.run( AccrdynWebAPI.class, args );
    }
}
