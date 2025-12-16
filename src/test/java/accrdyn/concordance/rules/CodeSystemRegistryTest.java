package accrdyn.concordance.rules;

import accrdyn.concordance.CodeSystemRegistry;
import accrdyn.exceptions.InitializationException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeSystemRegistryTest {

    private static final Logger LOG = LoggerFactory.getLogger( CodeSystemRegistryTest.class );

    @Test
    public void testCodeSystemRegistryInitialization() throws InitializationException {
        CodeSystemRegistry registry = new CodeSystemRegistry();
        registry.init();

        assert registry.getCodeSystems().size() == 2;
    }
}
