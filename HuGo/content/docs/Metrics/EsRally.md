# esrally 压测工具

`esrally` 是 Elastic 官方开源的一款基于 `python3` 的针对 ElasticSearch 的压测工具。

## 官方对 ES 的压测结果

> https://elasticsearch-benchmarks.elastic.co/

- **环境**
  - CPU: Intel(R) Core(TM) i7-6700 CPU @ 3.40GHz
  - RAM: 32 GB
  - SSD: Crucial MX200
  - OS: Linux Kernel version 4.8.0-53
  - JVM: Oracle JDK 1.8.0_131-b11
- **集群**
  - 一台 esrally
  - 一台 ElasticSearch
- **写入性能**
  - `10w+` docs/s
- **搜索性能**
  - **term** : `23ms`
  - **match-all** : `123ms`
  - **range** : `543ms`
  - **scroll** : `1,111ms`
  - **aggs** : `3,000ms`



## esrally 简介

### 概念

- `race` : 比赛，代表依次基准测
- `car` : 代表 ES 集群 及其 配置
- `track` : **rally track** 意为 **拉力赛道**，是针对不同场景的测试数据，压测过程中默认是自动下载的
- `challenge` : 挑战，是基准测试测场景，如：多个客户端每次写多少数据等

> **总结**：在汽车拉力赛(`Rally`)中，汽车(`car`)会在赛道(`track`)开过各种弯道(`challenge`)完成一次比赛(`race`)，通过比赛成绩能看出车(手)的能力；通过锦标赛(`tournament`)可以看出哪个车(手)的水平比较高。
>
> —— https://blog.lyremelody.org/2018/01/23/esrally/



### 参数

#### --offline

- 指定 `--offline` 不会自动下载 `track` 数据

#### --pipeline

```bash
$ docker run elastic/rally list pipelines --offline
```

|       `--pipeline`        | 简介                                                         |
| :-----------------------: | ------------------------------------------------------------ |
|   **`benchmark-only`**    | 使用自己配置的集群，通过 `--target-hosts=x.x.x:9200,` 指定集群地址 |
|    `from-distribution`    | 自动从官网下载 `--distribution-version=x.x.x` 指定的版本进行压测 |
|  `from-sources-complete`  | 【内部基准测试】自动 git 拉取 源码构建 ES                    |
| `from-sources-skip-build` | 【内部基准测试】包已近构建好的情况                           |

## Docker 方式运行

> - [Running Rally with Docker](https://esrally.readthedocs.io/en/latest/docker.html)
>   - 不支持集群
>   - 只支持 `--pipeline=benchmark-only`

```bash
$ docker pull elastic/rally

docker run -it -v $(PWD):/root/track rockybean/esrally esrally race \
 --track-path=/root/track/logging \
 --offline \
 --pipeline=benchmark-only \
 --target-hosts=192.168.1.105:9200

```











## Read More

- **官方资源** 
  - Github： [elastic / rally](https://github.com/elastic/rally) 
  - Doc：[Quick Start](http://esrally.readthedocs.io/en/latest/quickstart.html)
  - Blog ： [Rally: Our benchmarking tool for Elasticsearch](https://www.elastic.co/cn/blog/announcing-rally-benchmarking-for-elasticsearch) 
- [Elasticsearch 压测方案之 esrally 简介](https://segmentfault.com/a/1190000011174694)

