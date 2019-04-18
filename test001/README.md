## 示例说明
### 目标
1. 整合mysql
2. 整合非注解redis
3. 整合注解缓存
### 环境
硬件：mac
软件：mysql，redis-server，idea
框架：spring boot 2.0
### 步骤
1. 新建spring boot项目
2. pom.xml中添加依赖，具体查看pom.xml文件
### 用到的命令
```
//mybatis 自动生成 根路径控制台执行
mvn mybatis-generator:generate -e
```
### 注意点
1. mybatis自动生成，会执行覆盖