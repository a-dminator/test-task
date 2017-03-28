package io.adev.test_task.data.cloud;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import io.adev.test_task.data.cloud.base.RequestException;
import io.adev.test_task.data.cloud.exception.ParseException;
import io.adev.test_task.data.entity.Product;

import static io.adev.test_task.data.cloud.base.Extract.extractResponse;

public class ProductsMap {

    public static List<Product> mapProducts(String input) throws ParseException, RequestException {

        JsonArray productsArray = extractResponse(input).getAsJsonArray();

        List<Product> products = new ArrayList<>();
        for (JsonElement product : productsArray) {
            JsonObject productJson = product.getAsJsonObject();

            String sku = productJson.get("sku").getAsString();
            String description = productJson.get("description").getAsString();

            products.add(new Product(sku, description));
        }

        return products;
    }

}
