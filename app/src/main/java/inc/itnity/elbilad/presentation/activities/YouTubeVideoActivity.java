package inc.itnity.elbilad.presentation.activities;

import android.os.Bundle;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.constants.ApiConfig;

public class YouTubeVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
	
	public final static String ARG_VIDEO_NAME = "video_name";
	
	private String urlVideo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		// init video view
		YouTubePlayerView videoView = (YouTubePlayerView) findViewById(R.id.id_video_view);
		videoView.initialize(ApiConfig.YOUTUBE_KEY, this);
		urlVideo = getIntent().getStringExtra(ARG_VIDEO_NAME);
	}
	
	@Override
	public void onInitializationSuccess(Provider provider,
	             YouTubePlayer player, boolean wasRestored) {
	         if (!wasRestored && !urlVideo.isEmpty()) {
	        	 player.cueVideo(urlVideo); 
	        	 player.setFullscreen(true);
	         }
	}
	
	@Override
	public void onInitializationFailure(Provider arg0,
	            YouTubeInitializationResult arg1){
	}
}
