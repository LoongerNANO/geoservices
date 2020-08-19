package com.esrichina.geoservices.filter;

import cn.hutool.json.JSONUtil;
import com.esrichina.geoservices.constant.WebResultStatusConstant;
import com.esrichina.geoservices.result.WebResult;
import com.esrichina.geoservices.shrio.JwtToken;
import com.esrichina.geoservices.util.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class AuthFilter extends AccessControlFilter {


    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时,option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 拦截所有请求
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    /**
     * 拒绝通过处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        log.info(" [ MESSAGE ] - SHRIO FILTER IS CALLED, URL - {} ", httpServletRequest.getRequestURL());
        String jwt = httpServletRequest.getHeader("Authorization");
        log.info(" [ MESSAGE ] - TOKEN - {} ", jwt);


        WebResult webResult = null;
        if (jwt == null) {
            webResult = new WebResult(WebResultStatusConstant.NULL_TOEKN.getCode(), WebResultStatusConstant.NULL_TOEKN.getMsg());
            httpServletResponse.setHeader("Content-type", "text/json;charset=UTF-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().write(JSONUtil.toJsonStr(webResult));
            return false;
        }

        if (!JwtUtil.verity(jwt)) {
            webResult = new WebResult(WebResultStatusConstant.ILLEGAL_TOKEN.getCode(), WebResultStatusConstant.ILLEGAL_TOKEN.getMsg());
            httpServletResponse.setHeader("Content-type", "text/json;charset=UTF-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().write(JSONUtil.toJsonStr(webResult));
            return false;
        }

        JwtToken jwtToken = new JwtToken(jwt);
        this.getSubject(request, response).login(jwtToken);

        return true;
    }

}
