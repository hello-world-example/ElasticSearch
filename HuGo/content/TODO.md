# TODO

## 疑问

- 如何查看 ElasticSearch 默认配置
- 索引模板
-  Lucene 查询语法

  - `_exist_   _Miss_`
- 



## 重点监控指标

- /_cluster/health
- /_cluster/stats
- /_cat/nodes?v&h=n
- /_stats





## × 广度优先搜索

- 默认深度
- 设置为广度优先： ` "collect_mode" : "breadth_first"`

```json
{
    "size":0,
    "aggregations":{
        "source":{
            "terms":{
                "field":"source",
                "size":10,
                "collect_mode":"breadth_first"
            }
        }
    }
}
```



## × global_ordinals

```
{
    "size": 0,
    "aggregations": {
        "source": {
            "terms": {
                "field": "url.keyword",
                "size": 10,
                "execution_hint": "global_ordinals"
            }
        }
    }
}
```





## TD aggregations 聚合类型



http://172.16.2.164:9200/
http://localhost:9200/

```
{"size":0,"query":{"bool":{"must":[{"range":{"date":{"from":"2019-08-04 00:00:00","to":"2019-08-11 23:59:00","include_lower":true,"include_upper":true,"boost":1}}}],"disable_coord":false,"adjust_pure_negative":true,"boost":1}},"aggregations":{"url":{"cardinality":{"field":"url"}}}}


# 去重 统计
{"aggregations":{"url":{"cardinality":{"field":"url"}}}}


# 范围查询
"query":{"bool":{"must":[{"range":{"date":{"from":"2019-08-07","to":"2019-08-11","include_lower":true,"include_upper":true,"boost":1}}}],"disable_coord":false,"adjust_pure_negative":true,"boost":1}}


# 聚合
curl -X POST --data '{"size":0, "aggregations":{"url":{"terms":{"field":"url.keyword", "size": 3}}}}' http://172.16.4.115:9200/apm-info-log/_search
```



## query



```json
"query":{
    "bool":{
        "must":[
            {
                "range":{
                    "date":{
                        "from":"2019-08-07",
                        "to":"2019-08-11",
                        "include_lower":true,
                        "include_upper":true
                    }
                }
            }
        ],
        "disable_coord":false,
        "adjust_pure_negative":true
    }
}
```

