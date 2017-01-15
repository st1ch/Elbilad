package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by st1ch on 15.01.17.
 */

public class GetArticlesUseCase extends UseCase<List<Article>> {

  private ElbiladRepository repository;

  private boolean refresh;

  @Inject
  GetArticlesUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository repository) {
    super(subscribeOn, observeOn);
    this.repository = repository;
  }

  public void setRefresh(boolean refresh) {
    this.refresh = refresh;
  }

  @Override protected Observable<List<Article>> getUseCaseObservable() {
    return repository.getArticles(refresh);
  }
}
