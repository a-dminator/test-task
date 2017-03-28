package io.adev.test_task.data.base;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;

public class UseCase<T, C> {

    private final AbsObservableFactory<T, C> source;
    public UseCase(AbsObservableFactory<T, C> source) {
        this.source = source;
    }

    private DisposableObserver<T> observer;

    public void execute(DisposableObserver<T> observer, C criteria) {
        this.observer = source.create(criteria)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }

    public void dispose() {
        if (!observer.isDisposed()) {
            observer.dispose();
        }
    }

}
