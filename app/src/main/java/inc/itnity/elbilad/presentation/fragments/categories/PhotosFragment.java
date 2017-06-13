package inc.itnity.elbilad.presentation.fragments.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Gallery;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.models.article.Photo;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;
import inc.itnity.elbilad.presentation.adapters.HorizontalPhotoSlidePagerAdapter;
import inc.itnity.elbilad.presentation.adapters.PhotoCategoryNewsAdapter;
import inc.itnity.elbilad.presentation.custom.SimpleDividerItemLineDecoration;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.PhotoCategoryPresenter;
import inc.itnity.elbilad.presentation.views.PhotoCategoryView;
import inc.itnity.elbilad.utils.ElbiladUtils;
import inc.itnity.elbilad.utils.FragmentNavigator;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 18.01.17.
 */

public class PhotosFragment extends AbstractBaseFragment
    implements PhotoCategoryView, GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {

  private static final String ARG_PHOTO_ID = "photo_id_arg";

  public static PhotosFragment newInstance(String photoId) {
    Bundle args = new Bundle();
    args.putString(ARG_PHOTO_ID, photoId);
    PhotosFragment fragment = new PhotosFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @BindView(R.id.rv_news) RecyclerView rvNews;

  @Inject PhotoCategoryPresenter presenter;

  @Inject PhotoCategoryNewsAdapter photoCategoryNewsAdapter;
  @Inject HorizontalPhotoSlidePagerAdapter horizontalPhotoSlidePagerAdapter;

  @Inject FragmentNavigator fragmentNavigator;
  @Inject ElbiladUtils elbiladUtils;

  private GalleryViewHolder galleryViewHolder;
  private DescriptionViewHolder descriptionViewHolder;
  private GestureDetector gestureDetector;
  private int touchItemId;
  private int viewPagerPosition;

  @Override public int getContentView() {
    return R.layout.fragment_photo_news;
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
    ((AbstractBaseActivity) getActivity()).setTitleToolBar(getString(R.string.photos));

    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    galleryViewHolder = new GalleryViewHolder(rootView.findViewById(R.id.gallery_frame));
    descriptionViewHolder =
        new DescriptionViewHolder(rootView.findViewById(R.id.video_top_container));
    initContent();

    presenter.onCreate();

    return rootView;
  }

  private void initContent() {
    gestureDetector = new GestureDetector(getContext(), this);

    photoCategoryNewsAdapter.setCurrentItemId(getArguments().getString(ARG_PHOTO_ID));
    photoCategoryNewsAdapter.setNotifyListener(this::showGallery);
    rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvNews.addItemDecoration(new SimpleDividerItemLineDecoration(getActivity()));
    rvNews.setAdapter(photoCategoryNewsAdapter);
    rvNews.setNestedScrollingEnabled(false);

    galleryViewHolder.vpPhotoSlide.setAdapter(horizontalPhotoSlidePagerAdapter);
  }

  @Override public void showPhotos(List<Image> images) {
    photoCategoryNewsAdapter.setArticles(images);
  }

  @Override public void showGallery(Gallery gallery) {
    Log.wtf(TAG, "showGallery: " + gallery);
    photoCategoryNewsAdapter.showGallery(gallery);
  }

  private void showGallery() {
    Image article = photoCategoryNewsAdapter.getTopImage();

    Log.wtf(TAG, "showGallery: IMAGE: " + article);

    if (article instanceof Gallery) {
      //((TopNewsViewHolder) holder).vpPhotoSlide.setPageTransformer(false,
      //    new DefaultTransformer());
      List<Photo> photos = ((Gallery) article).getPhotos();
      galleryViewHolder.vpPhotoSlide.setOffscreenPageLimit(photos.size());
      horizontalPhotoSlidePagerAdapter.setPhotos(photos);
      if (photos.size() > 0) {
        galleryViewHolder.vpPhotoSlide.setCurrentItem(0);
      }

      galleryViewHolder.vpPhotoSlide.setOnTouchListener((v, event) -> {
        touchItemId = Integer.valueOf(article.getId());
        viewPagerPosition = galleryViewHolder.vpPhotoSlide.getCurrentItem();
        return gestureDetector.onTouchEvent(event);
      });

      galleryViewHolder.ivArrowLeft.setOnClickListener(v -> {
        galleryViewHolder.vpPhotoSlide.setCurrentItem(
            getNextSlidePosition(galleryViewHolder.vpPhotoSlide), true);
      });
      galleryViewHolder.ivArrowRight.setOnClickListener(v -> {

        galleryViewHolder.vpPhotoSlide.setCurrentItem(
            getPreviousSlidePosition(galleryViewHolder.vpPhotoSlide), true);
      });
    } else {
      presenter.onGallerySelected(Integer.valueOf(article.getId()));
    }

    //galleryViewHolder.itemView.setOnTouchListener((v, event) -> {
    //  touchItemId = Integer.valueOf(article.getId());
    //  viewPagerPosition = 0;
    //  return gestureDetector.onTouchEvent(event);
    //});

    descriptionViewHolder.tvPreview.setText(article.getPreview());
    descriptionViewHolder.tvTitle.setText(article.getTitle());
    descriptionViewHolder.tvCategory.setText(article.getCategoryTitle());
    descriptionViewHolder.tvDate.setText(
        elbiladUtils.getArticleTimeDate(article.getTime(), article.getDate()));
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

  @Override public boolean onSingleTapConfirmed(MotionEvent e) {
    return false;
  }

  @Override public boolean onDoubleTap(MotionEvent e) {
    return false;
  }

  @Override public boolean onDoubleTapEvent(MotionEvent e) {
    fragmentNavigator.startPhotoDetailsragment(touchItemId, viewPagerPosition);
    return true;
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

  class GalleryViewHolder {
    View itemView;
    @BindView(R.id.vp_photo_slide) ViewPager vpPhotoSlide;
    @BindView(R.id.iv_arrow_left) ImageView ivArrowLeft;
    @BindView(R.id.iv_arrow_right) ImageView ivArrowRight;

    GalleryViewHolder(View itemView) {
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
