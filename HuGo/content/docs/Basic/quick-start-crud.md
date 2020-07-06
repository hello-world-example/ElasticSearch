# RESTful 增删改查

## 状态查询

### 基本信息

```bash
$ curl -X GET 'http://localhost:9200/'
{
  "name" : "KpRS12p",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "PU5vpob2Q1GyVV-P9o0OwQ",
  "version" : {
    "number" : "5.6.12",
    "build_hash" : "cfe3d9f",
    "build_date" : "2018-09-10T20:12:43.732Z",
    "build_snapshot" : false,
    "lucene_version" : "6.6.1"
  },
  "tagline" : "You Know, for Search"
}
```

### 文档个数

```bash
$ curl -X GET 'http://localhost:9200/_count?pretty'
{
  "count" : 12,
  "_shards" : {
    "total" : 6,
    "successful" : 6,
    "skipped" : 0,
    "failed" : 0
  }
}
```

### 集群节点状态

```bash
$ curl -X GET 'http://localhost:9200/_cluster/state/nodes/?pretty'
{
  "cluster_name" : "elasticsearch",
  "nodes" : {
    "KpRS12pATMmPQv_emx6Szg" : {
      "name" : "KpRS12p",
      "ephemeral_id" : "iGiUhJ7gRImn5g0TYjlYbQ",
      "transport_address" : "127.0.0.1:9300",
      "attributes" : { }
    }
  }
}
```

## 文档管理 CRUD

### 创建或更新

如果 `/<_id>` 存在则为更新，如果不存在则为创建

```bash
$ curl -X PUT -d '{"id":"1","title":"New version of Elasticsearch released!!!!","content":"Version 1.0 released today!"}' 'http://localhost:9200/blog/article/2?pretty'

{
  "_index" : "blog",
  "_type" : "article",
  "_id" : "2",
  "_version" : 7,
  "result" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "created" : false
}
```

### 新增文档

```bash
$ curl -X POST -d '{"title":"新增文档","content":"内容1"}' 'http://localhost:9200/blog/article?pretty'

{
  "_index" : "blog",
  "_type" : "article",
  "_id" : "AWkaOUZRo273Me3hdpnS",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "created" : true
}
```

### 查询指定文档

```bash
$ curl -X GET 'http://localhost:9200/blog/article/AWkaOUZRo273Me3hdpnS?pretty'
{
  "_index" : "blog",
  "_type" : "article",
  "_id" : "AWkaOUZRo273Me3hdpnS",
  "_version" : 1,
  "found" : true,
  "_source" : {
    "title" : "新增文档",
    "content" : "内容1"
  }
}
```

### 更新文档字段

```bash
$ curl -X POST -d '{"script":"ctx._source.content = \"内容2\""}' 'http://localhost:9200/blog/article/AWkaOUZRo273Me3hdpnS/_update?pretty'
{
  "_index" : "blog",
  "_type" : "article",
  "_id" : "AWkaOUZRo273Me3hdpnS",
  "_version" : 2,
  "result" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  }
}
```

### 获取索引映射管理

```bash
$ curl 'http://localhost:9200/blog/_mapping?pretty'
{
  "blog" : {
    "mappings" : {
      "article" : {
        "properties" : {
          "content" : {
            "type" : "text",
            "fields" : {
              "keyword" : {
                "type" : "keyword",
                "ignore_above" : 256
              }
            }
          },
          "title" : {
            "type" : "text",
            "fields" : {
              "keyword" : {
                "type" : "keyword",
                "ignore_above" : 256
              }
            }
          }
        }
      }
    }
  }
}
```

### 搜索所有文档

```bash
$ curl 'http://localhost:9200/blog/_search?pretty'
{
  "took" : 4,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 1,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "blog",
        "_type" : "article",
        "_id" : "AWkaOUZRo273Me3hdpnS",
        "_score" : 1.0,
        "_source" : {
          "title" : "新增文档",
          "content" : "内容2"
        }
      }
    ]
  }
}
```

### 删除索引

```bash
$ curl -X DELETE 'http://localhost:9200/blog'
```

## Read More

- [Document APIs](https://www.elastic.co/guide/en/elasticsearch/reference/current/docs.html) 官方文档