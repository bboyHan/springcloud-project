package com.bboyhan.cloud.util;

import com.bboyhan.cloud.enums.ResultCodeEnum;
import lombok.Data;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/24 18:38
 */
@Data
public class Result<T> {

    public static final Result SUCCESS = new Result(ResultCodeEnum.SUCCESS);

    @SuppressWarnings("unchecked")
    public static <T> Result success(T data) {
        return new Result(ResultCodeEnum.SUCCESS,data);
    }

    public static Result error(ResultCodeEnum resultCodeEnum) {
        return new Result(resultCodeEnum);
    }

    @SuppressWarnings("unchecked")
    public static Result error(ResultCodeEnum resultCodeEnum, String msg) {
        return new Result(resultCodeEnum.getCode(), msg, null);
    }

    public static Result error(String msg) {
        return new Result(ResultCodeEnum.ERROR.getCode(), msg, null);
    }

    private Integer code;
    private String msg;
    private T data;

    public Result(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
    }

    public Result(ResultCodeEnum resultCodeEnum,T data) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}

