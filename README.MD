## spring-boot-rediscache-starter

#### 一、项目说明
> 该项目演进于公司rediscache组件,是一个简易的，易上手的，高效使用的缓存组件。该组件使用简单，稳定性高，如果你不想使用SpringCache组件，那他也许就是你的最佳选择,他没有像springCache一样复杂的配置或者使用技巧，小而简单只为做最好的缓存;

- 该组件以springboot-starter的方式提供使用，组件的停启用只决定与是否引入该jar包
- 该组件演进升级之后只适用于springBoot项目，非SpringBoot项目使用请提Issure留下email，我提供非SpringBoot版本
- 该组件包未上传至maven仓库，请使用者拉取源代码手动install后使用，或者上传只私服使用
- 项目开源欢迎广大开发者、企业使用，欢迎大家提bug和贡献代码
- 该项目对于个人学习研究、提升个人技术水平很有帮助

#### 二、使用说明
> 项目分为两部分：`spring-boot-rediscache-starter`为项目源代码，`demo`为项目使用示例

- 1、拉取项目后，对 `spring-boot-rediscache-starter`项目执行 `mvn install`操作
- 2、在项目pom.xml中引入：
``` java
<dependency>
    <groupId>com.hui</groupId>
    <artifactId>spring-boot-rediscache-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
- 3、在配置application.properties或者application.yml文件中,添加redis连接配置
```properties
redisCache.redis.host=127.0.0.1
redisCache.redis.port=6379
```

```yaml
redisCache
  redis
    host : 127.0.0.1
    port : 6379
```
> 到这里整个reidsCache配置完成，下面开始使用他吧
      
- 4、在业务代码中如何使用
```java
    @RedisCacheGet(key = "'getHello'")
    public String getHello(){
        return "hello world";
    }

    @RedisCacheClean(key = "'getHello'")
    public void updateHello(){
        log.info("com.example.demo.DemoService.updateHello");
    }
```
> [注意]：示例代码中的key = "'key''"而不是"key",因为这里实现使用了Spel

> 相比SpringCache RedisCache 的key值是自定义的，同时reids存储支持两种序列化方式，一种是Jdk自带的Serializable（需要对缓存的对象实现Serializable）,另一种是Json格式，存储结果可读性更高  

#### 三、你需要知道的奇技淫巧
> SPEL 表达式如何使用？

> 关于缓存注解你知道的一些潜规则？
 