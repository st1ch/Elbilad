package inc.itnity.elbilad.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.ArticleItem;
import inc.itnity.elbilad.utils.ElbiladUtils;
import inc.itnity.elbilad.utils.FragmentNavigator;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 04.02.17.
 */

public class BookmarksAdapter extends RecyclerView.Adapter<BookmarksAdapter.SimpleNewsViewHolder> {

  private static final int TYPE_TOP_SIMPLE = 110;
  private static final int TYPE_TOP_VIDEO = 111;
  private static final int TYPE_TOP_PHOTO = 112;

  private List<ArticleItem> articles = new ArrayList<>();

  private ImageLoaderHelper imageLoaderHelper;
  private ElbiladUtils elbiladUtils;
  private FragmentNavigator fragmentNavigator;

  @Inject BookmarksAdapter(ImageLoaderHelper imageLoaderHelper, ElbiladUtils elbiladUtils,
      FragmentNavigator fragmentNavigator) {
    this.imageLoaderHelper = imageLoaderHelper;
    this.elbiladUtils = elbiladUtils;
    this.fragmentNavigator = fragmentNavigator;
  }

  @Override public int getItemViewType(int position) {
    int type = getItem(position).getType();
    Log.wtf("adapter", "getItemViewType: " + type);

    if (position == 0) {
      if (type == ArticleItem.TYPE.VIDEO) {
        return TYPE_TOP_VIDEO;
      } else if (type == ArticleItem.TYPE.GALLERY) {
        return TYPE_TOP_PHOTO;
      } else {
        return TYPE_TOP_SIMPLE;
      }
    }
    return type;
  }

  @Override public SimpleNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case TYPE_TOP_SIMPLE:
        return new TopNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_news_top, parent, false));
      case TYPE_TOP_VIDEO:
        return new TopNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_video_top, parent, false));
      case TYPE_TOP_PHOTO:
        return new TopNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_photo_top, parent, false));
      case ArticleItem.TYPE.VIDEO:
        return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_video, parent, false));
      case ArticleItem.TYPE.GALLERY:
        return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_photo, parent, false));
      default:
        return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_news, parent, false));
    }
  }

  @Override public void onBindViewHolder(SimpleNewsViewHolder holder, int position) {
    int viewType = getItemViewType(position);

    Log.wtf("adapter", "type: " + viewType);

    ArticleItem article = getItem(position);

    switch (viewType) {
      case TYPE_TOP_VIDEO:
        ((TopNewsViewHolder) holder).tvCategory.setText(article.getCategoryTitle());

        if (!TextUtils.isEmpty(article.getImage())) {
          imageLoaderHelper.loadUrlImageLarge(article.getImage(), holder.ivAvatar);
        }

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startVideoDetailsFragment(article.getId()));
        break;
      case TYPE_TOP_SIMPLE:
      case TYPE_TOP_PHOTO:
        ((TopNewsViewHolder) holder).tvCategory.setText(article.getCategoryTitle());

        if (!TextUtils.isEmpty(article.getImage())) {
          imageLoaderHelper.loadUrlImageLarge(article.getImage(), holder.ivAvatar);
        }

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startArticleDetailsFragment(article.getId()));
        break;
      case ArticleItem.TYPE.VIDEO:
        if (!TextUtils.isEmpty(article.getImage())) {
          imageLoaderHelper.loadUrlImageThumb(article.getImage(), holder.ivAvatar);
        }

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startVideoDetailsFragment(article.getId()));
        break;
      case ArticleItem.TYPE.GALLERY:
        if (!TextUtils.isEmpty(article.getImage())) {
          imageLoaderHelper.loadUrlImageThumb(article.getImage(), holder.ivAvatar);
        }

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startArticleDetailsFragment(article.getId()));
        break;
      default:
        if (!TextUtils.isEmpty(article.getImage())) {
          imageLoaderHelper.loadUrlImageThumb(article.getImage(), holder.ivAvatar);
        }

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startArticleDetailsFragment(article.getId()));
        break;
    }

    holder.tvDate.setText(elbiladUtils.getArticleTimeDate(article.getTime(), article.getDate()));
    holder.tvPreview.setText(article.getPreview());
  }

  @Override public int getItemCount() {
    return articles.size();
  }

  private ArticleItem getItem(int position) {
    return articles.get(position);
  }

  public void setArticles(List<ArticleItem> articles) {
    this.articles.clear();
    this.articles.addAll(articles);
    notifyDataSetChanged();
  }

  class SimpleNewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_image) ImageView ivAvatar;
    @BindView(R.id.tv_preview) TextView tvPreview;
    @BindView(R.id.tv_date) TextView tvDate;

    SimpleNewsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  class TopNewsViewHolder extends SimpleNewsViewHolder {

    @BindView(R.id.tv_category) TextView tvCategory;

    TopNewsViewHolder(View itemView) {
      super(itemView);
    }
  }
}
