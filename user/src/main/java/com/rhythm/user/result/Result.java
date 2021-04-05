package com.rhythm.user.result;

import com.rhythm.user.Enum.ResultCode;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String,Object> data = new HashMap<String ,Object>();

    public Result(){}

    public static Result ok(){
        Result r = new Result();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setSuccess(ResultCode.SUCCESS.getStatus());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        return r;
    }

    public static Result ok(long total, long current, long size, Object records) {
        Result r = new Result();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setSuccess(ResultCode.SUCCESS.getStatus());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        r.setData("total", total);
        r.setData("current", current);
        r.setData("size", size);
        r.setData("records", records);
        return r;
    }

    public static Result error(){
        Result r = new Result();
        r.setCode(ResultCode.UNKNOW_REASON.getCode());
        r.setSuccess(ResultCode.UNKNOW_REASON.getStatus());
        r.setMessage(ResultCode.UNKNOW_REASON.getMessage());
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
    public Result setData(Map<String,Object> map){
        this.setData(map);
        return this;
    }

    public Result setData(String key,Object value){
        this.data.put(key,value);
        return this;
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


