## Spring JDBC
* 创建一个XXDAO接口
* 创建一个XXJDBCTemplate类实现XXDAO接口
* 创建实体类和实体类Mapper
* 在配置文件中初始化dataSource以及定义xxJDBCTemplate bean

## 关于 NamedParameterJdbcTemplate 和 SimpleJdbcTemplate
// TODO

## SQL 存储过程
> SimpleJdbcCall 类可用来调用一个存储过程通过IN和OUT参数
```
	DELIMITER $$

	DROP PROCEDURE IF EXISTS `TEST`.`getRecord` $$
	CREATE PROCEDURE `TEST`.`getRecord` (
	IN in_id INTEGER,
	OUT out_name VARCHAR(20),
	OUT out_age  INTEGER)
	BEGIN
	   SELECT name, age
	   INTO out_name, out_age
	   FROM Student where id = in_id;
	END $$

	DELIMITER ;
```

## 配置数据源

### 使用JNDI数据源
> 通过JNDI获取数据源的好处：数据源完全可以在应用程序之外进行管理，应用程序只需在访问数据库的时候查找数据源就可以了。在应用服务器中管理的数据源通常以池的方式组织，从而具备更好的性能，并且支持系统管理员对其进行热切换。？
位于jee命名空间下的<jee:jndi-lookup>元素可以用于检索JNDI 中的任何对象（包括数据源）并将其应用于Spring Bean中。
```
	<jee:jndi-lookup id="dataSource"
			jndi-name="/jdbc/SpitterDS" resource-ref="true" />
```

### 使用数据源连接池
```
	<bean id="dataSource"
		class="org.apache.commons.dhcp.BasicDataSource">
		<property name="driverName" value="xxx" />
		<property name="url" value="" />
		<property name="username" value="" />
		<property name="password" value="" />
		<!-- 前4个为必配项 -->
		<property name="initialSize" value="5" /> // 池启动时创建的连接数
		<property name="maxActive" value="10" /> // 同一时间可以从池里分配的最多连接数（0表示无限制）
		<property name="maxIdle" value="" /> // 池里不会被释放的最多空闲连接数（0表示无限制）
		<property name="maxOpenPreparedStatements" value="" /> // 在同一时间能够从语句池中分配的预处理语句的最大数量（0表示无限制）
		<property name="maxWait" value="" /> // 在抛出异常前，池等待连接回收的最大时间（当没有可用连接时）（-1表示无限等待）
		<property name="minEvictableIdleTimeMillis" value="" /> // 连接在池中保持空闲不被回收的最大时间
		<property name="minIdle" value="" /> // 在不创建心连接的情况下，池中保持空闲的最小连接数
		<property name="poolPreparedStatements" value="" /> // 是否对预处理语句进行池管理（布尔值）
	</bean>
```

### 基于JDBC 驱动的数据源
> Spring中提供了两种数据对象（均位于 org.springframework.jdbc.datasource 包中）：
	* DriverManagerDataSource: 在每个连接请求时都会返回一个新建的连接。没有进行池化管理。
	* SingleConnectionDataSource: 在每个连接请求时都会返回同一个连接。可视为只有一个连接的池。
