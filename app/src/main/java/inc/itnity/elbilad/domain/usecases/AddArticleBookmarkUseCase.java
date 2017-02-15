package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.Bookmark;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by st1ch on 13.02.17.
 */

public class AddArticleBookmarkUseCase extends UseCase<Bookmark> {

  private ElbiladRepository elbiladRepository;

  private Article article;

  @Inject AddArticleBookmarkUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository elbiladRepository) {
    super(subscribeOn, observeOn);
    this.elbiladRepository = elbiladRepository;
  }

  public void setArticle(Article article) {
    this.article = article;
  }

  @Override protected Observable<Bookmark> getUseCaseObservable() {
    return elbiladRepository.addToBookmark(new Bookmark(article));
  }
}
