package cn.ityihang.zblog.common.result;


import cn.ityihang.zblog.common.constant.CommonConstant;
import cn.ityihang.zblog.constant.enums.IResultCode;
import cn.ityihang.zblog.constant.enums.ResultCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

/**
 * @Author: yihang
 * @Date: 2020/10/11 11:14
 * @Description:
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestResponse<T> {

    private int code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private boolean success = true;

    @JsonIgnore
    @Transient
    public boolean isOk() {
        return code == ResultCode.SUCCESS.getCode() && success;
    }

    public static <T> RestResponse<T> ok() {
        return restResult(null, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> RestResponse<T> ok(T data) {
        return restResult(data, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> RestResponse<T> ok(T data, String msg) {
        return restResult(data, ResultCode.SUCCESS.getCode(), msg);
    }

    public static <T> RestResponse<T> failed() {
        return restResult(null, ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg(),false);
    }

    public static <T> RestResponse<T> failed(String msg) {
        return restResult(null, ResultCode.FAILURE.getCode(), msg,false);
    }

    public static <T> RestResponse<T> failed(IResultCode resultCode) {
        return restResult(null, resultCode.getCode(), resultCode.getMsg(),false);
    }

    public static <T> RestResponse<T> failed(IResultCode resultCode,String message) {
        return restResult(null, resultCode.getCode(), message,false);
    }

    public static <T> RestResponse<T> failed(T data) {
        return restResult(data, ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg(),false);
    }

    public static <T> RestResponse<T> failed(T data, String msg) {
        return restResult(data, ResultCode.FAILURE.getCode(), msg,false);
    }

    public static <T> RestResponse<T> failed(int code, String msg) {
        return restResult(null, code, msg,false);
    }

    public static <T> RestResponse<T> build(T data, int code, String msg) {
        return restResult(data, code, msg);
    }

    private static <T> RestResponse<T> restResult(T data, int code, String msg) {
        return restResult(data, code, msg,true);
    }

    private static <T> RestResponse<T> restResult(T data, int code, String msg,boolean success) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setCode(code);
        restResponse.setData(data);
        restResponse.setMsg(msg);
        restResponse.setSuccess(success);
        return restResponse;
    }

    /**
     * 无权限访问返回结果
     */
    public static RestResponse<Object> noauth(String msg) {
        return failed(CommonConstant.SC_JEECG_NO_AUTHZ, msg);
    }

    @JsonIgnore
    private String onlTable;
}
