package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.usecases.GetLastNewsUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.LastNewsView;
import java.util.List;

/**
 * Created by st1ch on 16.01.17.
 */

public class LastNewsPresenterImpl extends ProgressConnectionPresenter<LastNewsView>
    implements LastNewsPresenter {

  private GetLastNewsUseCase getLastNewsUseCase;

  public LastNewsPresenterImpl(GetLastNewsUseCase getLastNewsUseCase) {
    this.getLastNewsUseCase = getLastNewsUseCase;
  }

  @Override public void onCreate() {
    try {
      checkViewBound();
      checkConnection();

      getLastNewsUseCase.setRefresh(false);
      getLastNewsUseCase.execute(articlesSubscriber());
    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();
      getLastNewsUseCase.setRefresh(false);
      getLastNewsUseCase.execute(articlesSubscriber());
    }
  }

  @Override public void onDestroy() {
    getLastNewsUseCase.unsubscribe();
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
}
