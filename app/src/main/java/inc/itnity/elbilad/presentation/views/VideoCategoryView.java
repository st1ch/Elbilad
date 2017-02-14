package inc.itnity.elbilad.presentation.views;

import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.presentation.views.base.ConnectionView;
import java.util.List;

/**
 * Created by st1ch on 14.02.17.
 */

public interface VideoCategoryView extends ConnectionView {
  void showVideos(List<Video> videos);
}
