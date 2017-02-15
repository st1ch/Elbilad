package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by st1ch on 15.02.17.
 */

public class GetVideoUseCase extends UseCase<Video> {

  private ElbiladRepository elbiladRepository;

  private String videoId;

  @Inject GetVideoUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository elbiladRepository) {
    super(subscribeOn, observeOn);
    this.elbiladRepository = elbiladRepository;
  }

  public void setVideoId(String videoId) {
    this.videoId = videoId;
  }

  @Override protected Observable<Video> getUseCaseObservable() {
    return elbiladRepository.getVideo(videoId);
  }
}
