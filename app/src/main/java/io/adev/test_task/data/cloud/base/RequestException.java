package io.adev.test_task.data.cloud.base;

public class RequestException extends Exception {
    public final int code;
    public RequestException(int code, String message) {
        super("code=" + code + ", message=" + message);
        this.code = code;
    }
}
