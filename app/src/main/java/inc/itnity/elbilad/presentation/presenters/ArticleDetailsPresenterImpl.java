package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.usecases.GetArticleUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.ArticleDetailsView;

/**
 * Created by st1ch on 05.02.17.
 */

public class ArticleDetailsPresenterImpl extends ProgressConnectionPresenter<ArticleDetailsView>
    implements ArticleDetailsPresenter {

  private GetArticleUseCase getArticleUseCase;

  public ArticleDetailsPresenterImpl(GetArticleUseCase getArticleUseCase) {
    this.getArticleUseCase = getArticleUseCase;
  }

  @Override public void onCreate(String articleId) {
    try {
      checkViewBound();
      checkConnection();

      getArticleUseCase.setRefresh(false);
      getArticleUseCase.setArticleId(articleId);
      getArticleUseCase.execute(articleSubscriber());
    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();
      getArticleUseCase.setRefresh(false);
      getArticleUseCase.setArticleId(articleId);
      getArticleUseCase.execute(articleSubscriber());
    }
  }

  @Override public void onDestroy() {
    getArticleUseCase.unsubscribe();
    super.onDestroy();
  }

  private BaseProgressSubscriber<Article> articleSubscriber() {
    return new BaseProgressSubscriber<Article>(this) {
      @Override public void onNext(Article article) {
        super.onNext(article);

        try {
          checkViewBound();

          getView().showArticle(article);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }
}
