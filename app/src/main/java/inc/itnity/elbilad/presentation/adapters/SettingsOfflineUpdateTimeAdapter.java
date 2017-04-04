package inc.itnity.elbilad.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import inc.itnity.elbilad.R;
import javax.inject.Inject;

/**
 * Created by st1ch on 11.11.2016.
 */

public class SettingsOfflineUpdateTimeAdapter extends BaseAdapter implements SpinnerAdapter {

  private Context context;
  private final LayoutInflater mInflater;
  private String[] updateTimeItems;
  private String[] updateTimeItemValues;

  @Inject public SettingsOfflineUpdateTimeAdapter(Context context) {
    this.context = context;
    mInflater = LayoutInflater.from(context);
    updateTimeItems = context.getResources().getStringArray(R.array.settings_update_time);
    updateTimeItemValues =
        context.getResources().getStringArray(R.array.settings_update_time_values);
  }

  @Override public int getCount() {
    return updateTimeItems.length;
  }

  @Override public String getItem(int position) {
    return updateTimeItems[position];
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    View spinView;
    if (convertView == null) {
      spinView = mInflater.inflate(R.layout.item_sp_title, null);
      TextView tvHeader = (TextView) spinView.findViewById(R.id.tv_header);
      tvHeader.setText(context.getString(R.string.settings_updating_offline_mode_time));
    } else {
      spinView = convertView;
    }

    TextView tvTitle = (TextView) spinView.findViewById(R.id.tv_title);
    tvTitle.setText(getItem(position));

    return spinView;
  }

  @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
    View spinView;
    if (convertView == null) {
      spinView = mInflater.inflate(R.layout.item_sp_drop, null);
    } else {
      spinView = convertView;
    }
    TextView tvTitle = (TextView) spinView.findViewById(R.id.tv_data);
    tvTitle.setText(getItem(position));
    return spinView;
  }

  public String getTimeFromPosition(int position) {
    return updateTimeItemValues[position];
  }

  public int getItemPosition(int offlineUpdateInterval) {
    for (int position = 0; position < updateTimeItemValues.length; position++) {
      if (updateTimeItemValues[position].equals(String.valueOf(offlineUpdateInterval))) {
        return position;
      }
    }
    return 0;
  }
}
