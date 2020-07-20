package com.esrichina.geoservices.param.example;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "日志分页示例")
public class ArgumentsLogParameter {

    @NotNull(message = "当前页数不能为空")
    @ApiModelProperty(value = "当前页数", example = "1")
    private Integer current;
    @NotNull(message = "当前页总数不能为空")
    @ApiModelProperty(value = "当前页总数", example = "10")
    private Integer pages;

}
