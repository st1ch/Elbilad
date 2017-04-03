package inc.itnity.elbilad.data.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Artem Getman on 03.04.17.
 * a.e.getman@gmail.com
 */

public class AuthenticatorService extends Service {

  private Authenticator authenticator;

  @Override public void onCreate() {
    authenticator = new Authenticator(this);
  }

  @Nullable @Override public IBinder onBind(Intent intent) {
    return authenticator.getIBinder();
  }
}
