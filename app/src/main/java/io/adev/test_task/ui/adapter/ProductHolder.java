package io.adev.test_task.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import io.adev.test_task.R;
import io.adev.test_task.data.entity.ProductWithPrice;

public class ProductHolder extends RecyclerView.ViewHolder {

    private final TextView tvDescription;
    private final TextView tvCoast;
    public ProductHolder(View itemView) {
        super(itemView);
        tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
        tvCoast       = (TextView) itemView.findViewById(R.id.tvCoast);
    }

    public void bind(ProductWithPrice productWithPrice) {
        tvDescription.setText(productWithPrice.product.description);
        tvCoast.setText(String.valueOf(productWithPrice.price));
    }

}
