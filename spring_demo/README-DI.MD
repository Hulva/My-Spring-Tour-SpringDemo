## To use constructor arguments for mandatory dependencies and setters for optional dependencies.
> 对强依赖关系使用构造器 弱依赖使用setters

## Constructor-based Dependency Injection

### 构造器传多参的情况
```
	package x.y;
	
	public class Foo {
	   public Foo(Bar bar, Baz baz) {
	      // ...
	   }
	}
	
	<beans>
	   <bean id="foo" class="x.y.Foo">
	      <constructor-arg ref="bar"/>
	      <constructor-arg ref="baz"/>
	   </bean>
	
	   <bean id="bar" class="x.y.Bar"/>
	   <bean id="baz" class="x.y.Baz"/>
	</beans>
```
构造器中参数的顺序就是xml文件中 constructor-arg 的顺序

### 根据类型匹配
```
	package x.y;
	
	public class Foo {
	   public Foo(int year, String name) {
	      // ...
	   }
	}
	<beans>
	   <bean id="exampleBean" class="examples.ExampleBean">
	      <constructor-arg type="int" value="2001"/>
	      <constructor-arg type="java.lang.String" value="Zara"/>
	   </bean>
	</beans>
```

### 通过构造器注入的最好的方式
> 使用 index 属性明确的指定构造器参数的位置
```
	<beans>

	   <bean id="exampleBean" class="examples.ExampleBean">
	      <constructor-arg index="0" value="2001"/>
	      <constructor-arg index="1" value="Zara"/>
	   </bean>
	
	</beans>
```
> 如果是去引用一个对象，使用 ref 属性；原始类型直接使用 value 属性

## Setter-based Dependency Injection
> 通过容器去调用 setter 方法来完成注入（在通过无参构造或是无参工厂方法实例化 bean 后） <br />
  通过构造器注入和 setter 方法注入的注意点：
  * 通过构造器注入使用的是 <constructor-arg> 标签，setter 方法使用的 <property> 标签
  * 注入引用对象使用 <property> 的ref属性，原始类型注入使用 value 属性
  
### inner bean
> 当所注入的bean只被当前一个bean所依赖时，可将要注入的bean作为inner bean

## 复数类型的注入 （<list> <set> <map> <props>）
> 使用 <list> 或是 <set> 可以包裹任何 java.util.Collection 的实现或者 array

### null 和空字符串的注入
```
	<bean id="..." class="exampleBean">
	   <property name="email" value=""/>
	</bean>
```
上面的代码相当于 exampleBean.setEmail("")
```	
	<bean id="..." class="exampleBean">
	   <property name="email"><null/></property>
	</bean>
```
上面的代码相当于 exampleBean.setEmail(null)

