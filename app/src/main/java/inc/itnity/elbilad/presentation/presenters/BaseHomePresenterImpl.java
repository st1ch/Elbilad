package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.usecases.GetCategoriesUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.BaseHomeView;

/**
 * Created by st1ch on 16.01.17.
 */

public class BaseHomePresenterImpl extends ProgressConnectionPresenter<BaseHomeView>
    implements BaseHomePresenter {

  //private GetCategoriesUseCase getCategoriesUseCase;

  public BaseHomePresenterImpl(GetCategoriesUseCase getCategoriesUseCase) {
    //this.getCategoriesUseCase = getCategoriesUseCase;
  }

  @Override public void onCreate() {
    //getCategoriesUseCase.setRefresh(false);
    //getCategoriesUseCase.execute(categoriesSubscriber());
  }

  @Override public void onDestroy() {
    //getCategoriesUseCase.unsubscribe();
    super.onDestroy();
  }

  //private BaseUseCaseSubscriber<List<Category>> categoriesSubscriber() {
  //  return new BaseUseCaseSubscriber<List<Category>>() {
  //    @Override public void onNext(List<Category> categories) {
  //      super.onNext(categories);
  //
  //      try {
  //        checkViewBound();
  //
  //        getView().showLoadedCategories(categories);
  //      } catch (ViewNotBoundException e) {
  //        e.printStackTrace();
  //      }
  //    }
  //  };
  //}
}
