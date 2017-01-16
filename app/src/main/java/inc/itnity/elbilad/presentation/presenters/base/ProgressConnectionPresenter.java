package inc.itnity.elbilad.presentation.presenters.base;

import inc.itnity.elbilad.domain.exceptions.ServerForbiddenException;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.presentation.views.base.ConnectionView;

public class ProgressConnectionPresenter<V extends ConnectionView> extends BaseConnectionPresenter<V>
        implements BaseProgressSubscriber.ProgressSubscriberListener  {

    @Override
    public void onError(Throwable t) {
        try {
            checkViewBound();
            getView().hideProgress();

            if (t instanceof ServerForbiddenException) {
                getView().onDataNotAvailable();
            } else {
                getView().showErrorDialog(t.getMessage());
            }
        } catch (ViewNotBoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCompleted() {
        try {
            checkViewBound();
            getView().hideProgress();

        } catch (ViewNotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStartLoading() {
        try {
            checkViewBound();
            getView().showProgress();
        } catch (ViewNotBoundException e) {
            e.printStackTrace();
        }
    }

}
