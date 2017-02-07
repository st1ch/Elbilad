package inc.itnity.elbilad.data.repositories.remote;

import inc.itnity.elbilad.data.rest.api.ElbiladAPI;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.models.categorie.Category;
import inc.itnity.elbilad.domain.observables.BaseResponseObservable;
import java.util.List;
import rx.Observable;

/**
 * Created by st1ch on 15.01.17.
 */

public class ElbiladRemoteDataSourceImpl implements ElbiladRemoteDataSource {

  private ElbiladAPI elbiladAPI;

  public ElbiladRemoteDataSourceImpl(ElbiladAPI elbiladAPI) {
    this.elbiladAPI = elbiladAPI;
  }

  @Override public Observable<List<Category>> getCategories() {
    return elbiladAPI.getCategories()
        .flatMap(BaseResponseObservable::new)
        .map(categoryDataBaseResponse -> categoryDataBaseResponse.getData().getCategories());
  }

  @Override public Observable<HomeArticles> getHomeArticles() {
    return elbiladAPI.getHomeArticles();
  }

  @Override public Observable<List<Article>> getArticles() {
    return elbiladAPI.getArticles()
        .flatMap(BaseResponseObservable::new)
        .map(articleDataBaseResponse -> articleDataBaseResponse.getData().getArticles());
  }

  @Override public Observable<List<Article>> getCategorieArticles(int categoryId) {
    return elbiladAPI.getCategoryArticles(categoryId)
        .flatMap(BaseResponseObservable::new)
        .map(articleDataBaseResponse -> articleDataBaseResponse.getData().getArticles());
  }

  @Override public Observable<Article> getArticle(int articleId) {
    return elbiladAPI.getArticle(articleId);
  }
}
