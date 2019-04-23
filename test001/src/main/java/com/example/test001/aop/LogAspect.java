package com.example.test001.aop;


import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.Logger;

//申明是个切面
@Aspect
//申明是个spring管理的bean
@Component
@Order(1)
public class LogAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    ThreadLocal<ServletRequestAttributes> attributesThreadLocal = new ThreadLocal<>();

    private Logger logger = Logger.getLogger("log001");

    @Pointcut("execution(public * com.example.test001.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attributesThreadLocal.set(attributes);

    }

    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        ServletRequestAttributes attributes = attributesThreadLocal.get();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            StringBuilder sb = new StringBuilder();
            sb.append(request.getHeader("traceid"));
            sb.append(" || ");
            sb.append(request.getRequestURI());
            sb.append(" || ");
            sb.append(request.getMethod());
            sb.append(" || ");
            try {
                if(!Strings.isEmpty(request.getQueryString())){
                    sb.append(java.net.URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8.name()));
                }
            } catch (Exception e) {
                sb.append("decode err");
            }
            sb.append(" || ");
            sb.append(request.getRemoteAddr());
            sb.append(" || ");
            sb.append(System.currentTimeMillis() - startTime.get() + "ms");
            logger.info(sb.toString());
        }
    }
}
