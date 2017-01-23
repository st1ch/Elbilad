package inc.itnity.elbilad.di.components;

import dagger.Component;
import inc.itnity.elbilad.di.MainActivityScope;
import inc.itnity.elbilad.di.modules.ActivityContextModule;
import inc.itnity.elbilad.di.modules.MainActivityModule;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;
import inc.itnity.elbilad.presentation.fragments.categories.BookmarksFragment;
import inc.itnity.elbilad.presentation.fragments.categories.HomeFragment;
import inc.itnity.elbilad.presentation.fragments.HomeScreenBaseFragment;
import inc.itnity.elbilad.presentation.fragments.categories.LastNewsFragment;

/**
 * Created by st1ch on 17.01.17.
 */
@MainActivityScope @Component(dependencies = ApplicationComponent.class,
    modules = { ActivityContextModule.class, MainActivityModule.class })
public interface MainActivityComponent {
  /**
   * Injections of Activities
   */
  void inject(AbstractBaseActivity mainActivity);

  /**
   * Injections of Fragments
   */
  void inject(HomeScreenBaseFragment homeScreenBaseFragment);

  void inject(HomeFragment homeFragment);

  void inject(LastNewsFragment lastNewsFragment);

  void inject(BookmarksFragment bookmarksFragment);

}
