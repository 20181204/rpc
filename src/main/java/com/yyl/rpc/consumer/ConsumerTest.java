/*
package com.yyl.rpc.consumer;

import com.yyl.rpc.provider.ProviderTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

*/
/**
 * ${DESCRIPTION}
 *
 * @author yyl
 * @date 2018年11月28日 18:14
 *//*

public class ConsumerTest {
    public static void main(String[] args) throws NoSuchMethodException, IOException, ClassNotFoundException {
        String providerName=ProviderTest.class.getName();
        //需要远程执行的方法，其实就是消费者调用生产者的方法
        Method method = ProviderTest.class.getMethod("print", java.lang.String.class);
        //需要传递的参数
        Object[] rpcArgs = {"Hello RPC!"};
        Socket consumer = new Socket("127.0.0.1", 8899);
        //将方法名称和参数 传递给服务生产者
        ObjectOutputStream output = new ObjectOutputStream(consumer.getOutputStream());
        output.writeUTF(providerName);
        output.writeUTF(method.getName());
        output.writeObject(method.getParameterTypes());
        output.writeObject(rpcArgs);
        ObjectInputStream input = new ObjectInputStream(consumer.getInputStream());
        Object result = input.readObject();
        System.out.println(result.toString());
    }
}
*/
