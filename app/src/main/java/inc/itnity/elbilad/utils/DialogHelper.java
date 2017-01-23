package inc.itnity.elbilad.utils;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import inc.itnity.elbilad.R;
import javax.inject.Inject;

public class DialogHelper {

    private AppCompatActivity context;
    private Dialog dialog;

    @Inject
    DialogHelper(AppCompatActivity context) {
        this.context = context;
    }

    public void showErrorDialog(String title, String message) {
        //View view = LayoutInflater.from(context).inflate(R.layout.dialog_view, null);
        //if (view != null) {
        //    ((TextView) view.findViewById(R.id.tv_title)).setText(title);
        //    ((TextView) view.findViewById(R.id.tv_message)).setText(message);
        //    view.findViewById(R.id.tv_okay).setOnClickListener(v -> dismissDialog());
        //}
        //
        //dialog = new Dialog(context);
        //dialog.setCanceledOnTouchOutside(false);
        //dialog.setContentView(view);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //dialog.show();
    }

    public void showProgressDialog() {
        View view = LayoutInflater.from(context).inflate(R.layout.progress_dialog_view, null);

        dialog = new Dialog(context);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        dialog.show();

    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
