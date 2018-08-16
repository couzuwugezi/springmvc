package springmvc.liqiang.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author liqiang
 * 时间 2018-08-15 22:29
 * 描述 aop输出日志
 */
@Aspect
public class LogError {

    @Pointcut("execution(* springmvc.liqiang..*.*.*(..))")
    public void print() {
    }

    @AfterThrowing(pointcut = "print()", throwing = "e")
    public void handleThrowing(JoinPoint joinPoint, Exception e) {
        //类抛出的异常在这边捕获
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        //开始打log
        System.out.println("异常:" + e.getMessage());
        System.out.println("异常所在类：" + className);
        System.out.println("异常所在方法：" + methodName);
        for (Object arg : args) {
            System.out.println("异常中的参数：" + arg);
        }

    }
}
