package com.tong.httputil;

import java.lang.reflect.Method;
import java.util.Set;

@DataSource(name = "test001",appid = "123",appsec = "456")
public class Test {

    public static void main(String[] args){
        Scanner sc = new Scanner();
        try {
            Set<Class<?>> classes = sc.getAnnotationClasses("com.tong",DataSource.class);

            for (Class<?> c : classes) {
                System.out.println(c);
//                Test t = (Test) c.newInstance();
//                t.run();
                DataSource dataSource = c.getAnnotation(DataSource.class);
                System.out.println(dataSource.name());

            }

            Set<Method> methodSet = sc.getMethod(Test.class,GetLine.class);
            for(Method method : methodSet){
                GetLine getLine = method.getAnnotation(GetLine.class);
                System.out.println(getLine.value());
                ReflectInterface.getMethodParam(method);
//                method.getParameterAnnotations()
            }

//            ReflectInterface reflectInterface = new ReflectInterface();
            MyUrl myUrl = ReflectInterface.getInstance(MyUrl.class);
            User u = myUrl.getUser("adad",1);

            System.out.println(u.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetLine(value = "running aaaa")
    public void run(){
        System.out.println("running ...........");
    }
}
