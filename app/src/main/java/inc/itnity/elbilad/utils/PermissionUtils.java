package inc.itnity.elbilad.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import inc.itnity.elbilad.constants.Constants;
import javax.inject.Inject;

/**
 * Created by alexeyverbitskiy on 11/1/16.
 */

public class PermissionUtils {

  private AppCompatActivity context;

  @Inject public PermissionUtils(AppCompatActivity context) {
    this.context = context;
  }

  public boolean requestExternalStoragePermissions() {
    if (!checkWriteStoragePermissionGranted()) {
      return requestCameraAndExternalStorage();
    } else {
      return true;
    }
  }

  private boolean checkWriteStoragePermissionGranted() {
    return ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        == PackageManager.PERMISSION_GRANTED;
  }

  private boolean checkReadStoragePermissionGranted() {
    return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
        == PackageManager.PERMISSION_GRANTED;
  }

  private boolean requestCameraAndExternalStorage() {
    ActivityCompat.requestPermissions(context, new String[] {
        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
    }, Constants.REQUEST_PERMISSIONS_ELBILAD);
    return false;
  }

    /*public boolean onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case IntentData.REQUEST_PERMISSIONS_FAN: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("Permissions granted!");
                    return true;
                } else {
                    System.out.println("Permissions blocked!");
                    return false;
                }
            }
        }
        return false;
    }*/
}
