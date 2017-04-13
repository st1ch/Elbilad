package inc.itnity.elbilad.presentation.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
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
import inc.itnity.elbilad.utils.ElbiladUtils;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import inc.itnity.elbilad.utils.YouTubeHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 04.02.17.
 */

public class VideoCategoryNewsAdapter
    extends RecyclerView.Adapter<VideoCategoryNewsAdapter.SimpleNewsViewHolder> {

  private static final int TYPE_TOP = 0;
  private static final int TYPE_SIMPLE = 1;

  private List<Video> articles = new ArrayList<>();

  private ImageLoaderHelper imageLoaderHelper;
  private ElbiladUtils elbiladUtils;
  private FragmentManager childFragmentManager;
  //private FragmentNavigator fragmentNavigator;
  private YouTubeHelper youTubeHelper;

  @Inject VideoCategoryNewsAdapter(ImageLoaderHelper imageLoaderHelper, ElbiladUtils elbiladUtils,
      //FragmentNavigator fragmentNavigator
      YouTubeHelper youTubeHelper
  ) {
    this.imageLoaderHelper = imageLoaderHelper;
    this.elbiladUtils = elbiladUtils;
    //this.fragmentNavigator = fragmentNavigator;
    this.youTubeHelper = youTubeHelper;
  }

  @Override public int getItemViewType(int position) {
    if (position == 0) {
      return TYPE_TOP;
    }
    return TYPE_SIMPLE;
  }

  @Override public SimpleNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_TOP) {
      return new TopNewsViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_video_news_top, parent, false));
    }
    return new SimpleNewsViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_news, parent, false));
  }

  @Override public void onBindViewHolder(SimpleNewsViewHolder holder, int position) {
    int viewType = getItemViewType(position);

    Video article = getItem(position);

    if (viewType == TYPE_TOP) {
      ((TopNewsViewHolder) holder).tvPreview.setText(article.getPreview());
      String urlVideo = article.getYoutubeId();

      if(youTubeHelper.isYoutubeInstalled()){
        holder.ivAvatar.setVisibility(View.INVISIBLE);
        YouTubePlayerSupportFragment youTubePlayerFragment =
            YouTubePlayerSupportFragment.newInstance();

        childFragmentManager.beginTransaction()
            .add(R.id.youtube_view, youTubePlayerFragment)
            .commit();

        youTubePlayerFragment.initialize(ApiConfig.YOUTUBE_KEY,
            new YouTubePlayer.OnInitializedListener() {
              @Override public void onInitializationSuccess(YouTubePlayer.Provider provider,
                  YouTubePlayer youTubePlayer, boolean wasRestored) {
                youTubePlayer.setFullscreen(false);
                if (!wasRestored && !urlVideo.isEmpty()) {
                  youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                  youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
                  youTubePlayer.cueVideo(urlVideo);
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
        if (!TextUtils.isEmpty(article.getImage())) {
          imageLoaderHelper.loadVideoImageLarge(article.getImage(), holder.ivAvatar);
        }

        holder.itemView.setOnClickListener(v -> youTubeHelper.startPlayer(article.getYoutubeId()));
      }

    } else {
      if (!TextUtils.isEmpty(article.getImage())) {
        imageLoaderHelper.loadVideoImageThumb(article.getImage(), holder.ivAvatar);
      }

      holder.itemView.setOnClickListener(v -> moveToTop(position, article));
    }

    holder.tvCategory.setText(article.getCategoryTitle());
    holder.tvTitle.setText(article.getTitle());
    holder.tvDate.setText(elbiladUtils.getArticleTimeDate(article.getTime(), article.getDate()));

    //holder.itemView.setOnClickListener(
    //    v -> fragmentNavigator.startVideoDetailsFragment(article.getId(), false));
  }

  @Override public int getItemCount() {
    return articles.size();
  }

  private Video getItem(int position) {
    return articles.get(position);
  }

  public void setArticles(List<Video> articles) {
    this.articles.clear();
    this.articles.addAll(articles);
    notifyDataSetChanged();
  }

  private void moveToTop(int position, Video video) {
    this.articles.remove(position);
    this.articles.add(0, video);
    notifyDataSetChanged();
  }

  public void setChildFragmentManager(FragmentManager childFragmentManager) {
    this.childFragmentManager = childFragmentManager;
  }

  class SimpleNewsViewHolder extends RecyclerView.ViewHolder {

    @Nullable @BindView(R.id.iv_image) ImageView ivAvatar;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.tv_category) TextView tvCategory;
    @BindView(R.id.tv_title) TextView tvTitle;

    SimpleNewsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  class TopNewsViewHolder extends SimpleNewsViewHolder {

    @BindView(R.id.tv_preview) TextView tvPreview;

    TopNewsViewHolder(View itemView) {
      super(itemView);
    }
  }
}
