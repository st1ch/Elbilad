package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.buses.RefreshTabRxBus;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.GetPhotosUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.PhotoCategoryView;
import java.util.List;

/**
 * Created by st1ch on 14.02.17.
 */

public class PhotoCategoryPresenterImpl extends ProgressConnectionPresenter<PhotoCategoryView>
    implements PhotoCategoryPresenter {

  private GetPhotosUseCase getPhotosUseCase;

  private RefreshTabRxBus refreshTabRxBus;
  private BaseUseCaseSubscriber<Boolean> refreshTabSubscriber;

  public PhotoCategoryPresenterImpl(GetPhotosUseCase getPhotosUseCase,
      RefreshTabRxBus refreshTabRxBus) {
    this.getPhotosUseCase = getPhotosUseCase;
    this.refreshTabRxBus = refreshTabRxBus;
  }

  @Override public void onCreate() {
    try {
      checkViewBound();
      checkConnection();

      getPhotosUseCase.setRefresh(true);
      getPhotosUseCase.execute(photosSubscriber());
    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();
      getPhotosUseCase.setRefresh(false);
      getPhotosUseCase.execute(photosSubscriber());
    }

    if (refreshTabSubscriber == null) {
      refreshTabSubscriber = refreshTabSubscriber();
      refreshTabRxBus.getOpenTabObservable().subscribe(refreshTabSubscriber);
    }
  }

  @Override public void onDestroy() {
    getPhotosUseCase.unsubscribe();
    if(refreshTabSubscriber != null && !refreshTabSubscriber.isUnsubscribed()){
      refreshTabSubscriber.unsubscribe();
      refreshTabSubscriber = null;
    }
    super.onDestroy();
  }

  private BaseProgressSubscriber<List<Image>> photosSubscriber() {
    return new BaseProgressSubscriber<List<Image>>(this) {
      @Override public void onNext(List<Image> images) {
        super.onNext(images);

        try {
          checkViewBound();

          getView().showPhotos(images);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseUseCaseSubscriber<Boolean> refreshTabSubscriber() {
    return new BaseUseCaseSubscriber<Boolean>() {
      @Override public void onNext(Boolean aBoolean) {
        super.onNext(aBoolean);

        onCreate();
      }
    };
  }
}
