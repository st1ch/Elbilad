package inc.itnity.elbilad.data.repositories;

import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.domain.models.categorie.Category;
import java.util.List;
import rx.Observable;

/**
 * Created by st1ch on 15.01.17.
 */

public interface ElbiladRepository {

  Observable<Boolean> loadCategoriesAndHomeArticles(boolean refresh);

  Observable<List<Category>> getCategories(boolean refresh);

  Observable<HomeArticles> getHomeArticles(boolean refresh);

  Observable<List<Article>> getArticles(boolean refresh);

  Observable<List<Article>> getCategoryArticles(boolean refresh, int categoryId);

  Observable<Article> getArticle(boolean refresh, String articleId);

  Observable<List<Video>> getVideoList(boolean refresh);

  Observable<List<Article>> getLastNews(boolean refresh);

  Observable<List<Video>> getVideos(boolean refresh);

  Observable<List<Image>> getGallery(boolean refresh);

  Observable<Article> addToBookmark(Article article);

  Observable<List<Article>> getBookmarks();

}
