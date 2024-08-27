package com.example.testdemo.aspect;


import com.fasterxml.jackson.annotation.JacksonAnnotation;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;


@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 配置植入点，自定义注解的包路径
     */
    @Pointcut("@annotation(com.example.testdemo.annotation.log)")
    public void logPointCut(){

    }

    /**
     * 处理完请求之后执行
     */
    @AfterReturning(pointcut = "logPointCut()",returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult){

    }

    /**
     * 拦截异常操作
     */
    @AfterThrowing(value = "logPointCut()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Exception e){

    }

    /**
     * 处理日志
     */

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult){
        try{
            Log annotationLog = getAnnotationLog(joinPoint);
            if(annotationLog==null){
                return;
            }
             //获取当前用户
            String name="毛要强";
            //Operlog operlog = new Operlog();

        }catch(Exception exp){

        }
    }

    /**
     * 是否存在注解，如果存在就去获取
     */
    public Log getAnnotationLog(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if(method!=null){
            return method.getAnnotation(Log.class);
        }
        return  null;
    }

}
