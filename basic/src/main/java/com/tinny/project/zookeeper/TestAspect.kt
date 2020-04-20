package com.tinny.project.zookeeper

import org.aopalliance.intercept.Joinpoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * @author : Tinny
 * @date : Created on 2020/4/15 - 17:31
 * @description :
 * @modified :
 */
@Aspect
@Component
class TestAspect {
    private val log = LoggerFactory.getLogger(this.javaClass)

    //    @Pointcut("execution(public * com.tinny.project.zookeeper.ZooKeeperService.*(..))")
    @Pointcut("@annotation(com.tinny.project.zookeeper.TestAspectAnnotation)")
    fun someAspect() = Unit

    @Before("someAspect()")
    fun doBefore() {
        log.debug("this is before, info: ")
    }

    @After("someAspect()")
    fun doAfter() {
        log.debug("this is after, info: ")
    }

    @AfterReturning("someAspect()")
    fun doAfterReturning() {
        log.debug("this is after returning, info:")
    }

    @Around("someAspect()")
    fun doAround(joinpoint: ProceedingJoinPoint): Any {
        log.debug("this is around, info: ${joinpoint.target.javaClass.name}")
        val proceed = joinpoint.proceed()
        log.debug("this is around, info: ${joinpoint.javaClass.name}")
        return proceed
    }
}