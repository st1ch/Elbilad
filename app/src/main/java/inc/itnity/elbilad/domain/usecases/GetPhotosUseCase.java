package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by st1ch on 14.02.17.
 */

public class GetPhotosUseCase extends UseCase<List<Image>> {

  private ElbiladRepository elbiladRepository;

  private boolean refresh;

  @Inject GetPhotosUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository elbiladRepository) {
    super(subscribeOn, observeOn);
    this.elbiladRepository = elbiladRepository;
  }

  public void setRefresh(boolean refresh) {
    this.refresh = refresh;
  }

  @Override protected Observable<List<Image>> getUseCaseObservable() {
    return elbiladRepository.getGallery(refresh);
  }
}
