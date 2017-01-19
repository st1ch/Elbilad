package inc.itnity.elbilad.presentation.fragments;

import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.views.HomeScreenView;
import java.util.List;

/**
 * Created by st1ch on 18.01.17.
 */

public class HomeFragment extends AbstractBaseFragment implements HomeScreenView {

  public static HomeFragment newInstance() {
    return new HomeFragment();
  }

  @Override public int getContentView() {
    return R.layout.fragment_home;
  }

  @Override public void injectComponent() {
    MainActivity.getMainActivityComponent().inject(this);
  }

  @Override protected void bindPresenter() {

  }

  @Override protected void unbindPresenter() {

  }

  @Override public void showLoadedArticles(List<Article> articles) {

  }
}
