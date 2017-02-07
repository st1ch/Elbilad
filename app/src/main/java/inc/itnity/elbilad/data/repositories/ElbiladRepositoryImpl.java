package inc.itnity.elbilad.data.repositories;

import inc.itnity.elbilad.data.repositories.remote.ElbiladRemoteDataSource;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.models.categorie.Category;
import io.reactivecache.Provider;
import io.reactivecache.ProviderGroup;
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
  private final Provider<HomeArticles> homeArticlesCache;
  private final ProviderGroup<List<Article>> categoryArticleListCache;
  private final ProviderGroup<Article> articleCache;

  public ElbiladRepositoryImpl(ElbiladRemoteDataSource remoteDataSource,
      ReactiveCache reactiveCache) {
    this.remoteDataSource = remoteDataSource;
    this.categoryListCache = reactiveCache.<List<Category>>provider().withKey("categoryListCache");
    this.articleListCache = reactiveCache.<List<Article>>provider().withKey("articleListCache");
    this.homeArticlesCache = reactiveCache.<HomeArticles>provider().withKey("homeArticlesCache");
    this.categoryArticleListCache =
        reactiveCache.<List<Article>>providerGroup().withKey("categoryArticleListCache");
    this.articleCache = reactiveCache.<Article>providerGroup().withKey("articleCache");
  }

  @Override public Observable<Boolean> loadCategoriesAndArticles(boolean refresh) {
    if (refresh) {
      return Observable.zip(remoteDataSource.getCategories().compose(categoryListCache.replace()),
          remoteDataSource.getArticles().compose(articleListCache.replace()),
          (categories, articles) -> true);
    }
    return Observable.zip(
        remoteDataSource.getCategories().compose(categoryListCache.readWithLoader()),
        remoteDataSource.getArticles().compose(articleListCache.readWithLoader()),
        (categories, articles) -> true);
  }

  @Override public Observable<List<Category>> getCategories(boolean refresh) {
    if (refresh) {
      return remoteDataSource.getCategories().compose(categoryListCache.replace());
    }
    return remoteDataSource.getCategories().compose(categoryListCache.readWithLoader());
  }

  @Override public Observable<HomeArticles> getHomeArticles(boolean refresh) {
    if (refresh) {
      return remoteDataSource.getHomeArticles().compose(homeArticlesCache.replace());
    }
    return remoteDataSource.getHomeArticles().compose(homeArticlesCache.readWithLoader());
  }

  @Override public Observable<List<Article>> getArticles(boolean refresh) {
    if (refresh) {
      return remoteDataSource.getArticles().compose(articleListCache.replace());
    }
    return remoteDataSource.getArticles().compose(articleListCache.readWithLoader());
  }

  @Override public Observable<List<Article>> getCategoryArticles(boolean refresh, int categoryId) {
    if (refresh) {
      return remoteDataSource.getCategorieArticles(categoryId)
          .compose(categoryArticleListCache.replace(categoryId));
    }
    return remoteDataSource.getCategorieArticles(categoryId)
        .compose(categoryArticleListCache.readWithLoader(categoryId));
  }

  @Override public Observable<Article> getArticle(boolean refresh, int articleId) {
    if (refresh) {
      return remoteDataSource.getArticle(articleId).compose(articleCache.replace(articleId));
    }
    return remoteDataSource.getArticle(articleId).compose(articleCache.readWithLoader(articleId));
  }
}
