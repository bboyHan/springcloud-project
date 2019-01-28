package com.bboyhan.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/24 18:39
 */
public interface SysMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
