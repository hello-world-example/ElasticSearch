# elasticsearch-metrics



> - 官方文档：https://grafana.com/grafana/dashboards/878
> - Github: https://github.com/trevorndodds/elasticsearch-metrics



## 安装监控脚本

```bash
# 下载 Python 文件
$ wget https://raw.githubusercontent.com/trevorndodds/elasticsearch-metrics/master/Grafana/elasticsearch2elastic.py

# 编辑
# 修改 ES_METRICS_CLUSTER_URL & ES_METRICS_MONITORING_CLUSTER_URL 为 ES 地址
$ vim elasticsearch2elastic.py

# 启动脚本（建议后台运行）
$ python elasticsearch2elastic.py
```



## 导入 Grafana Dashboard 

### 创建 DataSource

- Name：`ElasticSearch-Metrics`
- Type：`ElasticSearch`
- HTTP
  - URL：`http://x.x.x.x:9200`
- Elasticsearch details
  - Pattern：`Daily`
  - Index name：`[elasticsearch_metrics-]YYYY.MM.DD`
  - Time field name： `@timestamp`

### 导入模板

- 模板文件：https://grafana.com/api/dashboards/878/revisions/8/download
- 选择数据源：`ElasticSearch-Metrics`



## 大致原理

- 每 60s（ `ES_METRICS_INTERVAL` 配置） 调用 ES 原生 API 获取监控数据
  - /_cluster/health
  - /_cluster/stats
  - /_cat/nodes?v&h=n
  - /_stats
- 获取到的数据 重新写入 ES，索引文件名 `ES_METRICS_INDEX_NAME` 默认 `elasticsearch_metrics`，每天滚动一个索引













