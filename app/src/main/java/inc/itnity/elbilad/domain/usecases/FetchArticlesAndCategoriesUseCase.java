package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by st1ch on 17.01.17.
 */

public class FetchArticlesAndCategoriesUseCase extends UseCase<Boolean>{

  private ElbiladRepository elbiladRepository;

  private boolean refresh;

  @Inject
  FetchArticlesAndCategoriesUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository elbiladRepository) {
    super(subscribeOn, observeOn);
    this.elbiladRepository = elbiladRepository;
  }

  public void setRefresh(boolean refresh) {
    this.refresh = refresh;
  }

  @Override protected Observable<Boolean> getUseCaseObservable() {
    return elbiladRepository.loadCategoriesAndArticles(refresh);
  }
}
