package com.soulroomie.demo.tools;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 * can use like this:
 * <p>
 * return Result.ok().code(...).message(...).data(Map)....
 * return Result.error().code(....).message(...)....
 */


@Data
public class Result {
    private static Integer SUCCESS = 1000;
    private static Integer UNKNOWN_REASON_ERROR = 1001;
    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();

    private Result() {
    }

    public static Result ok() {
        Result r = new Result();
        r.setSuccess(Boolean.TRUE);
        r.setCode(SUCCESS);
        r.setMessage("Success");
        return r;
    }

    public static Result error() {
        Result r = new Result();
        r.setSuccess(Boolean.FALSE);
        r.setCode(UNKNOWN_REASON_ERROR);
        r.setMessage("Unknown_Error");
        return r;
    }

    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
