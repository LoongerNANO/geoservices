package com.esrichina.geoservices.exception;

import cn.hutool.json.JSONUtil;
import com.esrichina.geoservices.constant.WebResultStatusConstant;
import com.esrichina.geoservices.result.WebResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class BaseRestException {

//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public WebResult notFountHandler(HttpServletRequest request, NoHandlerFoundException e) {
//        log.error(" [ ERROR MESSAGE ] - {} ", e.getMessage());
//        log.error(" [ ERROR MESSAGE ] - {} ", e.getStackTrace());
//        String method = request.getMethod();
//        String path = request.getRequestURI();
//        Map<String, Object> caseMap = new HashMap<>();
//        caseMap.put("method", method);
//        caseMap.put("path", path);
//        WebResult wbr = new WebResult(WebResultStatusConstant.ILLEGAL_URL.getCode(), WebResultStatusConstant.ILLEGAL_URL.getMsg(), caseMap);
//        return wbr;
//    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebResult exception(Exception e) {
        log.error(" [ ERROR MESSAGE ] - {} ", e.getMessage());
        log.error(" [ ERROR MESSAGE ] - {} ", e.getStackTrace());
        Map<String, String> caseMap = new HashMap<>();
        caseMap.put("msg", e.getCause().getMessage());
        WebResult wbr = new WebResult(WebResultStatusConstant.SERVER_ERROR.getCode(), WebResultStatusConstant.SERVER_ERROR.getMsg(), caseMap);
        return wbr;
    }

    @ExceptionHandler(ParameterException.class)
    @ResponseBody
    public WebResult parameterException(ParameterException e) {
        log.error(" [ ERROR MESSAGE] - {} ", e.getMessage());
        log.error(" [ ERROR MESSAGE ] - {} ", e.getStackTrace());
        WebResult wbr = new WebResult(WebResultStatusConstant.ARGUMENT_ERROR.getCode(), WebResultStatusConstant.ARGUMENT_ERROR.getMsg(), JSONUtil.parse(e.getMessage()));
        return wbr;
    }


}
