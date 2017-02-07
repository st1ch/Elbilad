package inc.itnity.elbilad.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.fragments.ArticleDetailsFragment;
import inc.itnity.elbilad.presentation.fragments.HomeScreenBaseFragment;
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
      Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);

      if (fragmentByTag != null && fragmentByTag.getClass().equals(fragment.getClass())) {
        fragmentManager.popBackStackImmediate(tag, 0);
      } else {
        fragmentManager.beginTransaction()
            .replace(R.id.content_main, fragment, tag)
            .addToBackStack(tag)
            .commit();
      }
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
    // clear all back stack
    int backStackCount = fragmentManager.getBackStackEntryCount();
    for (int i = 0; i < backStackCount; i++) {
      int backStackId = fragmentManager.getBackStackEntryAt(i).getId();
      fragmentManager.popBackStackImmediate(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
  }

  private void popUntilLastFragment() {
    int backStackCount = fragmentManager.getBackStackEntryCount();
    for (int i = backStackCount - 1; i > 0; i--) {
      int backStackId = fragmentManager.getBackStackEntryAt(i).getId();
      fragmentManager.popBackStackImmediate(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
  }

  private void popUntilTwoLastFragment() {
    int backStackCount = fragmentManager.getBackStackEntryCount();
    for (int i = backStackCount - 1; i > 1; i--) {
      int backStackId = fragmentManager.getBackStackEntryAt(i).getId();
      fragmentManager.popBackStackImmediate(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
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

  public void restoreRootFragment(){
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

  public void startArticleDetailsFragment(int id) {
    Fragment fragment = ArticleDetailsFragment.newInstance(id);
    openAsChildRoot(fragment);
  }
}
