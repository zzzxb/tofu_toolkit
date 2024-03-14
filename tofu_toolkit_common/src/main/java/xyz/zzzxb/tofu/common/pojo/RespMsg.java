package xyz.zzzxb.tofu.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * zzzxb
 * 2024/3/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespMsg<T> {
    private String code;
    private String msg;
    private T data;

    public RespMsg<T> success(String code, String msg) {
        return success(code, msg, null);
    }

    public RespMsg<T> success(String code, String msg, T data) {
        return new RespMsg<T>(code, msg, data);
    }

    public RespMsg<T> success(int code, String msg) {
        return success(code, msg, null);
    }

    public RespMsg<T> success(int code, String msg, T data) {
        return success(String.valueOf(code), msg, data);
    }

    public RespMsg<T> fail(int code, String msg) {
        return new RespMsg<T>(String.valueOf(code), msg, null);
    }

    public RespMsg<T> fail(String code, String msg) {
        return new RespMsg<T>(code, msg, null);
    }
}
