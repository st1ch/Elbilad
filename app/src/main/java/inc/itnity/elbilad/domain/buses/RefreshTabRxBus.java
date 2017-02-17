package inc.itnity.elbilad.domain.buses;

import inc.itnity.elbilad.di.MainActivityScope;
import javax.inject.Inject;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by st1ch on 24.01.17.
 */

@MainActivityScope public class RefreshTabRxBus {

  private PublishSubject<Boolean> subject = PublishSubject.create();

  @Inject RefreshTabRxBus() {
  }

  public void refreshTab() {
    subject.onNext(true);
  }

  public Observable<Boolean> getOpenTabObservable() {
    return subject;
  }
}
