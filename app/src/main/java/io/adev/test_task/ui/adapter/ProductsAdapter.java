package io.adev.test_task.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.adev.test_task.R;
import io.adev.test_task.data.entity.ProductWithPrice;

import static java.util.Collections.emptyList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductHolder> {

    private List<ProductWithPrice> data = emptyList();
    public void setData(List<ProductWithPrice> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        ProductWithPrice product = data.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
