package inc.itnity.elbilad.presentation.fragments.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Gallery;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;
import inc.itnity.elbilad.presentation.adapters.PhotoCategoryNewsAdapter;
import inc.itnity.elbilad.presentation.custom.SimpleDividerItemLineDecoration;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.PhotoCategoryPresenter;
import inc.itnity.elbilad.presentation.views.PhotoCategoryView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 18.01.17.
 */

public class PhotosFragment extends AbstractBaseFragment implements PhotoCategoryView {

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

    initContent();

    presenter.onCreate();

    return rootView;
  }

  private void initContent() {
    photoCategoryNewsAdapter.setCurrentItemId(getArguments().getString(ARG_PHOTO_ID));
    rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvNews.addItemDecoration(new SimpleDividerItemLineDecoration(getActivity()));
    rvNews.setAdapter(photoCategoryNewsAdapter);
    photoCategoryNewsAdapter.setGallerySelectedListener(
        galleryId -> presenter.onGallerySelected(galleryId));
  }

  @Override public void showPhotos(List<Image> images) {
    photoCategoryNewsAdapter.setArticles(images);
    photoCategoryNewsAdapter.selectCurrentItem();
  }

  @Override public void showGallery(Gallery gallery) {
    photoCategoryNewsAdapter.showGallery(gallery);
  }
}
