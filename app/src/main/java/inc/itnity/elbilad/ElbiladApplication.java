package inc.itnity.elbilad;

import android.app.Application;
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

    applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(getApplicationContext()))
        .build();
  }

  public static ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
