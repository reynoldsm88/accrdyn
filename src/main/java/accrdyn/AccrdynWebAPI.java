package accrdyn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(
        scanBasePackages = {
                "accrdyn",
                "accrdyn.web",
                "accrdyn.concordance",
                "accrdyn.datasource",
                "accrdyn.nlp"
        }
)
public class AccrdynWebAPI {

    public static void main( String[] args ) {
        SpringApplication.run( AccrdynWebAPI.class, args );
    }
}
