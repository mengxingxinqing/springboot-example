package com.tong.httputil;

import com.sun.xml.internal.ws.client.sei.MethodHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class ReflectInterface {
    private Map<String,Object> objectMap;
    public ReflectInterface(){
        objectMap = new HashMap<>();
    }


    public static <T>  T getInstance(Class<T> cls){
//        if(objectMap.containsKey(cls.toString())){
//            return (T)objectMap.get(cls.toString());
//        }
        MethodProxy invocationHandler = new MethodProxy();
        Object newProxyInstance = Proxy.newProxyInstance(
                cls.getClassLoader(),
                new Class[] { cls },
                invocationHandler);
//        objectMap.put(cls.toString(),newProxyInstance);
        return (T)newProxyInstance;
    }


    static class MethodProxy implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)  throws Throwable {
            DataSource dataSource = method.getDeclaringClass().getAnnotation(DataSource.class);
            String dataSourceName = dataSource.name();
            User u = new User();
            if(method.isAnnotationPresent(GetLine.class)){
                String url = method.getAnnotation(GetLine.class).value();
                u.setName("get a user from "+dataSourceName+"/"+url);
            }else if(method.isAnnotationPresent(PostLine.class)){
                u.setName("get a post");
            }

            return u;
        }

    }


    public static void getMethodParam(Method method){
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        int count = parameterAnnotations.length;
        for (int i = 0; i < count; i++) {
//            if(parameterAnnotations[i][0].)
            int paramAnnotationCount = parameterAnnotations[i].length;
            for(int j=0;j<paramAnnotationCount;j++){
                System.out.println("p->>>>>>>>>>>"+parameterAnnotations[i][j]);
            }

        }
    }

}
