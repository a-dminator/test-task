package io.adev.test_task;

import android.app.Application;

import io.adev.test_task.dagger.ApplicationModule;

public class App extends Application {
    public static ApplicationModule applicationModule = new ApplicationModule();
}
