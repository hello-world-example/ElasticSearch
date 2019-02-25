# 插件机制

ElasticSearch 插件是一个 jar 文件，也可能包含脚本和配置文件，并且**必须安装在群集中的每个节点上，安装后，必须重新启动每个节点才能看到插件**。



## elasticsearch-plugin

```bash
$ ./bin/elasticsearch-plugin -h
A tool for managing installed elasticsearch plugins

Commands
--------
list - Lists installed elasticsearch plugins
install - Install a plugin
remove - removes a plugin from Elasticsearch

Non-option arguments:
command

Option         Description
------         -----------
-h, --help     show help
-s, --silent   show minimal output
-v, --verbose  show verbose output
```

## elasticsearch-plugin 脚本内容

```bash
cat ./bin/elasticsearch-plugin

...

declare -a args=("$@")
path_props=(-Des.path.home="$ES_HOME")

if [ -e "$CONF_DIR" ]; then
  path_props=("${path_props[@]}" -Des.path.conf="$CONF_DIR")
fi

exec "$JAVA" $ES_JAVA_OPTS -Delasticsearch "${path_props[@]}" -cp "$ES_HOME/lib/*" org.elasticsearch.plugins.PluginCli "${args[@]}"
```

## 安装插件

### 官方插件

TODO

### 第三方插件

TODO

## 常用插件

| 插件                      | 简介                                        | 地址                                                         |
| ------------------------- | ------------------------------------------- | ------------------------------------------------------------ |
| Elastic-HQ                | Management and Monitoring for Elasticsearch | 官方：http://www.elastichq.org/<br />Github：[ElasticHQ/elasticsearch-HQ](https://github.com/ElasticHQ/elasticsearch-HQ) |
| elasticsearch-analysis-ik | IK 中文分词器                               | Github：[medcl/elasticsearch-analysis-ik](https://github.com/medcl/elasticsearch-analysis-ik) |
|                           |                                             |                                                              |
|                           |                                             |                                                              |
|                           |                                             |                                                              |



## Read More

- 官方文档 [Elasticsearch Plugins and Integrations](https://www.elastic.co/guide/en/elasticsearch/plugins/current/index.html)
- 跟随官方版本一起发布的 [核心插件](https://github.com/elastic/elasticsearch/tree/master/plugins)
- 官方 [Analysis Plugins](https://www.elastic.co/guide/en/elasticsearch/plugins/current/analysis.html)
- [ElasticSearch学习总结（八）：插件的开发](https://blog.csdn.net/eric_sunah/article/details/79458440)