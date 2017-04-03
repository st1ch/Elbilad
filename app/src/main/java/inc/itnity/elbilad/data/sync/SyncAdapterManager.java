package inc.itnity.elbilad.data.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.utils.PreferenceHelper;

import static android.content.Context.ACCOUNT_SERVICE;

/**
 * Created by Artem Getman on 03.04.17.
 * a.e.getman@gmail.com
 */

public class SyncAdapterManager {

  private static final String TAG = SyncAdapterManager.class.getSimpleName();
  private final String authority;
  private final String type;

  private Account account;
  private Context context;

  private PreferenceHelper preferenceHelper;

  public SyncAdapterManager(final Context context, PreferenceHelper preferenceHelper) {
    this.context = context;
    this.preferenceHelper = preferenceHelper;

    type = context.getString(R.string.accountType);
    authority = context.getString(R.string.authority);
    account = new Account(context.getString(R.string.app_name), type);
  }

  @SuppressWarnings("MissingPermission") public void beginPeriodicSync() {

    int intervalHours = preferenceHelper.getSyncIntervalHours();

    Log.i(TAG, "beginPeriodicSync() called with: updateConfigInterval = " + intervalHours);

    if (intervalHours == 0) {
      ContentResolver.removePeriodicSync(account, context.getString(R.string.authority),
          Bundle.EMPTY);
    } else {

      long updateConfigInterval = intervalHours * 60 * 60;

      final AccountManager accountManager =
          (AccountManager) context.getSystemService(ACCOUNT_SERVICE);

      if (!accountManager.addAccountExplicitly(account, null, null)) {
        account = accountManager.getAccountsByType(type)[0];
      }

      setAccountSyncable();

      ContentResolver.addPeriodicSync(account, context.getString(R.string.authority), Bundle.EMPTY,
          updateConfigInterval);
      ContentResolver.setMasterSyncAutomatically(true);

      ContentResolver.setSyncAutomatically(account, authority, true);
    }
  }

  public void removeSync(){
    ContentResolver.removePeriodicSync(account, context.getString(R.string.authority),
        Bundle.EMPTY);
  }

  private void setAccountSyncable() {
    if (ContentResolver.getIsSyncable(account, authority) == 0) {
      ContentResolver.setIsSyncable(account, authority, 1);
    }
  }
}
