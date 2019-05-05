package com.tong.httputil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class ReflectInterface {
    public ReflectInterface(){
    }


    public static <T> T getInstance(Class<T> cls){
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
            String url = NameService.getUrlByName(dataSource.name());
            if(method.isAnnotationPresent(GetLine.class)){
                String path = method.getAnnotation(GetLine.class).value();
                ParamStruct paramStruct = getMethodParam(method,args);
                String respStr = HttpUtil.addSignPost(HttpUtil.combineUrl(url,path),paramStruct.getParams(),paramStruct.getHeader(),"");
                return Decode.decode(respStr,method.getGenericReturnType());
            }
            return null;
        }
    }


    public static ParamStruct getMethodParam(Method method, Object[] args){
        ParamStruct resultStruct = new ParamStruct();
        Map<String,String> paramMap = new HashMap<>();
        Map<String,String> headerMap = null;
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        int count = parameterAnnotations.length;
        for (int i = 0; i < count; i++) {
            int paramAnnotationCount = parameterAnnotations[i].length;
            for(int j=0;j<paramAnnotationCount;j++){
                System.out.println("p->>>>>>>>>>>"+parameterAnnotations[i][j]);
                if(parameterAnnotations[i][j].annotationType() == Param.class){
                    Param p = (Param) parameterAnnotations[i][j];
                    paramMap.put(p.value(),args[i].toString());
                }else if(parameterAnnotations[i][j].annotationType() == Header.class
                    && args[i] instanceof Map) headerMap = (Map<String, String>) args[i];
            }
        }
        resultStruct.setParams(paramMap);
        resultStruct.setHeader(headerMap);
        return resultStruct;
    }




}
