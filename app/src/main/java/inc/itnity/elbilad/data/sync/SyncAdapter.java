package inc.itnity.elbilad.data.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;
import inc.itnity.elbilad.ElbiladApplication;
import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import rx.schedulers.Schedulers;

/**
 * Created by Artem Getman on 03.04.17.
 * a.e.getman@gmail.com
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {

  private ElbiladRepository elbiladRepository;

  public SyncAdapter(Context context, boolean autoInitialize, ElbiladRepository elbiladRepository) {
    super(context, autoInitialize);
    this.elbiladRepository = elbiladRepository;
    ElbiladApplication.getApplicationComponent().inject(this);
  }

  @Override public void onPerformSync(Account account, Bundle extras, String authority,
      ContentProviderClient provider, SyncResult syncResult) {

    elbiladRepository.getHomeArticles(true)
        .subscribeOn(Schedulers.io())
        .subscribe(
            homeArticles -> Log.i("SyncAdapter", "onPerformSync: " + homeArticles.toString()),
            Throwable::printStackTrace);
  }
}
