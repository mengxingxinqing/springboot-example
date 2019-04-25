## spring boot 配合apollo
1. 主要加上 @EnableApolloConfig
2. 配置propertites
3. 自定义配置可以使用如下
```aidl
@Configuration
@Value
```
```aidl
app.id=test001
apollo.meta=http://localhost:8080
spring.profiles.active=dev
```

主要问题是，静态配置propertites，可以直接配置到apollo中，在启动时自动加载，但是数据库之类的配置，想在运行时重新设置，比较麻烦，目前这个demo做的是其他的自定义配置，动态改变。