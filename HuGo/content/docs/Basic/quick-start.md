# 快速开始

## Docker 方式安装

### 安装

```bash
# 拉取镜像 
$ docker pull elasticsearch
# 启动 
$ docker run -d -p 9200:9200 -p 9300:9300 \
  -e TZ="Asia/Shanghai" \
  -e discovery.type="single-node" \
  --name es elasticsearch


# 从 elasticsearch 官方镜像仓库拉取 [速度比较慢]
# $ docker pull docker.elastic.co/elasticsearch/elasticsearch:6.6.1

# $ docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" \
#   docker.elastic.co/elasticsearch/elasticsearch:6.6.1
```



> - 官方文档：https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html
> - Docker 镜像仓库：https://www.docker.elastic.co/
> - `docker search elasticsearch` 搜索镜像



### 查看基本信息

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

### 9200 & 9300 端口

- **9200** 是 RESTful 接口

- **9300** 是 集群间通信端口

## Kibana

ElasticSearch 只提供 RESTful 接口进行访问。

Kibana 提供了可视化的数据分析功能，也可在线执行并测试 ElasticSearch 的 RESTful 接口，初期 认识ElasticSearch 使用 Kibana 调用 RESTful 的接口会更友好一点。

### 安装

```bash
# 拉取镜像
$ docker pull kibana

# 启动镜像
# http://192.168.1.5:9200 是 ElasticSearch 的 RESTful 接口地址
$ docker run -d -p 5601:5601 -e "ELASTICSEARCH_URL=http://192.168.1.5:9200" kibana
```

> - kibana 镜像使用说明：https://hub.docker.com/_/kibana
> - [Running Kibana on Docker](https://www.elastic.co/guide/en/kibana/current/docker.html)

### 基本配置

容器启动成功后，在浏览器访问：`http://localhost:5601`

首次进入 Kibana 需要配置一个索引配置规则 `Configure an index pattern`

- 在 `Index pattern` 一栏把默认的 `logstash-*` 改为 `*` 匹配所有索引
- `Time Filter field name` 一栏选择 `I don't want to use the Time Filter`
- `Create` 保存

### 异常处理

#### plugin:elasticsearch 有问题

```bash
# 删除索引
$ curl -X DELETE http://localhost:9200/.kibana

# 重建索引
$ curl -X PUT http://localhost:9200/.kibana
```



### Dev Tool

开始先不必了解 Kibana 的 报表和监控 等功能。

点击左侧菜单 `Dev Tool` ，这里工具可在线调用 ElasticSearch RESTful API，方便测试。

- `Ctrl/Cmd + Enter` 发起请求
- `Ctrl/Cmd + Up/Down` 跳转到上一个或下一个请求
- `Ctrl/Cmd + I` 自动缩进当前请求
- 
- `Ctrl/Cmd + Alt + L` 折叠或展开当前请求
- `Ctrl/Cmd + Option + 0`

## 其它图形界面工具

- `ElasticSearch Head` Chrome 插件
- `dejavu` Chrome 插件



