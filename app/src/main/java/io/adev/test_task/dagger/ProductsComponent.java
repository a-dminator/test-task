package io.adev.test_task.dagger;

import javax.inject.Singleton;

import dagger.Component;
import io.adev.test_task.ui.activity.ProductsActivity;

@Singleton
@Component(modules = { ApplicationModule.class, ProductsModule.class })
public interface ProductsComponent {
    void inject(ProductsActivity activity);
}
