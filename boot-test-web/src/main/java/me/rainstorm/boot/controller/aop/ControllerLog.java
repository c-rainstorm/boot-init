package me.rainstorm.boot.controller.aop;

import me.rainstorm.boot.config.SelfDefineProperties;
import me.rainstorm.boot.domain.constant.ResponseCodeEnum;
import me.rainstorm.boot.domain.exception.CommonBizException;
import me.rainstorm.boot.domain.response.IResponse;
import me.rainstorm.boot.domain.response.Response;
import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogLevel;
import me.rainstorm.boot.domain.util.log.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@Aspect
@Component
public class ControllerLog {

    @Resource
    private SelfDefineProperties selfDefineProperties;

    @Around("execution(public me.rainstorm.boot.domain.response.Response me.rainstorm.boot.controller..*(..))")
    public Object log(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        String category = null;
        String logMethodName = null;
        Object[] args = null;
        Object result = null;
        Throwable t = null;
        try {
            category = joinPoint.getSignature().getDeclaringType().getSimpleName();
            logMethodName = joinPoint.getSignature().getName();
            args = filterArgs(joinPoint.getArgs());

            result = joinPoint.proceed();
        } catch (Throwable e) {
            t = e;
            IResponse response = new Response();
            result = response.failure(e);
        } finally {
            LogUtil.log(LogBuilder.init(category, logMethodName)
                    .setLevel(t == null ? LogLevel.INFO : LogLevel.ERROR)
                    .setStartTime(startTime)
                    .setTraceEntity(args, result)
                    .setMessage(t == null ? null : t.getMessage(), t).build());
        }
        return result;
    }


    private Object[] filterArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return new Object[0];
        }

        List<Object> newArgs = new ArrayList<>(args.length);
        BindingResult bindingResult = null;

        for (Object arg : args) {
            if (arg == null) {
                newArgs.add("null");
                continue;
            }

            if (arg instanceof BindingResult) {
                bindingResult = (BindingResult) arg;
                continue;
            }

            String fullClassName = arg.getClass().getName();
            for (String basePackage : selfDefineProperties.getLogParamsPrefix()) {
                if (fullClassName.startsWith(basePackage)) {
                    newArgs.add(arg);
                    break;
                }
            }
        }

        if (bindingResult != null && bindingResult.hasErrors()) {
            StringJoiner errorInfo = new StringJoiner(";");
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorInfo.add(error.getDefaultMessage());
            }

            throw new CommonBizException(errorInfo.toString(), ResponseCodeEnum.BAD_REQUEST);
        }

        return newArgs.toArray();
    }
}
