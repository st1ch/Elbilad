package inc.itnity.elbilad.data.rest.api;

import inc.itnity.elbilad.domain.models.Journal;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.domain.models.categorie.Category;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by st1ch on 11.01.17.
 */

public interface ElbiladAPI {

  String ARTICLE = "/article";
  String CATEGORIE = "/categorie";

  String HOME_ARTICLES = "/home-requests";
  String LAST_6_NEWS = "/bloc?type=last";
  String LAST_NEWS = "/flash";
  String VIDEOS = "/video";
  String GALLERY = "/galerie";
  String PDF = "/pdf";

  String CATEGORIE_ID = "categorie_id";
  String ARTICLE_ID = "article_id";
  String PATH_ARTICLE_ID = "/{" + ARTICLE_ID + "}";

  String LIMIT = "limit";

  @GET(CATEGORIE) Observable<List<Category>> getCategories();

  @GET(HOME_ARTICLES) Observable<HomeArticles> getHomeArticles();

  @GET(ARTICLE) Observable<List<Article>> getArticles();

  @GET(ARTICLE) Observable<List<Article>> getCategoryArticles(@Query(CATEGORIE_ID) int categoryId);

  @GET(ARTICLE + PATH_ARTICLE_ID) Observable<List<Article>> getArticle(
      @Path(ARTICLE_ID) String articleId);

  @GET(LAST_NEWS) Observable<List<Article>> getLastNews();

  @GET(ARTICLE + LAST_6_NEWS) Observable<List<Article>> getLastNews(@Query(LIMIT) int limit);

  @GET(VIDEOS) Observable<List<Video>> getVideos();

  @GET(GALLERY) Observable<List<Image>> getGallery();

  @GET(PDF) Observable<Journal> getJournalData();
}
