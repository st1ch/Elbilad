package inc.itnity.elbilad.presentation.presenters.base;

import android.support.annotation.Nullable;
import inc.itnity.elbilad.presentation.views.base.IView;

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    @Nullable
    private V view;

    @Override
    public void bind(V view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        this.view = null;
    }

    @Override
    public void onDestroy() {
        unbind();
    }

    @Nullable
    public V getView() {
        return view;
    }

    public void checkViewBound() throws ViewNotBoundException {
        if (!isViewBound()) {
            throw new ViewNotBoundException();
        }
    }

    private boolean isViewBound() {
        return view != null;
    }

    protected static class ViewNotBoundException extends Exception {
        public ViewNotBoundException() {
            super("Please call Presenter.bind(MvpView) before requesting data to the Presenter");
        }
    }
}
