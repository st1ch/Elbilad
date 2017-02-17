package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.presentation.presenters.base.IPresenter;
import inc.itnity.elbilad.presentation.views.VideoDetailsView;

/**
 * Created by st1ch on 15.02.17.
 */

public interface VideoDetailsPresenter extends IPresenter<VideoDetailsView> {
  void onCreate(String videoId, boolean isArticle);

  void addToBookmarks(Video video);
}
