# 安装

## Docker

```bash
# 拉取镜像
$ docker pull docker.elastic.co/elasticsearch/elasticsearch:6.6.1

# 启动
$ docker run -p 9200:9200 -p 9300:9300 \
  -e "discovery.type=single-node" \
  docker.elastic.co/elasticsearch/elasticsearch:6.6.1
```



> - 官方文档：https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html
> - Docker 镜像仓库：https://www.docker.elastic.co/

