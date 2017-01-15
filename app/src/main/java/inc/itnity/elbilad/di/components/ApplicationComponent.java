package inc.itnity.elbilad.di.components;

import dagger.Component;
import inc.itnity.elbilad.di.modules.ActivityContextModule;
import inc.itnity.elbilad.di.modules.ApplicationModule;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.activities.SplashScreenActivity;
import inc.itnity.elbilad.presentation.fragments.HomeScreenFragment;
import javax.inject.Singleton;

/**
 * Created by st1ch on 15.01.17.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ActivityContextModule.class})
public interface ApplicationComponent {

  /**
   * Injections of Activities
   */
  void inject(MainActivity mainActivity);
  void inject(SplashScreenActivity splashScreenActivity);

  /**
   * Injections of Fragments
   */
  void inject(HomeScreenFragment homeScreenFragment);
}
