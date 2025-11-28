package accrdyn.concordance.rules;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ConcordanceRules {

    private KieContainer kContainer = KieServices.Factory.get().getKieClasspathContainer();


    public ConcordanceResponse performCodeConcordance( ConcordanceRequest request ) {
        KieBase kbase = kContainer.getKieBase( "concordance.rules.kbase" );
        KieSession kSession = kbase.newKieSession();


        kSession.insert( "hello" );

        kSession.fireAllRules();

        kSession.close();
        return new ConcordanceResponse();
    }

}
