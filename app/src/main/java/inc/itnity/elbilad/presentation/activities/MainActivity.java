package inc.itnity.elbilad.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import inc.itnity.elbilad.R;

public class MainActivity extends AppCompatActivity {

  private final String TAG = this.getClass().getSimpleName();

  @Nullable @BindView(R.id.toolbar) protected Toolbar toolbar;

  @Nullable @BindView(R.id.iv_refresh) protected ImageView ivRefresh;

  @Nullable @BindView(R.id.iv_menu) protected ImageView ivMenu;

  @Nullable @BindView(R.id.tv_title_text) protected TextView tvTitle;

  @Nullable @BindView(R.id.drawer_layout) DrawerLayout mDrawer;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);

    initToolbar();
    initDrawer();
  }

  private void initDrawer() {

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

  private void openCloseDrawer() {
    if (mDrawer != null) {
      if (mDrawer.isDrawerOpen(GravityCompat.END)) {
        mDrawer.closeDrawer(GravityCompat.END);
      } else {
        mDrawer.openDrawer(GravityCompat.END);
      }
    }
  }

  public void initToolbar() {
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      setTitle(null);
    }
  }
}
