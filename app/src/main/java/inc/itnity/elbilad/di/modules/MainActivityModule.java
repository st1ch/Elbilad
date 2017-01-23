package inc.itnity.elbilad.di.modules;

import dagger.Module;
import dagger.Provides;
import inc.itnity.elbilad.domain.usecases.GetArticlesUseCase;
import inc.itnity.elbilad.presentation.presenters.HomeScreenPresenter;
import inc.itnity.elbilad.presentation.presenters.HomeScreenPresenterImpl;

/**
 * Created by st1ch on 20.01.17.
 */
@Module
public class MainActivityModule {

  @Provides
  HomeScreenPresenter provideHomeScreenPresenter(GetArticlesUseCase getArticlesUseCase){
    return new HomeScreenPresenterImpl(getArticlesUseCase);
  }

}
