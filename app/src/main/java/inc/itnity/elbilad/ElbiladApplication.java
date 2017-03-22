package inc.itnity.elbilad;

import android.app.Application;
import android.util.Log;
import com.onesignal.OneSignal;
import inc.itnity.elbilad.di.components.ApplicationComponent;
import inc.itnity.elbilad.di.components.DaggerApplicationComponent;
import inc.itnity.elbilad.di.modules.ApplicationModule;

/**
 * Created by st1ch on 10.01.17.
 */

public class ElbiladApplication extends Application {

  private static ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();

    OneSignal.startInit(this)
        .setNotificationReceivedHandler(
            notification -> Log.wtf("RECEIVED", "notificationReceived: " + notification.toString()))
        .setNotificationOpenedHandler(
            result -> Log.wtf("OPENED", "notificationOpened: " + result.toString()))
        .init();

    applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(getApplicationContext()))
        .build();
  }

  public static ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
