# _cat 接口

_cat 接口的输出格式**适合 终端、人眼阅读**，不建议应用程序使用

使用方式简单：`GET http://localhost:9200/_cat`




## 参数

| 参数   | 描述                                     | 示例                 |
| ------ | ---------------------------------------- | -------------------- |
| help   | 帮助，查看所有的列和描述                 | `/_cat/count?help`   |
| v      | 输出列名                                 |                      |
| h      | 指定列表名，多个用 `,` 分割              | `?v&h=*` 输出所有列  |
| s      | 排序，多列用 `,` 分割                    | `?s=store.size:desc` |
| bytes  | 指定单位：`b` 、`k` 、`m` 、`g`          | `?v&bytes=b`         |
| format | `text`、`json`、`yaml`、`smile` 、`cbor` |                      |
| pretty | 格式化输出                               |                      |



## =^.^= 

| Type | 子接口 |      |      |
| :--: | ---- | ---- | ---- |
| Segment |  `/_cat/segments`|      |      |
| Segment |  `/_cat/segments/{index}`|      |      |
|  | | | |
| Doc |  `/_cat/count`| 文档数 |      |
| Doc |  `/_cat/count/{index}`| 文档数 |      |
|  | |  | |
| 索引 |  `/_cat/indices`| 索引信息 |      |
| 索引 |  `/_cat/indices/{index}`| 索引信息 |      |
| 索引 |  `/_cat/aliases`| 别名 |      |
| 索引 |  `/_cat/aliases/{alias}`| 别名 |      |
|  | |  | |
| 分片 |  `/_cat/shards`| 分片信息 |      |
| 分片 |  `/_cat/shards/{index}`| 分片信息 |      |
| 分片 |  `/_cat/allocation`| 分片数、磁盘占用 |      |
| 分片 |  `/_cat/recovery`| 分片移动恢复情况 |      |
| 分片 |  `/_cat/recovery/{index}`| 分片移动恢复情况 |      |
|  | |  | |
| 集群 |  `/_cat/master`|      |      |
| 集群 | ❤ **`/_cat/nodes`** | ❤性能指标❤ 内存、CPU、负载 |      |
| 集群 |  `/_cat/nodeattrs`| 节点属性 |      |
| 集群 |  `/_cat/health`| ❤集群健康❤ |      |
|  | |  | |
| 线程 |  `/_cat/tasks`|      |      |
| 线程 | **`/_cat/pending_tasks`** | 挂起等待的任务 |      |
| 线程 |  `/_cat/thread_pool`| 线程池情况 |      |
| 线程 |  `/_cat/thread_pool/{thread_pools}`| 线程池情况 |      |
|  | | | |
| Info |  `/_cat/plugins`| 插件信息 |      |
| Info |  `/_cat/templates`| 模板名 |      |
| Info |  `/_cat/fielddata`|      |      |
| Info |  `/_cat/fielddata/{fields}`|      |      |
| | | | |
| 快照 |  `/_cat/repositories`|      |      |
| 快照 |  `/_cat/snapshots/{repository}`|      |      |



## Read More

- [cat APIs](https://www.elastic.co/guide/en/elasticsearch/reference/current/cat.html)
- [Elasticsearch Cat 命令](https://blog.csdn.net/wangpei1949/article/details/82287444)