package inc.itnity.elbilad.presentation.views;

import inc.itnity.elbilad.domain.models.categorie.Category;
import inc.itnity.elbilad.presentation.views.base.ConnectionView;
import java.util.List;

/**
 * Created by st1ch on 16.01.17.
 */

public interface MainMenuView extends ConnectionView {

  void showLoadedCategories(List<Category> categories);


}
