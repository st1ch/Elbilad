package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.Bookmark;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.AddVideoBookmarkUseCase;
import inc.itnity.elbilad.domain.usecases.GetVideoArticleUseCase;
import inc.itnity.elbilad.domain.usecases.GetVideoUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.VideoDetailsView;

/**
 * Created by st1ch on 15.02.17.
 */

public class VideoDetailsPresenterImpl extends ProgressConnectionPresenter<VideoDetailsView>
    implements VideoDetailsPresenter {

  private GetVideoUseCase getVideosUseCase;
  private GetVideoArticleUseCase getVideoArticleUseCase;
  private AddVideoBookmarkUseCase addVideoBookmarkUseCase;

  public VideoDetailsPresenterImpl(GetVideoUseCase getVideosUseCase,
      GetVideoArticleUseCase getVideoArticleUseCase,
      AddVideoBookmarkUseCase addVideoBookmarkUseCase) {
    this.getVideosUseCase = getVideosUseCase;
    this.getVideoArticleUseCase = getVideoArticleUseCase;
    this.addVideoBookmarkUseCase = addVideoBookmarkUseCase;
  }

  @Override public void onCreate(String videoId, boolean isArticle) {
    if (!isArticle) {
      getVideosUseCase.setVideoId(videoId);
      getVideosUseCase.execute(videoSubscriber());
    } else {
      getVideoArticleUseCase.setVideoId(videoId);
      getVideoArticleUseCase.execute(videoSubscriber());
    }
  }

  @Override public void addToBookmarks(Video video) {
    addVideoBookmarkUseCase.setVideo(video);
    addVideoBookmarkUseCase.execute(addBookmarkSubscriber());
  }

  @Override public void onDestroy() {
    getVideosUseCase.unsubscribe();
    addVideoBookmarkUseCase.unsubscribe();
    super.onDestroy();
  }

  private BaseProgressSubscriber<Video> videoSubscriber() {
    return new BaseProgressSubscriber<Video>(this) {
      @Override public void onNext(Video video) {
        super.onNext(video);

        try {
          checkViewBound();

          getView().showVideo(video);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
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
}
