package io.adev.test_task.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import io.adev.test_task.dagger.DaggerProductsComponent;
import io.adev.test_task.dagger.ProductsComponent;
import io.adev.test_task.dagger.ProductsModule;
import io.adev.test_task.presenter.contract.ProductsContract;
import io.adev.test_task.ui.view.ProductsView;

import static io.adev.test_task.App.applicationModule;

public class ProductsActivity extends AppCompatActivity {

    @Inject ProductsContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProductsView view = new ProductsView(this);
        setContentView(view);

        ProductsComponent graph = DaggerProductsComponent.builder()
                .applicationModule(applicationModule)
                .productsModule(new ProductsModule(view))
                .build();

        graph.inject(this);

        view.setPresenter(presenter);

        presenter.init();
    }

}
