package accrdyn.nlp;

import accrdyn.exceptions.InitializationException;
import ai.djl.Application;
import ai.djl.Device;
import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BertTextEmbedder implements TextEmbedder {

    private static final Logger LOG = LoggerFactory.getLogger( BertTextEmbedder.class );

    private Predictor<String, float[]> modelPredictor;

    public BertTextEmbedder() {
        super();
        this.modelPredictor = null;
    }

    @Override
    public float[] embed( String text ) {
        try {
            if ( this.modelPredictor != null ) {
                if ( text != null && !text.isBlank() ) {
                    return this.modelPredictor.predict( text );
                } else {
                    LOG.warn( "asked to embed an empty string, don't do that...." );
                    return new float[ 0 ];
                }
            } else {
                throw new IllegalStateException( "the BERT embeddings model has not been properly initialized" );
            }
        } catch ( Exception e ) {
            // TODO - update this later...
            throw new RuntimeException( e );
        }
    }

    public void init() throws InitializationException {
        try {
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
            this.modelPredictor = model.newPredictor();
        } catch ( ModelNotFoundException | MalformedModelException | IOException e ) {
            //@formatter:off
            throw new InitializationException(
                    "error loading BERT word embeddings model...",
                    e,
                    this.getClass(),
                    "embeddings"
            );
            //@formatter:on
        }
    }
}
