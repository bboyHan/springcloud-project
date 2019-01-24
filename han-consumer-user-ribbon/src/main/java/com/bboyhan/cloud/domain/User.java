package com.bboyhan.cloud.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/20 16:28
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
