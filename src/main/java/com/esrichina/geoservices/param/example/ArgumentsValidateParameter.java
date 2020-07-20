package com.esrichina.geoservices.param.example;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


/**
 * 有参对象带校验的参数示例
 * <p>
 * eg:
 *
 * @NotNull 不能为null, 但可以为empty, 用在基本类型上
 * @NotEmpty 不能为null, 而且长度必须大于0, 用在集合类上面
 * @NotBlank 只能作用在String上, 不能为null, 而且调用trim()后, 长度必须大于0
 */


@Data
@ApiModel(description = "有参对象的参数示例")
public class ArgumentsValidateParameter {

    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "关键字")
    private String keyword;
    @NotBlank(message = "生日不能为空")
    @ApiModelProperty(value = "生日")
    private String birthday;
    @Email(message = "电子邮件格式不正确")
    @ApiModelProperty(value = "生日")
    private String email;

}
