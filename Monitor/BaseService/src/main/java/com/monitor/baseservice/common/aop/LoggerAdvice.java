package com.monitor.baseservice.common.aop;//package com.monitor.baseservice.common.aop;
//
//
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.log4j.Logger;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Service;
//
///**
// * 日志管理
// *
// * Created by Administrator on 2017/7/1 0001.
// */
//@Aspect
//@Service
//public class LoggerAdvice {
//    private Logger logger = Logger.getLogger(this.getClass());
//
//    @Before("within(com.monitor..*) && @annotation(loggerManage)")
//    public void addBeforeLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
//        logger.info("执行 " + loggerManage.description() + " 开始");
//        logger.info(joinPoint.getSignature().toString());
//        logger.info(parseParames(joinPoint.getArgs()));
//    }
//
//    @AfterReturning("within(com.monitor..*) && @annotation(loggerManage)")
//    public void addAfterReturningLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
//        logger.info("执行 " + loggerManage.description() + " 结束");
//    }
//
//    @AfterThrowing(pointcut = "within(com.monitor..*) && @annotation(loggerManage)", throwing = "ex")
//    public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerManage loggerManage, Exception ex) {
//        logger.error("执行 " + loggerManage.description() + " 异常", ex);
//    }
//
//    private String parseParames(Object[] parames) {
//        if (null == parames || parames.length <= 0) {
//            return "";
//        }
//        StringBuilder param = new StringBuilder("传入参数[{}] ");
//        for (Object obj : parames) {
//            param.append(ToStringBuilder.reflectionToString(obj)).append("  ");
//        }
//        return param.toString();
//    }
//}
