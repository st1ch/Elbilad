package inc.itnity.elbilad.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import inc.itnity.elbilad.ElbiladApplication;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.di.components.DaggerSplashScreenComponent;
import inc.itnity.elbilad.di.components.SplashScreenComponent;
import inc.itnity.elbilad.di.modules.ActivityContextModule;
import inc.itnity.elbilad.presentation.presenters.SplashScreenPresenter;
import inc.itnity.elbilad.presentation.views.SplashScreenView;
import inc.itnity.elbilad.utils.DialogHelper;
import inc.itnity.elbilad.utils.ElbiladUtils;
import javax.inject.Inject;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenView {

  @BindView(R.id.progressBar) ProgressBar pbLoading;

  @Inject ElbiladUtils elbiladUtils;

  @Inject DialogHelper dialogHelper;

  @Inject SplashScreenPresenter presenter;

  private static SplashScreenComponent splashScreenComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);
    ButterKnife.bind(this);

    splashScreenComponent = DaggerSplashScreenComponent.builder()
        .applicationComponent(ElbiladApplication.getApplicationComponent())
        .activityContextModule(new ActivityContextModule(this))
        .build();

    splashScreenComponent.inject(this);

    presenter.bind(this);
    presenter.onCreate();
  }

  @Override protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

  @Override public void showDataLoaded() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  @Override public void onDataNotAvailable() {
    dialogHelper.showErrorDialog(getString(R.string.error_data_loading),
        getString(R.string.error_server_forbidden));
  }

  @Override public void showErrorDialog(String text) {
    dialogHelper.showErrorDialog(getString(R.string.error_data_loading), text);
  }

  @Override public void showErrorCheckConnectionSnackbar() {
    showSnackbar(getString(R.string.error_message_network_connection_missing));
  }

  @Override public void showProgress() {
    //dialogHelper.showProgressDialog();
    pbLoading.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    //dialogHelper.dismissDialog();
    pbLoading.setVisibility(View.INVISIBLE);
  }

  @Override public void showSnackbarMessage(String text) {
    showSnackbar(text);
  }

  @Override public boolean isNetworkOnline() {
    return elbiladUtils.isNetworkConnected();
  }

  private void showSnackbar(String message) {
    final Snackbar
        snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
    snackbar.setAction(getResources().getString(R.string.okay), view -> snackbar.dismiss());

    snackbar.show();
  }
}
