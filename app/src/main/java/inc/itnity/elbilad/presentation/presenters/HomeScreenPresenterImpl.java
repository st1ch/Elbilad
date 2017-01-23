package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.usecases.GetArticlesUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.HomeScreenView;
import java.util.List;

/**
 * Created by st1ch on 16.01.17.
 */

public class HomeScreenPresenterImpl extends ProgressConnectionPresenter<HomeScreenView>
    implements HomeScreenPresenter {

  private GetArticlesUseCase getArticlesUseCase;

  public HomeScreenPresenterImpl(GetArticlesUseCase getArticlesUseCase) {
    this.getArticlesUseCase = getArticlesUseCase;
  }

  @Override public void onCreate() {
    getArticlesUseCase.setRefresh(false);
    getArticlesUseCase.execute(articlesSubscriber());
  }

  @Override public void onArticleSelected(int id) {

  }

  private BaseProgressSubscriber<List<Article>> articlesSubscriber(){
    return new BaseProgressSubscriber<List<Article>>(this){
      @Override public void onNext(List<Article> articles) {
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
