package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.buses.RefreshTabRxBus;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.GetCategoryArticlesUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.SimpleNewsView;
import java.util.List;

/**
 * Created by st1ch on 16.01.17.
 */

public class SimpleNewsPresenterImpl extends ProgressConnectionPresenter<SimpleNewsView>
    implements SimpleNewsPresenter {

  private GetCategoryArticlesUseCase getCategoryArticlesUseCase;

  private RefreshTabRxBus refreshTabRxBus;
  private BaseUseCaseSubscriber<Boolean> refreshTabSubscriber;

  public SimpleNewsPresenterImpl(GetCategoryArticlesUseCase getCategoryArticlesUseCase,
      RefreshTabRxBus refreshTabRxBus) {
    this.getCategoryArticlesUseCase = getCategoryArticlesUseCase;
    this.refreshTabRxBus = refreshTabRxBus;
  }

  @Override public void onCreate(int categoryId) {
    try {
      checkViewBound();
      checkConnection();

      getCategoryArticlesUseCase.setRefresh(true);
      getCategoryArticlesUseCase.setCategoryId(categoryId);
      getCategoryArticlesUseCase.execute(articlesSubscriber());
    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();
      getCategoryArticlesUseCase.setRefresh(false);
      getCategoryArticlesUseCase.setCategoryId(categoryId);
      getCategoryArticlesUseCase.execute(articlesSubscriber());
    }

    if (refreshTabSubscriber == null) {
      refreshTabSubscriber = refreshTabSubscriber(categoryId);
      refreshTabRxBus.getOpenTabObservable().subscribe(refreshTabSubscriber);
    }
  }

  @Override public void onDestroy() {
    getCategoryArticlesUseCase.unsubscribe();
    if(refreshTabSubscriber != null && !refreshTabSubscriber.isUnsubscribed()){
      refreshTabSubscriber.unsubscribe();
      refreshTabSubscriber = null;
    }
    super.onDestroy();
  }

  private BaseProgressSubscriber<List<Article>> articlesSubscriber() {
    return new BaseProgressSubscriber<List<Article>>(this) {
      @Override public void onNext(List<Article> articles) {
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

  private BaseUseCaseSubscriber<Boolean> refreshTabSubscriber(int categoryId) {
    return new BaseUseCaseSubscriber<Boolean>() {
      @Override public void onNext(Boolean aBoolean) {
        super.onNext(aBoolean);

        onCreate(categoryId);
      }
    };
  }
}
