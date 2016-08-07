## Spring MVC

### 配置 Dispatcher
> 修改 web.xml 文件，添加以下内容：
```
	<servlet>
	 <servlet-name>Dispatcher</servlet-name>
	 <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	 <load-on-startup>1</load-on-startup>
	</servlet>
	  
	<servlet-mapping>
	 <servlet-name>Dispatcher</servlet-name>
	 <url-pattern>/</url-pattern>
	</servlet-mapping>
```
并创建 SpringMVC 的Dispatcher的默认配置文件 Dispatcher-servlet.xml 【与web.xml同目录，名称为上面所取的<servlet-name>-serlet.xml】 <br />
如想要自定义此配置文件的存放位置与名称，则需要在web.xml中增加一下内容：
```
	<context-param>
	   <param-name>contextConfigLocation</param-name>
	   <param-value>/xx/xxx.xml</param-value>
	</context-param>
	
	<listener>
	   <listener-class>
	      org.springframework.web.context.ContextLoaderListener
	   </listener-class>
	</listener>
```
这个ContextLoaderListener应该就是用来根据配置的<context-param>去寻找配置文件并进行加载的

