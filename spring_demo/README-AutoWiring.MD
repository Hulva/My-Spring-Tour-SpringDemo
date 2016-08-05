## 自动装配 Auto-Wiring
> 可通过对<bean> 元素的 autowire 属性进行设置 （no[default] byName byType constructor autodetect)

### AutoWiring - byName
```
   <bean id="textEditor" class="com.tutorialspoint.TextEditor" 
      autowire="byName">
      <property name="name" value="Generic Text Editor" />
   </bean>

   <!-- Definition for spellChecker bean -->
   <bean id="spellChecker" class="com.tutorialspoint.SpellChecker">
   </bean>
```
说是 byName,但定义spellChecker是定义在id属性里的，还是说此处所说的name是textEditor中定义的需要注入的成员名？
=> 经测试，id和name其中之一符合成员名就行

#### 关于 <bean> 元素中的 id 和 name 属性：
// TODO

### AutoWiring - byType
> 直接根据成员的类型去找xml中是否有匹配的，无关乎 id 和 name

### AutoWiring - constructor
> 就是结合构造器注入与AutoWiring - byType <br />
可以使用 byType 或者是 constructor 去装配 arrays 和其他 typed-collections

### 自动装配的不足
| Limitations					|								Description
|-------------------------------|-----------------------------------------------------------------------------------
| Overriding possibility	    | You can still specify dependencies using <constructor-arg> and <property> settings which will always override autowiring.
| Primitive data types	        | You cannot autowire so-called simple properties such as primitives, Strings, and Classes.
| Confusing nature	            | Autowiring is less exact than explicit wiring, so if possible prefer using explict wiring.
> 自动装配虽好，但最好还是明确的指明
