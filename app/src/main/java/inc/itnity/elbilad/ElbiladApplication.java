package inc.itnity.elbilad;

import android.app.Application;
import android.util.Log;
import com.onesignal.OneSignal;
import inc.itnity.elbilad.di.components.ApplicationComponent;
import inc.itnity.elbilad.di.components.DaggerApplicationComponent;
import inc.itnity.elbilad.di.modules.ApplicationModule;
import inc.itnity.elbilad.utils.PreferenceHelper;
import javax.inject.Inject;
import org.json.JSONException;

/**
 * Created by st1ch on 10.01.17.
 */

public class ElbiladApplication extends Application {

  @Inject PreferenceHelper preferenceHelper;

  private static ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();

    OneSignal.startInit(this).setNotificationReceivedHandler(notification -> {
      try {
        Log.wtf("RECEIVED",
            "notificationReceived: " + notification.payload.additionalData.getString("article_id"));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }).setNotificationOpenedHandler(result -> {
      try {
        String article_id = result.notification.payload.additionalData.getString("article_id");
        Log.wtf("OPENED", "notificationOpened: " + article_id);

        preferenceHelper.setArticleId(article_id);

      } catch (JSONException e) {
        e.printStackTrace();
      }
    }).init();

    applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(getApplicationContext()))
        .build();
    applicationComponent.inject(this);
  }

  public static ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
