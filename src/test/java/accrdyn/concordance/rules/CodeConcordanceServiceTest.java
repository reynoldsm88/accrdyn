package accrdyn.concordance.rules;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CodeConcordanceServiceTest {

    @Test
    @Disabled
    public void testRulesEngineWorks() {
        CodeConcordanceService codeConcordanceService = new CodeConcordanceService();
        ConcordanceRequest request = new ConcordanceRequest();
        ConcordanceResponse response = codeConcordanceService.doCodeConcordance( request );
    }

    @Test
    @Disabled
    public void testLoadCodeDefinitions() {
        CodeConcordanceService codeConcordanceService = new CodeConcordanceService();
    }
}
