package inc.itnity.elbilad.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import inc.itnity.elbilad.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;

/**
 * Created by st1ch on 16.01.17.
 */

public class ElbiladUtils {

  private DateFormat articleDateFormat = new SimpleDateFormat("hh:mm | dd-MM-yyyy", Locale.ENGLISH);

  private AppCompatActivity context;

  @Inject ElbiladUtils(AppCompatActivity context) {
    this.context = context;
  }

  public boolean isNetworkConnected() {
    ConnectivityManager connectivityManager =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetInfo != null && activeNetInfo.isConnected();
  }

  public String getArticleDate(Date date) {
    return articleDateFormat.format(date);
  }

  public String getArticleTimeDate(String time, String date) {
    return context.getString(R.string.date, time, date);
  }

  public void shareArticleLink(String articleLink){
    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
    intent.putExtra(android.content.Intent.EXTRA_TEXT, articleLink);
    context.startActivity(Intent.createChooser(intent, "Choose sharing method"));
  }
}
