package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.buses.RefreshTabRxBus;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.GetVideosUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.VideoCategoryView;
import java.util.List;

/**
 * Created by st1ch on 14.02.17.
 */

public class VideoCategoryPresenterImpl extends ProgressConnectionPresenter<VideoCategoryView>
    implements VideoCategoryPresenter {

  private GetVideosUseCase getVideosUseCase;

  private RefreshTabRxBus refreshTabRxBus;
  private BaseUseCaseSubscriber<Boolean> refreshTabSubscriber;

  public VideoCategoryPresenterImpl(GetVideosUseCase getVideosUseCase,
      RefreshTabRxBus refreshTabRxBus) {
    this.getVideosUseCase = getVideosUseCase;
    this.refreshTabRxBus = refreshTabRxBus;
  }

  @Override public void onCreate() {
    try {
      checkViewBound();
      checkConnection();

      getVideosUseCase.setRefresh(true);
      getVideosUseCase.execute(videosSubscriber());
    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();
      getVideosUseCase.setRefresh(false);
      getVideosUseCase.execute(videosSubscriber());
    }

    if (refreshTabSubscriber == null) {
      refreshTabSubscriber = refreshTabSubscriber();
      refreshTabRxBus.getOpenTabObservable().subscribe(refreshTabSubscriber);
    }
  }

  @Override public void onDestroy() {
    getVideosUseCase.unsubscribe();
    if(refreshTabSubscriber != null && !refreshTabSubscriber.isUnsubscribed()){
      refreshTabSubscriber.unsubscribe();
      refreshTabSubscriber = null;
    }
    super.onDestroy();
  }

  private BaseProgressSubscriber<List<Video>> videosSubscriber() {
    return new BaseProgressSubscriber<List<Video>>(this) {
      @Override public void onNext(List<Video> videos) {
        super.onNext(videos);

        try {
          checkViewBound();

          getView().showVideos(videos);
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
