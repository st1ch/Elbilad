package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.buses.RefreshTabRxBus;
import inc.itnity.elbilad.domain.models.article.ArticleMostRead;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.GetMostReadNewsUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.MostReadNewsView;
import java.util.List;

/**
 * Created by st1ch on 16.01.17.
 */

public class MostReadNewsPresenterImpl extends ProgressConnectionPresenter<MostReadNewsView>
    implements MostReadNewsPresenter {

  private GetMostReadNewsUseCase mostReadNewsUseCase;

  private RefreshTabRxBus refreshTabRxBus;
  private BaseUseCaseSubscriber<Boolean> refreshTabSubscriber;

  public MostReadNewsPresenterImpl(GetMostReadNewsUseCase getMostReadNewsUseCase,
      RefreshTabRxBus refreshTabRxBus) {
    this.mostReadNewsUseCase = getMostReadNewsUseCase;
    this.refreshTabRxBus = refreshTabRxBus;
  }

  @Override public void onCreate() {
    try {
      checkViewBound();
      checkConnection();

      mostReadNewsUseCase.setRefresh(true);
      mostReadNewsUseCase.execute(articlesSubscriber());
    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();
      mostReadNewsUseCase.setRefresh(false);
      mostReadNewsUseCase.execute(articlesSubscriber());
    }

    if (refreshTabSubscriber == null) {
      refreshTabSubscriber = refreshTabSubscriber();
      refreshTabRxBus.getOpenTabObservable().subscribe(refreshTabSubscriber);
    }
  }

  @Override public void onDestroy() {
    mostReadNewsUseCase.unsubscribe();
    if(refreshTabSubscriber != null && !refreshTabSubscriber.isUnsubscribed()){
      refreshTabSubscriber.unsubscribe();
      refreshTabSubscriber = null;
    }
    super.onDestroy();
  }

  private BaseProgressSubscriber<List<ArticleMostRead>> articlesSubscriber() {
    return new BaseProgressSubscriber<List<ArticleMostRead>>(this) {
      @Override public void onNext(List<ArticleMostRead> articles) {
        super.onNext(articles);
        try {
          checkViewBound();

          getView().showArticles(articles);
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
