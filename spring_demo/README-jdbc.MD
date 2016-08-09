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
```
	<bean id="dataSource"
		class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverName" value="xxx" />
		<property name="url" value="" />
		<property name="username" value="" />
		<property name="password" value="" />
	</bean>
```
因为没有提供连接池的功能，所以没有可配置的池相关属性。SingleConnectionDataSource有且只有一个数据库连接，所以不适应用于多线程的应用程序。尽管DriverManagerDataSource支持多线程，但是每次请求连接时都会创建新连接，这是以洗呢、功能为代价的。建议使用数据源连接池。

### JDBC模板
	* JdbcTemplate 最基本的Spring JDBC 模板，支持最简单的JDBC数据库访问功能以及简单的索引参数查询。
	* NamedParameterJdbcTemplate 使用该模板执行查询时，可以将查询值以命名参数的形式绑定到SQL中，而不是使用简单的索引参数查询。
	* SimpleJdbcTemplate 该模板类利用Java5的一些特性，如自动装箱、泛型以及可变参数列表来简化JDBC模板的使用。

#### SimpleJdbcTemplate
> 只需设置DataSource就能够让SimpleJdbcTemplate正常工作
```
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.simple.SimpleJdbcTemplate">
		<constructor-arg ref="dataSource" />
	</bean>
```
属性dataSource引用的可以是任何javax.sql.DataSource的任意实现。
可以将jdbcTemplate装配到DAO中并使用它来访问数据库。
```
	public class SampleDAO implements ISampleDAO {
		...
		private SimpleJdbcTemplate jdbcTemplate;
		public void setSimpleJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}
	}
	...
```
装配SampleDAO的simpleJdbcTemplate属性：
```
	<bean id="sampleDAO" class="top.hulva.sample.SampleDAO">
		<property name="jdbcTemplate" ref="jdbcTemplate"
```

#### 使用命名参数
```
	public void insertSomething(Something something) {
		String SQL_INSERT_SAMPLE = "INSERT INTO SAMPLE (username, password, somethingelse) VALUES (:username, :password, :somethingelse)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", something.getUsername());
		params.put("password", something.getPassword());
		params.put("somethingelse", something.getSomethingelse());

		jdbcTemplate.update(SQL_INSERT_SAMPLE, params);
		something.setId(queryForIdentity());
	}
```
对于应用程序的每一个JDBC DAO类，我们都需要添加一个SimpleJdbcTemplate属性及对应的setter方法，并装配每个DAO的SimpleJdbcTemplate属性，创建一个含有SimpleJdbcTemplate属性的通用父类，通过这个父类来设置JDBC模板。Spring提供了这样的内置基类：_JdbcDaoSupport_ _SimpleJdbcDaoSupport_ _NamedParameterJdbcDaoSupport_ ，对应于不同的模板。
```
	public class SampleDAO extends SimpleJdbcDaoSupport implements ISampleDAO {
		...
		public void insertSomething(Something something){
			getSimpleJdbcTemple().update(SQL_INSERT_SAMPLE,something.getUsername(), something.getPassword(),
			something.getSomethingelse());
			something.setId(queryForIdentity());
		}
	}
```
这种继承Spring提供的基类有个方便的地方，就是不需要在声明SimpleJdbcTemplate Bean了。因为SimpleJdbcDaoSupport类中有dataSource属性，当SampleDao配置了dataSource属性后，它会在内部自动创建一个SimpleJdbcTemplate实例。
```
	<bean id="simpleDao" class="top.hulva.sample.SampleDao">
		<property name="dataSource" ref="dataSource" />
	</bean>
```
