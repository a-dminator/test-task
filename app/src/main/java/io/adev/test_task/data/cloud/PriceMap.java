package io.adev.test_task.data.cloud;

import com.google.gson.JsonObject;

import io.adev.test_task.data.cloud.base.RequestException;
import io.adev.test_task.data.cloud.exception.ParseException;
import io.adev.test_task.data.entity.PriceWithSku;

import static io.adev.test_task.data.cloud.base.Extract.extractResponse;

public class PriceMap {

    public static PriceWithSku mapPrice(String input) throws ParseException, RequestException {

        JsonObject priceWithSkuJson = extractResponse(input).getAsJsonObject();

        String sku = priceWithSkuJson.get("sku").getAsString();
        float price = priceWithSkuJson.get("price").getAsFloat();

        return new PriceWithSku(sku, price);
    }

}
