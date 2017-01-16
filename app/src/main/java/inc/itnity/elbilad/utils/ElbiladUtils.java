package inc.itnity.elbilad.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import javax.inject.Inject;

/**
 * Created by st1ch on 16.01.17.
 */

public class ElbiladUtils {

  private Context context;

  @Inject ElbiladUtils(Context context) {
    this.context = context;
  }

  public boolean isNetworkConnected() {
    ConnectivityManager connectivityManager =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetInfo != null && activeNetInfo.isConnected();
  }
}
