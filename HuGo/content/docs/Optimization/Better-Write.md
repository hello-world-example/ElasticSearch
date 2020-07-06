# 写入性能优化



## 整体思路

- 牺牲可靠性 和 搜索实时性
- 增加 refresh 间隔，减小搜索实时性
- 增加 flush 间隔，降低 segment merge 频率
- 增加 bulk 请求



## 时效性

### refresh_interval

- index.refresh_interval
  - 默认 1s，即数据写入 1s 后可被搜索到



### translog flush

- 默认 每个请求都 `flush`
- 相关配置
  - `index.translog.durability`
    - `request`
    - `async`
      - index.translog.sync_interval: 默认 5s
    - index.translog.flush_threshold_size: 默认 512m

## 段合并

- ES 2.0 开始，merge 行为不再由 ES 控制，而是由 Lucene 控制
- index.merge.scheduler.max_thread_count
  - 默认： max(1, min(4, cpu/2))
  - 机械硬盘建议：1
- index.merge.policy.*
  - tiered
  - log_byete_size
  - log_doc

## 索引

- 自动生成 Doc ID
  - 如果手动指定，ES 会尝试先读取原来的 doc，以判断是否需要更新
- 不需要建立索引的字段数据设置为： `no` 或者 `not_analyzed`
- 禁用 `_all` 字段
  - 【ES 6】 默认禁用，**可明显降低对 CPU 和 IO 的压力**
  - `_all` 字段包含所有字段分词后的关键字
  - 作用是在搜索时不指定特定的字段，从所有字段中检索
- 如果不需要评分，可禁用 `norms`
  - "字段" : {"type":"string", "norms":{"enabled"}}



## Read More

- [Elasticsearch写入性能优化](https://blog.csdn.net/jamesjxin/article/details/50512106)