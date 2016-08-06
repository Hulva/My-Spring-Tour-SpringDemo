## AOP Aspect Oriented Programming 面向切面编程
>  Aspect Oriented Programming entails breaking down program logic into distinct parts called so-called concerns. The functions that span multiple points of an application are called cross-cutting concerns and these cross-cutting concerns are conceptually separate from the application's business logic. There are various common good examples of aspects like logging, auditing, declarative transactions, security, and caching etc. <br />
*** 面向方面的编程需要打破程序逻辑到不同的部分称为所谓的关注。跨越一个应用程序的多个点的功能被称为横切关注点，这些横切关注点在概念上与应用程序的业务逻辑分离。有各种常见的好的例子，如日志记录，审计，声明性交易，安全性和缓存等方面。 --translated by BaiDu

### Key Words: 
	* Aspect
	* Join point
	* Advice
		- before
		- after
		- after-returning
		- after-throwing
		- around
	* Pointcut
	* Introduction
	* Weaving
 
## Spring AOP独有的依赖包：
> aspectjrt.jar, aspectjweaver.jar and aspectj.jar 