package me.rainstorm.boot.controller.aop;

import me.rainstorm.boot.config.SelfDefineProperties;
import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogLevel;
import me.rainstorm.boot.domain.util.log.LogUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author baochen1.zhang
 * @date 2019.07.06
 */
@Aspect
@Controller
public class DaoTimeLog {

    @Resource
    private SelfDefineProperties selfDefineProperties;

    @Around("execution(public * me.rainstorm.boot.dao..*Dao.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String category = null;
        String logMethodName = null;
        try {
            category = joinPoint.getSignature().getDeclaringType().getSimpleName();
            logMethodName = joinPoint.getSignature().getName();

            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            long warnThreshold = ObjectUtils.defaultIfNull(selfDefineProperties.getDaoAccessTimeWarnThreshold(), 200);
            long currentSpend = stopWatch.getTime(TimeUnit.MILLISECONDS);
            if (warnThreshold <= currentSpend) {
                LogUtil.log(LogBuilder.init(category, logMethodName)
                        .setLevel(LogLevel.WARN)
                        .setStartTime(stopWatch.getStartTime()).build());
            }
        }
    }
}
