package inc.itnity.elbilad.data.repositories.remote;

import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.categorie.Category;
import java.util.List;
import rx.Observable;

/**
 * Created by st1ch on 15.01.17.
 */

public interface ElbiladRemoteDataSource {

  Observable<List<Category>> getCategories();

  Observable<List<Article>> getArticles();

  Observable<List<Article>> getCategorieArticles(int categoryId);

  Observable<Article> getArticle(int articleId);
}
