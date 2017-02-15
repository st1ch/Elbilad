package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.categorie.Category;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.GetCategoriesUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.MainMenuView;
import java.util.List;

/**
 * Created by st1ch on 16.01.17.
 */

public class MainMenuPresenterImpl extends ProgressConnectionPresenter<MainMenuView>
    implements MainMenuPresenter {

  private GetCategoriesUseCase getCategoriesUseCase;

  public MainMenuPresenterImpl(GetCategoriesUseCase getCategoriesUseCase) {
    this.getCategoriesUseCase = getCategoriesUseCase;
  }

  @Override public void onCreate() {
    try {
      checkViewBound();

      getCategoriesUseCase.setRefresh(false);
      getCategoriesUseCase.execute(categoriesSubscriber());

    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    }
  }

  @Override public void onDestroy() {
    super.onDestroy();
    getCategoriesUseCase.unsubscribe();
  }

  private BaseUseCaseSubscriber<List<Category>> categoriesSubscriber() {
    return new BaseUseCaseSubscriber<List<Category>>(){
      @Override public void onNext(List<Category> categories) {
        super.onNext(categories);

        try {
          checkViewBound();

          getView().showLoadedCategories(categories);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }
}
