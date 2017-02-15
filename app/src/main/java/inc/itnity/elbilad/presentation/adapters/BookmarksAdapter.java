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
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.Bookmark;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.models.article.Video;
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
  private static final int TYPE_SIMPLE = 120;
  private static final int TYPE_TOP_VIDEO = 111;
  private static final int TYPE_VIDEO = 121;
  private static final int TYPE_TOP_PHOTO = 112;
  private static final int TYPE_PHOTO = 122;

  private List<Bookmark> bookmarks = new ArrayList<>();

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
    Bookmark.TYPE type = getItem(position).getType();
    if (position == 0) {
      if (type.equals(Bookmark.TYPE.VIDEO)) {
        return TYPE_TOP_VIDEO;
      } else if (type.equals(Bookmark.TYPE.PHOTO)) {
        return TYPE_TOP_PHOTO;
      } else {
        return TYPE_TOP_SIMPLE;
      }
    }

    if (type.equals(Bookmark.TYPE.VIDEO)) {
      return TYPE_VIDEO;
    } else if (type.equals(Bookmark.TYPE.PHOTO)) {
      return TYPE_PHOTO;
    } else {
      return TYPE_SIMPLE;
    }
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
      case TYPE_VIDEO:
        return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_video, parent, false));
      case TYPE_PHOTO:
        return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_photo, parent, false));
      default:
        return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_news, parent, false));
    }
  }

  @Override public void onBindViewHolder(SimpleNewsViewHolder holder, int position) {
    int viewType = getItemViewType(position);

    Bookmark bookmark = getItem(position);

    switch (viewType) {
      case TYPE_TOP_VIDEO:
        Video topVideo = bookmark.getVideo();

        ((TopNewsViewHolder) holder).tvCategory.setText(topVideo.getCategoryTitle());

        if (!TextUtils.isEmpty(topVideo.getImage())) {
          imageLoaderHelper.loadUrlImageLarge(topVideo.getImage(), holder.ivAvatar);
        }

        holder.tvDate.setText(
            elbiladUtils.getArticleTimeDate(topVideo.getTime(), topVideo.getDate()));
        holder.tvPreview.setText(topVideo.getPreview());

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startVideoDetailsFragment(topVideo.getId()));
        break;
      case TYPE_TOP_SIMPLE:
        Article topArticle = bookmark.getArticle();

        ((TopNewsViewHolder) holder).tvCategory.setText(topArticle.getCategoryTitle());

        if (!TextUtils.isEmpty(topArticle.getImage())) {
          imageLoaderHelper.loadUrlImageLarge(topArticle.getImage(), holder.ivAvatar);
        }

        holder.tvDate.setText(
            elbiladUtils.getArticleTimeDate(topArticle.getTime(), topArticle.getDate()));
        holder.tvPreview.setText(topArticle.getPreview());

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startVideoDetailsFragment(topArticle.getId()));
        break;
      case TYPE_TOP_PHOTO:
        Image topPhoto = bookmark.getPhoto();

        ((TopNewsViewHolder) holder).tvCategory.setText(topPhoto.getCategoryTitle());

        if (!TextUtils.isEmpty(topPhoto.getImage())) {
          imageLoaderHelper.loadUrlImageLarge(topPhoto.getImage(), holder.ivAvatar);
        }

        holder.tvDate.setText(
            elbiladUtils.getArticleTimeDate(topPhoto.getTime(), topPhoto.getDate()));
        holder.tvPreview.setText(topPhoto.getPreview());

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startVideoDetailsFragment(topPhoto.getId()));
        break;
      case TYPE_VIDEO:
        Video video = bookmark.getVideo();

        if (!TextUtils.isEmpty(video.getImage())) {
          imageLoaderHelper.loadUrlImageThumb(video.getImage(), holder.ivAvatar);
        }

        holder.tvDate.setText(elbiladUtils.getArticleTimeDate(video.getTime(), video.getDate()));
        holder.tvPreview.setText(video.getPreview());

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startVideoDetailsFragment(video.getId()));
        break;
      case TYPE_PHOTO:
        Image photo = bookmark.getPhoto();

        if (!TextUtils.isEmpty(photo.getImage())) {
          imageLoaderHelper.loadUrlImageThumb(photo.getImage(), holder.ivAvatar);
        }

        holder.tvDate.setText(elbiladUtils.getArticleTimeDate(photo.getTime(), photo.getDate()));
        holder.tvPreview.setText(photo.getPreview());

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startVideoDetailsFragment(photo.getId()));
        break;
      case TYPE_SIMPLE:
        Article article = bookmark.getArticle();

        if (!TextUtils.isEmpty(article.getImage())) {
          imageLoaderHelper.loadUrlImageThumb(article.getImage(), holder.ivAvatar);
        }

        holder.tvDate.setText(
            elbiladUtils.getArticleTimeDate(article.getTime(), article.getDate()));
        holder.tvPreview.setText(article.getPreview());

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startVideoDetailsFragment(article.getId()));
        break;
    }
  }

  @Override public int getItemCount() {
    return bookmarks.size();
  }

  private Bookmark getItem(int position) {
    return bookmarks.get(position);
  }

  public void setArticles(List<Bookmark> bookmarks) {
    //for (BaseArticle article : articles) {
    //  if (article.getType() == 10) {
    //    Log.wtf("adapter", ((Video) article).toString());
    //  } else {
    //    Log.wtf("adapter", ((Article) article).toString());
    //  }
    //}
    this.bookmarks.clear();
    this.bookmarks.addAll(bookmarks);
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
