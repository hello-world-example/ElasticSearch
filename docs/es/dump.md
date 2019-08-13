# 导入导出



## elasticsearch-dump

> 详见官方文档： https://github.com/taskrabbit/elasticsearch-dump



### 安装

```bash
$ mkdir /opt/websuite/elasticdump
$ cd /opt/websuite/elasticdump

# 本地安装
$ npm install elasticdump

# 查看帮助
$ ./node_modules/elasticdump/bin/elasticdump --help
```



### 常用命令

- type

  - mapping
  - data
  - analyzer

  

```bash
#
# 导出
#
$ /opt/websuite/elasticdump/node_modules/elasticdump/bin/elasticdump  \
  --input=http://172.16.2.164:9200/dev_carmodel \
  --output=/tmp/es/dev_carmodel_mapping.json \
  --type=mapping
  
$ /opt/websuite/elasticdump/node_modules/elasticdump/bin/elasticdump  \
  --input=http://172.16.2.164:9200/dev_carmodel \
  --output=/tmp/es/dev_carmodel_data.json \
  --type=data
  
#
# 导入
#
$ /opt/websuite/elasticdump/node_modules/elasticdump/bin/elasticdump  \
  --input=/tmp/es/dev_carmodel_data.json \
  --output=http://172.16.2.164:9200/dev_carmodel_dump \
  --type=data
  
# 删除测试数据
# curl -X DELETE http://172.16.2.164:9200/dev_carmodel_dump
```





