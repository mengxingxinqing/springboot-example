package com.example.test001.aop;


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
import java.util.Arrays;
import java.util.logging.Logger;

//申明是个切面
@Aspect
//申明是个spring管理的bean
@Component
@Order(1)
public class LogAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private Logger logger = Logger.getLogger("log001");

    @Pointcut("execution(public * com.example.test001.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            StringBuilder sb = new StringBuilder();
            sb.append(request.getRequestURL().toString());
            sb.append(" || ");
            sb.append(request.getMethod());
            sb.append(" || ");
            sb.append(request.getRemoteAddr());
            sb.append(" || ");
            sb.append(joinPoint.getSignature().getDeclaringTypeName());
            sb.append(".");
            sb.append(joinPoint.getSignature().getName());
            sb.append(" || ");
            Object[] args = joinPoint.getArgs().clone();
            sb.append(Arrays.toString(args));
            logger.info(sb.toString());
        }
    }

    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        logger.info("返回内容 : " + ret);
        logger.info("花费时间 : " + (System.currentTimeMillis() - startTime.get()) + "毫秒");
    }
}
