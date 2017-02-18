package inc.itnity.elbilad.presentation.views;

import inc.itnity.elbilad.domain.models.Journal;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.presentation.views.base.ConnectionView;

/**
 * Created by st1ch on 16.01.17.
 */

public interface HomeScreenView extends ConnectionView {

  void showLoadedArticles(HomeArticles articles);

  void showJournal(Journal journal);
}
