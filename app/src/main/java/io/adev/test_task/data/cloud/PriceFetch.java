package io.adev.test_task.data.cloud;

import android.util.Log;

import java.io.IOException;

import io.adev.test_task.data.cloud.base.OkHttp;
import io.adev.test_task.data.cloud.base.RequestException;
import io.adev.test_task.data.cloud.exception.NetworkException;
import io.adev.test_task.data.cloud.exception.ParseException;
import io.adev.test_task.data.entity.PriceWithSku;
import okhttp3.HttpUrl;
import okhttp3.Request;

import static io.adev.test_task.data.cloud.PriceMap.*;

public class PriceFetch {

    private static final String TAG = "PriceFetch";

    public static PriceWithSku fetchPrice(String sku) throws NetworkException, ParseException, RequestException {

        try {

            HttpUrl url = HttpUrl.parse("http://89.223.29.6:8080/taxi/test/price").newBuilder()
                    .addEncodedQueryParameter("sku", sku)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            String response = OkHttp.CLIENT.newCall(request)
                    .execute()
                    .body().string();

            return mapPrice(response);

        } catch (IOException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            throw new NetworkException();
        }

    }

}
