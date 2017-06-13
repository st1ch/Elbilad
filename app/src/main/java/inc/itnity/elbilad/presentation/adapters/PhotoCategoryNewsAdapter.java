package inc.itnity.elbilad.presentation.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.ArticleItem;
import inc.itnity.elbilad.domain.models.article.Gallery;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.utils.ElbiladUtils;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 04.02.17.
 */

public class PhotoCategoryNewsAdapter
    extends RecyclerView.Adapter<PhotoCategoryNewsAdapter.SimpleNewsViewHolder> {

  private static final int TYPE_TOP = 0;
  private static final int TYPE_SIMPLE = 1;
  private static final int TYPE_BANNER_100 = 2;
  private static final int TYPE_BANNER_50 = 3;

  private List<Image> articles = new ArrayList<>();

  private ImageLoaderHelper imageLoaderHelper;
  private ElbiladUtils elbiladUtils;

  private String currentItemId;
  private int currentItemPosition;

  private NotifyListener notifyListener;

  @Inject PhotoCategoryNewsAdapter(ImageLoaderHelper imageLoaderHelper, ElbiladUtils elbiladUtils) {
    this.imageLoaderHelper = imageLoaderHelper;
    this.elbiladUtils = elbiladUtils;
  }

  @Override public int getItemViewType(int position) {
    if (position == 0) {
      return TYPE_TOP;
    }
    if (getItem(position).getType() == ArticleItem.TYPE.BANNER_100) {
      return TYPE_BANNER_100;
    } else if (getItem(position).getType() == ArticleItem.TYPE.BANNER_50) {
      return TYPE_BANNER_50;
    }
    return TYPE_SIMPLE;
  }

  @Override public SimpleNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_TOP) {
      return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.empty_placeholder, parent, false));
    }

    if (viewType == TYPE_BANNER_100) {
      return new BannerViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_banner_4588, parent, false));
    }

    if (viewType == TYPE_BANNER_50) {
      return new BannerViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_banner_6582, parent, false));
    }

    return new SimpleNewsViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_news, parent, false));
  }

  @Override public void onBindViewHolder(SimpleNewsViewHolder holder, int position) {
    int viewType = getItemViewType(position);

    if (viewType != TYPE_BANNER_100 && viewType != TYPE_BANNER_50) {
      Image article = getItem(position);

      if (viewType != TYPE_TOP) {
        if (!TextUtils.isEmpty(article.getImage())) {
          imageLoaderHelper.loadGalleryImageThumb(article.getImage(), holder.ivAvatar);
        }

        holder.itemView.setOnClickListener(v -> moveToTop(position, article));

        holder.tvTitle.setText(article.getTitle());
        holder.tvCategory.setText(article.getCategoryTitle());
        holder.tvDate.setText(
            elbiladUtils.getArticleTimeDate(article.getTime(), article.getDate()));
      }
    }
  }

  @Override public int getItemCount() {
    return articles.size();
  }

  private Image getItem(int position) {
    return articles.get(position);
  }

  private void moveToTop(int position, Image image) {
    this.articles.remove(position);
    this.articles.add(0, image);
    notifyDataSetChanged();

    if (notifyListener != null) {
      notifyListener.onNotifyDataSetChanged();
    }
  }

  private void selectCurrentItem() {
    if (!TextUtils.isEmpty(currentItemId) && hasPhoto(currentItemId)) {
      moveToTop(currentItemPosition, getItem(currentItemPosition));
    } else {
      if (notifyListener != null) {
        notifyListener.onNotifyDataSetChanged();
      }
    }
  }

  private boolean hasPhoto(String itemId) {
    for (int i = 0; i < articles.size(); i++) {
      Image image = articles.get(i);
      String imageId = image.getId();
      if (!TextUtils.isEmpty(imageId) && imageId.equals(itemId)) {
        currentItemPosition = i;
        return true;
      }
    }
    return false;
  }

  public void setArticles(List<Image> articles) {
    this.articles.clear();

    if (articles.size() > 8) {
      for (int i = 0; i < 3; i++) {
        this.articles.add(articles.get(i));
      }
      this.articles.add(new Image(ArticleItem.TYPE.BANNER_100));
      for (int i = 3; i < 8; i++) {
        this.articles.add(articles.get(i));
      }
      this.articles.add(new Image(ArticleItem.TYPE.BANNER_50));
      for (int i = 8; i < articles.size(); i++) {
        this.articles.add(articles.get(i));
      }
    } else if (articles.size() > 3) {
      for (int i = 0; i < 3; i++) {
        this.articles.add(articles.get(i));
      }
      this.articles.add(new Image(ArticleItem.TYPE.BANNER_100));
      for (int i = 3; i < articles.size(); i++) {
        this.articles.add(articles.get(i));
      }
    } else {
      this.articles.addAll(articles);
    }

    notifyDataSetChanged();

    selectCurrentItem();
  }

  public void showGallery(Gallery gallery){
    articles.remove(0);
    articles.add(0, gallery);
    notifyDataSetChanged();

    if (notifyListener != null) {
      notifyListener.onNotifyDataSetChanged();
    }
  }

  public Image getTopImage() {
    return articles.get(0);
  }

  public void setCurrentItemId(String currentItemId) {
    this.currentItemId = currentItemId;
  }

  public void setNotifyListener(NotifyListener notifyListener) {
    this.notifyListener = notifyListener;
  }

  class SimpleNewsViewHolder extends RecyclerView.ViewHolder {

    @Nullable @BindView(R.id.iv_image) ImageView ivAvatar;
    @Nullable @BindView(R.id.tv_date) TextView tvDate;
    @Nullable @BindView(R.id.tv_category) TextView tvCategory;
    @Nullable @BindView(R.id.tv_title) TextView tvTitle;

    SimpleNewsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  class BannerViewHolder extends SimpleNewsViewHolder {

    @BindView(R.id.adView) AdView adView;

    BannerViewHolder(View itemView) {
      super(itemView);
      AdRequest adRequest = new AdRequest.Builder().build();
      adView.loadAd(adRequest);
    }
  }

  public interface NotifyListener {
    void onNotifyDataSetChanged();
  }
}
