## Transaction
> 全有或全无的操作？事务允许你将几个操作组合成一个要么全部发生要么全部不发生的工作单元。事务确保了数据或资源免于处在不一致的状态中。
 人们用一个术语（ _ACID_ ）来描述事务：
  * Atomicty (原子性)不可部分完成性。一个事务就是一个运行单元，其结果只有运行成功（全部发生）或是失败（全部不发生）。
  * Consistency 一致性。
  * Isolation 隔离性。允许多个用户对相同的数据进行操作，每个用户的操作不会与其他用户纠缠在一起。
  * Durability 持久性。一旦事务完成，事务的结果应该持久化。，这样就能从任何的系统崩溃中恢复过来。

### Spring对事务的支持
> Spring通过回调机制将实际的事务实现从事务性的代码中抽象出来。如果你的应用程序只是用一种持久化资源，Spring可以使用持久化机制本身所提供的事务性支持，这包括了JDBC、Hibernate以及Java持久化API（Java Persistence API，JTA）。但是如果应用程序的事务将跨多个资源，那么Spring会使用第三方的JTA实现来支持分布式（XA）事务。

## 局部事务 VS 全局事务


## 事务管理器
> Spring不直接管理事务，它提供了多种事务管理器，它们将事务管理的职责委托给JTA或其他持久化机制所提供的平台相关的事务实现。

![](../img/事务管理器.png)
![](../img/事务实现.png)
为了使用事务管理器，你需要在应用程序上下文中进行申明

### JDBC 事务
> 如果你咋应用程序中直接使用JDBC来进行持久化，_DataSourceTransactionManager_ 会为你处理事务边界。
```
  <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource" />
  </bean>
```
DataSourceTransactionManager 通过调用java.sql.Connection 来管理事务，而后者通过DataSource 得到。调用连接的commit() 方法来管理事务，rollback() 方法进行回滚。

### Hibernate 事务
> 如果应用程序使用Hibernate进行的持久化，那么你需要使用 _HibernateTransanctionManager_ :
```
  <bean id="transactionManager" class="org.springframework.orm.Hibernate3.HibernateTransanctionManager">
    <property name="sessionFactory" ref="sessionFactory" />
  </bean>
```
_HibernateTransanctionManager_ 将事务管理的职责委托给 _org.hibernate.Transaction_ 对象，后者从 _Hibernate Session_ 中获取到的。当事务成功完成时， _HibernateTransactionManager_ 将会调用 _Transaction_ 对象的 _commit()_ 方法；失败则会调用 _rollback()_ 方法。

### Java 持久化 API 事务
> 使用 JPA 的话，那你需要使用 Spring 的 _JpaTransactionManager_ 来处理事务。
```
  <bean id="transactionManager" class="org.springframework.orm.jpa.JapTransactionManager">
    <property name="entityManagerFactory" ref="entityManagerFactory" />
  </bean>
```
_JpaTransactionManager_ 只需要装配一个JPA实体管理工厂（javax.persistance.EntityManagerFactory 接口的任意实现）。_JpaTransactionManager_ 还支持将事务应用于简单的JDBC操作之中，这些JDBC操作所使用的DataSource与EntityManagerFactory使用的DataSource必须是一样的。为此，JpaTransactionManager必须装配一个JpaDialect的实现
```
  <bean id="jpaDialet" class="org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect" />
```

## 编程式事务 与 申明式事物
> 选择编程式事务还是声明式事务很大程度上是在 _细粒控制_ 和 _易用性_ 之间进行权衡。

### 编程式事务
> 在源代码中编码实现对事务的管理。精确控制事务的边界，它们的开始和结束完全取决于你的需求。

1. 使用使用  PlatformTransactionManager 直接实现事务
2. 开始一个新事务，需要一个有合适属性的 TransactionDefinition 实例
3. 通过 TransactionDefinition 实例的 getTransaction() 方法获得
4. 使用 commit() 方法提交事务
5. 使用 rollback() 方法回滚事务

> 添加事务的一种方式是直接通过编码使用Spring的 _TransactionTemplate_ 来添加事务性边界。 _TransactionTemplate_ 使用了一中回调机制。
```
  public void insert(fianl Sample sample) {
    txTemplate.execute(new TransactionCallback<void>() {
      public void doInTransaction(TransactionStatus txStatus) {
        try {
          sampleDao.save(sample);
        } catch (RuntimeException e) {
          throw e;
        }
        return null;
      }
    });
  }
```
通过将 _TransactionCallback_ 实现为内部类，将事务性的代码放到 _doInTransaction()_ 方法中。在事务性代码执行出现问题时，调用TransactionStatus对象的 _setRollbackOnly()_ 方法将回滚事务，否则，doInTransaction()成功返回，事务将提交。TransactionTemplate实例通过注入而来：
```
  <bean id="sampleService" class="top.hulva.sample.service.SampleServiceImpl">
    ...
    <property name="transactionTemplate">
      <bean class="org.springframework.transaction.support.TransactionTemplate">
        <property name="transactionManager" ref="transactionManager" />
      </bean>
    </property>
  </bean>
```
在背后，TransactionTemplate使用了 _PlatformTransactionManager_ 来实现处理特定平台的事务管理细节。transactionManager 的Bean引用来自上面第一个图中的任意一个事务管理器。编程式事务可以让你完全控制事务的边界，但它是侵入式的。

