package accrdyn.nlp;

import accrdyn.exceptions.InitializationException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestBertEmbedder {

    private BertTextEmbedder textEmbedder = new BertTextEmbedder();

    @Test
    @Disabled
    public void testBertEmbedder() throws InitializationException {
        textEmbedder.init();
        float[] embedding = textEmbedder.embed( "hello world" );

        assert embedding.length > 1;
    }
}
