package inc.itnity.elbilad.presentation.activities.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import inc.itnity.elbilad.ElbiladApplication;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.di.components.DaggerMainActivityComponent;
import inc.itnity.elbilad.di.components.MainActivityComponent;
import inc.itnity.elbilad.di.modules.ActivityContextModule;
import inc.itnity.elbilad.utils.FragmentNavigator;
import javax.inject.Inject;

public abstract class AbstractBaseActivity extends AppCompatActivity implements
    FragmentNavigator.NavigationListener {

  @Nullable @BindView(R.id.toolbar) protected Toolbar toolbar;

  @Nullable @BindView(R.id.tv_title_text) protected TextView tvTitleText;

  @Nullable @BindView(R.id.iv_title_logo) protected ImageView ivTitleLogo;

  @Nullable @BindView(R.id.iv_refresh) protected ImageView ivRefresh;

  @Nullable @BindView(R.id.iv_menu) protected ImageView ivMenu;

  @Inject protected FragmentNavigator fragmentNavigator;

  private static MainActivityComponent mainActivityComponent;

  public static MainActivityComponent getMainActivityComponent() {
    return mainActivityComponent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mainActivityComponent = DaggerMainActivityComponent.builder()
        .applicationComponent(ElbiladApplication.getApplicationComponent())
        .activityContextModule(new ActivityContextModule(this))
        .build();

    setContentView(getContentView());
    ButterKnife.bind(this);
    mainActivityComponent.inject(this);

    initToolbar();
    initFragmentNavigator();
  }

  private void initFragmentNavigator() {
    fragmentNavigator.init(getSupportFragmentManager());
    fragmentNavigator.setNavigationListener(this);
  }

  /** Need for bind butter knife in all activities */
  public abstract int getContentView();

  public void initToolbar() {
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      setTitle(null);
    }
  }

  public void setTitleToolBar(String title) {
    if (toolbar != null && tvTitleText != null) {
      tvTitleText.setText(title);
    }
  }

  public void showTitleLogo() {
    if (tvTitleText != null && ivTitleLogo != null) {
      tvTitleText.setVisibility(View.GONE);
      ivTitleLogo.setVisibility(View.VISIBLE);
    }
  }

  public void hideTitleLogo() {
    if (tvTitleText != null && ivTitleLogo != null) {
      tvTitleText.setVisibility(View.VISIBLE);
      ivTitleLogo.setVisibility(View.GONE);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {

    int id = item.getItemId();

    if (id == android.R.id.home) {
      finish();

      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void onBackstackChanged() {

  }
}
