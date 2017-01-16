package inc.itnity.elbilad.presentation.presenters.base;

import inc.itnity.elbilad.presentation.views.base.ConnectionView;

public abstract class BaseConnectionPresenter<V extends ConnectionView> extends BasePresenter<V> {

    public void checkConnection() throws ConnectionException {
        if(!getView().isNetworkOnline()){
            getView().showErrorCheckConnectionSnackbar();
                throw new ConnectionException();
        }
    }

    protected static class ConnectionException extends Exception {
        public ConnectionException() {
            super("Please check Internet connection before requesting data");
        }
    }
}
