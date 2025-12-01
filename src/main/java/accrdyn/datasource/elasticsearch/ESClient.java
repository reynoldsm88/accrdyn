package accrdyn.datasource.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class ESClient {
    private static final Logger LOG = LoggerFactory.getLogger( ESClient.class );

    @Value( "${elasticsearch.transport}" )
    private String esTransport;

    @Value( "${elasticsearch.host}" )
    private String esHost;

    @Value( "${elasticsearch.port}" )
    private int esPort;

    @Bean
    @Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
    public ElasticsearchClient getClient() {
        return ElasticsearchClient.of( builder -> {
            return builder.host( String.format( "%s://%s:%s", esTransport, esHost, esPort ) );
        } );
    }
}
