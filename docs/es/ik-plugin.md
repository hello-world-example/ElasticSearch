# IK 中文分词

## 安装

```bash
# https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.3.0/elasticsearch-analysis-ik-6.3.0.zip

$ Version=$(curl -s http://localhost:9200 | grep number | awk  -F '"' '{print $4}')

$ ./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v${Version}/elasticsearch-analysis-ik-${Version}.zip

-> Downloading https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v5.6.12/elasticsearch-analysis-ik-5.6.12.zip
[===========================>                     ] 56%
```

## 安装后启动报错问题

### 报错内容

```
[2019-02-26T07:19:11,994][INFO ][o.e.n.Node               ] [KpRS12p] started
[2019-02-26T07:19:12,421][INFO ][o.w.a.d.Monitor          ] try load config from /usr/share/elasticsearch/config/analysis-ik/IKAnalyzer.cfg.xml
[2019-02-26T07:19:12,425][INFO ][o.w.a.d.Monitor          ] try load config from /usr/share/elasticsearch/plugins/analysis-ik/config/IKAnalyzer.cfg.xml
[2019-02-26T07:19:12,426][ERROR][o.w.a.d.Monitor          ] ik-analyzer
java.io.FileNotFoundException: /usr/share/elasticsearch/config/analysis-ik/IKAnalyzer.cfg.xml (No such file or directory)
	at java.io.FileInputStream.open0(Native Method) ~[?:1.8.0_181]
	at java.io.FileInputStream.open(FileInputStream.java:195) ~[?:1.8.0_181]
	at java.io.FileInputStream.<init>(FileInputStream.java:138) ~[?:1.8.0_181]
	at org.wltea.analyzer.dic.Dictionary.<init>(Dictionary.java:110) ~[?:?]
...

[2019-02-26T07:19:12,454][ERROR][o.w.a.d.Monitor          ] /usr/share/elasticsearch/plugins/analysis-ik/config/main.dic (No such file or directory)
java.io.FileNotFoundException: /usr/share/elasticsearch/plugins/analysis-ik/config/main.dic (No such file or directory)
	at java.io.FileInputStream.open0(Native Method) ~[?:1.8.0_181]
	at java.io.FileInputStream.open(FileInputStream.java:195) ~[?:1.8.0_181]
	at java.io.FileInputStream.<init>(FileInputStream.java:138) ~[?:1.8.0_181]
...
```

> - [IK 1.10.6 安装后无法找到 IKAnalyzer.cfg.xml 以及 main.dic](https://github.com/medcl/elasticsearch-analysis-ik/issues/467)
> - [move config file to <path.conf>/analysis-ik](https://github.com/medcl/elasticsearch-analysis-ik/pull/192)
> - [Also load config from /etc/elasticsearch/analysis-ik](https://github.com/medcl/elasticsearch-analysis-ik/pull/197)

### Docker 环境解决方式

使用 `./bin/elasticsearch-plugin install` 安装后，IK 配置文件会背移动到 `/etc/elasticsearch/analysis-ik` 路径下，把配置文件移动到 报错的目录即可

- `mkdir /usr/share/elasticsearch/plugins/analysis-ik/config/`
- `cd /usr/share/elasticsearch/plugins/analysis-ik/config/`
- `mv /etc/elasticsearch/analysis-ik/* ./`

### 解压方式安装插件

```bash
# 进到插件目录
$ cd /usr/share/elasticsearch/plugins/

# 下载指定版本的插件压缩包
$ wget https://github.com/medcl/elasticsearch-analysis-
ik/releases/download/v5.6.12/elasticsearch-analysis-ik-5.6.12.zip

# 解压
$ unzip elasticsearch-analysis-ik-5.6.12.zip

# 重命名
$ mv elasticsearch analysis-ik

# 删除压缩包
$ rm -rf elasticsearch-analysis-ik-5.6.12.zip

# 查看已安装的插件
$ ./bin/elasticsearch-plugin list
analysis-ik
```



## 新增文档测试

```bash
# 删除索引
DELETE /index_ik_test

# 创建索引信息 
PUT /index_ik_test

# 查看索引信息
GET /index_ik_test

# 设置映射关系
PUT /index_ik_test/fulltext/_mapping
{
  "properties": {
    "title": {
      "type": "text",
      "analyzer": "ik_max_word"
    },
    "content": {
      "type": "text",
      "analyzer": "ik_smart"
    }
  }
}


# 获取映射
GET /index_ik_test/_mapping

# 获取设置
GET /index_ik_test/_settings

# 新增文档
POST /index_ik_test/fulltext/1
{
  "title": "中华人民共和国国歌",
  "content": "中华人民共和国国歌"
}

# 查看所有文档
GET /index_ik_test/_search

# 可以搜到
POST /index_ik_test/fulltext/_search
{
  "query": {
    "match": {
      "title": "中华"
    }
  }
}

# 搜索不到
POST /index_ik_test/fulltext/_search
{
  "query": {
    "match": {
      "content": "中华"
    }
  }
}

# 可以搜到(高亮显示)
POST /index_ik_test/fulltext/_search
{
  "query": {
    "match": {
      "content": "国歌"
    }
  },
  "highlight": {
    "pre_tags": [
      "<red>"
    ],
    "post_tags": [
      "</red>"
    ],
    "fields": {
      "content": {}
    }
  }
}

```

## ik_max_word 和 ik_smart 的区别

`ik_max_word`: 会将文本做最细粒度的拆分，会穷尽各种可能的组合，比如会将 **“中华人民共和国国歌”** 拆分为

- 中华人民共和国
- 中华人民
- 中华
- 华人
- 人民共和国
- 人民
- 共和国
- 共和
- 国
- 国歌

```bash
GET /_analyze
{
  "analyzer" : "ik_max_word",
  "text" : "中华人民共和国国歌"
}
```



`ik_smart`: 会做最粗粒度的拆分，比如会将  **“中华人民共和国国歌”** 拆分为

- 中华人民共和国
- 国歌

```bash
GET /_analyze
{
  "analyzer" : "ik_smart",
  "text" : "中华人民共和国国歌"
}
```

## Read More

- [wks/ik-analyzer](https://github.com/wks/ik-analyzer)

- [IK Analysis for Elasticsearch](https://github.com/medcl/elasticsearch-analysis-ik)

- [如何在Elasticsearch中安装中文分词器(IK+pinyin)](https://www.cnblogs.com/xing901022/p/5910139.html)

- [elasticsearch mapping 添加 编辑 删除字段](http://blog.51yip.com/server/1915.html)

- 

- 官方文档 

  - [Create Index](https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-create-index.html)
  - [Put Mapping](https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-put-mapping.html)

  

