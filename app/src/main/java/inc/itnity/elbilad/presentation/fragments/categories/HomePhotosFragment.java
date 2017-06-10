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

public class HomePhotosFragment extends AbstractBaseFragment implements PhotoCategoryView {

  public static HomePhotosFragment newInstance() {
    return new HomePhotosFragment();
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
    View rootView = super.onCreateView(inflater, container, savedInstanceState);

    initContent();

    presenter.onCreate();

    return rootView;
  }

  private void initContent() {
    rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvNews.addItemDecoration(new SimpleDividerItemLineDecoration(getActivity()));
    rvNews.setAdapter(photoCategoryNewsAdapter);
  }

  @Override public void showPhotos(List<Image> images) {
    photoCategoryNewsAdapter.setArticles(images);
  }

  @Override public void showGallery(Gallery gallery) {

  }
}
