package io.adev.test_task.data.cloud.base;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.adev.test_task.data.cloud.exception.ParseException;

public class Extract {

    private static final OnError defaultOnError = new OnError() {
        @Override
        public JsonElement handle(int code, String message) throws RequestException {
            throw new RequestException(code, message);
        }
    };

    public static JsonElement extractResponse(String input) throws ParseException, RequestException {
        return extractResponse(input, defaultOnError);
    }

    public static JsonElement extractResponse(String input, OnError onError) throws ParseException, RequestException {

        try {

            JsonObject rootJson = new JsonParser().parse(input).getAsJsonObject();

            int code = rootJson.get("code").getAsInt();
            if (code == 0) {
                return rootJson.get("response");
            } else {
                String message = rootJson.get("message").getAsString();
                return onError.handle(code, message);
            }

        } catch (Exception e) {
            throw new ParseException();
        }

    }

}
