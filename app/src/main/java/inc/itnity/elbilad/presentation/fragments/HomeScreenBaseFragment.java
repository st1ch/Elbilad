package inc.itnity.elbilad.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;
import inc.itnity.elbilad.presentation.adapters.HomeScreenPagerAdapter;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.BaseHomePresenter;
import inc.itnity.elbilad.presentation.views.BaseHomeView;
import javax.inject.Inject;

/**
 * Created by st1ch on 14.01.17.
 */

public class HomeScreenBaseFragment extends AbstractBaseFragment implements BaseHomeView {

  @BindView(R.id.viewpager) ViewPager viewPager;

  @BindView(R.id.tab_layout) TabLayout tabLayout;

  @Inject BaseHomePresenter presenter;

  private TabBadgeViewHolder homeTabViewHolder;
  private TabBadgeViewHolder lastNewsTabViewHolder;
  private TabBadgeViewHolder mostReadTabViewHolder;
  private TabBadgeViewHolder bookmarksTabViewHolder;

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

    initContent();

    //presenter.onCreate();

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
    //homeScreenPagerAdapter =
    //    new HomeScreenPagerAdapter(getActivity(), getChildFragmentManager(), categories);
    homeScreenPagerAdapter =
        new HomeScreenPagerAdapter(getActivity(), getChildFragmentManager());

    viewPager.setAdapter(homeScreenPagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    viewPager.setOffscreenPageLimit(tabLayout.getTabCount());

    /** init tab typeface*/
    View homeTabView = LayoutInflater.from(getContext()).inflate(R.layout.tab_layout, null);
    View lastNewsTabView = LayoutInflater.from(getContext()).inflate(R.layout.tab_layout, null);
    View mostReadTabView = LayoutInflater.from(getContext()).inflate(R.layout.tab_layout, null);
    View bookmarksTabView = LayoutInflater.from(getContext()).inflate(R.layout.tab_layout, null);
    tabLayout.getTabAt(0).setCustomView(homeTabView);
    tabLayout.getTabAt(1).setCustomView(lastNewsTabView);
    tabLayout.getTabAt(2).setCustomView(mostReadTabView);
    tabLayout.getTabAt(3).setCustomView(bookmarksTabView);

    homeTabViewHolder = new TabBadgeViewHolder(homeTabView);
    homeTabViewHolder.title.setText(getString(R.string.home));

    lastNewsTabViewHolder = new TabBadgeViewHolder(lastNewsTabView);
    lastNewsTabViewHolder.title.setText(getString(R.string.last_news));

    mostReadTabViewHolder = new TabBadgeViewHolder(mostReadTabView);
    mostReadTabViewHolder.title.setText(getString(R.string.news_most_read));

    bookmarksTabViewHolder = new TabBadgeViewHolder(bookmarksTabView);
    bookmarksTabViewHolder.title.setText(getString(R.string.bookmarks));


    /** fixing scrollable tab layout full width problem */
    ViewGroup slidingTabStrip = (ViewGroup) tabLayout.getChildAt(0);
    for (int i = 0; i < tabLayout.getTabCount(); i++) {
      View tab = slidingTabStrip.getChildAt(i);
      LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tab.getLayoutParams();
      layoutParams.weight = 1;
      tab.setLayoutParams(layoutParams);
    }

  }

  //@Override public void openTab(int position) {
  //  if (homeScreenPagerAdapter.getCount() > position) {
  //    viewPager.setCurrentItem(position);
  //  }
  //}

  //@Override public void showLoadedCategories(List<Category> categories) {
  //  initContent(categories);
  //}

  protected class TabBadgeViewHolder {
    @BindView(R.id.tv_tab_badge_title) TextView title;

    TabBadgeViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}
