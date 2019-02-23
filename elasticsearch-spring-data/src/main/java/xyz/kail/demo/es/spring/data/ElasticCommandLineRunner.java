package xyz.kail.demo.es.spring.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ElasticCommandLineRunner implements CommandLineRunner {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void run(String... strings) throws Exception {

    }
}
