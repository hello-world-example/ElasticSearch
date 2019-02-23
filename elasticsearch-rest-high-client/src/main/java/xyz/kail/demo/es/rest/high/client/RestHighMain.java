package xyz.kail.demo.es.rest.high.client;

import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import xyz.kail.demo.es.rest.client.RestClientFactory;

import java.io.IOException;

/**
 * Supported APIs：
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/5.6/java-rest-high-supported-apis.html
 *
 * @author Kail
 */
public class RestHighMain {

    public static void info(RestHighLevelClient restHighLevelClient) throws IOException {
        MainResponse info = restHighLevelClient.info();

        System.out.println("info()");
        System.out.println(String.format("  isAvailable:%s", info.isAvailable()));
        System.out.println(String.format("  getClusterName:%s", info.getClusterName()));
        System.out.println(String.format("  getBuild:%s", info.getBuild()));
        System.out.println(String.format("  getClusterUuid:%s", info.getClusterUuid()));
        System.out.println(String.format("  getNodeName:%s", info.getNodeName()));
        System.out.println(String.format("  getVersion:%s", info.getVersion()));
    }

    public static void main(String[] args) throws IOException {
        try (RestClient restClient = RestClientFactory.instance()) {
            RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClient);

//            基本信息
            info(restHighLevelClient);


        }
    }

}
