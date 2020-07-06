# 配置示例



## 索引模板配置

```json
{
  /* 模板优先级，数字越大，优先级越高，优先级高的模板覆盖优先级低的模板 */
  "order":0,
  /* 匹配的索引，如果多个模板匹配一个索引，优先级高的模板覆盖优先级低的模板 */
  "template":"demo-*",
  "settings":{
    "index":{
      /* 分片数 */
      "number_of_shards":"3",
      /* 副本数 */
      "number_of_replicas":"1",
      /* 【可见性】refresh 周期，默认 1s，即数据写入 1s 后可见 */
      "refresh_interval":"60s",
      /* 自动生成 doc id 以提高写入效率【7.0废弃】 */
      "optimize_auto_generated_id":"true",
      /* 节点脱离集群，主节点延迟重分配分片，以减少重新平衡分片带来的资源浪费，默认 1m */
      "unassigned.node_left.delayed_timeout":"1d",
      /* flush 策略 */
      "translog":{
        /* 【可靠性】异步刷新，存在数据丢失的情况，默认 request， */
        "durability":"async",
        /* 【可靠性】异步刷新间隔 */
        "sync_interval":"30s",
        /* 允许 translog 在 flush 前存放更大的 Segment，以减少 flush 的频率，减少磁盘IO */
        "flush_threshold_size":"1000mb"
      },
      "merge":{
        "policy":{
          /* 默认 5G */
          "max_merged_segment":"2gb",
          /* 分段的数量，数值越小，最终 segment 越少，但是需要 merge 的操作会更多，默认 10 */
          "segments_per_tier":"24",
          /* 默认一次合并的分段数 */
          "max_merge_at_once": "10"
        },
        "scheduler":{
          /* Merge 线程数，默认 max(1, min(4, cpu/2))， 机械硬盘建议1，SSD 可调高 */
          "max_thread_count":"1"
        }
      }
    }
  },
  "mappings":{
    "doc":{
      "_all":{
        /* _all 字段包含所有字段分析后的信息，作用是在搜索时不指定特定的字段 */
        /* ES6 之后，默认禁用，可明显降低 CPU 和 IO 压力 */
        "enabled":false
      },
      "_source" : { 
        /* 用于存储 doc 原始数据，对于不需要存储的字段可禁用 */
        "enabled" : false,
        /* 包含指定的字段 */
        "includes":["field1","field2"],
        /* 排除指定的字段 */
        "excludes":["field1","field2"]
      }
    }
  }
}
```



## elasticsearch.yml

```yml
# 已经索引好的文档会先存放在内存缓存中，等待被写到到 Segment，默认 堆内存的10%
indices.memory.index_buffer_size: 30%
# 默认 48m
indices.memory.min_index_buffer_size: 96mb
```



## Read More

- 【老的方式】[REST APIs](https://www.elastic.co/guide/en/elasticsearch/reference/current/rest-apis.html) » [Index APIs](https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html) » [Put index template API](https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-templates-v1.html)
- 【7.8 组合模板】[REST APIs](https://www.elastic.co/guide/en/elasticsearch/reference/current/rest-apis.html) » [Index APIs](https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html) » [Index templates](https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-templates.html)
- ❤ [Elasticsearch Index Template（索引模板)](https://www.jianshu.com/p/1f67e4436c37)

