package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.presentation.presenters.base.IPresenter;
import inc.itnity.elbilad.presentation.views.PhotoDetailsView;

/**
 * Created by st1ch on 15.02.17.
 */

public interface PhotoDetailsPresenter extends IPresenter<PhotoDetailsView> {
  void onCreate();

  void addToBookmarks(Image image);
}
