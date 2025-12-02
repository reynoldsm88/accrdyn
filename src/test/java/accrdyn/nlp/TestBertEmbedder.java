package accrdyn.nlp;

import ai.djl.Application;
import ai.djl.Device;
import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

public class TestBertEmbedder {

    private final String GLOVE_MODEL = "models/glove/glove.6B/glove.6B.200d.txt";

    @Test
    public void testBertEmbedder() throws IOException, ModelNotFoundException, MalformedModelException, TranslateException {
        //@formatter:off
        Criteria<String, float[]> criteria =
                Criteria.builder()
                        .optApplication( Application.NLP.TEXT_EMBEDDING )
                        .setTypes( String.class, float[].class )
                        .optDevice( Device.cpu() )
                        .optEngine( "PyTorch" )
                        .optProgress( new ProgressBar() )
                        .build();
        //@formatter:on

        ZooModel<String, float[]> model = criteria.loadModel();
        Predictor<String, float[]> predictor = model.newPredictor();

        System.out.println( Arrays.toString( predictor.predict( "hello world!" ) ) );
    }
}
