package io.adev.test_task.data.base;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;

public abstract class ConsequenceAdapter<T, C> extends AbsObservableFactory<T, C> {

    private static final String TAG = "ConsequenceAdapter";

    public ConsequenceAdapter(Scheduler workScheduler) {
        super(workScheduler);
    }

    @Override
    protected final Observable<T> buildObservable(final C criteria) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {

                try {
                    execute(emitter, criteria);
                    emitter.onComplete();
                } catch (Throwable e) {
                    if (!emitter.isDisposed()) {
                        emitter.onError(e);
                    } else {
                        Log.e(TAG, Log.getStackTraceString(e));
                    }
                }

            }
        });
    }

    protected abstract void execute(ObservableEmitter<T> emitter, C criteria) throws Exception;

}
