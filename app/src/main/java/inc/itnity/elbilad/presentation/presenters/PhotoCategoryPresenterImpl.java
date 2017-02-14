package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
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

  public PhotoCategoryPresenterImpl(GetPhotosUseCase getPhotosUseCase) {
    this.getPhotosUseCase = getPhotosUseCase;
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
  }

  @Override public void onDestroy() {
    getPhotosUseCase.unsubscribe();
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
}
