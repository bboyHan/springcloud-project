package com.bboyhan.cloud.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/24 18:39
 */
@Component
@Aspect
@Slf4j
public class ExcuteMethodCostTimeAspect {

    @Pointcut("@annotation(com.bboyhan.cloud.annotation.CostTime)")
    public void costTimePointCut(){}

    @Around("costTimePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        logCostTime(point, time);
        return result;
    }

    private void logCostTime(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("----- class: "+className+" method: "+methodName + " cost:"+time+" ms -----");
    }

}
