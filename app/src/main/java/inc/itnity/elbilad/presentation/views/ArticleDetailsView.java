package inc.itnity.elbilad.presentation.views;

import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.presentation.views.base.ConnectionView;

/**
 * Created by st1ch on 05.02.17.
 */

public interface ArticleDetailsView extends ConnectionView {
  void showArticle(Article article);
}
