package inc.itnity.elbilad.data.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import inc.itnity.elbilad.ElbiladApplication;
import javax.inject.Inject;

/**
 * Created by Artem Getman on 03.04.17.
 * a.e.getman@gmail.com
 */

public class SyncService extends Service {

  @Inject SyncAdapter syncAdapter;

  @Override public void onCreate() {
    super.onCreate();
    ElbiladApplication.getApplicationComponent().inject(this);
  }

  @Nullable @Override public IBinder onBind(Intent intent) {
    return syncAdapter.getSyncAdapterBinder();
  }
}
