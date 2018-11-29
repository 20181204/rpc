package com.yyl.rpc.controller;

import com.yyl.rpc.common.SpringBeanFactory;
import com.yyl.rpc.domain.RpcRequestData;
import com.yyl.rpc.domain.RpcResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author yyl
 * @date 2018年11月29日 13:22
 */
@Controller
@RequestMapping("/rpc")
public class RpcController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Map<String, Class<?>> primitiveClassMap = new HashMap<String, Class<?>>();
    {
        primitiveClassMap.put(Integer.TYPE.getName(), Integer.TYPE);
        primitiveClassMap.put(Short.TYPE.getName(), Short.TYPE);
        primitiveClassMap.put(Long.TYPE.getName(), Long.TYPE);
        primitiveClassMap.put(Float.TYPE.getName(), Float.TYPE);
        primitiveClassMap.put(Boolean.TYPE.getName(), Boolean.TYPE);
        primitiveClassMap.put(Byte.TYPE.getName(), Byte.TYPE);
        primitiveClassMap.put(Double.TYPE.getName(), Double.TYPE);
        primitiveClassMap.put(Character.TYPE.getName(), Character.TYPE);
        primitiveClassMap.put(Character.TYPE.getName(), Character.TYPE);
    }

    private Class<?> getClass(String className) throws ClassNotFoundException{
        Class<?> clazz = primitiveClassMap.get(className);
        return clazz == null ? Class.forName(className) : clazz;
    }
    @RequestMapping("/invoke")
    @ResponseBody
    public RpcResponseData<Object> invoke(@RequestBody RpcRequestData request, HttpServletRequest httpServletRequest){
        if(request==null){
            logger.debug("请求参数不能为空");
        }
        if(request.getInterfaceType().isEmpty()|| request.getMethodName().isEmpty()){
            throw new IllegalArgumentException("参数有误");
        }
        RpcResponseData<Object> responseData=new RpcResponseData<>();
        try {
            Class<?> clazz = this.getClass(request.getInterfaceType());
            Object serviceImpl=SpringBeanFactory.getBean(clazz);
            if(serviceImpl==null){
                throw new ClassNotFoundException("没发现服务类");
            }
            List<Class<?>> list=new ArrayList<>();
            for (String paramTypeName:request.getParameterTypes()) {
                list.add(this.getClass(paramTypeName));
            }
            final Method method =clazz.getMethod(request.getMethodName(),list.toArray(new Class[0]));
            Object object=method.invoke(serviceImpl,request.getArgs());
            responseData.setSuccess(true);
            responseData.setResult(object);

        }catch (Exception e){
            responseData.setSuccess(false);
            responseData.setErrorMessage(e.getMessage());
        }
        return responseData;
    }

}
