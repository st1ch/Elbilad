package inc.itnity.elbilad.di.modules;

import dagger.Module;
import dagger.Provides;
import inc.itnity.elbilad.domain.usecases.FetchHomeArticlesAndCategoriesUseCase;
import inc.itnity.elbilad.presentation.presenters.SplashScreenPresenter;
import inc.itnity.elbilad.presentation.presenters.SplashScreenPresenterImpl;

/**
 * Created by st1ch on 17.01.17.
 */
@Module public class SplashScreenModule {

  @Provides SplashScreenPresenter provideSplashScreenPresenter(
      FetchHomeArticlesAndCategoriesUseCase fetchHomeArticlesAndCategoriesUseCase) {
    return new SplashScreenPresenterImpl(fetchHomeArticlesAndCategoriesUseCase);
  }
}
