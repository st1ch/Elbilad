package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.buses.RefreshTabRxBus;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.GetHomeArticlesUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.HomeScreenView;

/**
 * Created by st1ch on 16.01.17.
 */

public class HomeScreenPresenterImpl extends ProgressConnectionPresenter<HomeScreenView>
    implements HomeScreenPresenter {

  private GetHomeArticlesUseCase getHomeArticlesUseCase;

  private RefreshTabRxBus refreshTabRxBus;
  private BaseUseCaseSubscriber<Boolean> refreshTabSubscriber;

  public HomeScreenPresenterImpl(GetHomeArticlesUseCase getHomeArticlesUseCase,
      RefreshTabRxBus refreshTabRxBus) {
    this.getHomeArticlesUseCase = getHomeArticlesUseCase;
    this.refreshTabRxBus = refreshTabRxBus;
  }

  @Override public void onCreate() {
    try {
      checkViewBound();
      checkConnection();

      getHomeArticlesUseCase.setRefresh(true);
      getHomeArticlesUseCase.execute(articlesSubscriber());
    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();
      getHomeArticlesUseCase.setRefresh(true);
      getHomeArticlesUseCase.execute(articlesSubscriber());
    }

    if (refreshTabSubscriber == null) {
      refreshTabSubscriber = refreshTabSubscriber();
      refreshTabRxBus.getOpenTabObservable().subscribe(refreshTabSubscriber);
    }
  }

  @Override public void onDestroy() {
    getHomeArticlesUseCase.unsubscribe();
    if(refreshTabSubscriber != null && !refreshTabSubscriber.isUnsubscribed()){
      refreshTabSubscriber.unsubscribe();
      refreshTabSubscriber = null;
    }
    super.onDestroy();
  }

  private BaseProgressSubscriber<HomeArticles> articlesSubscriber() {
    return new BaseProgressSubscriber<HomeArticles>(this) {
      @Override public void onNext(HomeArticles articles) {
        super.onNext(articles);
        try {
          checkViewBound();

          getView().showLoadedArticles(articles);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseUseCaseSubscriber<Boolean> refreshTabSubscriber() {
    return new BaseUseCaseSubscriber<Boolean>() {
      @Override public void onNext(Boolean aBoolean) {
        super.onNext(aBoolean);

        onCreate();
      }
    };
  }
}
