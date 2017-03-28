package io.adev.test_task.data.cloud;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import io.adev.test_task.data.cloud.base.OkHttp;
import io.adev.test_task.data.cloud.base.RequestException;
import io.adev.test_task.data.cloud.exception.NetworkException;
import io.adev.test_task.data.cloud.exception.ParseException;
import io.adev.test_task.data.entity.Product;
import okhttp3.HttpUrl;
import okhttp3.Request;

import static io.adev.test_task.data.cloud.ProductsMap.*;

public class ProductsFetch {

    private static final String TAG = "ProductsFetch";

    public static List<Product> fetchProducts(int offset, int count) throws NetworkException, ParseException, RequestException {

        try {

            HttpUrl url = HttpUrl.parse("http://89.223.29.6:8080/taxi/test/products").newBuilder()
                    .addEncodedQueryParameter("offset", String.valueOf(offset))
                    .addEncodedQueryParameter("count", String.valueOf(count))
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            String response = OkHttp.CLIENT.newCall(request)
                    .execute()
                    .body().string();

            return mapProducts(response);

        } catch (IOException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            throw new NetworkException();
        }

    }

}
