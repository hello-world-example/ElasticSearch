# Java Client

## 简介

- `elasticsearch-rest-client` 使用 9200 HTTP 协议进行通讯，上手简单，是低阶 API

  - httpcore
  - httpclient
  - httpasyncclient
  - httpcore-nio

- `elasticsearch-rest-high-level-client` 使用 9200 HTTP 协议进行通讯，对直接发送原始数据的低阶API进行分装，相对低阶API 更加友好，但是想要的功能并不一定封装全，任何未实现的功能可通过 低阶API进行操作

  - elasticsearch-rest-client
  - parent-join-client
  - aggs-matrix-stats-client
  - elasticsearch

- `transport` 使用 9300 TCP 协议端口进行通讯，性能好

  - transport-netty3-client
  - transport-netty4-client
  - reindex-client
    - elasticsearch-rest-client

  

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

> 代码示例详见：https://github.com/hello-world-example/ElasticSearch/tree/master/elasticsearch-rest-client

## Rest High Level Client

```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>5.6.15</version>
</dependency>
```

> 代码示例详见：https://github.com/hello-world-example/ElasticSearch/tree/master/elasticsearch-rest-high-client

## Transport Client

```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>transport</artifactId>
    <version>5.6.15</version>
</dependency>
```

> 代码示例详见：https://github.com/hello-world-example/ElasticSearch/tree/master/elasticsearch-client

## Read More

- [官方文档 Elasticsearch Clients](https://www.elastic.co/guide/en/elasticsearch/client/index.html)
  -  [Java REST Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/index.html)
  -  [Java API](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html)

