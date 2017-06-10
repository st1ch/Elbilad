package inc.itnity.elbilad.presentation.fragments.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.constants.ApiConfig;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;
import inc.itnity.elbilad.presentation.adapters.VideoCategoryNewsAdapter;
import inc.itnity.elbilad.presentation.custom.SimpleDividerItemLineDecoration;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.VideoCategoryPresenter;
import inc.itnity.elbilad.presentation.views.VideoCategoryView;
import inc.itnity.elbilad.utils.ElbiladUtils;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import inc.itnity.elbilad.utils.YouTubeHelper;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 18.01.17.
 */

public class VideosFragment extends AbstractBaseFragment implements VideoCategoryView {

  private static final String ARG_VIDEO_ID = "video_id_arg";

  public static VideosFragment newInstance(String videoId) {
    Bundle args = new Bundle();
    args.putString(ARG_VIDEO_ID, videoId);
    VideosFragment fragment = new VideosFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @BindView(R.id.rv_news) RecyclerView rvNews;

  @Inject VideoCategoryPresenter presenter;

  @Inject VideoCategoryNewsAdapter videoCategoryNewsAdapter;

  @Inject YouTubeHelper youTubeHelper;
  @Inject ImageLoaderHelper imageLoaderHelper;
  @Inject ElbiladUtils elbiladUtils;

  private DescriptionViewHolder descriptionViewHolder;
  private VideoViewHolder videoViewHolder;
  private boolean needAutoStart = false;
  private YouTubePlayerSupportFragment youTubePlayerFragment;

  @Override public int getContentView() {
    return R.layout.fragment_video_news;
  }

  @Override public void injectComponent() {
    MainActivity.getMainActivityComponent().inject(this);
  }

  @Override protected void bindPresenter() {
    presenter.bind(this);
  }

  @Override protected void unbindPresenter() {
    presenter.onDestroy();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    ((AbstractBaseActivity) getActivity()).showHomeToolbar();
    ((AbstractBaseActivity) getActivity()).hideTitleLogo();
    ((AbstractBaseActivity) getActivity()).setTitleToolBar(getString(R.string.videos));

    View rootView = super.onCreateView(inflater, container, savedInstanceState);

    descriptionViewHolder = new DescriptionViewHolder(rootView.findViewById(R.id.video_top_container));
    videoViewHolder = new VideoViewHolder(rootView.findViewById(R.id.video_frame));

    initContent();

    presenter.onCreate();

    return rootView;
  }

  private void initContent() {
    videoCategoryNewsAdapter.setCurrentItemId(getArguments().getString(ARG_VIDEO_ID));
    videoCategoryNewsAdapter.setNotifyListener(this::showTopVideo);
    rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvNews.addItemDecoration(new SimpleDividerItemLineDecoration(getContext()));
    rvNews.setAdapter(videoCategoryNewsAdapter);
    rvNews.setNestedScrollingEnabled(false);
  }

  @Override public void showVideos(List<Video> videos) {
    videoCategoryNewsAdapter.setArticles(videos);
  }

  private void showTopVideo(){
    Video article = videoCategoryNewsAdapter.getTopVIdeo();

    String urlVideo = article.getYoutubeId();

    if (youTubeHelper.isYoutubeInstalled()) {
      videoViewHolder.ivAvatar.setVisibility(View.GONE);
      videoViewHolder.ivPlay.setVisibility(View.GONE);
      videoViewHolder.youtubeView.setVisibility(View.VISIBLE);
      youTubePlayerFragment =
          YouTubePlayerSupportFragment.newInstance();

      getChildFragmentManager().beginTransaction()
          .replace(R.id.youtube_view, youTubePlayerFragment)
          .commit();

      youTubePlayerFragment.initialize(ApiConfig.YOUTUBE_KEY,
          new YouTubePlayer.OnInitializedListener() {
            @Override public void onInitializationSuccess(YouTubePlayer.Provider provider,
                YouTubePlayer youTubePlayer, boolean wasRestored) {
              youTubePlayer.setFullscreen(false);
              if (!wasRestored && !urlVideo.isEmpty()) {
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                youTubePlayer.setFullscreenControlFlags(
                    YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
                if (needAutoStart) {
                  youTubePlayer.loadVideo(urlVideo);
                } else {
                  youTubePlayer.cueVideo(urlVideo);
                }
                youTubePlayer.setOnFullscreenListener(b -> {
                  youTubeHelper.startPlayer(urlVideo);
                });
              }
            }

            @Override public void onInitializationFailure(YouTubePlayer.Provider provider,
                YouTubeInitializationResult youTubeInitializationResult) {
            }
          });
    } else {
      videoViewHolder.youtubeView.setVisibility(View.GONE);
      videoViewHolder.ivAvatar.setVisibility(View.VISIBLE);
      videoViewHolder.ivPlay.setVisibility(View.VISIBLE);

      if (!TextUtils.isEmpty(article.getImage())) {
        imageLoaderHelper.loadVideoImageLarge(article.getImage(), videoViewHolder.ivAvatar);
      }

      videoViewHolder.itemView.setOnClickListener(
          v -> youTubeHelper.startPlayer(article.getYoutubeId()));
    }

    descriptionViewHolder.tvPreview.setText(article.getPreview());
    descriptionViewHolder.tvCategory.setText(article.getCategoryTitle());
    descriptionViewHolder.tvTitle.setText(article.getTitle());
    descriptionViewHolder.tvDate.setText(elbiladUtils.getArticleTimeDate(article.getTime(), article.getDate()));
  }

  class VideoViewHolder {
    View itemView;
    @BindView(R.id.iv_image) ImageView ivAvatar;
    @BindView(R.id.iv_play) ImageView ivPlay;
    @BindView(R.id.youtube_view) View youtubeView;

    VideoViewHolder(View itemView) {
      this.itemView = itemView;
      ButterKnife.bind(this, itemView);
    }
  }

  class DescriptionViewHolder {
    View itemView;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_preview) TextView tvPreview;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.tv_category) TextView tvCategory;

    DescriptionViewHolder(View itemView) {
      this.itemView = itemView;
      ButterKnife.bind(this, itemView);
    }
  }
}
