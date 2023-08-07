package org.chench.extra.dubbo.apisix.constant;

/**
 * 响应状态码
 *
 * @author chench
 * @desc org.chench.extra.dubbo.apisix.constant.ResponseStatusCode
 * @date 2023.08.07
 */
public class ResponseStatusCode {
    /** 执行成功 */
    public static ResponseStatusCode OK = new ResponseStatusCode(200, "ok");
    /** 执行失败 */
    public static ResponseStatusCode ERROR = new ResponseStatusCode(500, "error");

    private int code = -1;
    private String message = "";
    private ResponseStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}