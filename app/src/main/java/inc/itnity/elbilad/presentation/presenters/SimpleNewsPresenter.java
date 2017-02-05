package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.presentation.presenters.base.IPresenter;
import inc.itnity.elbilad.presentation.views.SimpleNewsView;

/**
 * Created by st1ch on 26.01.17.
 */

public interface SimpleNewsPresenter extends IPresenter<SimpleNewsView> {
  void onCreate(int categoryId);
}
