package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by st1ch on 05.02.17.
 */

public class GetArticleUseCase extends UseCase<Article> {

  private ElbiladRepository elbiladRepository;

  private boolean refresh;
  private int articleId;

  @Inject
  GetArticleUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository elbiladRepository) {
    super(subscribeOn, observeOn);
    this.elbiladRepository = elbiladRepository;
  }

  public void setRefresh(boolean refresh) {
    this.refresh = refresh;
  }

  public void setArticleId(int articleId) {
    this.articleId = articleId;
  }

  @Override protected Observable<Article> getUseCaseObservable() {
    return elbiladRepository.getArticle(refresh, articleId);
  }
}
