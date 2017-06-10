package inc.itnity.elbilad.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;

public class MainActivity extends AbstractBaseActivity {

  private final String TAG = this.getClass().getSimpleName();

  @Nullable @BindView(R.id.drawer_layout) DrawerLayout mDrawer;
  @Nullable @BindView(R.id.nested_scroll_view) NestedScrollView mainMenuScrollView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    String pushArticle = preferenceHelper.getArticleId();
    if (!TextUtils.isEmpty(pushArticle)) {
      fragmentNavigator.startArticleDetailsFragment(false, pushArticle);
    } else {
      fragmentNavigator.startHomeScreenFragment();
    }

    if (mDrawer != null) {
      mDrawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
        //@Override public void onDrawerOpened(View drawerView) {
        //  super.onDrawerOpened(drawerView);
          //if (mainMenuScrollView != null) {
          //  mainMenuScrollView.scrollTo(0, 0);
          //}
        //}

        @Override public void onDrawerClosed(View drawerView) {
          super.onDrawerClosed(drawerView);
          if (mainMenuScrollView != null) {
            mainMenuScrollView.scrollTo(0, 0);
          }
        }
      });
    }

  }

  @Override protected void onDestroy() {
    preferenceHelper.clearArticleId();
    super.onDestroy();
  }

  @Override public void onBackPressed() {
    if (mDrawer != null && mDrawer.isDrawerOpen(GravityCompat.END)) {
      mDrawer.closeDrawer(GravityCompat.END);
    } else {
      fragmentNavigator.navigateBack(this);
    }
  }

  @Override public int getContentView() {
    return R.layout.activity_main;
  }

  @OnClick({ R.id.iv_refresh, R.id.iv_menu, R.id.iv_back, R.id.iv_menu_details })
  protected void onToolbarItemClick(View view) {
    switch (view.getId()) {
      case R.id.iv_back:
        onBackPressed();
        break;
      case R.id.iv_menu:
        openCloseDrawer();
        break;
      case R.id.iv_menu_details:
        openCloseDrawer();
        break;
    }
  }

  public void openCloseDrawer() {
    if (mDrawer != null) {
      if (mDrawer.isDrawerOpen(GravityCompat.END)) {
        mDrawer.closeDrawer(GravityCompat.END);
      } else {
        mDrawer.openDrawer(GravityCompat.END);
      }
    }
  }
}
