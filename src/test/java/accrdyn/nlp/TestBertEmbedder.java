package accrdyn.nlp;

import ai.djl.Application;
import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import org.junit.jupiter.api.Test;

public class TestBertEmbedder {

    @Test
    public void testBertEmbedderSetup() {
        //@formatter:off
        Criteria<String, float[]> criteria = Criteria.builder()
                .optApplication( Application.NLP.TEXT_EMBEDDING )
                .setTypes( String.class, float[].class )
                .optModelUrls( "djl://ai.djl.huggingface.pytorch/sentence-transformers/bert-base-nli-cls-token" )
                .optEngine( "PyTorch" )
                .optProgress( new ProgressBar() )
                .build();
        //@formatter:on

        try {
            ZooModel<String, float[]> model = criteria.loadModel();
            Predictor<String, float[]> predictor = model.newPredictor();

        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

}
