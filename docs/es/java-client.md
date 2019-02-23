# Java Client

## 简介

- `elasticsearch-rest-client` 使用 9200 HTTP 协议进行通讯，上手简单，是低阶 API

  - org.apache.httpcomponents:httpasyncclient
  - org.apache.httpcomponents:httpcore-nio
  - org.apache.httpcomponents:**httpclient**
  - org.apache.httpcomponents:httpcore
  - commons-codec:commons-codec
  - commons-logging:commons-logging

- `elasticsearch-rest-high-level-client` 使用 9200 HTTP 协议进行通讯，对常用操作进行语义化封装，相对低阶API 更加友好，但是想要的功能并不一定封装全，任何未实现的功能可通过 低阶API进行操作

  - org.elasticsearch.client:**elasticsearch-rest-client**
  - org.elasticsearch:**elasticsearch**
  - org.elasticsearch.plugin:parent-join-client
  - org.elasticsearch.plugin:aggs-matrix-stats-client

- `transport` 使用 9300 TCP 协议端口进行通讯，性能好

  - transport-netty3-client
  - **transport-netty4-client**
  - reindex-client
    - **elasticsearch-rest-client**

  

> 官方建议使用 REST 方式， `transport`  在 7.0 开始废弃，8.0版本移除
>
> 详见官方文档：https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/client.html



>【【注意】】
>
>客户机应该具有与集群相同的版本，起码主版本必须一致
>
>主版本如果不一致，可能会出现调用错误
>
>次版本如果不一致，可能新功能无法使用

## Rest Client

```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-client</artifactId>
    <version>5.6.15</version>
</dependency>
```

> - 代码示例详见：
>   - https://github.com/hello-world-example/ElasticSearch/tree/master/elasticsearch-rest-client
> - 官方入门文档：
>   - https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-low-usage-initialization.html
> - 官方 Java Doc
>   - https://artifacts.elastic.co/javadoc/org/elasticsearch/client/elasticsearch-rest-client/6.6.1/org/elasticsearch/client/package-summary.html

## Rest High Level Client

```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>5.6.15</version>
</dependency>
```

初始化

```java
// 通过 低阶客户端 RestClient 作为参数进行初始化
RestHighLevelClient client = new RestHighLevelClient(lowLevelRestClient); 
```



> - 代码示例详见
>   - https://github.com/hello-world-example/ElasticSearch/tree/master/elasticsearch-rest-high-client
>
> - 官方 Java Doc
>   - https://artifacts.elastic.co/javadoc/org/elasticsearch/client/elasticsearch-rest-high-level-client/5.6.15/index.html
> - 

## Transport Client

```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>transport</artifactId>
    <version>5.6.15</version>
</dependency>
```

> - 代码示例详见
>   - https://github.com/hello-world-example/ElasticSearch/tree/master/elasticsearch-client

## Spring Data Elasticsearch

> https://spring.io/projects/spring-data-elasticsearch
>
> 官方文档：https://docs.spring.io/spring-data/elasticsearch/docs/3.1.5.RELEASE/reference/html/

## Read More

- [官方文档 Elasticsearch Clients](https://www.elastic.co/guide/en/elasticsearch/client/index.html)
  -  [Java REST Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/index.html)
  -  [Java API](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html)

