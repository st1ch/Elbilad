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
import butterknife.OnClick;
import inc.itnity.elbilad.ElbiladApplication;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.di.components.DaggerMainActivityComponent;
import inc.itnity.elbilad.di.components.MainActivityComponent;
import inc.itnity.elbilad.di.modules.ActivityContextModule;
import inc.itnity.elbilad.domain.buses.RefreshTabRxBus;
import inc.itnity.elbilad.utils.FragmentNavigator;
import inc.itnity.elbilad.utils.PreferenceHelper;
import javax.inject.Inject;

public abstract class AbstractBaseActivity extends AppCompatActivity
    implements FragmentNavigator.NavigationListener {

  @Inject protected FragmentNavigator fragmentNavigator;

  @Inject RefreshTabRxBus refreshTabRxBus;

  @Inject protected PreferenceHelper preferenceHelper;

  private ToolbarHomeViewHolder toolbarHomeViewHolder;
  private ToolbarDetailsViewHolder toolbarDetailsViewHolder;

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

    toolbarHomeViewHolder = new ToolbarHomeViewHolder(findViewById(R.id.toolbar_home));
    toolbarDetailsViewHolder = new ToolbarDetailsViewHolder(findViewById(R.id.toolbar_details));

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
    if (toolbarHomeViewHolder.toolbar != null) {
      setSupportActionBar(toolbarHomeViewHolder.toolbar);
      setTitle(null);
    }
  }

  public void setTitleToolBar(String title) {
    if (toolbarHomeViewHolder.tvTitleText != null) {
      toolbarHomeViewHolder.tvTitleText.setText(title);
    }
  }

  public void showTitleLogo() {
    if (toolbarHomeViewHolder.tvTitleText != null && toolbarHomeViewHolder.ivTitleLogo != null) {
      toolbarHomeViewHolder.tvTitleText.setVisibility(View.GONE);
      toolbarHomeViewHolder.ivTitleLogo.setVisibility(View.VISIBLE);
    }
  }

  public void hideTitleLogo() {
    if (toolbarHomeViewHolder.tvTitleText != null && toolbarHomeViewHolder.ivTitleLogo != null) {
      toolbarHomeViewHolder.tvTitleText.setVisibility(View.VISIBLE);
      toolbarHomeViewHolder.ivTitleLogo.setVisibility(View.GONE);
    }
  }

  public void showDetailToolbar(String title) {
    if (toolbarHomeViewHolder.rootView != null && toolbarDetailsViewHolder.rootView != null) {
      setSupportActionBar(toolbarDetailsViewHolder.toolbar);
      setTitle(null);

      toolbarDetailsViewHolder.rootView.setVisibility(View.VISIBLE);
      toolbarHomeViewHolder.rootView.setVisibility(View.GONE);
      toolbarDetailsViewHolder.tvTitleText.setText(title);
    }
  }

  public void showHomeToolbar() {
    if (toolbarHomeViewHolder.rootView != null && toolbarDetailsViewHolder.rootView != null) {
      setSupportActionBar(toolbarHomeViewHolder.toolbar);
      setTitle(null);

      toolbarDetailsViewHolder.rootView.setVisibility(View.GONE);
      toolbarHomeViewHolder.rootView.setVisibility(View.VISIBLE);
    }
  }

  public void hideRefreshHomeToolbar() {
    if (toolbarHomeViewHolder.rootView != null && toolbarDetailsViewHolder.rootView != null) {
      toolbarHomeViewHolder.ivRefresh.setVisibility(View.GONE);
    }
  }

  public void showRefreshHomeToolbar() {
    if (toolbarHomeViewHolder.rootView != null && toolbarDetailsViewHolder.rootView != null) {
      toolbarHomeViewHolder.ivRefresh.setVisibility(View.VISIBLE);
    }
  }

  //public void showToolbar(){
  //  toolbarDetailsViewHolder.rootView.setVisibility(View.GONE);
  //  toolbarHomeViewHolder.rootView.setVisibility(View.GONE);
  //}

  public void hideToolbar() {
    toolbarDetailsViewHolder.rootView.setVisibility(View.GONE);
    toolbarHomeViewHolder.rootView.setVisibility(View.GONE);
  }

  public void setOnBookmarkClickListener(View.OnClickListener onBookmarkClickListener) {
    toolbarDetailsViewHolder.ivBookmark.setOnClickListener(onBookmarkClickListener);
  }

  public void setOnShareArticleClickListener(View.OnClickListener onShareArticleClickListener) {
    toolbarDetailsViewHolder.ivShare.setOnClickListener(onShareArticleClickListener);
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

  public void showArticleBookmarked() {
    toolbarDetailsViewHolder.ivBookmark.setImageResource(R.drawable.ic_bookmark_gold);
  }

  public void showArticleNOTBookmarked() {
    toolbarDetailsViewHolder.ivBookmark.setImageResource(R.drawable.ic_bookmark);
  }

  class ToolbarHomeViewHolder {

    View rootView;
    @Nullable @BindView(R.id.toolbar) protected Toolbar toolbar;
    @Nullable @BindView(R.id.tv_title_text) protected TextView tvTitleText;
    @Nullable @BindView(R.id.iv_title_logo) protected ImageView ivTitleLogo;
    @Nullable @BindView(R.id.iv_refresh) protected ImageView ivRefresh;
    @Nullable @BindView(R.id.iv_menu) protected ImageView ivMenu;

    ToolbarHomeViewHolder(View view) {
      ButterKnife.bind(this, view);
      rootView = view;
    }

    @OnClick(R.id.iv_refresh) void onRefreshClick() {
      refreshTabRxBus.refreshTab();
    }
  }

  class ToolbarDetailsViewHolder {

    View rootView;
    @Nullable @BindView(R.id.toolbar) protected Toolbar toolbar;
    @Nullable @BindView(R.id.tv_title_text) protected TextView tvTitleText;
    @Nullable @BindView(R.id.iv_back) protected ImageView ivBack;
    @Nullable @BindView(R.id.iv_share_toolbar) protected ImageView ivShare;
    @Nullable @BindView(R.id.iv_bookmark_toolbar) protected ImageView ivBookmark;
    @Nullable @BindView(R.id.iv_menu_details) protected ImageView ivMenu;

    ToolbarDetailsViewHolder(View view) {
      ButterKnife.bind(this, view);
      rootView = view;
    }
  }
}
