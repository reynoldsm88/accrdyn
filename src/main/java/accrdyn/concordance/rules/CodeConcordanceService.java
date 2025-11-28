package accrdyn.concordance.rules;

import accrdyn.concordance.CodeSystemRegistry;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeConcordanceService {

    private static final Logger LOG = LoggerFactory.getLogger( CodeConcordanceService.class );

    private static final KieBase KBASE = KieServices.Factory.get().getKieClasspathContainer().getKieBase( "concordance.rules.kbase" );
    private CodeSystemRegistry codeSystemRegistry;


    public ConcordanceResponse doCodeConcordance( ConcordanceRequest request ) {
        KieSession kSession = KBASE.newKieSession();

        kSession.insert( "hello" );
        kSession.fireAllRules();


        kSession.close();

        return new ConcordanceResponse();
    }

}
