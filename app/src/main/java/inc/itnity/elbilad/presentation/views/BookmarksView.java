package inc.itnity.elbilad.presentation.views;

import inc.itnity.elbilad.domain.models.article.Bookmark;
import inc.itnity.elbilad.presentation.views.base.ProgressView;
import java.util.List;

/**
 * Created by st1ch on 14.02.17.
 */

public interface BookmarksView extends ProgressView {
  void showBookmarks(List<Bookmark> bookmarks);
  void showEmptyView();
}
