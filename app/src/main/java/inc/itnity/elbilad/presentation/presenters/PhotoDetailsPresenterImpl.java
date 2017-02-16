package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.Bookmark;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.AddPhotoBookmarkUseCase;
import inc.itnity.elbilad.domain.usecases.GetPhotosUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.PhotoDetailsView;
import java.util.List;

/**
 * Created by st1ch on 15.02.17.
 */

public class PhotoDetailsPresenterImpl extends ProgressConnectionPresenter<PhotoDetailsView>
    implements PhotoDetailsPresenter {

  private GetPhotosUseCase getPhotosUseCase;
  private AddPhotoBookmarkUseCase addPhotoBookmarkUseCase;

  public PhotoDetailsPresenterImpl(GetPhotosUseCase getPhotosUseCase,
      AddPhotoBookmarkUseCase addPhotoBookmarkUseCase) {
    this.getPhotosUseCase = getPhotosUseCase;
    this.addPhotoBookmarkUseCase = addPhotoBookmarkUseCase;
  }

  @Override public void onDestroy() {
    getPhotosUseCase.unsubscribe();
    addPhotoBookmarkUseCase.unsubscribe();
    super.onDestroy();
  }

  @Override public void onCreate() {
    getPhotosUseCase.setRefresh(false);
    getPhotosUseCase.execute(photosSubscriber());
  }

  @Override public void addToBookmarks(Image image) {
    addPhotoBookmarkUseCase.setPhoto(image);
    addPhotoBookmarkUseCase.execute(addBookmarkSubscriber());
  }

  private BaseUseCaseSubscriber<Bookmark> addBookmarkSubscriber() {
    return new BaseUseCaseSubscriber<Bookmark>() {
      @Override public void onNext(Bookmark bookmark) {
        super.onNext(bookmark);

        try {
          checkViewBound();

          getView().showAddedToBookmarks();
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseProgressSubscriber<List<Image>> photosSubscriber() {
    return new BaseProgressSubscriber<List<Image>>(this) {
      @Override public void onNext(List<Image> images) {
        super.onNext(images);

        try {
          checkViewBound();

          getView().showSlideshow(images);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }
}
