package io.adev.test_task.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.adev.test_task.data.source.ProductsWithPricesSource;
import io.adev.test_task.presenter.ProductsPresenter;
import io.adev.test_task.presenter.contract.ProductsContract;

@Module
public class ProductsModule {

    private final ProductsContract.View view;
    public ProductsModule(ProductsContract.View view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public ProductsContract.Presenter presenter(ProductsWithPricesSource productsWithPricesSource) {
        return new ProductsPresenter(productsWithPricesSource, view);
    }

}
