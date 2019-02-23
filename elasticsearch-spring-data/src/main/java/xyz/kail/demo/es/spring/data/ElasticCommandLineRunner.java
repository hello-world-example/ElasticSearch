package xyz.kail.demo.es.spring.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * spring-data-elasticsearch API操作示例 http://cxytiandi.com/blog/detail/5862
 */
@Component
public class ElasticCommandLineRunner implements CommandLineRunner {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void run(String... strings) throws Exception {
    }
}
