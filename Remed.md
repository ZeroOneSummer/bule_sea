# *springboot实现多套UI共用一个server*

### 1.实现：web 端指定 server 端入口启动：SpringApplication.run(ServerApp.class, args)

### 2.web 端引入 thymeleaf 即可，ip+port 默认跳转到 index.html，其他页面跳转 controller 里返回 stirng 视图路径即可
### 3.静态资源放入static、templates 中即可，无需任何配置，css/xx.css直接引用
### 4.@SpringBootApplication 放在 server，properties 配置以 web 为准，数据源 datasource 可以在server 的 yml 中配置
### 5.启动类上的 @MapperScan 不可少，接口上的 @mapper 和 mapper-locations 二选一
### 6.如果使用 tkmybatis，@MapperScan 使用它的，entity 基础类型用包装类，否则加 @Column