package com.bboyhan.common.aop;

import com.bboyhan.common.annotation.DbName;
import com.bboyhan.common.config.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/24 18:39
 */
@Component
@Order(-1)//保证在@Transactional之前执行
@Aspect
@Slf4j
public class ChooseDbAspect {

    @Pointcut("@annotation(com.bboyhan.common.annotation.DbName)")
    public void chooseDbPointCut(){}

    //改变数据源
    @Before("@annotation(dbName)")
    public void changeDataSource(JoinPoint joinPoint, DbName dbName) {
        String dbid = dbName.value();

        if (!DynamicDataSourceContextHolder.isContainsDataSource(dbid)) {
            //joinPoint.getSignature() ：获取连接点的方法签名对象
            log.error("数据源 " + dbid + " 不存在使用默认的数据源 -> " + joinPoint.getSignature());
        } else {
            log.debug("使用数据源：" + dbid);
            DynamicDataSourceContextHolder.setDataSourceType(dbid);
        }
    }

    @After("@annotation(dbName)")
    public void clearDataSource(JoinPoint joinPoint, DbName dbName) {
        log.debug("清除数据源 " + dbName.value() + " ! - start");
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}
