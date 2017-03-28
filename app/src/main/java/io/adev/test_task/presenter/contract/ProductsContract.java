package io.adev.test_task.presenter.contract;

import java.util.List;

import io.adev.test_task.data.entity.ProductWithPrice;

public interface ProductsContract {
    interface Presenter {
        void init();
    }
    interface View {
        void displayProductsWithPrices(List<ProductWithPrice> data);
        void displayError();
    }
}
