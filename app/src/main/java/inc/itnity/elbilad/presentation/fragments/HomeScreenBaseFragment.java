package inc.itnity.elbilad.presentation.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.adapters.HomeScreenPagerAdapter;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;

/**
 * Created by st1ch on 14.01.17.
 */

public class HomeScreenBaseFragment extends AbstractBaseFragment {

  @BindView(R.id.viewpager) ViewPager viewPager;

  @BindView(R.id.tab_layout) TabLayout tabLayout;

  public static HomeScreenBaseFragment newInstance() {
    return new HomeScreenBaseFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View fragmentView = super.onCreateView(inflater, container, savedInstanceState);

    initContent();
    return fragmentView;
  }

  @Override public int getContentView() {
    return R.layout.fragment_home_screen;
  }

  @Override public void injectComponent() {
    MainActivity.getMainActivityComponent().inject(this);
  }

  @Override protected void bindPresenter() {
  }

  @Override protected void unbindPresenter() {
  }

  private void initContent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }

    /** init tabs */
    viewPager.setAdapter(new HomeScreenPagerAdapter(getActivity(), getChildFragmentManager()));
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    viewPager.setOffscreenPageLimit(tabLayout.getTabCount());

    /** fixing scrollable tab layout full width problem */
    ViewGroup slidingTabStrip = (ViewGroup) tabLayout.getChildAt(0);
    for (int i = 0; i < tabLayout.getTabCount(); i++) {
      View tab = slidingTabStrip.getChildAt(i);
      LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tab.getLayoutParams();
      layoutParams.weight = 1;
      tab.setLayoutParams(layoutParams);
    }

  }
}
