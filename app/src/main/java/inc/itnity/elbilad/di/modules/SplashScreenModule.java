package inc.itnity.elbilad.di.modules;

import android.support.v7.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;
import inc.itnity.elbilad.di.SplashScreenScope;
import inc.itnity.elbilad.domain.usecases.FetchHomeArticlesAndCategoriesUseCase;
import inc.itnity.elbilad.presentation.presenters.SplashScreenPresenter;
import inc.itnity.elbilad.presentation.presenters.SplashScreenPresenterImpl;
import inc.itnity.elbilad.utils.DialogHelper;

/**
 * Created by st1ch on 17.01.17.
 */
@Module public class SplashScreenModule {

  @Provides @SplashScreenScope DialogHelper provideDialogHelper(AppCompatActivity activity) {
    return new DialogHelper(activity);
  }

  @Provides SplashScreenPresenter provideSplashScreenPresenter(
      FetchHomeArticlesAndCategoriesUseCase fetchHomeArticlesAndCategoriesUseCase) {
    return new SplashScreenPresenterImpl(fetchHomeArticlesAndCategoriesUseCase);
  }
}
