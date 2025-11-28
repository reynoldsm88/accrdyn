package accrdyn.concordance.rules;

import org.junit.jupiter.api.Test;

public class ConcordanceRulesTest {

    private ConcordanceRules concordanceRules = new ConcordanceRules();


    @Test
    public void testRules() {
        ConcordanceRequest request = new ConcordanceRequest();
        ConcordanceResponse response = concordanceRules.performCodeConcordance( request );
    }
}
