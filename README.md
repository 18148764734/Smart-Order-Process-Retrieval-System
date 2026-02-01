# 智能订单全流程检索系统 (Smart Order Process Retrieval System)

基于LangChain + RAG + 知识图谱的跨文档智能检索系统，专注于解决企业内跨部门业务流程的智能问答场景。

## 🌟 核心特性

- 基于LangChain的智能检索框架
- RAG(检索增强生成)技术提升回答准确性
- 知识图谱构建订单全流程关系网络
- MCP协议实现跨部门系统通信
- 向量数据库支持的相似文档检索
- 支持跨文档的混合检索策略

## 🛠 技术栈

- **后端框架**: Spring Boot 2.7.0
- **RAG技术**: LangChain4j
- **向量数据库**: Chroma
- **知识图谱**: Neo4j
- **文档处理**: Apache Jena
- **通信协议**: MCP (Message Communication Protocol)
- **API文档**: SpringDoc OpenAPI

## 💡 业务流程

### 文档处理流程
1. 收集各部门业务文档
2. 文档分块与向量化处理
3. 存储至向量数据库

### 知识图谱构建
1. 提取订单流程实体关系
2. 构建Neo4j知识图谱
3. 维护实体间关联关系

### 订单全流程
1. 下单部门：订单创建与基础信息录入
2. 支付部门：订单支付处理
3. 库存部门：商品备货管理
4. 履约部门：订单履约处理
5. 配送部门：物流配送跟踪

### 检索查询流程
1. 接收用户查询请求
2. RAG技术检索相关文档
3. 知识图谱补充结构化信息
4. MCP协议获取实时数据
5. 信息整合与智能回答

## 🚀 快速开始

### 环境要求
- JDK 11+
- Maven 3.6+
- Neo4j 4.x
- OpenAI API Key

### 配置说明
```properties
# OpenAI配置
openai.api.key=your-openai-api-key

# Neo4j配置
neo4j.uri=bolt://localhost:7687
neo4j.username=neo4j
neo4j.password=password
```

### 启动步骤
1. 克隆项目
```bash
git clone https://github.com/yourusername/smart-order-retrieval.git
```

2. 修改配置
```bash
cd smart-order-retrieval
vim src/main/resources/application.properties
```

3. 编译运行
```bash
mvn clean package
java -jar target/cross-document-retrieval-1.0-SNAPSHOT.jar
```

## 📚 API文档

启动后访问: http://localhost:8080/swagger-ui.html

### 示例接口
```bash
# 查询订单流程
GET /api/retrieval/order-flow?orderId=12345&question=订单从下单到配送的完整流程是什么？
```

## 🎯 项目亮点

1. **智能混合检索**
   - 结合RAG与知识图谱的混合检索策略
   - 支持跨文档的上下文理解
   - 实时数据与历史文档的融合

2. **知识图谱增强**
   - 构建完整的订单流程知识网络
   - 支持复杂关系推理
   - 提供结构化信息补充

3. **跨系统通信**
   - 自定义MCP协议
   - 支持跨部门实时数据获取
   - 保证数据一致性

4. **高性能设计**
   - 向量化检索提升效率
   - 多级缓存优化
   - 异步处理机制

## 📝 许可证

版权所有 © 2024 

**免责声明**：
- 本项目仅用于学习和研究目的
- 未经作者本人同意，禁止用于任何商业用途
- 使用本项目造成的任何损失，作者不承担责任

## 🤝 贡献指南

欢迎提交Issue和Pull Request，一起完善项目。

## 📞 联系方式

- 作者：[qq749812679]
- 邮箱：[749812679@qq.com]
- GitHub：https://github.com/qq749812679?tab=repositories

## ⭐ 致谢

感谢以下开源项目：
- LangChain4j
- Neo4j
- Spring Boot
- Apache Jena
