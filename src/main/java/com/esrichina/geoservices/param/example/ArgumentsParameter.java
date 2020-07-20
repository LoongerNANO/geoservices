package com.esrichina.geoservices.param.example;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 有参对象的参数示例
 */

@Data
@ApiModel(description = "有参对象的参数示例")
public class ArgumentsParameter {

    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "关键字")
    private String keyword;

}
