package inc.itnity.elbilad.presentation.views;

import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.presentation.views.base.ConnectionView;
import java.util.List;

/**
 * Created by st1ch on 16.01.17.
 */

public interface HomeScreenView extends ConnectionView {

  void showLoadedArticles(List<Article> articles);

}
