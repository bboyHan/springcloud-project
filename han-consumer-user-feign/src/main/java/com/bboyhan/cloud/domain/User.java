package com.bboyhan.cloud.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: wushaohan
 * @Date: 2019/1/14 14:00
 * @Description:
 */
@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Integer status;
    private Date createTime;
    private Long managerId;
    private String remark;
}
