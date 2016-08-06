## ApplicationContext 掌管着 bean 的整个生命周期
* ContextRefreshedEvent
* ContextTartedEvent
* ContextStopedEvent
* ContextClosedEvent
* RequestHandledEvent
* ApplicationEvent
* ApplicationListener
	- onApplicationEvent()
> Spring的时间处理是单线程的，所以除非所有的接收者得到了信息，否则进程将被blocked，流程也将无法往下走。

## 自定义事件
1. 事件类 继承 ApplicationEvent
2. 事件发布类 实现 ApplicationEventPublisherAware
3. 事件处理类 实现 ApplicationListener 的 onApplicationEvent 方法 
