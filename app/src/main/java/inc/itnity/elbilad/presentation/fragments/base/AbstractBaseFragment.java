package inc.itnity.elbilad.presentation.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.views.base.ConnectionView;
import inc.itnity.elbilad.utils.DialogHelper;
import inc.itnity.elbilad.utils.ElbiladUtils;
import inc.itnity.elbilad.utils.UIHelper;
import javax.inject.Inject;

/**
 * Created by st1ch on 16.01.17.
 */

public abstract class AbstractBaseFragment extends Fragment implements ConnectionView {

  private final String TAG = this.getClass().getSimpleName();

  @Inject ElbiladUtils elbiladUtils;

  @Inject UIHelper uiHelper;

  @Inject DialogHelper dialogHelper;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injectComponent();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View fragmentView = inflater.inflate(getContentView(), container, false);
    ButterKnife.bind(this, fragmentView);
    bindPresenter();

    return fragmentView;
  }

  @Override public void onDestroyView() {
    unbindPresenter();
    super.onDestroyView();
  }

  /** Need for bind butter knife in all fragments */
  public abstract int getContentView();

  /** Need for injecting in all fragments */
  public abstract void injectComponent();

  protected abstract void bindPresenter();

  protected abstract void unbindPresenter();


  @Override public void onDataNotAvailable() {
    dialogHelper.showErrorDialog(getString(R.string.error_data_loading),
        getString(R.string.error_server_forbidden));
  }

  @Override public void showErrorDialog(String text) {
    dialogHelper.showErrorDialog(getString(R.string.error_data_loading), text);
  }

  @Override public void showErrorCheckConnectionSnackbar() {
    uiHelper.showSnackbar(getString(R.string.error_message_network_connection_missing));
  }

  @Override public void showProgress() {
    dialogHelper.showProgressDialog();
  }

  @Override public void hideProgress() {
    dialogHelper.dismissDialog();
  }

  @Override public void showSnackbarMessage(String text) {
    uiHelper.showSnackbar(text);
  }

  @Override public boolean isNetworkOnline() {
    return elbiladUtils.isNetworkConnected();
  }

}
