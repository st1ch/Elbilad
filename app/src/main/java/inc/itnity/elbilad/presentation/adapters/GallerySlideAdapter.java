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
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.utils.FragmentNavigator;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by st1ch on 10.02.17.
 */

public class GallerySlideAdapter extends RecyclerView.Adapter<GallerySlideAdapter.ImageViewHolder> {

  private ImageLoaderHelper imageLoaderHelper;
  private FragmentNavigator fragmentNavigator;

  private List<Image> images = new ArrayList<>();

  public GallerySlideAdapter(ImageLoaderHelper imageLoaderHelper,
      FragmentNavigator fragmentNavigator) {
    this.imageLoaderHelper = imageLoaderHelper;
    this.fragmentNavigator = fragmentNavigator;
  }

  @Override
  public GallerySlideAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ImageViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_photo_news_horizontal, parent, false));
  }

  @Override public void onBindViewHolder(GallerySlideAdapter.ImageViewHolder holder, int position) {

    Image image = getItem(position);

    if (!TextUtils.isEmpty(image.getImage())) {
      imageLoaderHelper.loadGalleryImageUne(image.getImage(), holder.ivImage);
    }

    holder.tvTitle.setText(image.getTitle());
    holder.tvDate.setText(getArticleDate(holder, image.getDate(), image.getTime()));

    //holder.itemView.setOnClickListener(v -> fragmentNavigator.startPhotoDetailsragment());
    holder.itemView.setOnClickListener(v -> fragmentNavigator.startPhotosFragment());
  }

  private String getArticleDate(ImageViewHolder holder, String date, String time) {
    return holder.itemView.getContext().getString(R.string.date, time, date);
  }

  @Override public int getItemCount() {
    return images.size();
  }

  private Image getItem(int position) {
    return images.get(position);
  }

  public void setImages(List<Image> videos) {
    this.images.clear();
    this.images.addAll(videos);
  }

  class ImageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_image) ImageView ivImage;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.tv_title) TextView tvTitle;

    ImageViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
