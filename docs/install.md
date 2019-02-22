# 安装

## Docker 方式安装

```bash
# 拉取镜像 docker pull elasticsearch
$ docker pull docker.elastic.co/elasticsearch/elasticsearch:6.6.1

# 启动 docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch
$ docker run -p 9200:9200 -p 9300:9300 \
  -e "discovery.type=single-node" \
  docker.elastic.co/elasticsearch/elasticsearch:6.6.1
```



> - 官方文档：https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html
> - Docker 镜像仓库：https://www.docker.elastic.co/
> - `docker search elasticsearch` 搜索镜像

```bash
$ curl http://localhost:9200/
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

## 9200 & 9300 端口

- **9200** 是 RESTful 接口

- **9300** 是 集群间通信端口

## Kibana 图形界面 TODO

ElasticSearch 只提供 RESTful 接口进行访问，

```bash
# 拉取镜像
$ docker pull kibana

# 启动镜像
$ docker run -d -p 5601:5601 -e "ELASTICSEARCH_URL=http://192.168.1.5:9200" kibana
```

> - kibana 镜像使用说明：https://hub.docker.com/_/kibana
> - [Running Kibana on Docker](https://www.elastic.co/guide/en/kibana/current/docker.html)

