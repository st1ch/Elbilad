package inc.itnity.elbilad.presentation.presenters.base;

import inc.itnity.elbilad.presentation.views.base.IView;

public interface IPresenter<V extends IView> {
    void bind(V view);
    void unbind();
    void onDestroy();

}
