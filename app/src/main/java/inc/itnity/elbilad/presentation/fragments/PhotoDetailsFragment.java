package inc.itnity.elbilad.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Photo;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;
import inc.itnity.elbilad.presentation.adapters.PhotoSlidePagerAdapter;
import inc.itnity.elbilad.presentation.custom.vertical_view_pager.VerticalViewPager;
import inc.itnity.elbilad.presentation.custom.vertical_view_pager.transforms.DefaultTransformer;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.PhotoDetailsPresenter;
import inc.itnity.elbilad.presentation.views.PhotoDetailsView;
import inc.itnity.elbilad.utils.ElbiladUtils;
import inc.itnity.elbilad.utils.FragmentNavigator;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 15.02.17.
 */

public class PhotoDetailsFragment extends AbstractBaseFragment implements PhotoDetailsView {

  private static final String ARG_GALLERY_ID = "gallery_id_arg";

  public static PhotoDetailsFragment newInstance(int galleryId) {
    Bundle args = new Bundle();
    args.putInt(ARG_GALLERY_ID, galleryId);
    PhotoDetailsFragment fragment = new PhotoDetailsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @BindView(R.id.vp_photo_slide) VerticalViewPager vpPhotoSlide;

  //@BindView(R.id.tv_description) TextView tvDescription;

  @Inject ElbiladUtils elbiladUtils;

  @Inject PhotoDetailsPresenter presenter;

  @Inject PhotoSlidePagerAdapter photoSlidePagerAdapter;

  @Inject FragmentNavigator fragmentNavigator;

  @Override public int getContentView() {
    return R.layout.fragment_photo_details;
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
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    ((AbstractBaseActivity) getActivity()).hideToolbar();

    initContent();

    presenter.onCreate(getArguments().getInt(ARG_GALLERY_ID));

    return rootView;
  }

  private void initContent() {
    vpPhotoSlide.setAdapter(photoSlidePagerAdapter);
    vpPhotoSlide.setPageTransformer(false, new DefaultTransformer());
    //vpPhotoSlide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    //  @Override
    //  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    //
    //  }
    //
    //  @Override public void onPageSelected(int position) {
    //    //tvDescription.setText(photoSlidePagerAdapter.getPhoto(position).getPreview());
    //  }
    //
    //  @Override public void onPageScrollStateChanged(int state) {
    //
    //  }
    //});
  }

  @Override public void showSlideshow(List<Photo> photos) {
    vpPhotoSlide.setOffscreenPageLimit(photos.size());
    photoSlidePagerAdapter.setPhotos(photos);
    //tvDescription.setText(photoSlidePagerAdapter.getPhoto(0).getPreview());
  }

  @Override public void showAddedToBookmarks() {
    super.showSnackbarMessage(getString(R.string.added_to_bookmarks));
  }

  //@OnClick(R.id.iv_share) void onShareClick() {
  //  elbiladUtils.shareArticleLink(
  //      photoSlidePagerAdapter.getPhoto(vpPhotoSlide.getCurrentItem()).getLink());
  //}

  //@OnClick(R.id.iv_bookmark) void onBookmarkClick() {
  //  //presenter.addToBookmarks(photoSlidePagerAdapter.getPhoto(vpPhotoSlide.getCurrentItem()));
  //}

  @OnClick(R.id.iv_arrow_left) void onArrowLeftClick() {
    vpPhotoSlide.setCurrentItem(getPreviousSlidePosition(), true);
  }

  @OnClick(R.id.iv_arrow_right) void onArrowRightClick() {
    vpPhotoSlide.setCurrentItem(getNextSlidePosition(), true);
  }

  @OnClick(R.id.iv_cross) void onCrossClick() {
    fragmentNavigator.navigateBack(getActivity());
  }

  private int getNextSlidePosition() {
    int totalCount = photoSlidePagerAdapter.getCount();
    int currentItemPosition = vpPhotoSlide.getCurrentItem();
    int nextItemPosition = currentItemPosition + 1;
    return (totalCount == currentItemPosition + 1) ? currentItemPosition : nextItemPosition;
  }

  private int getPreviousSlidePosition() {
    int currentItemPosition = vpPhotoSlide.getCurrentItem();
    int previousItemPosition = currentItemPosition - 1;
    return (currentItemPosition == 0) ? currentItemPosition : previousItemPosition;
  }
}
