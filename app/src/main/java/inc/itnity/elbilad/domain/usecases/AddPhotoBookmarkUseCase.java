package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.models.article.Bookmark;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by st1ch on 13.02.17.
 */

public class AddPhotoBookmarkUseCase extends UseCase<Bookmark> {

  private ElbiladRepository elbiladRepository;

  private Image photo;

  @Inject AddPhotoBookmarkUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository elbiladRepository) {
    super(subscribeOn, observeOn);
    this.elbiladRepository = elbiladRepository;
  }

  public void setPhoto(Image photo) {
    this.photo = photo;
  }

  @Override protected Observable<Bookmark> getUseCaseObservable() {
    return elbiladRepository.addToBookmark(new Bookmark(photo));
  }
}
