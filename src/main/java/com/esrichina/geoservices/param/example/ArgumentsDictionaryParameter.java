package com.esrichina.geoservices.param.example;

import com.esrichina.geoservices.annotation.StateAnnotation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 非法字典值参数示例
 * <p>
 * eg:
 *
 * @NotNull 不能为null, 但可以为empty, 用在基本类型上
 * @NotEmpty 不能为null, 而且长度必须大于0, 用在集合类上面
 * @NotBlank 只能作用在String上, 不能为null, 而且调用trim()后, 长度必须大于0
 */


@Data
@ApiModel(description = "非法字典值参数示例")
public class ArgumentsDictionaryParameter {

    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "关键字")
    private String keyword;
    @StateAnnotation(value = "sys_log_type", message = "非法参数")
    @NotBlank(message = "日志类型错误")
    @ApiModelProperty(value = "日志类型")
    private String status;

}
