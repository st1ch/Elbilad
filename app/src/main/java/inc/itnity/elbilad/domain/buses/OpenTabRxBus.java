package inc.itnity.elbilad.domain.buses;

import inc.itnity.elbilad.di.MainActivityScope;
import javax.inject.Inject;
import rx.subjects.PublishSubject;

/**
 * Created by st1ch on 24.01.17.
 */

@MainActivityScope public class OpenTabRxBus {

  private PublishSubject<Integer> subject = PublishSubject.create();

  @Inject OpenTabRxBus() {
  }

  public void openTab(int position){
    subject.onNext(position);
  }

  public PublishSubject<Integer> getOpenTabObservable() {
    return subject;
  }
}
