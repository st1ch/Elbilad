package inc.itnity.elbilad.data.repositories;

import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.categorie.Category;
import java.util.List;
import rx.Observable;

/**
 * Created by st1ch on 15.01.17.
 */

public interface ElbiladRepository {

  Observable<Boolean> loadCategoriesAndArticles(boolean refresh);

  Observable<List<Category>> getCategories(boolean refresh);

  Observable<List<Article>> getArticles(boolean refresh);

  Observable<List<Article>> getCategoryArticles(boolean refresh, int categoryId);

  Observable<Article> getArticle(boolean refresh, int articleId);
}
