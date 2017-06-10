package inc.itnity.elbilad.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.fragments.ArticleDetailsFragment;
import inc.itnity.elbilad.presentation.fragments.HomeScreenBaseFragment;
import inc.itnity.elbilad.presentation.fragments.PhotoDetailsFragment;
import inc.itnity.elbilad.presentation.fragments.PreferencesFragment;
import inc.itnity.elbilad.presentation.fragments.VideoDetailsFragment;
import inc.itnity.elbilad.presentation.fragments.categories.BookmarksFragment;
import inc.itnity.elbilad.presentation.fragments.categories.CategoryNewsFragment;
import inc.itnity.elbilad.presentation.fragments.categories.HomeFragment;
import inc.itnity.elbilad.presentation.fragments.categories.LastNewsFragment;
import inc.itnity.elbilad.presentation.fragments.categories.PhotosFragment;
import inc.itnity.elbilad.presentation.fragments.categories.VideosFragment;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by st1ch on 14.01.17.
 */
@Singleton public class FragmentNavigator {

  @Inject public FragmentNavigator() {
  }

  public interface NavigationListener {
    void onBackstackChanged();
  }

  private FragmentManager fragmentManager;

  private NavigationListener navigationListener;

  public void init(FragmentManager fragmentManager) {
    this.fragmentManager = fragmentManager;
    this.fragmentManager.addOnBackStackChangedListener(() -> {
      if (navigationListener != null) {
        navigationListener.onBackstackChanged();
      }
    });
  }

  public boolean isRootFragmentVisible() {
    return fragmentManager.getBackStackEntryCount() <= 1;
  }

  public void setNavigationListener(NavigationListener navigationListener) {
    this.navigationListener = navigationListener;
  }

  /**
   * Displays the next fragment
   */
  private void open(Fragment fragment) {
    if (fragmentManager != null) {
      String tag = fragment.getClass().getName();
      //Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);

      //if (fragmentByTag != null && fragmentByTag.getClass().equals(fragment.getClass())) {
      //  fragmentManager.popBackStackImmediate(tag, 0);
      //} else {
      fragmentManager.beginTransaction()
          .replace(R.id.content_main, fragment, tag)
          .addToBackStack(tag)
          .commit();
      //}
    }
  }

  private void openAdd(Fragment fragment) {
    if (fragmentManager != null) {
      String tag = fragment.getClass().getName();
      //Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);

      //if (fragmentByTag != null && fragmentByTag.getClass().equals(fragment.getClass())) {
      //  fragmentManager.popBackStackImmediate(tag, 0);
      //} else {
      fragmentManager.beginTransaction()
          .add(R.id.content_main, fragment, tag)
          .addToBackStack(tag)
          .commit();
      //}
    }
  }

  /**
   * Pops every fragment and starts the given fragment as a new one
   */
  private void openAsRoot(Fragment fragment) {
    popEveryFragment();
    open(fragment);
  }

  private void openAsChildRoot(Fragment fragment) {
    popUntilLastFragment();
    open(fragment);
  }

  /**
   * Pops all the queued fragments
   */
  private void popEveryFragment() {
    while (fragmentManager.getBackStackEntryCount() != 0) {
      fragmentManager.popBackStackImmediate();
    }
  }

  private void popUntilLastFragment() {
    int backStackCount = fragmentManager.getBackStackEntryCount();
    for (int i = backStackCount - 1; i > 0; i--) {
      int backStackId = fragmentManager.getBackStackEntryAt(i).getId();
      fragmentManager.popBackStackImmediate(backStackId, 0);
    }
  }

  private void popUntilTwoLastFragment() {
    int backStackCount = fragmentManager.getBackStackEntryCount();
    for (int i = backStackCount - 1; i > 1; i--) {
      int backStackId = fragmentManager.getBackStackEntryAt(i).getId();
      fragmentManager.popBackStackImmediate(backStackId, 0);
    }
  }

  /**
   * Navigates back by popping back stack. If there is no more items left we finish the current
   * activity.
   */
  public void navigateBack(Activity baseActivity) {
    if (fragmentManager.getBackStackEntryCount() == 1) {
      baseActivity.finish();
    } else {
      fragmentManager.popBackStackImmediate();
    }
  }

  public void restoreRootFragment() {
    popUntilLastFragment();
  }

  /**
   * Methods for root fragments
   */

  public void startHomeScreenFragment() {
    Fragment fragment = HomeScreenBaseFragment.newInstance();
    openAsRoot(fragment);
  }

  /**
   * Methods for NOT root fragments
   */

  public void startArticleDetailsFragment(boolean isFlash, String id) {
    Fragment fragment = ArticleDetailsFragment.newInstance(isFlash, id);
    open(fragment);
  }

  public void startCategoryFragment(int categoryId, String categoryName) {
    Fragment fragment = CategoryNewsFragment.newInstance(categoryId, categoryName);
    openAsChildRoot(fragment);
  }

  public void startHomeFragment() {
    Fragment fragment = HomeFragment.newInstance();
    openAsChildRoot(fragment);
  }

  public void startBookmarksFragment() {
    Fragment fragment = BookmarksFragment.newInstance();
    openAsChildRoot(fragment);
  }

  public void startLastNewsFragment() {
    Fragment fragment = LastNewsFragment.newInstance();
    openAsChildRoot(fragment);
  }

  public void startVideosFragment(String id) {
    Fragment fragment = VideosFragment.newInstance(id);
    openAsChildRoot(fragment);
  }

  public void startVideoDetailsFragment(String videoId, boolean isArticle) {
    Fragment fragment = VideoDetailsFragment.newInstance(videoId, isArticle);
    open(fragment);
  }

  public void startPhotosFragment(String id) {
    Fragment fragment = PhotosFragment.newInstance(id);
    openAsChildRoot(fragment);
  }

  public void startPhotoDetailsragment(int galleryId) {
    Fragment fragment = PhotoDetailsFragment.newInstance(galleryId);
    openAdd(fragment);
  }

  public void startPreferencesFragment() {
    Fragment fragment = PreferencesFragment.newInstance();
    open(fragment);
  }
}
