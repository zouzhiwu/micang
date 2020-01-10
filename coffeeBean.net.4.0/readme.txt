CoffeeBean是一款集成框架，主要解决客户端与服务端网络通信交互。
CoffeeBean版本分为CoffeeBean.Java,CoffeeBean.Csharp,CoffeeBean.Js,CoffeeBean.C版本
弥补了Netty跨语言之间的障碍，而且添加了些消息管理消息分发等功能，使得用户可以把精力都用来解决具体业务上来。

CoffeeBean1.x至CoffeeBean2.x使用的是Netty插件ByteBuff作为buffer序列化与反序列化，这样也对于使用者而言，显得使用起来不是太方便。
所以CoffeeBean专门编写了一套机制PackageBuff，只需要传输的对象只需要实现ifsPkg就可以传输，而且实现了多语言。
后来ProtoBuff兴起，而且经过我测试发现ProtoBuff效率也非常高，以下是我测试结果：

并发次数	PackageBuff序列化和反序列化耗时		ProtoBuff序列化和反序列化耗时
1000		1.53								1.72
2000		3.31								3.97
5000		6.56								7.01
10000		13									15
50000		60									54
100000		110									90

以上是我个人测试结果，介于电脑配置不同，结果可能会与其他测试有偏差，但是基本可以得出一个结论，在小规模并发的时候
PackageBuff性能高于ProtoBuff，而大规模并发时ProtoBuff性能高与PackageBuff

所以从CoffeeBean4.0版本后，开始放弃使用ByteBuff和PackageBuff，使用ProtoBuff

ProtoBuff传输结果：
消息前两个字节short类型，用来描述整个消息的长度。
接着是消息头，消息头内容Header：Msgcode，ErrorCode, ErrorInfo, BodyLength
消息体Body：Byte数组，即Proto序列化后的二进制数组，具体内容应接口而定。

CoffeeBean在启动时需要让应用层实现抽象类AbsLisener,AbsLisener要求实现接口getActionPath，用来获取接口的包路径，这样CoffeeBean在启动的时候
就会扫面该路径下的所有带@Action标签的方法，把这些方法视为与客户端的接口，免去了手动注册的麻烦，而且还可以做消息检查，避免相同MsgCode相同的消息
应用层需要继承AbsLisener实现Lisener,CoffeeBean会把该监听器用于监听启动端口的消息