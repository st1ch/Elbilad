package inc.itnity.elbilad.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.categorie.Category;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.adapters.MenuCategoryAdapter;
import inc.itnity.elbilad.presentation.custom.SimpleDividerItemLineDecoration;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.MainMenuPresenter;
import inc.itnity.elbilad.presentation.views.MainMenuView;
import inc.itnity.elbilad.utils.FragmentNavigator;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 14.01.17.
 */

public class MainMenuFragment extends AbstractBaseFragment implements MainMenuView {

  @BindView(R.id.rv_menu_categories) RecyclerView rvCategories;

  @Inject FragmentNavigator fragmentNavigator;

  @Inject MenuCategoryAdapter menuCategoryAdapter;

  @Inject MainMenuPresenter presenter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View fragmentView = super.onCreateView(inflater, container, savedInstanceState);

    initContent();

    presenter.onCreate();

    return fragmentView;
  }

  private void initContent() {
    rvCategories.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvCategories.addItemDecoration(new SimpleDividerItemLineDecoration(getActivity()));
    rvCategories.setNestedScrollingEnabled(false);
    rvCategories.setAdapter(menuCategoryAdapter);
    menuCategoryAdapter.setCategoryClickListener((position, id, title) -> {
      fragmentNavigator.startCategoryFragment(id, title);
      ((MainActivity) getActivity()).openCloseDrawer();
    });
  }

  @Override public int getContentView() {
    return R.layout.fragment_main_menu;
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

  @Override public void showLoadedCategories(List<Category> categories) {
    menuCategoryAdapter.setCategories(categories);
  }

  @Override public void showMainFragment() {
    fragmentNavigator.restoreRootFragment();
  }

  @OnClick(R.id.iv_menu_arrow) protected void onMenuCloseClick() {
    ((MainActivity) getActivity()).openCloseDrawer();
  }

  @OnClick({
      R.id.tv_menu_home, R.id.tv_menu_last_news, R.id.tv_menu_hz, R.id.tv_menu_bookmarks,
      R.id.tv_menu_videos, R.id.tv_menu_photos, R.id.tv_menu_about, R.id.tv_menu_preferences
  }) public void onMenuItemsClick(View view) {
    switch (view.getId()) {
      case R.id.tv_menu_home:
        fragmentNavigator.startHomeFragment();
        break;
      case R.id.tv_menu_last_news:
        fragmentNavigator.startLastNewsFragment();
        break;
      case R.id.tv_menu_bookmarks:
        fragmentNavigator.startBookmarksFragment();
        break;
      case R.id.tv_menu_videos:
        fragmentNavigator.startVideosFragment();
        break;
      case R.id.tv_menu_photos:
        fragmentNavigator.startPhotosFragment();
        break;
      case R.id.tv_menu_about:
        break;
      case R.id.tv_menu_preferences:
        break;
      default:
        break;
    }
    ((MainActivity) getActivity()).openCloseDrawer();
  }
}

