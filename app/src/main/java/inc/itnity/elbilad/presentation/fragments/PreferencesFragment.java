package inc.itnity.elbilad.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnItemSelected;
import com.onesignal.OneSignal;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.data.sync.SyncAdapterManager;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;
import inc.itnity.elbilad.presentation.adapters.SettingsOfflineUpdateTimeAdapter;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.utils.PreferenceHelper;
import javax.inject.Inject;
import org.json.JSONException;

/**
 * Created by Artem Getman on 04.04.17.
 * a.e.getman@gmail.com
 */

public class PreferencesFragment extends AbstractBaseFragment {

  public static PreferencesFragment newInstance() {
    return new PreferencesFragment();
  }

  @BindView(R.id.sc_offline_mode) SwitchCompat scOfflineMode;
  @BindView(R.id.sc_push_notifications) SwitchCompat scPushNotifications;
  @BindView(R.id.sp_offline_update_time) Spinner spOfflineUpdateTime;

  @Inject SettingsOfflineUpdateTimeAdapter settingsOfflineUpdateTimeAdapter;

  @Inject PreferenceHelper preferenceHelper;

  @Inject SyncAdapterManager syncAdapterManager;

  private boolean offlineEnabled;
  private boolean pushEnabled;
  private int offlineUpdateInterval;

  @Override public int getContentView() {
    return R.layout.fragment_preferences;
  }

  @Override public void injectComponent() {
    MainActivity.getMainActivityComponent().inject(this);
  }

  @Override protected void bindPresenter() {

  }

  @Override protected void unbindPresenter() {

  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    ((AbstractBaseActivity) getActivity()).showHomeToolbar();
    ((AbstractBaseActivity) getActivity()).hideTitleLogo();
    ((AbstractBaseActivity) getActivity()).hideRefreshHomeToolbar();
    ((AbstractBaseActivity) getActivity()).setTitleToolBar(getString(R.string.settings));

    View rootView = super.onCreateView(inflater, container, savedInstanceState);

    initContent();

    return rootView;
  }

  private void initContent() {
    spOfflineUpdateTime.setAdapter(settingsOfflineUpdateTimeAdapter);

    offlineEnabled = preferenceHelper.isOfflineModeEnabled();
    pushEnabled = preferenceHelper.isPushNotificationsEnabled();
    offlineUpdateInterval = preferenceHelper.getSyncIntervalHours();

    scOfflineMode.setChecked(offlineEnabled);
    scPushNotifications.setChecked(pushEnabled);
    spOfflineUpdateTime.setSelection(
        settingsOfflineUpdateTimeAdapter.getItemPosition(offlineUpdateInterval));

    checkOfflineEnabled();
  }

  @Override public void onDestroyView() {
    ((AbstractBaseActivity) getActivity()).showRefreshHomeToolbar();
    preferenceHelper.setPushNotifiactionsEnabled(pushEnabled);
    preferenceHelper.setOfflineModeEnabled(offlineEnabled);
    preferenceHelper.setSyncIntervalHours(offlineUpdateInterval);
    super.onDestroyView();
  }

  @OnItemSelected(R.id.sp_offline_update_time) void onUpdateTimeSelected(int position) {
    offlineUpdateInterval =
        Integer.valueOf(settingsOfflineUpdateTimeAdapter.getTimeFromPosition(position));
    preferenceHelper.setSyncIntervalHours(offlineUpdateInterval);
  }

  @OnCheckedChanged(R.id.sc_offline_mode) public void OnChangedOfflineMode(boolean checked) {
    offlineEnabled = checked;
    preferenceHelper.setOfflineModeEnabled(checked);
    checkOfflineEnabled();

    if (checked) {
      syncAdapterManager.beginPeriodicSync();
    } else {
      syncAdapterManager.removeSync();
    }
  }

  @OnCheckedChanged(R.id.sc_push_notifications)
  public void OnChangedPushNotificationMode(boolean checked) {
    pushEnabled = checked;
    preferenceHelper.setPushNotifiactionsEnabled(checked);

    if (checked) {
      OneSignal.startInit(getActivity().getApplicationContext())
          .setNotificationReceivedHandler(notification -> {
            try {
              Log.wtf("RECEIVED",
                  "notificationReceived: " + notification.payload.additionalData.getString(
                      "article_id"));
            } catch (JSONException e) {
              e.printStackTrace();
            }
          })
          .setNotificationOpenedHandler(result -> {
            try {
              String article_id =
                  result.notification.payload.additionalData.getString("article_id");
              Log.wtf("OPENED", "notificationOpened: " + article_id);

              preferenceHelper.setArticleId(article_id);
            } catch (JSONException e) {
              e.printStackTrace();
            }
          })
          .init();
      OneSignal.setSubscription(true);
    } else {
      OneSignal.startInit(getActivity().getApplicationContext()).init();
      OneSignal.setSubscription(false);
    }
  }

  private void checkOfflineEnabled() {
    if (offlineEnabled) {
      spOfflineUpdateTime.setEnabled(true);
      spOfflineUpdateTime.setAlpha(1f);
    } else {
      spOfflineUpdateTime.setEnabled(false);
      spOfflineUpdateTime.setAlpha(0.5f);
    }
  }
}
