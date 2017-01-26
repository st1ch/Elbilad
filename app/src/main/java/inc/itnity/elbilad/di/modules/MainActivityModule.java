package inc.itnity.elbilad.di.modules;

import dagger.Module;
import dagger.Provides;
import inc.itnity.elbilad.domain.buses.OpenTabRxBus;
import inc.itnity.elbilad.domain.usecases.GetArticlesUseCase;
import inc.itnity.elbilad.domain.usecases.GetCategoriesUseCase;
import inc.itnity.elbilad.presentation.presenters.BaseHomePresenter;
import inc.itnity.elbilad.presentation.presenters.BaseHomePresenterImpl;
import inc.itnity.elbilad.presentation.presenters.HomeScreenPresenter;
import inc.itnity.elbilad.presentation.presenters.HomeScreenPresenterImpl;
import inc.itnity.elbilad.presentation.presenters.MainMenuPresenter;
import inc.itnity.elbilad.presentation.presenters.MainMenuPresenterImpl;
import inc.itnity.elbilad.presentation.presenters.SimpleNewsPresenter;
import inc.itnity.elbilad.presentation.presenters.SimpleNewsPresenterImpl;

/**
 * Created by st1ch on 20.01.17.
 */
@Module public class MainActivityModule {

  @Provides HomeScreenPresenter provideHomeScreenPresenter(GetArticlesUseCase getArticlesUseCase) {
    return new HomeScreenPresenterImpl(getArticlesUseCase);
  }

  @Provides MainMenuPresenter provideMainMenuPresenter(GetCategoriesUseCase getCategoriesUseCase,
      OpenTabRxBus openTabRxBus) {
    return new MainMenuPresenterImpl(getCategoriesUseCase, openTabRxBus);
  }

  @Provides BaseHomePresenter provideBaseHomePresenter(OpenTabRxBus openTabRxBus, GetCategoriesUseCase getCategoriesUseCase) {
    return new BaseHomePresenterImpl(openTabRxBus, getCategoriesUseCase);
  }

  @Provides SimpleNewsPresenter provideSimpleNewsPresenter(GetArticlesUseCase getArticlesUseCase) {
    return new SimpleNewsPresenterImpl(getArticlesUseCase);
  }
}
