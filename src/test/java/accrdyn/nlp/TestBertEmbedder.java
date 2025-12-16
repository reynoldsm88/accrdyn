package accrdyn.nlp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class TestBertEmbedder {


    @Autowired
    private TextEmbedder textEmbedder;


    @Test
    public void testBertEmbedder() {
        float[] embedding = textEmbedder.embed( "hello world" );
        assert embedding.length > 1;
    }
}
