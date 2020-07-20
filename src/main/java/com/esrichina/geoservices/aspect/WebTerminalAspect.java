package com.esrichina.geoservices.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.esrichina.geoservices.annotation.LogAnnotation;
import com.esrichina.geoservices.constant.LogConstant;
import com.esrichina.geoservices.entity.TSysLogEntity;
import com.esrichina.geoservices.exception.ParameterException;
import com.esrichina.geoservices.util.IPUtil;
import com.esrichina.geoservices.mapper.TSysLogMapper;
import com.google.gson.Gson;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

@Aspect
@Component
@Log4j2
public class WebTerminalAspect {

    ThreadLocal<Long> time = new ThreadLocal<Long>();

    @Autowired
    private TSysLogMapper tSysLogMapper;


    /**
     * 切面通知
     */
    @Pointcut("execution(public * com.esrichina.geoservices.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("webLog() ")
    public void doBeforeWithout(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        log.info(" [ MESSAGE ] [ URL ] - " + request.getRequestURL().toString());
        time.set(System.currentTimeMillis());
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("webLog() && args(..,bindingResult)")
    public void doBefore(JoinPoint joinPoint, BindingResult bindingResult) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        log.info(" [ MESSAGE ] [ URL ] - " + request.getRequestURL().toString());

        time.set(System.currentTimeMillis());

        List<Map<String, String>> errorList = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(item -> {
                Map<String, String> map = new HashMap<>();
                String fields = item.getCodes().length >= 1 ? item.getCodes()[0] : "";
                if (fields != null) {
                    fields = fields.substring(fields.lastIndexOf(".") + 1);
                    map.put("filed", fields);
                    map.put("msg", item.getDefaultMessage());
                }
                errorList.add(map);
            });
            throw new ParameterException(JSONUtil.toJsonStr(errorList));
        }
    }

    /**
     * 环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        return result;
    }


    /**
     * 后置通知
     *
     * @param joinPoint
     */
    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) {
    }

    /**
     * 处理完请求，返回内容
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String agent = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);

        String param = "";
        if ("POST".equals(request.getMethod())) {
            param = JSONUtil.parseArray(joinPoint.getArgs()).toString();
        } else {
            Map<String, String> map = new HashMap<String, String>();
            Enumeration<String> paramter = request.getParameterNames();
            while (paramter.hasMoreElements()) {
                String str = (String) paramter.nextElement();
                map.put(str, request.getParameter(str));
            }
            param = "[" + new Gson().toJson(map) + "]";
        }
        String username = "";
        String name = "";

        TSysLogEntity tSysLogEntity = new TSysLogEntity();
        tSysLogEntity.setId(IdUtil.simpleUUID());
        tSysLogEntity.setUsername("");
        tSysLogEntity.setFullname("");
        tSysLogEntity.setUrl(request.getRequestURL().toString());
        tSysLogEntity.setMethod(request.getMethod());
        tSysLogEntity.setIp(IPUtil.getClientIp(request));
        tSysLogEntity.setFunctions(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        tSysLogEntity.setParameter(param);
        tSysLogEntity.setOperation(method.getAnnotation(LogAnnotation.class).value());
        tSysLogEntity.setTime((System.currentTimeMillis() - time.get()) + " ms");
        tSysLogEntity.setAsk(DateUtil.parse(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));
        tSysLogEntity.setBrowser(userAgent.getBrowser().getName());
        tSysLogEntity.setVersion(userAgent.getBrowserVersion().toString());
        tSysLogEntity.setOs(userAgent.getOperatingSystem().getName());
        tSysLogEntity.setLog_type(LogConstant.INFO.getName());
        tSysLogMapper.insert(tSysLogEntity);
        log.info(" [ MESSAGE ] - REST SUCESSFUL,  RETURN DATA - {} ", tSysLogEntity.toString());
    }

    /**
     * 后置异常通知
     *
     * @param joinPoint
     */
    @AfterThrowing(throwing = "ex", pointcut = "webLog()")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        String agent = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);

        String username = "";
        String name = "";

        TSysLogEntity tSysLogEntity = new TSysLogEntity();
        tSysLogEntity.setId(IdUtil.simpleUUID());
        tSysLogEntity.setUsername("");
        tSysLogEntity.setFullname("");
        tSysLogEntity.setUrl(request.getRequestURL().toString());
        tSysLogEntity.setMethod(request.getMethod());
        tSysLogEntity.setIp(IPUtil.getClientIp(request));
        tSysLogEntity.setFunctions(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        tSysLogEntity.setParameter(JSONUtil.parseArray(joinPoint.getArgs()).toString());
        tSysLogEntity.setOperation(method.getAnnotation(LogAnnotation.class).value());
        tSysLogEntity.setTime((System.currentTimeMillis() - time.get()) + " ms");
        tSysLogEntity.setAsk(DateUtil.parse(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));
        tSysLogEntity.setBrowser(userAgent.getBrowser().getName());
        tSysLogEntity.setVersion(userAgent.getBrowserVersion().toString());
        tSysLogEntity.setOs(userAgent.getOperatingSystem().getName());
        tSysLogEntity.setLog_type(LogConstant.ERROR.getName());
        tSysLogEntity.setError(ex.toString());
        tSysLogMapper.insert(tSysLogEntity);
        log.error(" [ MESSAGE ] - - REST FAILED,  RETURN DATA - {} ", tSysLogEntity.toString());
    }
}
