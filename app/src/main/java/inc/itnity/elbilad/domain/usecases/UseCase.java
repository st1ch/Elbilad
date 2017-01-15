package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by alexeyverbitskiy on 10/31/16.
 */

public abstract class UseCase<T> {
    private SubscribeOn subscribeOn;
    private ObserveOn observeOn;
    private Subscription subscription = Subscriptions.empty();
    private Observable<T> observable;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    public UseCase(SubscribeOn subscribeOn, ObserveOn observeOn){
        this.subscribeOn = subscribeOn;
        this.observeOn = observeOn;
    }

    public void execute(Subscriber<T> subscriber){
        if(observable == null){
            observable = getUseCaseObservable()
                    .subscribeOn(subscribeOn.getScheduler())
                    .observeOn(observeOn.getScheduler())
                    .cache()
                    .doOnError(throwable -> observable = null)
                    .doOnCompleted(() -> observable = null);
            subscription = observable.subscribe(subscriber);
        }
    }

    protected abstract Observable<T> getUseCaseObservable();

    public boolean isWorking(){
        return observable != null;
    }

    void addSubscription(Subscription subscription){
        subscriptions.add(subscription);
    }

    public void unsubscribe() {
        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        if(!subscriptions.isUnsubscribed()){
            subscriptions.unsubscribe();
        }
    }
}
