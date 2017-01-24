package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.buses.OpenTabRxBus;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.presentation.presenters.base.BasePresenter;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.BaseHomeView;

/**
 * Created by st1ch on 16.01.17.
 */

public class BaseHomePresenterImpl extends ProgressConnectionPresenter<BaseHomeView>
    implements BaseHomePresenter {

  private OpenTabRxBus openTabRxBus;

  public BaseHomePresenterImpl(OpenTabRxBus openTabRxBus) {
    this.openTabRxBus = openTabRxBus;
  }

  @Override public void onCreate() {
    openTabRxBus.getOpenTabObservable().subscribe(openTabSubscriber());
  }

  private BaseUseCaseSubscriber<Integer> openTabSubscriber() {
    return new BaseUseCaseSubscriber<Integer>() {
      @Override public void onNext(Integer integer) {
        super.onNext(integer);

        try {
          checkViewBound();

          getView().openTab(integer);
        } catch (BasePresenter.ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

}
