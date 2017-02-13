package inc.itnity.elbilad.data.repositories;

import inc.itnity.elbilad.data.repositories.remote.ElbiladRemoteDataSource;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.domain.models.categorie.Category;
import io.reactivecache.Provider;
import io.reactivecache.ProviderGroup;
import io.reactivecache.ReactiveCache;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;

/**
 * Created by st1ch on 15.01.17.
 */

public class ElbiladRepositoryImpl implements ElbiladRepository {

  private ElbiladRemoteDataSource remoteDataSource;
  private final Provider<List<Category>> categoryListCache;
  private final Provider<List<Article>> articleListCache;
  private final Provider<List<Article>> lastNewsCache;
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
        reactiveCache.<List<Article>>providerGroup().lifeCache(1, TimeUnit.DAYS)
            .withKey("categoryArticleListCache");
    this.articleCache =
        reactiveCache.<Article>providerGroup().lifeCache(1, TimeUnit.DAYS).withKey("articleCache");
    this.lastNewsCache = reactiveCache.<List<Article>>provider().withKey("lastNewsCache");
  }

  @Override public Observable<Boolean> loadCategoriesAndHomeArticles(boolean refresh) {
    if (refresh) {
      return Observable.zip(remoteDataSource.getCategories().compose(categoryListCache.replace()),
          remoteDataSource.getHomeArticles().compose(homeArticlesCache.replace()),
          (categories, articles) -> true);
    }
    return Observable.zip(
        remoteDataSource.getCategories().compose(categoryListCache.readWithLoader()),
        remoteDataSource.getHomeArticles().compose(homeArticlesCache.readWithLoader()),
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
      return remoteDataSource.getCategoryArticles(categoryId)
          .compose(categoryArticleListCache.replace(categoryId));
    }
    return remoteDataSource.getCategoryArticles(categoryId)
        .compose(categoryArticleListCache.readWithLoader(categoryId));
  }

  @Override public Observable<Article> getArticle(boolean refresh, String articleId) {
    if (refresh) {
      return remoteDataSource.getArticle(articleId).compose(articleCache.replace(articleId));
    }
    return remoteDataSource.getArticle(articleId).compose(articleCache.readWithLoader(articleId));
  }

  @Override public Observable<List<Video>> getVideoList(boolean refresh) {
    if (refresh) {
      return getHomeArticles(true).map(HomeArticles::getVideos);
    }
    return getHomeArticles(false).map(HomeArticles::getVideos);
  }

  @Override public Observable<List<Article>> getLastNews(boolean refresh) {
    if (refresh) {
      return remoteDataSource.getLastNews().compose(lastNewsCache.replace());
    }
    return remoteDataSource.getLastNews().compose(lastNewsCache.readWithLoader());
  }
}
