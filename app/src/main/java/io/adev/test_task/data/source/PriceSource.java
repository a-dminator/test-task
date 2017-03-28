package io.adev.test_task.data.source;

import io.adev.test_task.data.base.ConsequenceAdapter;
import io.adev.test_task.data.entity.PriceWithSku;
import io.reactivex.ObservableEmitter;

import static io.adev.test_task.data.cloud.PriceFetch.fetchPrice;
import static io.reactivex.schedulers.Schedulers.io;

public class PriceSource extends ConsequenceAdapter<PriceWithSku, PriceSource.Criteria> {

    public PriceSource() {
        super(io());
    }

    public static class Criteria {
        public final String sku;
        public Criteria(String sku) {
            this.sku = sku;
        }
    }

    @Override
    protected void execute(ObservableEmitter<PriceWithSku> emitter, Criteria criteria) throws Exception {
        PriceWithSku priceWithSku = fetchPrice(criteria.sku);
        emitter.onNext(priceWithSku);
    }

}
