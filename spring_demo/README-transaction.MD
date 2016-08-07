## 数据库事务(特性：ACID)
> * Atomicty 不可部分完成性。一个事务就是一个运行单元，其结果只有运行成功或是失败。
  * Consistency 一致性。
  * Isolation 隔离性。
  * Durability 耐久性。
> 所有的操作都成功才会执行提交否则将进行事务回滚。

## 局部事务 VS 全局事务

## 编程式事务 与 申明式事物

### 编程式事务
> 在源代码中编码实现对事务的管理。灵活但不便于维护。

1. 使用使用  PlatformTransactionManager 直接实现事务
2. 开始一个新事务，需要一个有合适属性的 TransactionDefinition 实例
3. 通过 TransactionDefinition 实例的 getTransaction() 方法获得
4. 使用 commit() 方法提交事务
5. 使用 rollback() 方法回滚事务

### 申明式事务
> 申明式事务允许你将事务管理从业务代码中独立出来，使用注解和xml配置文件来管理事务

- 使用 <tx:advice /> 标签创建一个事务处理的 advice

## 至此，没有导入 aspectjrt.jar 和 aopalliance.jar ,没有异常？That is weird!!!
