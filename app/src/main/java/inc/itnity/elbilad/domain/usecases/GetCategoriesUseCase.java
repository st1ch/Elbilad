package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.models.categorie.Category;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by st1ch on 15.01.17.
 */

public class GetCategoriesUseCase extends UseCase<List<Category>> {

  private ElbiladRepository repository;

  private boolean refresh;

  @Inject
  GetCategoriesUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository repository) {
    super(subscribeOn, observeOn);
    this.repository = repository;
  }

  public void setRefresh(boolean refresh) {
    this.refresh = refresh;
  }

  @Override protected Observable<List<Category>> getUseCaseObservable() {
    return repository.getCategories(refresh);
  }
}
