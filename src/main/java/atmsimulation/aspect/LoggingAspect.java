package atmsimulation.aspect;

import atmsimulation.AtmSimulation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
    private Logger logger = LogManager.getLogger(AtmSimulation.class);

    @Pointcut("execution(* atmsimulation.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* atmsimulation.services.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* atmsimulation.repository.*.*(..))")
    private void forRepoPackage(){}

    @Pointcut("forControllerPackage() || forRepoPackage() || forServicePackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        logger.info("@Before calling method: " + method);

        Object[] args = joinPoint.getArgs();

        for (Object temp : args) {
            logger.info("Argument " + temp);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void returning(JoinPoint joinPoint, Object result){

        String method = joinPoint.getSignature().toShortString();
        logger.info("@After Returning from " + method);

        logger.info("Result " + result);

    }
}
