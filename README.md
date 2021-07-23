### 问题

1. 需要对外提供统一访问路径
2. 只有http header中有字段可以确定uri

### 思路

1. 由于SpringMVC中的controller分发逻辑即根据uri 分发的，因此只需要覆写uri方法从而获得真实业务uri即可
2. 那么每个controller的method需要根据tranCode映射处理，需要将tranCode和RequestMapping进行映射对照
3. 利用RequestMappingHandlerMapping可以获取到对应method（自然也可以拿到method上面的注解信息），以及mapping path（即RequestMapping的uri value）
4. 利用第三步的想法，创建一个TranCode注解标注TranCode
   value，在容器启动时（ContextRefreshedEvent），保存code与uri的映射，将此信息在覆写Request.getURI的时候过一遍，即可完成真实uri分发

### 验证

```shell
curl -X GET --location "http://localhost:8080/portal?input=arano"     -H "TranCode: tran-hello"
curl -X POST --location "http://localhost:8080/portal" -H "TranCode: tran-hello-post" -d "arano"
```