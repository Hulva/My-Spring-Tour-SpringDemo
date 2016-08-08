## Spring 表达式语言 Spring Expression Language, SpEL
> 一种强大、简洁的装配Bean的方式，在运行期间执行的表达式将值装配到Bean的属性或构造器参数中。
	* 使用Bean的ID来引用Bean
	* 调用方法和访问对象的属性
	* 对值进行算术、关系和逻辑运算
	* 正则表达式匹配
	* 集合操作

### 基本原理
> SpEL 表达式的首要目标是通过计算获得某个值。字面值、Bean的属性或是某个类的常量

	1. 字面值
	在<property>元素的value属性中使用 #{} 界定符把值装配到Bean的属性中。
```
  <property name="count" value="#{5}" />
  // 与非SpEL表达式的值混用
  <property name="count" value="The value is #{5}" />
  // 浮点型数字
  <property name="count" value="#{21.6}" />
  // 科学计数法
  <property name="count" value="#{1e4}" />
  // String类型的字面值
  <property name="count" value="#{'Spring'}" />
  <property name="count" value='#{"Spring"}' />
  // 布尔型的 true 和 false
  <property name="count" value="#{false}" />
```
	2. 引用 Bean、property 和方法
```
	// ID为“saxphone”的Bean装配到另一个Bean的属性中
	<property name="instrument" value="#{saxphone}" />
	// 等同于
	<property name="instrument" ref="saxphone" />
	// 使用Bean的引用来获取Bean的属性
	<bean id="kenny" class="top.hulva.example.InstrumentList">
		<property name="song" value="I been hope" />
	</bean>
	<bean id="carl" class="top.hulva.example.InstrumentList">
		<property name="song" value="#{kenny.song}" />
	</bean>
	// 调用其他Bean的方法
	<property name="song" value="#{songSelector.selectSong()}" />
	// 转换字符串的值为大写
	<property name="song" value="#{songSelector.selectSong().toUpperCase()}" />
	// SpEL中避免抛出空指针异常的方法,使用null-safe存取器
	<property name="song" value="#{songSelector.selectSong()?.toUpperCase()}" />
```
	?. 运算符代替 . 来访问 toUpperCase() 方法，在访问右边的方法之前， ?. 运算符会确保左边项的值不会为 null ，当左边项的值为null时，SpEL将不再尝试调用右边的方法。
	3. 操作类
	在SpEL中，使用 T() 运算符调用类作用域的方法和常量。
```
	<property name="multiplier" value="#{T(java.lang.Math).random()}" />
	<property name="multiplier" value="#{T(java.lang.Math).PI}" />
```

### 在SpEL值上执行操作
```
	<property name="adjustedAmount" value="#{counter.total + 42}" />
	<property name="adjustedAmount" value="#{counter.total - 42}" />
	<property name="circumference" value="#{T(java.lang.Math).PI * circle.radius}" />
	<property name="average" value="#{counter.total / counter.count}" />
	<property name="average" value="#{counter.total % counter.count}" />
	<property name="area" value="#{T(java.lang.Math).PI * circle.radius ^ 2}" />
	// 另外 + 还可作为字符串连接符
	 <property name="fullName" value="perfomer.firstName + ' ' + performer.lastName}" />
	 <property name="equal" value="#{counter.total == 100}" />
	 // le 代表小于等于 ge 代表大于等于
	 <property name="hasCapacity" value="#{counter.total le 1000" />
```
| 运算符  | 符号   | 文本类型 |
| :------| :-----| :-------|
| 等于    | ==    |	eq	   |
| 小于    | <     |	lt	   |
| 小于等于 | <=    |	le	   |
| 大于    | >     |	gt	   |
| 大于等于 | >=    |	ge	   |
```
	<property name="largeCicle" value="#{shape.kind == 'circle' and shape.perimeter gt 1000}" />
	<property name="outOfStock" value="#{!product.available}" />
	<property name="outOfStock" value="#{not product.available}" />
	// 条件运算符
	<property name="instrument" class="#{songSelector.selectSong()=='Jingle Bells'?piano:saxphone}" />
	// 正则表达式
	<property name="validEmail" value="#{admin.email matches '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com'}"
```

### 在SpEL中筛选集合
```
	package top.hulva.example.cities;
	public class City {
		private String name;
		private String state;
		private int population;
	}
```
```
	<util:list id="cities">
		<bean class="top.hulva.example.cities.City"
			p:name="Chicago" p:state="IL" p:population="2135" />
		<bean class="top.hulva.example.cities.City"
			p:name="Atlanta" p:state="IL" p:population="27135" />
		<bean class="top.hulva.example.cities.City"
			p:name="Dalas" p:state="GA" p:population="21835" />
		<bean class="top.hulva.example.cities.City"
			p:name="Houston" p:state="IL" p:population="213455" />
		<bean class="top.hulva.example.cities.City"
			p:name="Jal" p:state="TX" p:population="21354" />
		<bean class="top.hulva.example.cities.City"
			p:name="Las Cruces" p:state="NM" p:population="213586" />
	</util:list>
	
	<property name="choseCity" value="#{cities[2]}" />
	<property name="choseCity" value="#{cities[T(java.lang.Math).random() * cities.size()]}" />
```
```
	// 加载配置文件
	<util:properties id="settings" location="classpath:settings.properties" />
```
	settings Bean是一个 java.util.Properties类，加载了名为settings.properties的文件。
```
	<property name="accessToken" value="#{settings['twitter.accessToken']}" />
	// systemEnvironment 包含了应用程序所在机器上的所有环境变量
	<property name="homePath" value="#{systemEnvironment['HOME']}" />
	// systemProperties 包含了Java程序启动时所设置的所有属性（通常通用-D参数）例如： -D application.home=/etc/myapp 来启动JVM，则可通过以下方式将该值注入属性：
	<property name="homePath" value="#{systemProperties['application.home']}" />
	// [] 运算符同样可以通过索引来得到字符串的某个字符。下面的表达式将返回“s”
	"This is a test"[3]
	// 查询集合成员 .?[](符合条件的)  .^[]（第一个匹配项）  .$[]（最后一个匹配项）
	<property name="bigCities" value="#{cities.?[population gt 1000]}" />
	<property name="aBigCities" value="#{cities.^[population gt 1000]}" />
	<property name="aBigCities" value="#{cities.$[population gt 1000]}" />
	
```
	集合投影 是从集合的每一个成员中选择特定的属性放入一个新集合中。投影运算符 .![]
```
	<property name="cityNames" value="#{cities.![name]}" />
	<property name="cityNames" value="#{cities.![name + ', ' + state]}" />
	<property name="cityNames" value="#{cities.?[population gt 1000].![name + ', ' + state]}" />
```

## Spring容器
> Spring自带了多种容器的实现，可归为两类：
	* BeanFactory 最简单的容器，提供基础的依赖注入和Bean装配服务
	* ApplicationContext 拥有更高级的框架服务
	


