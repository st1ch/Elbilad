package inc.itnity.elbilad.presentation.views;

import inc.itnity.elbilad.domain.models.article.Gallery;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.presentation.views.base.ConnectionView;
import java.util.List;

/**
 * Created by st1ch on 14.02.17.
 */

public interface PhotoCategoryView extends ConnectionView {
  void showPhotos(List<Image> images);

  void showGallery(Gallery gallery);
}
