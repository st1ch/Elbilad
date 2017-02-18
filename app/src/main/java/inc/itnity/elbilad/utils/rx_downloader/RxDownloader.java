package inc.itnity.elbilad.utils.rx_downloader;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by st1ch on 18.02.17.
 */

public class RxDownloader {

  private Context mContext;
  private DownloadManager mDownloadManager;

  private String dirName;

  private LongSparseArray<PublishSubject<String>> mSubjectMap = new LongSparseArray<>();

  public RxDownloader(Context context) {
    mContext = context.getApplicationContext();
    dirName = mContext.getApplicationContext().getPackageName();
    DownloadStatusReceiver downloadStatusReceiver = new DownloadStatusReceiver();
    IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
    context.registerReceiver(downloadStatusReceiver, intentFilter);
  }

  public Observable<String> download(String link, String filename) {
    return download(link, filename, "*/*");
  }

  public Observable<String> download(String link, String filename, String mimeType) {
    return download(getDefaultRequest(link, filename, mimeType));
  }

  public Observable<String> download(String directoryName, String link, String filename, String mimeType) {
    this.dirName = directoryName;
    return download(getDefaultRequest(link, filename, mimeType));
  }

  public Observable<String> download(DownloadManager.Request request) {
    if (mDownloadManager == null) {
      mDownloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    long downloadId = mDownloadManager.enqueue(request);

    PublishSubject<String> publishSubject = PublishSubject.create();
    mSubjectMap.put(downloadId, publishSubject);

    return publishSubject;
  }

  private DownloadManager.Request getDefaultRequest(String link, String filename, String mimeType) {
    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(link));
    request.setDescription("Downloading file...");
    request.setMimeType(mimeType);
    request.setDestinationInExternalPublicDir(
        Environment.DIRECTORY_DOWNLOADS, filename);
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
    return request;
  }

  private class DownloadStatusReceiver extends BroadcastReceiver {

    @Override public void onReceive(Context context, Intent intent) {
      long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0L);
      PublishSubject<String> publishSubject = mSubjectMap.get(id);

      if (publishSubject == null) return;

      DownloadManager.Query query = new DownloadManager.Query();
      query.setFilterById(id);
      Cursor cursor = mDownloadManager.query(query);

      if (!cursor.moveToFirst()) {
        mDownloadManager.remove(id);
        publishSubject.onError(new IllegalStateException("Cursor empty, this shouldn't happened"));
        mSubjectMap.remove(id);
        return;
      }

      int statusIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
      if (DownloadManager.STATUS_SUCCESSFUL != cursor.getInt(statusIndex)) {
        mDownloadManager.remove(id);
        publishSubject.onError(new IllegalStateException("Download Failed"));
        mSubjectMap.remove(id);
        return;
      }

      int uriIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
      String downloadedPackageUriString = cursor.getString(uriIndex);

      publishSubject.onNext(downloadedPackageUriString);
      publishSubject.onCompleted();
      mSubjectMap.remove(id);
    }
  }
}
