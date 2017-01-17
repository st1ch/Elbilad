package inc.itnity.elbilad.di.modules;

import dagger.Module;
import dagger.Provides;
import inc.itnity.elbilad.domain.usecases.FetchArticlesAndCategoriesUseCase;
import inc.itnity.elbilad.presentation.presenters.SplashScreenPresenter;
import inc.itnity.elbilad.presentation.presenters.SplashScreenPresenterImpl;

/**
 * Created by st1ch on 17.01.17.
 */
@Module public class SplashScreenModule {

  @Provides SplashScreenPresenter provideSplashScreenPresenter(
      FetchArticlesAndCategoriesUseCase fetchArticlesAndCategoriesUseCase) {
    return new SplashScreenPresenterImpl(fetchArticlesAndCategoriesUseCase);
  }
}
