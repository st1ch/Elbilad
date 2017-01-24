package inc.itnity.elbilad.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;

public class MainActivity extends AbstractBaseActivity {

  private final String TAG = this.getClass().getSimpleName();

  @Nullable @BindView(R.id.drawer_layout) DrawerLayout mDrawer;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    fragmentNavigator.startHomeScreenFragment(0);
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

  @OnClick({ R.id.iv_refresh, R.id.iv_menu }) protected void onToolbarItemClick(View view) {
    switch (view.getId()) {
      case R.id.iv_refresh:
        break;
      case R.id.iv_menu:
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
