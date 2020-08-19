package com.esrichina.geoservices.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.esrichina.geoservices.annotation.LogAnnotation;
import com.esrichina.geoservices.constant.WebResultStatusConstant;
import com.esrichina.geoservices.entity.TPermissionEntity;
import com.esrichina.geoservices.entity.TRolePermissionEntity;
import com.esrichina.geoservices.entity.TUserEntity;
import com.esrichina.geoservices.param.authorization.LoginParameter;
import com.esrichina.geoservices.result.WebResult;
import com.esrichina.geoservices.service.TPermissionService;
import com.esrichina.geoservices.service.TRolePermissionService;
import com.esrichina.geoservices.service.TUserService;
import com.esrichina.geoservices.util.DictConvertUtil;
import com.esrichina.geoservices.util.JwtUtil;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = {"登录模块接口"})
@RestController
@RequestMapping(value = "/authorization")
public class AuthorizationController {

    @Autowired
    private Producer kaptcha;

    @Autowired
    private TUserService tUserService;

    @Autowired
    private TRolePermissionService tRolePermissionService;

    @Autowired
    private TPermissionService tPermissionService;


    @LogAnnotation(value = "登录接口")
    @ApiOperation(value = "登录接口", notes = "登录接口")
    @PostMapping(value = "/login")
    public WebResult login(HttpSession session, @Valid @RequestBody LoginParameter loginParameter, BindingResult result) {
        WebResult wResult = null;

        String kaptcha = (String) session.getAttribute("kaptcha");

        // 验证码超时
        long old = Long.parseLong(kaptcha.split("-")[1]);

        if (System.currentTimeMillis() - old > 20000) {
            wResult = new WebResult(WebResultStatusConstant.TIME_OUT_KAPTCHA.getCode(), WebResultStatusConstant.TIME_OUT_KAPTCHA.getMsg());
            return wResult;
        }

        // 验证码有误
        if (!kaptcha.split("-")[0].equals(loginParameter.getKaptcha())) {
            wResult = new WebResult(WebResultStatusConstant.UNKWON_KAPTCHA.getCode(), WebResultStatusConstant.UNKWON_KAPTCHA.getMsg());
            return wResult;
        }


        LambdaQueryWrapper<TUserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TUserEntity::getUsername, loginParameter.getUsername());
        lambdaQueryWrapper.eq(TUserEntity::getPassword, loginParameter.getPassword());
        TUserEntity t = tUserService.getOne(lambdaQueryWrapper);

        if (t == null) {
            wResult = new WebResult(WebResultStatusConstant.UNKWON_USER.getCode(), WebResultStatusConstant.UNKWON_USER.getMsg());
            return wResult;
        }

        // 获取ROLES
        LambdaQueryWrapper<TRolePermissionEntity> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper2.eq(TRolePermissionEntity::getRole, t.getRole());
        List<TRolePermissionEntity> l1 = tRolePermissionService.list(lambdaQueryWrapper2);

        if (l1.size() == 0) {
            wResult = new WebResult(WebResultStatusConstant.UNKWON_USER_ROLE.getCode(), WebResultStatusConstant.UNKWON_USER_ROLE.getMsg());
            return wResult;
        }

        List<String> list = l1.stream().map(e -> e.getPermission()).collect(Collectors.toList());
        // 获取PERMISS
        LambdaQueryWrapper<TPermissionEntity> lambdaQueryWrapper3 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper3.in(TPermissionEntity::getId, list).orderByAsc(TPermissionEntity::getPermission);
        List<TPermissionEntity> l2 = tPermissionService.list(lambdaQueryWrapper3);

        // SING TOKEN
        String token = JwtUtil.sign(t.getUsername(), t.getName());

        Map<String, Object> map = new HashMap<String, Object>();
        t.setPassword("");
        DictConvertUtil.convertToDictionary(t);
        map.put("info", t);
        map.put("permission", l2);
        map.put("token", token);
        wResult = new WebResult(WebResultStatusConstant.SUCCESS_OPERATION.getCode(), WebResultStatusConstant.SUCCESS_OPERATION.getMsg(), map);

        return wResult;
    }

    @LogAnnotation(value = "获取验证码")
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @GetMapping(value = "/kaptcha")
    public void kaptcha(HttpServletResponse response, HttpSession session) {

        // 生成验证码
        String text = kaptcha.createText();
        BufferedImage image = kaptcha.createImage(text);
        text = text + "-" + System.currentTimeMillis();
        // 将验证码存入session
        session.setAttribute("kaptcha", text);

        // 将突图片输出给浏览器
        response.setContentType("image/png");

        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
        }
    }

}
