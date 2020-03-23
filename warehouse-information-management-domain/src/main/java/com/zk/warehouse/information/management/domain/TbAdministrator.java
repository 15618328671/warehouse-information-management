package com.zk.warehouse.information.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zk.warehouse.information.management.commons.persistence.BaseEntity;
import com.zk.warehouse.information.management.commons.utils.RegexUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @author zk
 * @date 2020/1/18-14:35
 */
@Data
public class TbAdministrator extends BaseEntity {
    @Length(min = 2,max = 20,message = "用户名需要在2~20位之间")
    private String username;
    @JsonIgnore
    @Length(min = 6,max = 20,message = "密码长度需要在6~20位之间")
    private String password;
    @Pattern(regexp = RegexUtils.PHONE,message = "手机号格式不正确")
    private String phone;
    @Pattern(regexp = RegexUtils.EMAIL,message = "邮箱地址格式不正确")
    private String email;
    private Boolean isRemember;
}
