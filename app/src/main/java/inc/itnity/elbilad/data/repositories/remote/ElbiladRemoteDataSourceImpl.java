package inc.itnity.elbilad.data.repositories.remote;

import inc.itnity.elbilad.constants.ApiConfig;
import inc.itnity.elbilad.data.rest.api.ElbiladAPI;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.models.categorie.Category;
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
    return elbiladAPI.getCategories();
  }

  @Override public Observable<HomeArticles> getHomeArticles() {
    return elbiladAPI.getHomeArticles();
  }

  @Override public Observable<List<Article>> getArticles() {
    return elbiladAPI.getArticles();
  }

  @Override public Observable<List<Article>> getCategoryArticles(int categoryId) {
    return elbiladAPI.getCategoryArticles(categoryId);
  }

  @Override public Observable<Article> getArticle(String articleId) {
    return elbiladAPI.getArticle(articleId).map(article -> article.get(0));
  }

  @Override public Observable<List<Article>> getLastNews() {
    return elbiladAPI.getLastNews(ApiConfig.LAST_NEWS_LIMIT);
  }
}
