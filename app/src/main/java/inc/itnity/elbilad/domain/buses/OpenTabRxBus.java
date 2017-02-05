package inc.itnity.elbilad.domain.buses;

import inc.itnity.elbilad.di.MainActivityScope;
import javax.inject.Inject;
import rx.subjects.BehaviorSubject;

/**
 * Created by st1ch on 24.01.17.
 */

@MainActivityScope public class OpenTabRxBus {

  private BehaviorSubject<Integer> subject = BehaviorSubject.create();

  @Inject OpenTabRxBus() {
  }

  public void openTab(int position){
    subject.onNext(position);
  }

  public void clearPosition(){
    subject.onNext(null);
  }

  public BehaviorSubject<Integer> getOpenTabObservable() {
    return subject;
  }
}
