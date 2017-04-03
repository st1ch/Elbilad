package inc.itnity.elbilad.presentation.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;

/**
 * Created by Artem Getman on 03.04.17.
 * a.e.getman@gmail.com
 */

public class SettingsFragment extends PreferenceFragmentCompat {

  public static SettingsFragment newInstance() {
    return new SettingsFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    ((AbstractBaseActivity) getActivity()).showHomeToolbar();
    ((AbstractBaseActivity) getActivity()).hideTitleLogo();
    ((AbstractBaseActivity) getActivity()).setTitleToolBar(getString(R.string.settings));
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    addPreferencesFromResource(R.xml.preferences);
  }
}
