package accrdyn.concordance.rules;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class CodeConcordanceServiceTest {

    @Test
    public void testRulesEngineWorks() {
        CodeConcordanceService codeConcordanceService = new CodeConcordanceService();
        ConcordanceRequest request = new ConcordanceRequest();
        ConcordanceResponse response = codeConcordanceService.doCodeConcordance( request );
    }

}
