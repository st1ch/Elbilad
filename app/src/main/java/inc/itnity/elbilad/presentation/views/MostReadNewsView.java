package inc.itnity.elbilad.presentation.views;

import inc.itnity.elbilad.domain.models.article.ArticleMostRead;
import inc.itnity.elbilad.presentation.views.base.ConnectionView;
import java.util.List;

/**
 * Created by st1ch on 26.01.17.
 */

public interface MostReadNewsView extends ConnectionView {

  void showArticles(List<ArticleMostRead> articles);

}
