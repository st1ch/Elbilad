package inc.itnity.elbilad.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by st1ch on 10.02.17.
 */

public class VideoSlideAdapter extends RecyclerView.Adapter<VideoSlideAdapter.VideoViewHolder> {

  private ImageLoaderHelper imageLoaderHelper;

  private List<Video> videos = new ArrayList<>();

  public VideoSlideAdapter(ImageLoaderHelper imageLoaderHelper) {
    this.imageLoaderHelper = imageLoaderHelper;
  }

  @Override
  public VideoSlideAdapter.VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new VideoViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_video_news_horizontal, parent, false));
  }

  @Override public void onBindViewHolder(VideoSlideAdapter.VideoViewHolder holder, int position) {

    Video video = getItem(position);

    if (!TextUtils.isEmpty(video.getImage())) {
      imageLoaderHelper.loadUrlImageThumb(video.getImage(), holder.ivImage);
    }

    holder.tvTitle.setText(video.getTitle());
    holder.tvDate.setText(getArticleDate(holder, video.getDate(), video.getTime()));
  }

  private String getArticleDate(VideoViewHolder holder, String date, String time){
    return holder.itemView.getContext().getString(R.string.date, time, date);
  }

  @Override public int getItemCount() {
    return videos.size();
  }

  private Video getItem(int position) {
    return videos.get(position);
  }

  public void setVideos(List<Video> videos) {
    this.videos.clear();
    this.videos.addAll(videos);
  }

  class VideoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_image) ImageView ivImage;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.tv_title) TextView tvTitle;

    VideoViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