### 申明式事务（_tx命名空间_ 和 _注解_）
> 申明式事务允许你将事务管理从业务代码中独立出来，使用注解和xml配置文件来管理事务

使用 <tx:advice /> 标签创建一个事务处理的 advice

Spring 对声明式事务的支持是通过使用Spring AOP框架来实现的。事务是在应用程序主要功能之上的系统级服务。可将Spring事务想象成将方法“包装”上事务边界的切面。

 - 事务属性（描述了事务策略如何应用到方法上）
 * 传播行为（propagation behavior） 定义了客户端与被调用方法之间的事务边界
 ![](../img/propagation-behavior.PNG)
 * 隔离级别（isolation level） 定义了一个事务可能受其他并发事务影响的程度。另一种考虑事务的方式就是将其想象成事务对于事务性数据的自私程度？？
 > 并发可能导致的问题：
  * 脏读（Dirty reads） 一个事务读取了另一个事务改写但尚未提交的数据。
  * 不可重复读（Nonrepeatable read） 一个事务执行相同的查询两次或两次以上，但每次都得到不同的数据。通常这是由于另一个并发事务在多次查询期间更新了数据。
  * 幻读（Phantom read） 与不可重复读类似。一个事务由于另一并发事务的插入操作而发现原本不存在的记录的情况。
  理想情况下，事务之间是完全隔离的，从而防止这些问题的发生。但完全隔离会导致性能问题，它通常会设计锁定数据库中的记录（有时甚至是整张表）。侵占性的锁定会阻碍并发性。
  ![](../img/isolation-level.png)
  _ISOLATION_READ_UNCOMMITTED_ 是最高效的事务隔离级别，但是事务隔离的程度最低，事务可能会导致脏读、不可重复读以及幻读。另一个极端就是 _ISOLATION-SERIALIZABLE_ 能阻止所有的隔离问题但最低效。
  * 只读（readonly） 事务只对后端的数据库进行读操作，数据库可利用事务的只读特性进行一些特定的优化。
  * 事务超时（timeout） 长时间的事务会不必要的占用数据库资源。对那些具备可能启动一个新事务的传播行为（PROPAGATION_REQUIRED、PROPAGATION_REQUIRES_NEW以及PROPAGATION_NESTED）的方法来说，声明事务超时才有意义。
  * 回滚规则（rollback rule） 规则定义了哪些异常会导致事务回滚而哪些不会。默认情况下只有在遇到运行时异常时才会回滚。但是可以设定在遇到特定的检查型异常时像遇到运行时异常那样回滚。

### 在XML中定义事务
> 使用 _tx_ 命名空间配置生命式事务。
```
  <tx:advice id="txAdvice">
    <tx:attributes>
      <tx:method name="save*" propagation="REQUIRED" />
      <tx:method name="*" propagation="SUPPORTS" read-only="true" />
    </tx:attributes>
  </tx:advice>
```
根据约定优于配置，<tx:advice>假定事务管理器被声明为一个id为transactionManager的Bean，如果事务管理器被配置成为了一个不同的id（如txManager），则需要在transaction-manager属性中指明：
```
  <tx:advice id="txAdvice" transaction-manager="txManager">
    <tx:attributes>
      <tx:method name="save*" propagation="REQUIRED" />
      <tx:method name="*" propagation="SUPPORTS" read-only="true" />
    </tx:attributes>
  </tx:advice>
```
<tx:method>有多个属性来帮助定义方法的策略：
![](../img/transaction-5.png)
<tx:advice>只是定义了AOP通知，用于把事务边界通知给方法，而不是完整的事务性切面。定义一个通知器（advisor）:
```
  <aop:config>
    <aop:advisor pointcut="execution(* *..SampleService.*(..))" advice-ref="txAdvice" />
  </aop:config>
```

### 注解驱动的事务
使用注解你只需在XML中添加这么一行就够了
```
  <tx:annotation-driven />
```
在多一点就是你没有使用事务管理器的默认命名transactionManager
```
  <tx:annotation-driven transaction-manager="txManager" />
```
<tx:annotation-driven /> 元素告诉Spring检查上下文中所有的Bean并查找使用@Transactional注解的Bean，并自动给它们添加事务通知，事务通知的属性通过@Transactional注解的参数来定义。
```
  @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
  public class SampleServiceImpl implements ISampleService {
    ...
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void insert() {
      ...
    }
    ...
  }
```
在类级别上使用了@Transactional注解表示所有的方法都支持事务并且只读；在方法级别上通过注解来标识这个方法所需要的事务上下文。
