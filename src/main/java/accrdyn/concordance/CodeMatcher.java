package accrdyn.concordance;


import accrdyn.datasource.elasticsearch.ESClient;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CodeMatcher {

    private static final Logger LOG = LoggerFactory.getLogger( CodeMatcher.class );

    @Autowired
    public ESClient esClient;

    public ESClient getEsClient() {
        return esClient;
    }

    public void setEsClient( ESClient esClient ) {
        this.esClient = esClient;
    }

    @PostConstruct
    public void init() throws IOException {
        System.out.println( this.esClient.getClient().ping().toString() );
    }
}
