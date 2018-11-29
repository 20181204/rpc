# rpc
#使用http实现rpc远程调用

步骤
1.启动项目
2.post请求模拟
localhost:8080/rpc/invoke
{
	
	"clientID":"1222",
	"interfaceType":"com.yyl.rpc.service.DemoService",
	"serviceName":"com.yyl.rpc.service.impl.DemoServiceImpl",
	"methodName":"add",
	"args":["1","22222"],
	"parameterTypes":["java.lang.String","java.lang.String"]
}
