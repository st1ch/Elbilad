package inc.itnity.elbilad.presentation.fragments;

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
import inc.itnity.elbilad.presentation.presenters.BaseHomePresenter;
import inc.itnity.elbilad.presentation.views.BaseHomeView;
import javax.inject.Inject;

/**
 * Created by st1ch on 14.01.17.
 */

public class HomeScreenBaseFragment extends AbstractBaseFragment implements BaseHomeView {

  private static final String ARG_TAB_POSITION = "tab_position";

  @BindView(R.id.viewpager) ViewPager viewPager;

  @BindView(R.id.tab_layout) TabLayout tabLayout;

  @Inject BaseHomePresenter presenter;

  //public static HomeScreenBaseFragment newInstance() {
  //  return new HomeScreenBaseFragment();
  //}

  public static HomeScreenBaseFragment newInstance(int tabPosition) {
    Bundle args = new Bundle();
    args.putInt(ARG_TAB_POSITION, tabPosition);
    HomeScreenBaseFragment fragment = new HomeScreenBaseFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View fragmentView = super.onCreateView(inflater, container, savedInstanceState);

    initContent();

    presenter.onCreate();

    return fragmentView;
  }

  @Override public int getContentView() {
    return R.layout.fragment_home_screen;
  }

  @Override public void injectComponent() {
    MainActivity.getMainActivityComponent().inject(this);
  }

  @Override protected void bindPresenter() {
    presenter.bind(this);
  }

  @Override protected void unbindPresenter() {
    presenter.onDestroy();
  }

  private void initContent() {
    tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

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

    int position = getArguments().getInt(ARG_TAB_POSITION);
    if (position != -1) {
      openTab(position);
    }
  }

  @Override public void openTab(int position) {
    if (viewPager.getChildCount() > position) {
      viewPager.setCurrentItem(position);
    }
  }
}
