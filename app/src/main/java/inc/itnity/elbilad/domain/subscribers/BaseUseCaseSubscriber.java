package inc.itnity.elbilad.domain.subscribers;

import android.util.Log;
import rx.Subscriber;

/**
 * Created by alexeyverbitskiy on 10/31/16.
 */

public class BaseUseCaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e("Exception", String.valueOf(e));
    }

    @Override
    public void onNext(T t) {

    }
}
