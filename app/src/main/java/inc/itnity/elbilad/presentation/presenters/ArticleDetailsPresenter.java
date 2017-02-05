package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.presentation.presenters.base.IPresenter;
import inc.itnity.elbilad.presentation.views.ArticleDetailsView;

/**
 * Created by st1ch on 05.02.17.
 */

public interface ArticleDetailsPresenter extends IPresenter<ArticleDetailsView> {
  void onCreate(int articleId);
}
