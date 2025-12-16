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
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component( value = "textEmbedder" )
public class BertTextEmbedder implements TextEmbedder {

    private static final Logger LOG = LoggerFactory.getLogger( BertTextEmbedder.class );

    private static final String MODEL_ENGINE = "PyTorch";

    private Predictor<String, float[]> modelPredictor;

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

    @PostConstruct
    @Override
    public void init() throws InitializationException {
        try {
            if ( this.modelPredictor == null ) {
                LOG.info( "initializing BERT word embedder. this might take a few seconds..." );
                //@formatter:off
                    Criteria<String, float[]> criteria =
                            Criteria.builder()
                                .optApplication( Application.NLP.TEXT_EMBEDDING )
                                .setTypes( String.class, float[].class )
                                .optDevice( Device.cpu() )
                                .optEngine( BertTextEmbedder.MODEL_ENGINE )
                                .optProgress( new ProgressBar() )
                            .build();
                    //@formatter:on

                ZooModel<String, float[]> model = criteria.loadModel();


                // NOTE - automatically loaded model is: distilbert-multilingual-nli-stsb-quora-ranking, 786 dims
                LOG.info( String.format( "BERT model loaded: name = %s", model.getName() ) );
                this.modelPredictor = model.newPredictor();
                LOG.info( "BERT word embedder finished initializing..." );

            } else {
                LOG.warn( "BERT word embedder already initialized..." );
            }
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
