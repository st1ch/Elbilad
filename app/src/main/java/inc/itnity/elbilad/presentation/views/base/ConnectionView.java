package inc.itnity.elbilad.presentation.views.base;

public interface ConnectionView extends ProgressView {

    void onDataNotAvailable();

    void showErrorDialog(String text);

    boolean isNetworkOnline();

    void showErrorCheckConnectionSnackbar();

}
