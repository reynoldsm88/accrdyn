package accrdyn.nlp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static accrdyn.utils.DevTools.printDebug;

@SpringBootTest
@Disabled
public class TestBertEmbedder {


    @Autowired
    private TextEmbedder textEmbedder;


    @Test
    public void testBertEmbedder() {
        float[] embedding = textEmbedder.embed( "hello world" );
        printDebug( Arrays.toString( embedding ) );
        assert embedding.length > 1;
    }
}
