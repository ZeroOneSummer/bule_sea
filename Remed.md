### springboot实现多套UI共用一个server

###### 1.实现：web端指定server端入口启动：SpringApplication.run(ServerApp.class, args)
###### 2.web端引入thymeleaf即可，ip+port默认跳转到index.html，其他页面跳转controller里返回stirng视图路径即可
###### 3.静态资源放入static、templates中即可，无需任何配置，css/xx.css直接引用
###### 4.@SpringBootApplication放在server，properties关于环境的配置以web为主，其他的组合读取，key重复的以web端为主
###### 5.数据源datasource可以在server的yml中配置

