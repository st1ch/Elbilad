package inc.itnity.elbilad.di.components;

import dagger.Component;
import inc.itnity.elbilad.di.MainActivityScope;
import inc.itnity.elbilad.di.modules.ActivityContextModule;
import inc.itnity.elbilad.di.modules.MainActivityModule;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;
import inc.itnity.elbilad.presentation.fragments.ArticleDetailsFragment;
import inc.itnity.elbilad.presentation.fragments.HomeScreenBaseFragment;
import inc.itnity.elbilad.presentation.fragments.MainMenuFragment;
import inc.itnity.elbilad.presentation.fragments.categories.BookmarksFragment;
import inc.itnity.elbilad.presentation.fragments.categories.CategoryNewsFragment;
import inc.itnity.elbilad.presentation.fragments.categories.HomeBookmarksFragment;
import inc.itnity.elbilad.presentation.fragments.categories.HomeFragment;
import inc.itnity.elbilad.presentation.fragments.categories.HomeLastNewsFragment;
import inc.itnity.elbilad.presentation.fragments.categories.HomePhotosFragment;
import inc.itnity.elbilad.presentation.fragments.categories.HomeVideosFragment;
import inc.itnity.elbilad.presentation.fragments.categories.LastNewsFragment;
import inc.itnity.elbilad.presentation.fragments.categories.PhotosFragment;
import inc.itnity.elbilad.presentation.fragments.categories.SimpleNewsFragment;
import inc.itnity.elbilad.presentation.fragments.categories.VideosFragment;

/**
 * Created by st1ch on 17.01.17.
 */
@MainActivityScope @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityContextModule.class, MainActivityModule.class
}) public interface MainActivityComponent {
  /**
   * Injections of Activities
   */
  void inject(AbstractBaseActivity mainActivity);

  /**
   * Injections of Fragments
   */
  void inject(HomeScreenBaseFragment homeScreenBaseFragment);

  void inject(MainMenuFragment mainMenuFragment);

  void inject(HomeFragment homeFragment);

  void inject(LastNewsFragment lastNewsFragment);

  void inject(HomeLastNewsFragment lastNewsFragment);

  void inject(BookmarksFragment bookmarksFragment);

  void inject(HomeBookmarksFragment bookmarksFragment);

  void inject(SimpleNewsFragment simpleNewsFragment);

  void inject(CategoryNewsFragment categoryNewsFragment);

  void inject(ArticleDetailsFragment articleDetailsFragment);

  void inject(VideosFragment videosFragment);

  void inject(HomeVideosFragment videosFragment);

  void inject(HomePhotosFragment photosFragment);

  void inject(PhotosFragment photosFragment);
}
