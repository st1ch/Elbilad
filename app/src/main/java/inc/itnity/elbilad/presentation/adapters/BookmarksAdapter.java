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

  //private static final int TYPE_TOP_SIMPLE = 110;
  private static final int TYPE_SIMPLE = 120;
  //private static final int TYPE_TOP_VIDEO = 111;
  private static final int TYPE_VIDEO = 121;
  //private static final int TYPE_TOP_PHOTO = 112;
  private static final int TYPE_PHOTO = 122;
  private static final int TYPE_BANNER_100 = 123;
  private static final int TYPE_BANNER_50 = 124;

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
    //if (position == 0) {
    //  if (type.equals(Bookmark.TYPE.VIDEO)) {
    //    return TYPE_TOP_VIDEO;
    //  } else if (type.equals(Bookmark.TYPE.PHOTO)) {
    //    return TYPE_TOP_PHOTO;
    //  } else {
    //    return TYPE_TOP_SIMPLE;
    //  }
    //}

    if (type.equals(Bookmark.TYPE.VIDEO)) {
      return TYPE_VIDEO;
    } else if (type.equals(Bookmark.TYPE.PHOTO)) {
      return TYPE_PHOTO;
    } else if (type.equals(Bookmark.TYPE.BANNER_100)) {
      return TYPE_BANNER_100;
    } else if (type.equals(Bookmark.TYPE.BANNER_50)) {
      return TYPE_BANNER_50;
    } else {
      return TYPE_SIMPLE;
    }
  }

  @Override public SimpleNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      //case TYPE_TOP_SIMPLE:
      //  return new TopNewsViewHolder(LayoutInflater.from(parent.getContext())
      //      .inflate(R.layout.item_category_news_top, parent, false));
      //case TYPE_TOP_VIDEO:
      //  return new TopNewsViewHolder(LayoutInflater.from(parent.getContext())
      //      .inflate(R.layout.item_category_video_top, parent, false));
      //case TYPE_TOP_PHOTO:
      //  return new TopNewsViewHolder(LayoutInflater.from(parent.getContext())
      //      .inflate(R.layout.item_category_photo_top, parent, false));
      case TYPE_VIDEO:
        return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_video, parent, false));
      case TYPE_PHOTO:
        return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_photo, parent, false));
      case TYPE_BANNER_100:
        return new BannerViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_banner_4588, parent, false));
      case TYPE_BANNER_50:
        return new BannerViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_banner_6582, parent, false));
      default:
        return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_news, parent, false));
    }
  }

  @Override public void onBindViewHolder(SimpleNewsViewHolder holder, int position) {
    int viewType = getItemViewType(position);

    Bookmark bookmark = getItem(position);

    switch (viewType) {
      //case TYPE_TOP_VIDEO:
      //  Video topVideo = bookmark.getVideo();
      //
      //  ((TopNewsViewHolder) holder).tvCategory.setText(topVideo.getCategoryTitle());
      //
      //  if (!TextUtils.isEmpty(topVideo.getImage())) {
      //    imageLoaderHelper.loadVideoImageLarge(topVideo.getImage(), holder.ivAvatar);
      //  }
      //
      //  holder.tvDate.setText(
      //      elbiladUtils.getArticleTimeDate(topVideo.getTime(), topVideo.getDate()));
      //  holder.tvPreview.setText(topVideo.getPreview());
      //
      //  holder.itemView.setOnClickListener(
      //      v -> fragmentNavigator.startVideoDetailsFragment(topVideo.getId(),
      //          topVideo.isArticle()));
      //  break;
      //case TYPE_TOP_SIMPLE:
      //  Article topArticle = bookmark.getArticle();
      //
      //  ((TopNewsViewHolder) holder).tvCategory.setText(topArticle.getCategoryTitle());
      //
      //  if (!TextUtils.isEmpty(topArticle.getImage())) {
      //    imageLoaderHelper.loadUrlImageLarge(topArticle.getImage(), holder.ivAvatar);
      //  }
      //
      //  holder.tvDate.setText(
      //      elbiladUtils.getArticleTimeDate(topArticle.getTime(), topArticle.getDate()));
      //  holder.tvPreview.setText(topArticle.getPreview());
      //
      //  holder.itemView.setOnClickListener(
      //      v -> fragmentNavigator.startArticleDetailsFragment(topArticle.getId()));
      //  break;
      //case TYPE_TOP_PHOTO:
      //  Image topPhoto = bookmark.getPhoto();
      //
      //  ((TopNewsViewHolder) holder).tvCategory.setText(topPhoto.getCategoryTitle());
      //
      //  if (!TextUtils.isEmpty(topPhoto.getImage())) {
      //    imageLoaderHelper.loadGalleryImageLarge(topPhoto.getImage(), holder.ivAvatar);
      //  }
      //
      //  holder.tvDate.setText(
      //      elbiladUtils.getArticleTimeDate(topPhoto.getTime(), topPhoto.getDate()));
      //  holder.tvPreview.setText(topPhoto.getPreview());
      //
      //  holder.itemView.setOnClickListener(v -> fragmentNavigator.startPhotoDetailsragment());
      //  break;
      case TYPE_VIDEO:
        Video video = bookmark.getVideo();

        if (!TextUtils.isEmpty(video.getImage())) {
          imageLoaderHelper.loadVideoImageLarge(video.getImage(), holder.ivAvatar);
        }

        holder.tvDate.setText(elbiladUtils.getArticleTimeDate(video.getTime(), video.getDate()));
        holder.tvPreview.setText(video.getPreview());

        holder.itemView.setOnClickListener(
            v -> fragmentNavigator.startVideoDetailsFragment(video.getId(), video.isArticle()));
        break;
      case TYPE_PHOTO:
        Image photo = bookmark.getPhoto();

        if (!TextUtils.isEmpty(photo.getImage())) {
          imageLoaderHelper.loadGalleryImageLarge(photo.getImage(), holder.ivAvatar);
        }

        holder.tvDate.setText(elbiladUtils.getArticleTimeDate(photo.getTime(), photo.getDate()));
        holder.tvPreview.setText(photo.getPreview());

        holder.itemView.setOnClickListener(v -> fragmentNavigator.startPhotoDetailsragment());
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
            v -> fragmentNavigator.startArticleDetailsFragment(article.getId()));
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

    if (bookmarks.size() > 8) {
      for (int i = 0; i < 3; i++) {
        this.bookmarks.add(bookmarks.get(i));
      }
      this.bookmarks.add(new Bookmark(Bookmark.TYPE.BANNER_100));
      for (int i = 3; i < 8; i++) {
        this.bookmarks.add(bookmarks.get(i));
      }
      this.bookmarks.add(new Bookmark(Bookmark.TYPE.BANNER_50));
      for (int i = 8; i < bookmarks.size(); i++) {
        this.bookmarks.add(bookmarks.get(i));
      }
    } else if (bookmarks.size() > 3) {
      for (int i = 0; i < 3; i++) {
        this.bookmarks.add(bookmarks.get(i));
      }
      this.bookmarks.add(new Bookmark(Bookmark.TYPE.BANNER_100));
      for (int i = 3; i < bookmarks.size(); i++) {
        this.bookmarks.add(bookmarks.get(i));
      }
    } else {
      this.bookmarks.addAll(bookmarks);
    }
    notifyDataSetChanged();
  }

  class SimpleNewsViewHolder extends RecyclerView.ViewHolder {

    @Nullable @BindView(R.id.iv_image) ImageView ivAvatar;
    @Nullable @BindView(R.id.tv_preview) TextView tvPreview;
    @Nullable @BindView(R.id.tv_date) TextView tvDate;

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

  class BannerViewHolder extends SimpleNewsViewHolder {

    @BindView(R.id.adView) AdView adView;

    BannerViewHolder(View itemView) {
      super(itemView);
      AdRequest adRequest = new AdRequest.Builder().build();
      adView.loadAd(adRequest);
    }
  }
}
