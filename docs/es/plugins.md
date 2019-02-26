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

如果是 [官方插件](https://github.com/elastic/elasticsearch/tree/master/plugins)，直接 `./bin/elasticsearch-plugin install [plugin_name]` 即可自动下载安装

### 第三方插件

- 从远端 url 下载安装：`elasticsearch-plugin install http://some.domain/path/to/plugin.zip`
- 本地安装：`elasticsearch-plugin install file:///C:/path/to/plugin.zip`
- 解压安装：压缩包解压到 `${ES_HOME}/plugins/` 目录即可

## 自定义插件开发

> - 官方文档 [Help for plugin authors](https://www.elastic.co/guide/en/elasticsearch/plugins/current/plugin-authors.html)
> - [官方示例](https://github.com/elastic/elasticsearch/tree/master/plugins/examples)
> - [Elasticsearch5.5.1插件开发指南](https://blog.csdn.net/cheng123bin/article/details/77573902)



## Read More

- 官方文档 [Elasticsearch Plugins and Integrations](https://www.elastic.co/guide/en/elasticsearch/plugins/current/index.html)
- 跟随官方版本一起发布的 [核心插件](https://github.com/elastic/elasticsearch/tree/master/plugins)
- 官方 [Analysis Plugins](https://www.elastic.co/guide/en/elasticsearch/plugins/current/analysis.html)