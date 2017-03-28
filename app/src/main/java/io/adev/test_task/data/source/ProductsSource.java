package io.adev.test_task.data.source;

import java.util.List;

import io.adev.test_task.data.base.ConsequenceAdapter;
import io.adev.test_task.data.entity.Product;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;

import static io.adev.test_task.data.cloud.ProductsFetch.fetchProducts;

public class ProductsSource extends ConsequenceAdapter<List<Product>, ProductsSource.Criteria> {

    public ProductsSource() {
        super(Schedulers.io());
    }

    public static class Criteria {
        public final int offset;
        public final int count;
        public Criteria(int offset, int count) {
            this.offset = offset;
            this.count = count;
        }
    }

    @Override
    protected void execute(ObservableEmitter<List<Product>> emitter, Criteria criteria) throws Exception {
        List<Product> products = fetchProducts(criteria.offset, criteria.count);
        emitter.onNext(products);
    }

}
