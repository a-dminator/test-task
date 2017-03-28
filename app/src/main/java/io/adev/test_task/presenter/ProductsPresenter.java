package io.adev.test_task.presenter;

import java.util.List;

import io.adev.test_task.data.base.UseCase;
import io.adev.test_task.data.entity.ProductWithPrice;
import io.adev.test_task.data.source.ProductsSource;
import io.adev.test_task.data.source.ProductsWithPricesSource;
import io.adev.test_task.presenter.contract.ProductsContract;
import io.reactivex.observers.DisposableObserver;

public class ProductsPresenter implements ProductsContract.Presenter {

    private final UseCase<List<ProductWithPrice>, ProductsSource.Criteria> productWithPricesUseCase;
    private DisposableObserver<List<ProductWithPrice>> createProductsWithCoastsObserver() {
        return new DisposableObserver<List<ProductWithPrice>>() {
            @Override
            public void onNext(List<ProductWithPrice> productWithPrices) {
                view.displayProductsWithPrices(productWithPrices);
            }
            @Override
            public void onError(Throwable e) {
                view.displayError();
            }
            @Override public void onComplete() {}
        };
    }

    private ProductsContract.View view;
    public ProductsPresenter(ProductsWithPricesSource productsWithPricesSource,
                             ProductsContract.View view) {
        productWithPricesUseCase = new UseCase<>(productsWithPricesSource);
        this.view = view;
    }

    @Override
    public void init() {
        productWithPricesUseCase.execute(createProductsWithCoastsObserver(), new ProductsSource.Criteria(0, 20));
    }

}
