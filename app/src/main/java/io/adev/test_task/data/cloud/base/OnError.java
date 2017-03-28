package io.adev.test_task.data.cloud.base;

import com.google.gson.JsonElement;

public interface OnError {
    JsonElement handle(int code, String message) throws RequestException;
}
