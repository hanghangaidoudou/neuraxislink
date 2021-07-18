package com.etong.pms.model.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.*;

/**
 * 用户类视图
 *
 * @author  王超华
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    @ApiModelProperty("用户ID")
    public Long id;
    @ApiModelProperty("用户Code")
    public Long userCode;
    @ApiModelProperty("用户名称")
    public String userName;
    @ApiModelProperty("用户密码")
    public String password;
    @ApiModelProperty("角色")
    public String roles;
    @ApiModelProperty("电话")
    public String tel;
    @ApiModelProperty("邮箱")
    public String email;
    @ApiModelProperty("性别")
    public String sex;
    @ApiModelProperty("所属部门Id")
    public String deptId;
    @ApiModelProperty("所属部门Code")
    public String deptCode;
    @ApiModelProperty("所属部门名称")
    public String deptName;
    @ApiModelProperty("所属小组Id")
    public String teamId;
    @ApiModelProperty("所属小组Code")
    public String teamCode;
    @ApiModelProperty("所属小组名称")
    public String teamName;
    @ApiModelProperty("在职状态")
    public String state;
    @ApiModelProperty("入职日期")
    public String entryDate;
    @ApiModelProperty("离职日期")
    public String leaveDate;

    public String introduction = "I am Lalala";
    public String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
}
