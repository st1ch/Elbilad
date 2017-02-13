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
import inc.itnity.elbilad.domain.models.categorie.Category;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;
import inc.itnity.elbilad.presentation.adapters.HomeScreenPagerAdapter;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.BaseHomePresenter;
import inc.itnity.elbilad.presentation.views.BaseHomeView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 14.01.17.
 */

public class HomeScreenBaseFragment extends AbstractBaseFragment implements BaseHomeView {

  @BindView(R.id.viewpager) ViewPager viewPager;

  @BindView(R.id.tab_layout) TabLayout tabLayout;

  @Inject BaseHomePresenter presenter;

  private HomeScreenPagerAdapter homeScreenPagerAdapter;

  public static HomeScreenBaseFragment newInstance() {
    return new HomeScreenBaseFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    ((AbstractBaseActivity) getActivity()).showHomeToolbar();
    ((AbstractBaseActivity) getActivity()).showTitleLogo();

    View fragmentView = super.onCreateView(inflater, container, savedInstanceState);

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

  private void initContent(List<Category> categories) {
    tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

    /** init tabs */
    homeScreenPagerAdapter =
        new HomeScreenPagerAdapter(getActivity(), getChildFragmentManager(), categories);

    viewPager.setAdapter(homeScreenPagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    viewPager.setOffscreenPageLimit(3);

    /** fixing scrollable tab layout full width problem */
    ViewGroup slidingTabStrip = (ViewGroup) tabLayout.getChildAt(0);
    for (int i = 0; i < tabLayout.getTabCount(); i++) {
      View tab = slidingTabStrip.getChildAt(i);
      LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tab.getLayoutParams();
      layoutParams.weight = 1;
      tab.setLayoutParams(layoutParams);
    }

  }

  @Override public void openTab(int position) {
    if (homeScreenPagerAdapter.getCount() > position) {
      viewPager.setCurrentItem(position);
    }
  }

  @Override public void showLoadedCategories(List<Category> categories) {
    initContent(categories);
  }
}
