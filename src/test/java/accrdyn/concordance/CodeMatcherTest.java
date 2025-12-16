package accrdyn.concordance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static accrdyn.utils.DevTools.printDebug;


@SpringBootTest
//@Disabled
public class CodeMatcherTest {


    @Autowired
    private CodeMatcher codeMatcher;


    @Test
    public void testCodeMatcher() {
        try {
            printDebug( codeMatcher );
        } catch ( Exception e ) {

        }
    }

}
