package inc.itnity.elbilad.presentation.views;

import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.presentation.views.base.ConnectionView;

/**
 * Created by st1ch on 15.02.17.
 */

public interface VideoDetailsView extends ConnectionView {
  void showVideo(Video video);

  void showAddedToBookmarks();
}
