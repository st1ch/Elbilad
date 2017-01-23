package inc.itnity.elbilad.presentation.fragments.categories;

import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;

/**
 * Created by st1ch on 18.01.17.
 */

public class BookmarksFragment extends AbstractBaseFragment {

  public static BookmarksFragment newInstance() {
    return new BookmarksFragment();
  }

  @Override public int getContentView() {
    return R.layout.fragment_bookmarks;
  }

  @Override public void injectComponent() {
    MainActivity.getMainActivityComponent().inject(this);
  }

  @Override protected void bindPresenter() {

  }

  @Override protected void unbindPresenter() {

  }
}
