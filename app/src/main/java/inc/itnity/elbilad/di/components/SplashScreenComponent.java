package inc.itnity.elbilad.di.components;

import dagger.Component;
import inc.itnity.elbilad.di.SplashScreenScope;
import inc.itnity.elbilad.di.modules.ActivityContextModule;
import inc.itnity.elbilad.di.modules.SplashScreenModule;
import inc.itnity.elbilad.presentation.activities.SplashScreenActivity;

/**
 * Created by st1ch on 20.01.17.
 */
@SplashScreenScope
@Component(dependencies = ApplicationComponent.class, modules = {
    ActivityContextModule.class, SplashScreenModule.class
}) public interface SplashScreenComponent {
  void inject(SplashScreenActivity splashScreenActivity);
}
