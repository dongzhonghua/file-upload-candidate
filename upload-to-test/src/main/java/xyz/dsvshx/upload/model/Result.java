package xyz.dsvshx.upload.model;

import lombok.Data;

/**
 * @author dongzhonghua
 * Created on 2021-04-24
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }
}
