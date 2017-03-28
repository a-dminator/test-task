package io.adev.test_task.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.adev.test_task.data.source.PriceSource;
import io.adev.test_task.data.source.ProductsSource;
import io.adev.test_task.data.source.ProductsWithPricesSource;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public PriceSource priceSource() {
        return new PriceSource();
    }

    @Provides
    @Singleton
    public ProductsSource productsSource() {
        return new ProductsSource();
    }

    @Provides
    @Singleton
    public ProductsWithPricesSource productsWithCoastsSource(ProductsSource productsSource,
                                                             PriceSource priceSource) {
        return new ProductsWithPricesSource(
                productsSource,
                priceSource);
    }

}
