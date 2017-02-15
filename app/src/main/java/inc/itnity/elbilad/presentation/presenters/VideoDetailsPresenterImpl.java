package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.ArticleItem;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.AddBookmarkUseCase;
import inc.itnity.elbilad.domain.usecases.GetVideoUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.VideoDetailsView;

/**
 * Created by st1ch on 15.02.17.
 */

public class VideoDetailsPresenterImpl extends ProgressConnectionPresenter<VideoDetailsView>
    implements VideoDetailsPresenter {

  private GetVideoUseCase getVideosUseCase;
  private AddBookmarkUseCase addBookmarkUseCase;

  public VideoDetailsPresenterImpl(GetVideoUseCase getVideosUseCase,
      AddBookmarkUseCase addBookmarkUseCase) {
    this.getVideosUseCase = getVideosUseCase;
    this.addBookmarkUseCase = addBookmarkUseCase;
  }

  @Override public void onCreate(String videoId) {
    getVideosUseCase.setVideoId(videoId);
    getVideosUseCase.execute(videoSubscriber());
  }

  @Override public void addToBookmarks(Video video) {
    addBookmarkUseCase.setArticle(video);
    addBookmarkUseCase.execute(addBookmarkSubscriber());
  }

  @Override public void onDestroy() {
    getVideosUseCase.unsubscribe();
    addBookmarkUseCase.unsubscribe();
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

  private BaseUseCaseSubscriber<ArticleItem> addBookmarkSubscriber() {
    return new BaseUseCaseSubscriber<ArticleItem>() {
      @Override public void onNext(ArticleItem articleItem) {
        super.onNext(articleItem);

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
