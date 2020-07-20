package com.esrichina.geoservices.controller;

import com.esrichina.geoservices.annotation.LogAnnotation;
import com.esrichina.geoservices.constant.WebResultStatusConstant;
import com.esrichina.geoservices.entity.TSysLogEntity;
import com.esrichina.geoservices.model.example.LogModel;
import com.esrichina.geoservices.param.example.ArgumentsDictionaryParameter;
import com.esrichina.geoservices.param.example.ArgumentsLogParameter;
import com.esrichina.geoservices.param.example.ArgumentsParameter;
import com.esrichina.geoservices.param.example.ArgumentsValidateParameter;
import com.esrichina.geoservices.result.WebResult;
import com.esrichina.geoservices.service.TSysLogService;
import com.esrichina.geoservices.transfer.ExampleTransfer;
import com.esrichina.geoservices.util.DictConvertUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = {"接口示例接口"})
@RestController
@RequestMapping(value = "/example")
public class ExampleController {


    @Autowired
    private TSysLogService tSysLogService;

    @Resource
    private ExampleTransfer exampleTransfer;



    @LogAnnotation(value = "日志分页示例")
    @ApiOperation(value = "日志分页示例", notes = "日志分页示例")
    @GetMapping(value = "/samples/logs/page")
    public WebResult samplesLogPageController(@Valid ArgumentsLogParameter argumentsLogParameter, BindingResult result) {

        // 初始化分页插件
        PageHelper.startPage(argumentsLogParameter.getCurrent(), argumentsLogParameter.getPages());
        // 查数据 支持Lambad行为查询
        List<TSysLogEntity> list = tSysLogService.list();
        // 字典转换
        DictConvertUtil.convertToDictionaryList(list);
        // 装载分页对象
        PageInfo pageInfo = new PageInfo<>(list);
        // 对象转换 po - > vo
        List<LogModel> listLog = exampleTransfer.tSysLogEntity2LoggerModelListTransfer(pageInfo.getList());
        // 重新装载
        pageInfo.setList(listLog);

        WebResult wResult = new WebResult(WebResultStatusConstant.SUCCESS_OPERATION.getCode(), WebResultStatusConstant.SUCCESS_OPERATION.getMsg(), pageInfo);
        return wResult;
    }

    @LogAnnotation(value = "无参GET请求示例")
    @ApiOperation(value = "无参GET请求示例", notes = "无参GET请求示例")
    @GetMapping(value = "/nonparametric/get")
    public WebResult nonparametricGetController() {
        WebResult wResult = new WebResult(WebResultStatusConstant.SUCCESS_OPERATION.getCode(), WebResultStatusConstant.SUCCESS_OPERATION.getMsg());
        return wResult;
    }

    @LogAnnotation(value = "无参DELETE请求示例")
    @ApiOperation(value = "无参DELETE请求示例", notes = "无参DELETE请求示例")
    @DeleteMapping(value = "/nonparametric/delete/{id}")
    public WebResult nonparametricDeleteController(@PathVariable("id") String id) {
        WebResult wResult = new WebResult(WebResultStatusConstant.SUCCESS_OPERATION.getCode(), WebResultStatusConstant.SUCCESS_OPERATION.getMsg());
        return wResult;
    }


    @LogAnnotation(value = "有参POST请求示例")
    @ApiOperation(value = "有参POST请求示例", notes = "有参POST请求示例")
    @PostMapping(value = "/arguments/post/{id}")
    public WebResult argumentsPostController(@PathVariable("id") String id, @RequestBody ArgumentsParameter argumentsChainParameter) {
        WebResult wResult = new WebResult(WebResultStatusConstant.SUCCESS_OPERATION.getCode(), WebResultStatusConstant.SUCCESS_OPERATION.getMsg());
        return wResult;
    }

    @LogAnnotation(value = "有参带校验POST请求示例")
    @ApiOperation(value = "有参带校验POST请求示例", notes = "有参带校验POST请求示例")
    @PostMapping(value = "/arguments/post/validate/{id}")
    public WebResult argumentsValidatePostController(@PathVariable("id") String id, @Valid @RequestBody ArgumentsValidateParameter argumentsValidateParameter, BindingResult result) {
        WebResult wResult = new WebResult(WebResultStatusConstant.SUCCESS_OPERATION.getCode(), WebResultStatusConstant.SUCCESS_OPERATION.getMsg());
        return wResult;
    }

    @LogAnnotation(value = "非法字典POST请求示例")
    @ApiOperation(value = "非法字典POST请求示例", notes = "非法字典POST请求示例")
    @PostMapping(value = "/arguments/post/validate/dictionary/{id}")
    public WebResult argumentsValidateDictionaryPostController(@PathVariable("id") String id, @Valid @RequestBody ArgumentsDictionaryParameter argumentsDictionaryParameter, BindingResult result) {
        WebResult wResult = new WebResult(WebResultStatusConstant.SUCCESS_OPERATION.getCode(), WebResultStatusConstant.SUCCESS_OPERATION.getMsg());
        return wResult;
    }


    @LogAnnotation(value = "路径带参GET请求示例")
    @ApiOperation(value = "路径带参GET请求示例", notes = "路径带参GET请求示例")
    @GetMapping(value = "/arguments/get/link/{id}")
    public WebResult argumentsLinkGetController(@PathVariable("id") String id) {
        WebResult wResult = new WebResult(WebResultStatusConstant.SUCCESS_OPERATION.getCode(), WebResultStatusConstant.SUCCESS_OPERATION.getMsg());
        return wResult;
    }

    @LogAnnotation(value = "路径对象带参GET请求示例")
    @ApiOperation(value = "路径对象带参GET请求示例", notes = "路径带参GET请求示例")
    @GetMapping(value = "/arguments/get/chain/{id}")
    public WebResult argumentsChainGetController(@PathVariable("id") String id, ArgumentsParameter argumentsChainParameter) {
        WebResult wResult = new WebResult(WebResultStatusConstant.SUCCESS_OPERATION.getCode(), WebResultStatusConstant.SUCCESS_OPERATION.getMsg());
        return wResult;
    }

    @LogAnnotation(value = "路径对象带参GET请求示例")
    @ApiOperation(value = "路径对象带参GET请求示例", notes = "路径带参GET请求示例")
    @GetMapping(value = "/arguments/get/chain/validate/{id}")
    public WebResult argumentsChainValidateGetController(@PathVariable("id") String id, @Valid ArgumentsValidateParameter argumentsChainValidateParamete, BindingResult result) {
        WebResult wResult = new WebResult(WebResultStatusConstant.SUCCESS_OPERATION.getCode(), WebResultStatusConstant.SUCCESS_OPERATION.getMsg());
        return wResult;
    }

    @LogAnnotation(value = "路径非对象带参GET请求示例")
    @ApiOperation(value = "路径非对象带参GET请求示例", notes = "路径非对象带参GET请求示例")
    @GetMapping(value = "/arguments/get/chain/validate/fileds/{id}")
    public WebResult argumentsChainValidateFiledsGetController(@PathVariable("id") String id, @RequestParam("name") String name) {
        // 自行校验
        WebResult wResult = new WebResult(WebResultStatusConstant.SUCCESS_OPERATION.getCode(), WebResultStatusConstant.SUCCESS_OPERATION.getMsg());
        return wResult;
    }

}