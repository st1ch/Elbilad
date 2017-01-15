package inc.itnity.elbilad.data.repositories;

import inc.itnity.elbilad.data.repositories.remote.ElbiladRemoteDataSource;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.categorie.Category;
import io.reactivecache.Provider;
import io.reactivecache.ReactiveCache;
import java.util.List;
import rx.Observable;

/**
 * Created by st1ch on 15.01.17.
 */

public class ElbiladRepositoryImpl implements ElbiladRepository {

  private ElbiladRemoteDataSource remoteDataSource;
  private final Provider<List<Category>> categoryListCache;
  private final Provider<List<Article>> articleListCache;

  public ElbiladRepositoryImpl(ElbiladRemoteDataSource remoteDataSource,
      ReactiveCache reactiveCache) {
    this.remoteDataSource = remoteDataSource;
    this.categoryListCache = reactiveCache.<List<Category>>provider().withKey("categoryListCache");
    this.articleListCache = reactiveCache.<List<Article>>provider().withKey("articleListCache");
  }

  @Override public Observable<List<Category>> getCategories(boolean refresh) {
    if (refresh) {
      remoteDataSource.getCategories().compose(categoryListCache.replace());
    }
    return remoteDataSource.getCategories().compose(categoryListCache.readWithLoader());
  }

  @Override public Observable<List<Article>> getArticles(boolean refresh) {
    if (refresh) {
      remoteDataSource.getArticles().compose(articleListCache.replace());
    }
    return remoteDataSource.getArticles().compose(articleListCache.readWithLoader());
  }
}
