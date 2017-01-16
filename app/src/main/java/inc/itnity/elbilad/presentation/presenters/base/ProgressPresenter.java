package inc.itnity.elbilad.presentation.presenters.base;

import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.presentation.views.base.ProgressView;

public class ProgressPresenter<V extends ProgressView> extends BasePresenter<V>
        implements BaseProgressSubscriber.ProgressSubscriberListener {
    @Override
    public void onError(Throwable t) {
        try {
            checkViewBound();

            getView().hideProgress();
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
