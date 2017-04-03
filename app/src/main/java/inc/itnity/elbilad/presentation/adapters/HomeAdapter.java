package inc.itnity.elbilad.presentation.adapters;

import android.content.Context;
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
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.ArticleItem;
import inc.itnity.elbilad.domain.models.article.ArticleTop5;
import inc.itnity.elbilad.domain.models.article.ArticleVideo;
import inc.itnity.elbilad.domain.models.article.CategoryHeader;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.presentation.custom.HorizontalSpaceItemDecoration;
import inc.itnity.elbilad.utils.FragmentNavigator;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 19.01.17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeItemViewHolder> {

  private Context context;
  private ImageLoaderHelper imageLoaderHelper;
  private FragmentNavigator fragmentNavigator;

  private List<ArticleItem> articles = new ArrayList<>();
  private List<Video> videos = new ArrayList<>();
  private List<Image> gallery = new ArrayList<>();

  @Inject HomeAdapter(Context context, ImageLoaderHelper imageLoaderHelper,
      FragmentNavigator fragmentNavigator) {
    this.context = context;
    this.imageLoaderHelper = imageLoaderHelper;
    this.fragmentNavigator = fragmentNavigator;
  }

  @Override public int getItemViewType(int position) {
    if (position == 0) {
      return ArticleItem.TYPE.TOP;
    } else {
      return getItem(position).getType();
    }
  }

  @Override public HomeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case ArticleItem.TYPE.TOP:
        return new TopNewsItemViewHolder(getView(parent, R.layout.item_home_news_top));
      case ArticleItem.TYPE.TOP_5:
      case ArticleItem.TYPE.IMPORTANT:
      case ArticleItem.TYPE.INTERNATIONAL:
      case ArticleItem.TYPE.MUSIC:
      case ArticleItem.TYPE.MAHAKIM:
      case ArticleItem.TYPE.CULTURE:
        return new RegularNewsItemViewHolder(getView(parent, R.layout.item_home_news));
      case ArticleItem.TYPE.MOST_READ:
        return new MostReadItemViewHolder(getView(parent, R.layout.item_home_most_read));
      case ArticleItem.TYPE.SPORT:
      case ArticleItem.TYPE.RASID:
        return new RegularNewsImageItemViewHolder(getView(parent, R.layout.item_home_news_image));
      case ArticleItem.TYPE.VIDEO_ARTICLE:
        return new VideoArticleItemViewHolder(getView(parent, R.layout.item_home_news_video));
      case ArticleItem.TYPE.VIDEO:
        return new VideoItemViewHolder(getView(parent, R.layout.item_video_container));
      case ArticleItem.TYPE.GALLERY:
        return new GalleryItemViewHolder(getView(parent, R.layout.item_gallery_container));
      case ArticleItem.TYPE.CATEGORY_HEADER:
        return new CategoryHeaderViewHolder(
            getView(parent, R.layout.item_home_news_category_header));
      case ArticleItem.TYPE.CATEGORY_HEADER_ORANGE:
        return new CategoryHeaderViewHolder(
            getView(parent, R.layout.item_home_news_category_header_orange));
      default:
        return null;
    }
  }

  private View getView(ViewGroup parent, int layoutId) {
    return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
  }

  @Override public void onBindViewHolder(HomeItemViewHolder holder, int position) {
    int viewType = getItemViewType(position);

    ArticleItem article = getItem(position);

    switch (viewType) {
      case ArticleItem.TYPE.TOP:
        ArticleTop5 topArticle = (ArticleTop5) article;

        String imageTop = topArticle.getImage();
        if (!TextUtils.isEmpty(imageTop)) {
          imageLoaderHelper.loadUrlImageUneSlide(imageTop,
              ((TopNewsItemViewHolder) holder).ivImage);
        }

        ((TopNewsItemViewHolder) holder).tvTitle.setText(topArticle.getTitle());
        ((TopNewsItemViewHolder) holder).tvDate.setText(
            getArticleDate(holder, topArticle.getDate(), topArticle.getTime()));
        ((TopNewsItemViewHolder) holder).tvAuthor.setText(topArticle.getCategoryTitle());
        ((TopNewsItemViewHolder) holder).itemView.setOnClickListener(
            v -> fragmentNavigator.startArticleDetailsFragment(topArticle.getId()));
        break;
      case ArticleItem.TYPE.TOP_5:
      case ArticleItem.TYPE.IMPORTANT:
      case ArticleItem.TYPE.INTERNATIONAL:
      case ArticleItem.TYPE.MUSIC:
      case ArticleItem.TYPE.MAHAKIM:
      case ArticleItem.TYPE.CULTURE:
        Article regularArticle = (Article) article;

        String imageRegular = regularArticle.getImage();
        if (!TextUtils.isEmpty(imageRegular)) {
          imageLoaderHelper.loadUrlImageThumb(imageRegular,
              ((RegularNewsItemViewHolder) holder).ivImage);
        }

        ((RegularNewsItemViewHolder) holder).tvPreview.setText(regularArticle.getTitle());
        ((RegularNewsItemViewHolder) holder).tvDate.setText(
            getArticleDate(holder, regularArticle.getDate(), regularArticle.getTime()));
        ((RegularNewsItemViewHolder) holder).tvAuthor.setText(regularArticle.getCategoryTitle());
        ((RegularNewsItemViewHolder) holder).itemView.setOnClickListener(
            v -> fragmentNavigator.startArticleDetailsFragment(regularArticle.getId()));
        break;
      case ArticleItem.TYPE.MOST_READ:
        Article mostReadArticle = (Article) article;

        String imageMostRead = mostReadArticle.getImage();
        if (!TextUtils.isEmpty(imageMostRead)) {
          imageLoaderHelper.loadUrlImageThumb(imageMostRead,
              ((MostReadItemViewHolder) holder).ivImage);
        }

        ((MostReadItemViewHolder) holder).tvReadCount.setText(mostReadArticle.getNumberViews());
        ((MostReadItemViewHolder) holder).tvPreview.setText(mostReadArticle.getTitle());
        ((MostReadItemViewHolder) holder).tvDate.setText("2017-03-19 20:58");
        //((MostReadItemViewHolder) holder).tvDate.setText(
        //    getArticleDate(holder, mostReadArticle.getDate(), mostReadArticle.getTime()));
        ((MostReadItemViewHolder) holder).tvAuthor.setText(mostReadArticle.getCategoryTitle());
        ((MostReadItemViewHolder) holder).itemView.setOnClickListener(
            v -> fragmentNavigator.startArticleDetailsFragment(mostReadArticle.getId()));

        break;
      case ArticleItem.TYPE.SPORT:
      case ArticleItem.TYPE.RASID:
        Article regularImageArticle = (Article) article;

        String imageRegularBig = regularImageArticle.getImage();
        if (!TextUtils.isEmpty(imageRegularBig)) {
          imageLoaderHelper.loadUrlImageLargeCat(imageRegularBig,
              ((RegularNewsImageItemViewHolder) holder).ivImage);
        }

        ((RegularNewsImageItemViewHolder) holder).tvPreview.setText(
            regularImageArticle.getPreview());
        ((RegularNewsImageItemViewHolder) holder).tvTitle.setText(regularImageArticle.getTitle());
        ((RegularNewsImageItemViewHolder) holder).tvDate.setText(
            getArticleDate(holder, regularImageArticle.getDate(), regularImageArticle.getTime()));
        ((RegularNewsImageItemViewHolder) holder).itemView.setOnClickListener(
            v -> fragmentNavigator.startArticleDetailsFragment(regularImageArticle.getId()));
        break;
      case ArticleItem.TYPE.VIDEO_ARTICLE:
        Article videoImageArticle = (Article) article;

        String imageVideo = videoImageArticle.getImage();
        if (!TextUtils.isEmpty(imageVideo)) {
          imageLoaderHelper.loadUrlImageLargeCat(imageVideo,
              ((VideoArticleItemViewHolder) holder).ivImage);
        }

        ((VideoArticleItemViewHolder) holder).tvPreview.setText(videoImageArticle.getTitle());
        ((VideoArticleItemViewHolder) holder).tvTitle.setText(videoImageArticle.getTitle());
        ((VideoArticleItemViewHolder) holder).tvAuthor.setText(
            videoImageArticle.getCategoryTitle());
        ((VideoArticleItemViewHolder) holder).tvDate.setText(
            getArticleDate(holder, videoImageArticle.getDate(), videoImageArticle.getTime()));

        ((VideoArticleItemViewHolder) holder).itemView.setOnClickListener(
            v -> fragmentNavigator.startVideoDetailsFragment(videoImageArticle.getId(), true));
        break;
      case ArticleItem.TYPE.VIDEO:
        LinearLayoutManager videoLayoutManager =
            new LinearLayoutManager(((VideoItemViewHolder) holder).itemView.getContext());
        videoLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ((VideoItemViewHolder) holder).rvVideoNews.setLayoutManager(videoLayoutManager);
        ((VideoItemViewHolder) holder).rvVideoNews.addItemDecoration(
            new HorizontalSpaceItemDecoration());
        VideoSlideAdapter videoSlideAdapter =
            new VideoSlideAdapter(imageLoaderHelper, fragmentNavigator);
        ((VideoItemViewHolder) holder).rvVideoNews.setAdapter(videoSlideAdapter);
        videoSlideAdapter.setVideos(videos);
        break;
      case ArticleItem.TYPE.GALLERY:
        LinearLayoutManager galleryLayoutManager =
            new LinearLayoutManager(((GalleryItemViewHolder) holder).itemView.getContext());
        galleryLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ((GalleryItemViewHolder) holder).rvGalleryNews.setLayoutManager(galleryLayoutManager);
        ((GalleryItemViewHolder) holder).rvGalleryNews.addItemDecoration(
            new HorizontalSpaceItemDecoration());
        GallerySlideAdapter gallerySlideAdapter =
            new GallerySlideAdapter(imageLoaderHelper, fragmentNavigator);
        ((GalleryItemViewHolder) holder).rvGalleryNews.setAdapter(gallerySlideAdapter);
        gallerySlideAdapter.setImages(gallery);
        break;
      case ArticleItem.TYPE.CATEGORY_HEADER:
      case ArticleItem.TYPE.CATEGORY_HEADER_ORANGE:
        CategoryHeader header = (CategoryHeader) getItem(position);
        ((CategoryHeaderViewHolder) holder).tvTitle.setText(header.getTitle());
        break;
      default:
        break;
    }
  }

  private String getArticleDate(HomeItemViewHolder holder, String date, String time) {
    return holder.itemView.getContext().getString(R.string.date, time, date);
  }

  @Override public int getItemCount() {
    return articles.size();
  }

  private ArticleItem getItem(int position) {
    return articles.get(position);
  }

  public void setArticles(HomeArticles articles) {
    List<ArticleVideo> videoArticles = articles.getVideoArticles();

    this.articles.clear();

    this.articles.addAll(articles.getTop5Articles());

    this.articles.add(new Video());
    this.videos.clear();
    this.videos.addAll(articles.getVideos());

    this.articles.addAll(articles.getImportantArticles());
    if (videoArticles != null && videoArticles.size() > 0) {
      this.articles.add(videoArticles.get(0));
    }

    this.articles.add(
        new CategoryHeader(context.getString(R.string.news_title_international), false));
    this.articles.addAll(articles.getInternationalArticles());

    this.articles.add(new CategoryHeader(context.getString(R.string.news_title_sport), false));
    this.articles.addAll(articles.getSportArticles());
    if (videoArticles != null && videoArticles.size() > 1) {
      this.articles.add(videoArticles.get(1));
    }

    this.articles.add(new CategoryHeader(context.getString(R.string.news_title_music), false));
    this.articles.addAll(articles.getMusicArticles());

    this.articles.add(new CategoryHeader(context.getString(R.string.news_title_mahakim), false));
    this.articles.addAll(articles.getMahakimArticles());

    this.articles.add(new CategoryHeader(context.getString(R.string.news_title_culture), false));
    this.articles.addAll(articles.getCultureArticles());
    if (videoArticles != null && videoArticles.size() > 2) {
      this.articles.add(videoArticles.get(2));
    }

    this.articles.add(new CategoryHeader(context.getString(R.string.news_title_rasid), false));
    this.articles.addAll(articles.getRasidArticles());

    this.articles.add(new Image());
    this.gallery.clear();
    this.gallery.addAll(articles.getGallery());

    this.articles.add(new CategoryHeader(context.getString(R.string.news_most_read), true));
    this.articles.addAll(articles.getMostReadArticles());

    notifyDataSetChanged();
  }

  class HomeItemViewHolder extends RecyclerView.ViewHolder {

    @Nullable @BindView(R.id.iv_image) ImageView ivImage;
    @Nullable @BindView(R.id.tv_date) TextView tvDate;
    @Nullable @BindView(R.id.tv_category) TextView tvAuthor;

    HomeItemViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  class TopNewsItemViewHolder extends HomeItemViewHolder {

    @BindView(R.id.tv_title) TextView tvTitle;

    TopNewsItemViewHolder(View itemView) {
      super(itemView);
    }
  }

  class RegularNewsItemViewHolder extends HomeItemViewHolder {

    @BindView(R.id.tv_preview) TextView tvPreview;

    RegularNewsItemViewHolder(View itemView) {
      super(itemView);
    }
  }

  class MostReadItemViewHolder extends RegularNewsItemViewHolder {

    @BindView(R.id.tv_read_count) TextView tvReadCount;

    MostReadItemViewHolder(View itemView) {
      super(itemView);
    }
  }

  class RegularNewsImageItemViewHolder extends HomeItemViewHolder {

    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_preview) TextView tvPreview;

    RegularNewsImageItemViewHolder(View itemView) {
      super(itemView);
    }
  }

  class VideoArticleItemViewHolder extends HomeItemViewHolder {

    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_preview) TextView tvPreview;

    VideoArticleItemViewHolder(View itemView) {
      super(itemView);
    }
  }

  class VideoItemViewHolder extends HomeItemViewHolder {

    @BindView(R.id.rv_video_news) RecyclerView rvVideoNews;

    VideoItemViewHolder(View itemView) {
      super(itemView);
    }
  }

  class GalleryItemViewHolder extends HomeItemViewHolder {

    @BindView(R.id.rv_gallery_news) RecyclerView rvGalleryNews;

    GalleryItemViewHolder(View itemView) {
      super(itemView);
    }
  }

  class CategoryHeaderViewHolder extends HomeItemViewHolder {

    @BindView(R.id.tv_title) TextView tvTitle;

    CategoryHeaderViewHolder(View itemView) {
      super(itemView);
    }
  }
}
