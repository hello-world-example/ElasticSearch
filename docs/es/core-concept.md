# 核心概念

## Lucene

### 词汇表

- 文档（document）：索引和搜索时使用的主要数据载体，包含一个或多个存有数据的字段
- 字段（field）：文档的一部分，包含名称和值两部分
- 词（term）：一个搜索单元，表示文本中的一个词
- 标记（token）：表示在字段文本中出现的词，由这个词的文本、开始和结束偏移量以及类
  型组成
- 倒排索引（inverted index）
