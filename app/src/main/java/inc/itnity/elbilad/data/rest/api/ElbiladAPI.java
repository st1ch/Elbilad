package inc.itnity.elbilad.data.rest.api;

import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.ArticleData;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.models.categorie.CategoryData;
import inc.itnity.elbilad.domain.models.responses.BaseResponse;
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

  String CATEGORIE_ID = "categorie_id";
  String ARTICLE_ID = "article_id";
  String PATH_ARTICLE_ID = "/{" + ARTICLE_ID + "}";

  @GET(CATEGORIE) Observable<BaseResponse<CategoryData>> getCategories();

  @GET(HOME_ARTICLES) Observable<HomeArticles> getHomeArticles();

  @GET(ARTICLE) Observable<BaseResponse<ArticleData>> getArticles();

  @GET(ARTICLE) Observable<BaseResponse<ArticleData>> getCategoryArticles(
      @Query(CATEGORIE_ID) int categoryId);

  @GET(ARTICLE + PATH_ARTICLE_ID) Observable<Article> getArticle(
      @Path(ARTICLE_ID) int articleId);
}
