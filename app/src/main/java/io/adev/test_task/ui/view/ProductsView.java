package io.adev.test_task.ui.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import java.util.List;

import io.adev.test_task.R;
import io.adev.test_task.data.entity.ProductWithPrice;
import io.adev.test_task.presenter.contract.ProductsContract;
import io.adev.test_task.ui.adapter.ProductsAdapter;

public class ProductsView extends FrameLayout implements ProductsContract.View {

    private ProductsContract.Presenter presenter;
    public void setPresenter(ProductsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private ProductsAdapter adapter = new ProductsAdapter();

    public RecyclerView rvProducts;

    public ProductsView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_products, this, true);

        rvProducts = (RecyclerView) findViewById(R.id.rvProducts);
        rvProducts.setLayoutManager(new GridLayoutManager(context, 2));
        rvProducts.setAdapter(adapter);
    }

    @Override
    public void displayProductsWithPrices(List<ProductWithPrice> data) {
        adapter.setData(data);
    }

    @Override
    public void displayError() {

    }

}
