package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.usecases.GetArticleUseCase;
import inc.itnity.elbilad.domain.usecases.GetLastNewsArticlesUseCase;
import inc.itnity.elbilad.domain.usecases.GetLastVideosUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.ArticleDetailsView;
import java.util.List;

/**
 * Created by st1ch on 05.02.17.
 */

public class ArticleDetailsPresenterImpl extends ProgressConnectionPresenter<ArticleDetailsView>
    implements ArticleDetailsPresenter {

  private GetArticleUseCase getArticleUseCase;
  private GetLastVideosUseCase getLastVideosUseCase;
  private GetLastNewsArticlesUseCase getLastNewsArticlesUseCase;

  public ArticleDetailsPresenterImpl(GetArticleUseCase getArticleUseCase,
      GetLastVideosUseCase getLastVideosUseCase,
      GetLastNewsArticlesUseCase getLastNewsArticlesUseCase) {
    this.getArticleUseCase = getArticleUseCase;
    this.getLastVideosUseCase = getLastVideosUseCase;
    this.getLastNewsArticlesUseCase = getLastNewsArticlesUseCase;
  }

  @Override public void onCreate(String articleId) {
    try {
      checkViewBound();
      checkConnection();

      getArticleUseCase.setRefresh(false);
      getArticleUseCase.setArticleId(articleId);
      getArticleUseCase.execute(articleSubscriber());

      getLastVideosUseCase.setRefresh(false);
      getLastVideosUseCase.execute(videosSubscriber());

      getLastNewsArticlesUseCase.setRefresh(false);
      getLastNewsArticlesUseCase.execute(lastNewsSubscriber());
    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();
      getArticleUseCase.setRefresh(false);
      getArticleUseCase.setArticleId(articleId);
      getArticleUseCase.execute(articleSubscriber());

      getLastVideosUseCase.setRefresh(false);
      getLastVideosUseCase.execute(videosSubscriber());

      getLastNewsArticlesUseCase.setRefresh(false);
      getLastNewsArticlesUseCase.execute(lastNewsSubscriber());
    }
  }

  @Override public void onDestroy() {
    getArticleUseCase.unsubscribe();
    getLastNewsArticlesUseCase.unsubscribe();
    getLastVideosUseCase.unsubscribe();
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

  private BaseProgressSubscriber<List<Video>> videosSubscriber() {
    return new BaseProgressSubscriber<List<Video>>(this) {
      @Override public void onNext(List<Video> videos) {
        super.onNext(videos);

        try {
          checkViewBound();

          getView().showVideoNews(videos);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseProgressSubscriber<List<Article>> lastNewsSubscriber() {
    return new BaseProgressSubscriber<List<Article>>(this) {
      @Override public void onNext(List<Article> articles) {
        super.onNext(articles);

        try {
          checkViewBound();

          getView().showLastNews(articles);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }
}
