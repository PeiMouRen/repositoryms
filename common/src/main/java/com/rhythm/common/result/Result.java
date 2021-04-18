package com.rhythm.common.result;

import com.rhythm.common.Enum.ResultCode;
import lombok.Data;

@Data
public class Result {

    private Boolean success;

    private Integer code;

    private String message;

    private Object data;

    private long total;

    public Result(){}

    public static Result ok(){
        Result r = new Result();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setSuccess(ResultCode.SUCCESS.getStatus());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        return r;
    }


    public static Result error(){
        Result r = new Result();
        r.setCode(ResultCode.ERROR.getCode());
        r.setSuccess(ResultCode.ERROR.getStatus());
        r.setMessage(ResultCode.ERROR.getMessage());
        return r;
    }

    public static Result ok(ResultCode codeEnum){
        Result r = new Result();
        r.setCode(codeEnum.getCode());
        r.setSuccess(codeEnum.getStatus());
        r.setMessage(codeEnum.getMessage());
        return r;
    }

    public static Result error(ResultCode codeEnum){
        Result r = new Result();
        r.setCode(codeEnum.getCode());
        r.setSuccess(codeEnum.getStatus());
        r.setMessage(codeEnum.getMessage());
        return r;
    }


    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }


}


