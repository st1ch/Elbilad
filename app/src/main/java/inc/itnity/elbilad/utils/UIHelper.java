package inc.itnity.elbilad.utils;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import inc.itnity.elbilad.R;
import javax.inject.Inject;

public class UIHelper {

    private AppCompatActivity context;

    @Inject UIHelper(AppCompatActivity context) {
        this.context = context;
    }

    public void showSnackbar(String message) {
        final Snackbar
            snackbar = Snackbar.make(context.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.setAction(context.getResources().getString(R.string.okay), view -> snackbar.dismiss());

        snackbar.show();
    }

    public void hideKeyboard() {
        new Handler().postDelayed(() -> {
            {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

                if (context.getCurrentFocus() != null
                        && (context.getCurrentFocus().getWindowToken() != null)) {
                    imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
                }
            }
        }, 200);
    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 1);
    }

    public void showShortToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
