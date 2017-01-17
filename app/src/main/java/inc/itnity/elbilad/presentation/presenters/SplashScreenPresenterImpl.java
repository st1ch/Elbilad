package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.usecases.FetchArticlesAndCategoriesUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.SplashScreenView;

/**
 * Created by st1ch on 16.01.17.
 */

public class SplashScreenPresenterImpl extends ProgressConnectionPresenter<SplashScreenView>
    implements SplashScreenPresenter {

  private FetchArticlesAndCategoriesUseCase fetchArticlesAndCategoriesUseCase;

  public SplashScreenPresenterImpl(
      FetchArticlesAndCategoriesUseCase fetchArticlesAndCategoriesUseCase) {
    this.fetchArticlesAndCategoriesUseCase = fetchArticlesAndCategoriesUseCase;
  }

  @Override public void onCreate() {
    fetchArticlesAndCategoriesUseCase.setRefresh(false);
    fetchArticlesAndCategoriesUseCase.execute(fetchDataSubscriber());
  }

  @Override public void onDestroy() {
    fetchArticlesAndCategoriesUseCase.unsubscribe();
    super.onDestroy();
  }

  private BaseProgressSubscriber<Boolean> fetchDataSubscriber() {
    return new BaseProgressSubscriber<Boolean>(this) {
      @Override public void onNext(Boolean status) {
        super.onNext(status);

        try {
          checkViewBound();

          if (status) {
            getView().showDataLoaded();
          }
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }
}
