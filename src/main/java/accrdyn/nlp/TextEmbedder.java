package accrdyn.nlp;

import accrdyn.exceptions.InitializationException;

public interface TextEmbedder {

    float[] embed( String text );

    void init() throws InitializationException;

}
