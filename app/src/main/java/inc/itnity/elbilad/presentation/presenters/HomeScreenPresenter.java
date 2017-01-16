package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.presentation.presenters.base.IPresenter;
import inc.itnity.elbilad.presentation.views.HomeScreenView;

/**
 * Created by st1ch on 16.01.17.
 */

public interface HomeScreenPresenter extends IPresenter<HomeScreenView> {
  void onCreate();
  void onArticleSelected(int id);
}
