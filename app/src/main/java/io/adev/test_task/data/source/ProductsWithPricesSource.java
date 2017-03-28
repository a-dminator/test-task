package io.adev.test_task.data.source;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.adev.test_task.data.base.AbsObservableFactory;
import io.adev.test_task.data.entity.PriceWithSku;
import io.adev.test_task.data.entity.Product;
import io.adev.test_task.data.entity.ProductWithPrice;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;

import static io.reactivex.schedulers.Schedulers.io;

public class ProductsWithPricesSource extends AbsObservableFactory<List<ProductWithPrice>, ProductsSource.Criteria> {

    private final ProductsSource productsSource;
    private final PriceSource priceSource;
    public ProductsWithPricesSource(ProductsSource productsSource,
                                    PriceSource priceSource) {
        super(io());
        this.productsSource = productsSource;
        this.priceSource = priceSource;
    }

    @Override
    protected Observable<List<ProductWithPrice>> buildObservable(ProductsSource.Criteria criteria) {
        return productsSource.create(criteria)
                .flatMap(new Function<List<Product>, ObservableSource<List<ProductWithPrice>>>() {
                    @Override
                    public ObservableSource<List<ProductWithPrice>> apply(@NonNull final List<Product> products) throws Exception {

                        List<Observable<PriceWithSku>> priceSources = new ArrayList<>();
                        for (Product product : products) {
                            priceSources.add(priceSource.create(new PriceSource.Criteria(product.sku)));
                        }

                        return Observable.merge(priceSources)
                                .flatMap(new Function<PriceWithSku, ObservableSource<?>>() {
                                    @Override
                                    public ObservableSource<?> apply(@NonNull PriceWithSku priceWithSku) throws Exception {
                                        Product product = findProduct(priceWithSku.sku, products);
                                        return Observable.just(new ProductWithPrice(product, priceWithSku.price));
                                    }
                                })
                                .subscribeOn(commonScheduler)
                                .collect(new Callable<List<ProductWithPrice>>() {
                                    @Override
                                    public List<ProductWithPrice> call() throws Exception {
                                        return new ArrayList<>();
                                    }
                                }, new BiConsumer<List<ProductWithPrice>, Object>() {
                                    @Override
                                    public void accept(@NonNull List<ProductWithPrice> productsWithPrices, @NonNull Object productWithPrice) throws Exception {
                                        productsWithPrices.add((ProductWithPrice) productWithPrice);
                                    }
                                })
                                .flatMapObservable(new Function<List<ProductWithPrice>, ObservableSource<? extends List<ProductWithPrice>>>() {
                                    @Override
                                    public ObservableSource<? extends List<ProductWithPrice>> apply(@NonNull List<ProductWithPrice> productWithPrices) throws Exception {
                                        return Observable.just(productWithPrices);
                                    }
                                });
                    }
                });
    }

    private Product findProduct(String sku, List<Product> products) {

        for (Product product : products) {
            if (product.sku.equals(sku)) {
                return product;
            }
        }

        return null;
    }

}
