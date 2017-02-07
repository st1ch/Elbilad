package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.usecases.GetHomeArticlesUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.HomeScreenView;

/**
 * Created by st1ch on 16.01.17.
 */

public class HomeScreenPresenterImpl extends ProgressConnectionPresenter<HomeScreenView>
    implements HomeScreenPresenter {

  private GetHomeArticlesUseCase getHomeArticlesUseCase;

  public HomeScreenPresenterImpl(GetHomeArticlesUseCase getHomeArticlesUseCase) {
    this.getHomeArticlesUseCase = getHomeArticlesUseCase;
  }

  @Override public void onCreate() {
    getHomeArticlesUseCase.setRefresh(false);
    getHomeArticlesUseCase.execute(articlesSubscriber());
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
}
