package com.bboyhan.cloud.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Auther: wushaohan
 * @Date: 2019/1/14 10:43
 * @Description:
 */
@Entity
@Data
@Table(name = "tb_user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
