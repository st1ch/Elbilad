package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.Gallery;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.usecases.GetGalleryUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.PhotoDetailsView;

/**
 * Created by st1ch on 15.02.17.
 */

public class PhotoDetailsPresenterImpl extends ProgressConnectionPresenter<PhotoDetailsView>
    implements PhotoDetailsPresenter {

  //private GetPhotosUseCase getPhotosUseCase;
  private GetGalleryUseCase getGalleryUseCase;
  //private AddPhotoBookmarkUseCase addPhotoBookmarkUseCase;

  public PhotoDetailsPresenterImpl(GetGalleryUseCase getGalleryUseCase) {
    //this.getPhotosUseCase = getPhotosUseCase;
    //this.addPhotoBookmarkUseCase = addPhotoBookmarkUseCase;
    this.getGalleryUseCase = getGalleryUseCase;
  }

  @Override public void onDestroy() {
    //getPhotosUseCase.unsubscribe();
    //addPhotoBookmarkUseCase.unsubscribe();
    getGalleryUseCase.unsubscribe();
    super.onDestroy();
  }

  @Override public void onCreate(int galleryId) {
    getGalleryUseCase.setId(galleryId);
    getGalleryUseCase.execute(photosSubscriber());
    //getPhotosUseCase.setRefresh(false);
    //getPhotosUseCase.execute(photosSubscriber());
  }

  //@Override public void addToBookmarks(Image image) {
  //  addPhotoBookmarkUseCase.setPhoto(image);
  //  addPhotoBookmarkUseCase.execute(addBookmarkSubscriber());
  //}

  //private BaseUseCaseSubscriber<Bookmark> addBookmarkSubscriber() {
  //  return new BaseUseCaseSubscriber<Bookmark>() {
  //    @Override public void onNext(Bookmark bookmark) {
  //      super.onNext(bookmark);
  //
  //      try {
  //        checkViewBound();
  //
  //        getView().showAddedToBookmarks();
  //      } catch (ViewNotBoundException e) {
  //        e.printStackTrace();
  //      }
  //    }
  //  };
  //}

  private BaseProgressSubscriber<Gallery> photosSubscriber() {
    return new BaseProgressSubscriber<Gallery>(this) {
      @Override public void onNext(Gallery gallery) {
        super.onNext(gallery);

        try {
          checkViewBound();

          getView().showSlideshow(gallery.getPhotos());
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }
}
