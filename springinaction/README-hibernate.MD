## 在Spring中集成Hibernate
  * 延迟加载 (Lazy Loading)对象关系变得复杂时，有时我们并不希望立即获取完整的对象间关系。使用延迟加载，我们可以只抓取需要的数据。
  * 预先抓取 (Eager fetching) 与延迟记载相对。一次查询获取完整的关联对象，省去了多次查询的成本。
  * 级联 (Cascading) 更改数据库中的某张表的同时修改其关联表。
  提供了以上服务的框架称之为 Object-Relational mapping ORM框架。
  Spring对ORM框架提供了以下支持：
  * Spring的声明式事务的集成支持
  * 透明的异常处理
  * 线程安全的、轻量级的模板类
  * DAO支持类
  * 资源管理

### Hibernate
> Hibernate 不仅提供了基本的对象关系映射，还提供了作为ORM工具所应具有的复杂功能，比如 _缓存_ _延迟加载_ _预先抓取_ 以及 _分布式缓存_

#### Hibernate的Session工厂
> _org.hibernate.Session_ Session接口提供了基本的数据访问功能，如保存、更新、删除以及从数据库加载对象的功能。
在配置Hibernate Session工厂Bean时，我们需要确定持久化域对象是通过XML文件还是通过注解来进行配置。
  * 在XML中定义对象和数据库之间的映射，那么需要在Spring中配置LocalSessionFactoryBean:

  ```
    <bean id="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
      <property name="dataSource" ref="dataSource" />
      <property name="mappingResource">
        <list>
          <value>Sample.hbn.xml</value>
        </list>
      </property>
      <property name="hibernateProperties">
        <props>
          <prop key="dialect"> _数据库方言_ </prop>
        </props>
      </property>
    </bean>
  ```

  * 注解方式定义持久化，需要使用AnnotationSessionFactoryBean

  ```
    <bean id="sessionFactory" class="org.springframework.orm.hibernate3.annotation.AnnoationSessionFactoryBean">
      <property name="dataSource" ref="dataSource" />
      <property name="packageToScann" value="被扫描的包" />
      <property name="hibernateProperties">
        <props>
          <prop key="dialect"> _数据库方言_ </prop>
        </props>
      </property>
    </bean>
  ```

  packageToScann属性告诉Spring扫描一个或多个包以查找域类，使用JPA的@Entity或@MappedSuperclass注解以及Hibernate的@Entity注解进行标注的类都会包含在内。

  ```
    // 扫描多个包
    <property name="packageToScann">
      <list>
        <value>要扫描的包</value>
        <value>要扫描的包</value>
      </list>
    </property>
  ```

#### 不依赖于Spring的Hibernate代码
>  直接将Hibernate Session 装配到DAO类中
```
  @Repository
  public class HibernateSampleDao implements ISampleDao {
    private SessionFactory sessionFactory;
    @Autowired
    public HibernateSampleDao(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
    }
    public Session currentSession() {
      return this.sessionFactory.getCurrentSession();
    }
    public insert(Sample sample){
      currentSession().save(sample);
    }
    public Sample selectByID(Integer id) {
      return (Sample)currentSession().get(Sample.class, id);
    }
    public void update(Sample sample) {
      currentSession().update(sample);
    }
   }
```

  _@Repository_ 的两个用处：
  1. 能够使对象被 <context:component-scan> 扫描到，这样酒不必声明 此DAO Bean了
  2. 给不使用模板的Hibernate DAO添加异常转换功能，我们只需在Spring应用上下文添加一个PersistenceExceptionTranslationPostProcessor Bean:

  ```
    <bean class="org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor" />
  ```
  
  PersistenceExceptionTranslationPostProcessor是一个Bean的后置处理程序，它会在所有拥有@Repository注解的类上添加一个通知器(advisor)，这样就会捕获任何平台相关的异常并以Spring的非检查型数据访问异常的形式重新抛出。

### Spring 与 Java 持久化API
