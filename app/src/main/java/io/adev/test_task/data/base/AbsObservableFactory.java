package io.adev.test_task.data.base;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public abstract class AbsObservableFactory<T, C> {

    public static Scheduler commonScheduler = Schedulers.from(newSingleThreadExecutor());
    static {
        commonScheduler.scheduleDirect(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("common-thread");
            }
        });
    }

    private final Scheduler workScheduler;
    public AbsObservableFactory(Scheduler workScheduler) {
        this.workScheduler = workScheduler;
    }

    public Observable<T> create(C criteria) {
        return buildObservable(criteria)
                .subscribeOn(workScheduler);
    }

    protected abstract Observable<T> buildObservable(C criteria);

}

