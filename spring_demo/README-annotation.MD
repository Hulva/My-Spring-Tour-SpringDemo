## 注解配置 @Required @Autowired @Qualifier JSR-250 Annotations
> Spring 容器默认没有将 注解装配 打开，在使用之前需要加入以下内容到配置文件
```
	<context:annotation-config/>
```

### @Required 应用于setter方法

### @Autowired
> 通过在setter方法上使用@Autowired就不用再在xml文件中使用<property>元素了 【byType】 <br />
可直接将@Autowired 置于属性之上，还可省去setter方法 <br /> 
将@Autowired 置于构造器上可实现auto wiring in by constructor <br />
@Autowired with (required=false) option

### @Qualifier
> 当有几个相同类型的bean的时候，将其中的一个装配给一个属性时，可同时使用@Qualifier 和@Autowired 准确的指定被装配的bean

### JSR-250
@Resource(name= "xxx")  根据xxx去xml中找对应的bean
