package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by st1ch on 04.02.17.
 */

public class GetCategoryArticlesUseCase extends UseCase<List<Article>> {

  private ElbiladRepository elbiladRepository;

  private int categoryId;
  private boolean refresh;


  @Inject
  GetCategoryArticlesUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository elbiladRepository) {
    super(subscribeOn, observeOn);
    this.elbiladRepository = elbiladRepository;
  }

  public void setRefresh(boolean refresh) {
    this.refresh = refresh;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  @Override protected Observable<List<Article>> getUseCaseObservable() {
    return elbiladRepository.getCategoryArticles(refresh, categoryId);
  }
}
