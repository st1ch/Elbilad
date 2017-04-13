package inc.itnity.elbilad.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import inc.itnity.elbilad.constants.ApiConfig;
import inc.itnity.elbilad.presentation.activities.YouTubeVideoActivity;
import javax.inject.Inject;

/**
 * Created by st1ch on 15.02.17.
 */

public class YouTubeHelper {

  private Context activity;

  private final String WATCH = "https://www.youtube.com/watch?v=";

  @Inject YouTubeHelper(AppCompatActivity context) {
    this.activity = context;
  }

  public void initializeThumbnail(YouTubeThumbnailView imageVideo, String videoId) {
    imageVideo.setTag(videoId);
    imageVideo.initialize(ApiConfig.YOUTUBE_KEY, new YouTubeThumbnailView.OnInitializedListener() {

      @Override public void onInitializationFailure(YouTubeThumbnailView arg0,
          YouTubeInitializationResult arg1) {

      }

      @Override public void onInitializationSuccess(YouTubeThumbnailView view,
          YouTubeThumbnailLoader loader) {
        loader.setVideo((String) view.getTag());
      }
    });

    imageVideo.setOnClickListener(v -> {
      Intent startVideoActivity = new Intent(activity, YouTubeVideoActivity.class);
      startVideoActivity.putExtra(YouTubeVideoActivity.ARG_VIDEO_NAME, videoId);
      activity.startActivity(startVideoActivity);
    });
  }

  public boolean isYoutubeInstalled(){
    return YouTubeIntents.isYouTubeInstalled(activity);
  }

  public void startPlayer(String videoId) {
    if(YouTubeIntents.isYouTubeInstalled(activity)){
      Intent startVideoActivity = new Intent(activity, YouTubeVideoActivity.class);
      startVideoActivity.putExtra(YouTubeVideoActivity.ARG_VIDEO_NAME, videoId);
      activity.startActivity(startVideoActivity);
    } else {
      String url = WATCH + videoId;
      if (!TextUtils.isEmpty(url) && URLUtil.isValidUrl(url)) {
        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
      }
    }
  }
}
