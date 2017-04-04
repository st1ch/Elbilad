package inc.itnity.elbilad.utils;

import android.content.SharedPreferences;

import static inc.itnity.elbilad.constants.Constants.PREFS_OFFLINE_MODE;
import static inc.itnity.elbilad.constants.Constants.PREFS_PUSH_ARTICLE_ID;
import static inc.itnity.elbilad.constants.Constants.PREFS_PUSH_NOTIFICATIONS;
import static inc.itnity.elbilad.constants.Constants.PREFS_SYNC_INTERVAL_HOURS;

public class PreferenceHelper {

  private SharedPreferences sharedPreferences;

  public PreferenceHelper(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  public String getArticleId() {
    return sharedPreferences.getString(PREFS_PUSH_ARTICLE_ID, "");
  }

  public void setArticleId(String articleId) {
    sharedPreferences.edit().putString(PREFS_PUSH_ARTICLE_ID, articleId).apply();
  }

  public void clearArticleId() {
    sharedPreferences.edit().remove(PREFS_PUSH_ARTICLE_ID).apply();
  }

  public int getSyncIntervalHours() {
    return sharedPreferences.getInt(PREFS_SYNC_INTERVAL_HOURS, 6);
  }

  public void setSyncIntervalHours(int hoursInterval) {
    sharedPreferences.edit().putInt(PREFS_SYNC_INTERVAL_HOURS, hoursInterval).apply();
  }

  public void removeSyncInterval() {
    sharedPreferences.edit().remove(PREFS_SYNC_INTERVAL_HOURS).apply();
  }

  public boolean isOfflineModeEnabled() {
    return sharedPreferences.getBoolean(PREFS_OFFLINE_MODE, true);
  }

  public void setOfflineModeEnabled(boolean enabled) {
    sharedPreferences.edit().putBoolean(PREFS_OFFLINE_MODE, enabled).apply();
  }

  public boolean isPushNotificationsEnabled() {
    return sharedPreferences.getBoolean(PREFS_PUSH_NOTIFICATIONS, true);
  }

  public void setPushNotifiactionsEnabled(boolean enabled) {
    sharedPreferences.edit().putBoolean(PREFS_PUSH_NOTIFICATIONS, enabled).apply();
  }

  public void clearInfo() {
    sharedPreferences.edit().clear().apply();
  }
}
