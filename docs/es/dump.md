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
  
#
# 复制数据
#
$ /opt/websuite/elasticdump/node_modules/elasticdump/bin/elasticdump  \
  --input=http://172.16.2.164:9200/dev_carmodel \
  --output=http://172.16.2.164:9200/dev_carmodel_dump
  
# 删除测试数据
# curl -X DELETE http://172.16.2.164:9200/dev_carmodel_dump

# 替换ID，导入多次
for i in {1..5} ; do 
  cat dev_carmodel.json | sed "s/,\"_id\":\"/,\"_id\":\"${i}-/g" >>  dev_carmodel_multi.json; 
done
```



TODO

```json
{
	_index: 'dev_carmodel_dump',
	_type: 'vehiclebrand',
	_id: '5-3883',
	status: 429,
	error: {
		type: 'es_rejected_execution_exception',
		reason: 'rejected execution of org.elasticsearch.transport.TransportService$7@3f96bcef on EsThreadPoolExecutor[bulk, queue capacity = 200, org.elasticsearch.common.util.concurrent.EsThreadPoolExecutor@6c40d30e[Running, pool size = 4, active threads = 4, queued tasks = 258, completed tasks = 436492]]'
	}
}
```

