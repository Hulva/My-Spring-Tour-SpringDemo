## Spring 与 Java 持久化API
> _实体Bean(Entity Bean)_ _Bean管理持久化(Bean-managed persistence,BMP)_ _容器管理持久化(Container-managed persistence,CMP)_

### 配置实体管理器工厂
> 在Spring中使用LocalContainerEntityManagerFactoryBean来配置容器管理类型的JPA
```
  <bean id="emf" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <property name="jpaVendorAdapter" ref="jpaVendorAdapter" />
  </bean>
```
jpaVendorAdapter属性指明所使用的是哪一个厂商的JPA 实现。Spring提供的JPA厂商适配器：

  * EclipseLinkJpaVendorAdapter
  * HibernateJpaVendorAdapter
  * OpenJpaVendorAdapter
  * TopLinkJpaVendorAdapter

```
  <bean id="jpaVendorAdapter" class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
    <property name="database" value="HSQL" />
    <property name="showSql" value="true" />
    <property name="generateDdl" value="false" />
    <property name="databasePlateform" value="org.hibernate.dialect.HSQLDialet" />
  </bean>
```
database属性：
| 数据库平台            | 属性database的值  |
| :------------------- | :--------------- |
| IBM DB2              | DB2              |
| Apache               | DERBY            |
| H2                   | H2               |
| Hypersonic           | HSQL             |
| Informix             | INFORMIX         |
| MySQL                | MYSQL            |
| Oracle               | ORACLE           |
| PostgresQL           | POSTGRESQL       |
| Microsoft SQL Server | SQLSERVER        |
| Sybase               | SYBASE           |
对于简单的应用程序来说，LocalEntityManagerFactoryBean就够用了。鉴于LocalContainerEntityManagerFactoryBean能够让我们在Spring中配置更多的JPA，所以更适合应用于生产级的使用场景。

### 从JNDI中获取实体管理器工厂
```
  <jee:jndi-lookup id="emf" jndi-name="persistence/samplePU" />
```

## 编写基于JPA的DAO
  * Spring 对JPA集成了JpaTemplate模板以及对应支持类JpaDaoSupport（已弃用）
  * 类似于Hibernate上下文Session的纯粹JPA

不使用Spring模板的JPA DAO
```
  @Respository("sampleDao")
  @Transactional
  public class JpaSampleDao implements ISampleDao {
    @PersistenceContext
    private EntityManager em;
    public void insert(Sample sample) {
      em.persist(sample);
    }
    public Sample selectByID(Integer id) {
      return em.find(Sample.class, id);
    }
    public void update(Sample sample) {
      em.merge(sample);
    }
  }
```

JpaSampleDao使用EntityManager处理持久化。em属性上的 _@PersistenceContext_ 注解表明需要将一个EntityManager实例注入到em上，在Spring上下文中配置一个 _PersistenceAnnotationBeanPostProcessor_ :
```
  <bean class="org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor" />
```
  * _@Transactional_ 注解表明这个DAO中的持久化方法是在事务上下文中执行的。
  * _@Repository_ 的作用与Hibernate上下文Session版本的DAO中的是一样的。
  * Spring 简化数据访问的方式之一就是管理数据连接的生命和ORM框架的Session，以确保他们根据需要打开或关闭。
