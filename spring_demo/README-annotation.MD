## 注解配置 @Required @Autowired @Qualifier JSR-250 Annotations
> Spring 容器默认没有将 注解装配 打开，在使用之前需要加入以下内容到配置文件
```
	<context:annotation-config/>
```

### @Required 应用于setter方法
