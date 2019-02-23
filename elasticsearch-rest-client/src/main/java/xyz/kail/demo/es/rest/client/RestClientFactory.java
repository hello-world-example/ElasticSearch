package xyz.kail.demo.es.rest.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Kail
 */
public class RestClientFactory {

    public static RestClient instance() {
        return SingleClient.CLIENT;
    }

    public static RestClient newInstance() {
        return SingleClient.newInstance();
    }

    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final class SingleClient {

        private static final RestClient CLIENT = newInstance();

        private static RestClient newInstance() {
            return RestClient
                    .builder(
                            new HttpHost("localhost", 9200, "http")
                    )
                    .setFailureListener(new RestClientFactory.DefaultFailureListener())
                    .build();
        }
    }

    private static final class DefaultFailureListener extends RestClient.FailureListener {

        @Override
        public void onFailure(HttpHost host) {
            System.out.println("Failure:" + host.toHostString());
        }
    }

}
