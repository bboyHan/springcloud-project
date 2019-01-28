package com.bboyhan.ribbon.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/20 18:42
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
