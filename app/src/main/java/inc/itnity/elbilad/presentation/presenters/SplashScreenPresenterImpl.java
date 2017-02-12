package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.usecases.FetchHomeArticlesAndCategoriesUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.SplashScreenView;

/**
 * Created by st1ch on 16.01.17.
 */

public class SplashScreenPresenterImpl extends ProgressConnectionPresenter<SplashScreenView>
    implements SplashScreenPresenter {

  private FetchHomeArticlesAndCategoriesUseCase fetchHomeArticlesAndCategoriesUseCase;

  public SplashScreenPresenterImpl(
      FetchHomeArticlesAndCategoriesUseCase fetchHomeArticlesAndCategoriesUseCase) {
    this.fetchHomeArticlesAndCategoriesUseCase = fetchHomeArticlesAndCategoriesUseCase;
  }

  @Override public void onCreate() {
    try {
      checkViewBound();
      checkConnection();

      fetchHomeArticlesAndCategoriesUseCase.setRefresh(true);
      fetchHomeArticlesAndCategoriesUseCase.execute(fetchDataSubscriber());
    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();

      fetchHomeArticlesAndCategoriesUseCase.setRefresh(false);
      fetchHomeArticlesAndCategoriesUseCase.execute(fetchDataSubscriber());
    }
  }

  @Override public void onDestroy() {
    fetchHomeArticlesAndCategoriesUseCase.unsubscribe();
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
