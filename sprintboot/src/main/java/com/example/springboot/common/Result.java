package com.example.springboot.common;

import lombok.*;

/**
 * @Classname Result
 * @Description JSON result(code, msg, data)
 * @Author zjj
 * @Date 10/22/23 6:40 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    public static final String CODE_SUCCESS = "200";
    public static final String CODE_AUTH_ERROR = "401";
    public static final String CODE_SYS_ERROR = "500";

    /**
     * return code: 200, 401, 404, 500
     * can know whether true or false and tell type of false
     * 200: success
     * 401: not authorized
     * 404: no resource
     * 500: system error
     * @return
     */
    private String code;
    /**
     * msg: detail information about failure
     */
    private String msg;
    /**
     * data comes from where
     * ex: user, List, Map
     * Object --
     */
    private Object data;


    public static Result success() {
        //return Result.builder().code(CODE_SUCCESS).msg("request success").build();
        return new Result(CODE_SUCCESS, "request success", null);
    }

    public static Result success(Object data) {
        return new Result(CODE_SUCCESS, "request success", data);
    }

    public static Result error(String msg) {
        return new Result(CODE_SYS_ERROR, msg, null);
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result error() {
        return new Result(CODE_SYS_ERROR, "system error", null);
    }

}
