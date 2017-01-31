package inc.itnity.elbilad.presentation.fragments.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.adapters.PhotoSlidePagerAdapter;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import javax.inject.Inject;

/**
 * Created by st1ch on 18.01.17.
 */

public class BookmarksFragment extends AbstractBaseFragment {

  public static BookmarksFragment newInstance() {
    return new BookmarksFragment();
  }

  @BindView(R.id.viewpager) ViewPager viewPager;

  @Inject PhotoSlidePagerAdapter photoSlidePagerAdapter;

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

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootVIew = super.onCreateView(inflater, container, savedInstanceState);

    viewPager.setAdapter(photoSlidePagerAdapter);

    return rootVIew;
  }
}
