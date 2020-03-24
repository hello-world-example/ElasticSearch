# Profile API



```json
{
  ...,
  "profile" : true,
  ...
}
```



## shards.id

```bash
# ID 格式
[nodeID][indexName][shardID]
```

> GET /_nodes/stats 查看节点信息





## shards.searches

### shards.searches.query

- `type` 它向我们显示了哪种类型的查询被触发
- `description`  该字段显示启动查询的lucene方法
- `time` lucene 执行此查询所用的时间
- `breakdown` 有关查询的更详细的细节，主要与 lucene 参数有关。
- children – 具有多个关键字的查询被拆分成相应术语的布尔查询，每个查询都作为单独的查询来执行。每个子查询的详细信息将填充到Profile API输出的子段中。在上面的章节中，可以看到第一个子元素查询是"levi"，下面给出查询时间和其他breakdown参数等详细信息。同样，对于第二个关键字，有一个名为"goals"的子元素具有与其兄弟相同的信息。从查询中的子段中，我们可以得到关于哪个搜索项在总体搜索中造成最大延迟的信息。

### shards.searches.rewrite_time

将查询重写成一个或多个组合查询的时间被称为“重写时间”，以纳秒为单位

### shards.searches.collector

在 Lucene 中，收集器是负责 收集原始结果，收集组合结果，执行结果排序等的过程。例如，在上面的执行的查询中，当查询语句中给出size:0时，使用的收集器是"totalHitCountCollector"。这只返回搜索结果的数量（search_count），不返回文档。此外，收集者所用的时间也一起给出了。








## shards.aggregations

