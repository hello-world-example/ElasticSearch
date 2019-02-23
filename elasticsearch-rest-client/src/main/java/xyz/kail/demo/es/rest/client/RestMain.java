package xyz.kail.demo.es.rest.client;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * 构造方式
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/5.6/java-rest-low-usage-initialization.html
 * <p>
 * 发起请求
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/5.6/java-rest-low-usage-requests.html
 *
 * @author Kail
 */
public class RestMain {

    /**
     * 系统信息
     */
    private static void info(RestClient restClient) throws IOException {
        Response response = restClient.performRequest(
                "GET",
                "/"
        );
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 统计文档个数
     */
    private static void count(RestClient restClient) throws IOException {
        Map<String, String> params = Collections.singletonMap("pretty", "true");

        Response response = restClient.performRequest(
                "GET",
                "/_count",
                params
        );
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 创建文档
     */
    private static void putDoc(RestClient restClient) {
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);

        Map<String, String> params = Collections.singletonMap("pretty", "true");


        restClient.performRequestAsync(
                "PUT",
                "/posts/doc/1",
                params,
                entity,
                new ResponseListener() {
                    @Override
                    public void onSuccess(Response response) {
                        try {
                            System.out.println(EntityUtils.toString(response.getEntity()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }


    public static void main(String[] args) throws IOException {


        try (RestClient restClient = RestClientFactory.instance()) {
            // 系统信息
             info(restClient);

            // 统计文档个数
//             count(restClient);

            // 创建文档
//             putDoc(restClient);
        }

    }


}
