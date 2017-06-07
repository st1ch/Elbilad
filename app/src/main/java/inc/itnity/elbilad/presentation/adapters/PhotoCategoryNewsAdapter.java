package inc.itnity.elbilad.presentation.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import inc.itnity.elbilad.utils.FragmentNavigator;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 04.02.17.
 */

public class PhotoCategoryNewsAdapter
    extends RecyclerView.Adapter<PhotoCategoryNewsAdapter.SimpleNewsViewHolder>
    implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

  private static final int TYPE_TOP = 0;
  private static final int TYPE_SIMPLE = 1;
  private static final int TYPE_BANNER_100 = 2;
  private static final int TYPE_BANNER_50 = 3;

  private List<Image> articles = new ArrayList<>();
  //private List<Photo> photoList = new ArrayList<>();

  private ImageLoaderHelper imageLoaderHelper;
  private ElbiladUtils elbiladUtils;
  private FragmentNavigator fragmentNavigator;
  private GestureDetector gestureDetector;

  private HorizontalPhotoSlidePagerAdapter horizontalPhotoSlidePagerAdapter;

  private String currentItemId;
  private int currentItemPosition;
  private int touchItemId;

  private GallerySelectedListener gallerySelectedListener;

  @Inject PhotoCategoryNewsAdapter(Context context, ImageLoaderHelper imageLoaderHelper,
      ElbiladUtils elbiladUtils, FragmentNavigator fragmentNavigator,
      HorizontalPhotoSlidePagerAdapter horizontalPhotoSlidePagerAdapter) {
    this.imageLoaderHelper = imageLoaderHelper;
    this.elbiladUtils = elbiladUtils;
    gestureDetector = new GestureDetector(context, this);
    this.fragmentNavigator = fragmentNavigator;
    this.horizontalPhotoSlidePagerAdapter = horizontalPhotoSlidePagerAdapter;
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
      return new TopNewsViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_photo_news_top, parent, false));
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

      if (viewType == TYPE_TOP) {
        if (article instanceof Gallery) {
          ((TopNewsViewHolder) holder).vpPhotoSlide.setAdapter(horizontalPhotoSlidePagerAdapter);
          //((TopNewsViewHolder) holder).vpPhotoSlide.setPageTransformer(false,
          //    new DefaultTransformer());
          ((TopNewsViewHolder) holder).vpPhotoSlide.setOffscreenPageLimit(
              ((Gallery) article).getPhotos().size());
          horizontalPhotoSlidePagerAdapter.setPhotos(((Gallery) article).getPhotos());

          ((TopNewsViewHolder) holder).vpPhotoSlide.setOnTouchListener((v, event) -> {
            touchItemId = Integer.valueOf(article.getId());
            return gestureDetector.onTouchEvent(event);
          });

          ((TopNewsViewHolder) holder).ivArrowLeft.setOnClickListener(v -> {
            ((TopNewsViewHolder) holder).vpPhotoSlide.setCurrentItem(
                getNextSlidePosition(((TopNewsViewHolder) holder).vpPhotoSlide), true);
          });
          ((TopNewsViewHolder) holder).ivArrowRight.setOnClickListener(v -> {

            ((TopNewsViewHolder) holder).vpPhotoSlide.setCurrentItem(
                getPreviousSlidePosition(((TopNewsViewHolder) holder).vpPhotoSlide), true);
          });
        } else {
          gallerySelectedListener.onGallerySelected(Integer.valueOf(article.getId()));
        }

        ((TopNewsViewHolder) holder).tvPreview.setText(article.getPreview());

        //if (!TextUtils.isEmpty(article.getImage())) {
        //  imageLoaderHelper.loadGalleryImageLarge(article.getImage(), holder.ivAvatar);
        //}

        ((TopNewsViewHolder) holder).itemView.setOnTouchListener((v, event) -> {
          touchItemId = Integer.valueOf(article.getId());
          return gestureDetector.onTouchEvent(event);
        });

        //holder.itemView.setOnClickListener(
        //    v -> fragmentNavigator.startPhotoDetailsragment());
      } else {
        if (!TextUtils.isEmpty(article.getImage())) {
          imageLoaderHelper.loadGalleryImageThumb(article.getImage(), holder.ivAvatar);
        }

        holder.itemView.setOnClickListener(v -> moveToTop(position, article));
      }

      holder.tvTitle.setText(article.getTitle());
      holder.tvCategory.setText(article.getCategoryTitle());
      holder.tvDate.setText(elbiladUtils.getArticleTimeDate(article.getTime(), article.getDate()));

      //holder.itemView.setOnClickListener(
      //    v -> fragmentNavigator.startPhotoDetailsragment());
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
    //this.photoList.remove(position);
    //this.photoList.add(0, image);
    notifyDataSetChanged();
  }

  public void selectCurrentItem() {
    if (!TextUtils.isEmpty(currentItemId) && hasPhoto(currentItemId)) {
      Image item = getItem(currentItemPosition);
      this.articles.remove(currentItemPosition);
      this.articles.add(0, item);
      //this.photoList.remove(currentItemPosition);
      //this.photoList.add(0, item);
      notifyDataSetChanged();
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

  private int getNextSlidePosition(ViewPager viewPager) {
    int totalCount = horizontalPhotoSlidePagerAdapter.getCount();
    int currentItemPosition = viewPager.getCurrentItem();
    int nextItemPosition = currentItemPosition + 1;
    return (totalCount == currentItemPosition + 1) ? currentItemPosition : nextItemPosition;
  }

  private int getPreviousSlidePosition(ViewPager viewPager) {
    int currentItemPosition = viewPager.getCurrentItem();
    int previousItemPosition = currentItemPosition - 1;
    return (currentItemPosition == 0) ? currentItemPosition : previousItemPosition;
  }

  //public void setPhotoList(List<Photo> photoList) {
  //this.photoList.clear();
  //this.photoList.addAll(photoList);
  //}

  public void showGallery(Gallery gallery) {
    articles.remove(0);
    articles.add(0, gallery);
    notifyDataSetChanged();
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

    //this.photoList.clear();
    //this.photoList.addAll(articles);

    notifyDataSetChanged();
  }

  @Override public boolean onSingleTapConfirmed(MotionEvent e) {
    return false;
  }

  @Override public boolean onDoubleTap(MotionEvent e) {
    fragmentNavigator.startPhotoDetailsragment(touchItemId);
    return true;
  }

  @Override public boolean onDoubleTapEvent(MotionEvent e) {
    return false;
  }

  @Override public boolean onDown(MotionEvent e) {
    return true;
  }

  @Override public void onShowPress(MotionEvent e) {

  }

  @Override public boolean onSingleTapUp(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    return false;
  }

  @Override public void onLongPress(MotionEvent e) {

  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    return false;
  }

  public void setCurrentItemId(String currentItemId) {
    this.currentItemId = currentItemId;
  }

  public void setGallerySelectedListener(GallerySelectedListener gallerySelectedListener) {
    this.gallerySelectedListener = gallerySelectedListener;
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

  class TopNewsViewHolder extends SimpleNewsViewHolder {

    @BindView(R.id.vp_photo_slide) ViewPager vpPhotoSlide;
    @BindView(R.id.iv_arrow_left) ImageView ivArrowLeft;
    @BindView(R.id.iv_arrow_right) ImageView ivArrowRight;
    @BindView(R.id.tv_preview) TextView tvPreview;

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

  public interface GallerySelectedListener {
    void onGallerySelected(int galleryId);
  }
}
