package inc.itnity.elbilad.domain.observables;

import inc.itnity.elbilad.domain.exceptions.ServerForbiddenException;
import inc.itnity.elbilad.domain.models.responses.BaseResponse;
import rx.Observable;

/**
 * Created by st1ch on 08.12.16.
 */

public class BaseResponseObservable<T extends BaseResponse> extends Observable<T> {

  public BaseResponseObservable(T baseResponse) {
    super(subscriber -> {
      if (baseResponse != null) {
        subscriber.onNext(baseResponse);
        subscriber.onCompleted();
      } else {
        subscriber.onError(new ServerForbiddenException());
      }
    });
  }
}
