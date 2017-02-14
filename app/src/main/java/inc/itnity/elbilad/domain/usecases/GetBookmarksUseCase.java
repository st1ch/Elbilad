package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.models.article.ArticleItem;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by st1ch on 13.02.17.
 */

public class GetBookmarksUseCase extends UseCase<List<ArticleItem>> {

  private ElbiladRepository elbiladRepository;

  @Inject GetBookmarksUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository elbiladRepository) {
    super(subscribeOn, observeOn);
    this.elbiladRepository = elbiladRepository;
  }

  @Override protected Observable<List<ArticleItem>> getUseCaseObservable() {
    return elbiladRepository.getBookmarks();
  }
}
