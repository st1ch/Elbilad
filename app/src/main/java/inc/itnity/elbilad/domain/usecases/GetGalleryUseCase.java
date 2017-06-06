package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.models.article.Gallery;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by Artem Getman on 06.06.17.
 * a.e.getman@gmail.com
 */

public class GetGalleryUseCase extends UseCase<Gallery> {

  private ElbiladRepository repository;

  private int id;

  @Inject GetGalleryUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository repository) {
    super(subscribeOn, observeOn);
    this.repository = repository;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override protected Observable<Gallery> getUseCaseObservable() {
    return repository.getGallery(id);
  }
}
