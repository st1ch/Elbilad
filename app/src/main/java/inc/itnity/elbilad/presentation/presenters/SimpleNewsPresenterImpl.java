package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.usecases.GetArticlesUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.SimpleNewsView;
import java.util.List;

/**
 * Created by st1ch on 16.01.17.
 */

public class SimpleNewsPresenterImpl extends ProgressConnectionPresenter<SimpleNewsView>
    implements SimpleNewsPresenter {

  private GetArticlesUseCase getArticlesUseCase;

  public SimpleNewsPresenterImpl(GetArticlesUseCase getArticlesUseCase) {
    this.getArticlesUseCase = getArticlesUseCase;
  }

  @Override public void onArticleSelected(int id) {

  }

  private BaseProgressSubscriber<List<Article>> articlesSubscriber(){
    return new BaseProgressSubscriber<List<Article>>(this){
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

  @Override public void onCreate(int categoryId) {

  }
}
