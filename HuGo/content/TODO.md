# TODO

### Lucene 查询语法

- `_exist_   _Miss_`

## /_cat 

- 简介
- 参数
- **要看哪些东西**

/_cat/segments/ttpai-pinpointx-tracelog-2020.03.17?v

POST /index_name/_forcemerge?max_num_segments=1

POST /logstash-2014-10/_optimize?max_num_segments=1（无法在5.6.3上运行呢？）

https://blog.csdn.net/u010454030/article/details/79629400

## 重点监控指标

- /_cluster/health
- /_cluster/stats
- /_cat/nodes?v&h=n
- /_stats

