package inc.itnity.elbilad.presentation.presenters;

import android.util.Log;
import inc.itnity.elbilad.domain.buses.OpenTabRxBus;
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
  private OpenTabRxBus openTabRxBus;

  public MainMenuPresenterImpl(GetCategoriesUseCase getCategoriesUseCase, OpenTabRxBus openTabRxBus) {
    this.getCategoriesUseCase = getCategoriesUseCase;
    this.openTabRxBus = openTabRxBus;
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

  @Override public void openBaseFragmentTab(int position) {
    Log.wtf("pres", "openBaseFragmentTab: " + position);
    openTabRxBus.openTab(position);
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
