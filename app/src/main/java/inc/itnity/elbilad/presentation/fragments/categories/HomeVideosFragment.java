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
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.adapters.VideoCategoryNewsAdapter;
import inc.itnity.elbilad.presentation.custom.SimpleDividerItemLineDecoration;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.VideoCategoryPresenter;
import inc.itnity.elbilad.presentation.views.VideoCategoryView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 18.01.17.
 */

public class HomeVideosFragment extends AbstractBaseFragment implements VideoCategoryView {

  public static HomeVideosFragment newInstance() {
    return new HomeVideosFragment();
  }

  @BindView(R.id.rv_news) RecyclerView rvNews;

  @Inject VideoCategoryPresenter presenter;

  @Inject VideoCategoryNewsAdapter videoCategoryNewsAdapter;

  @Override public int getContentView() {
    return R.layout.fragment_video_news;
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
    rvNews.addItemDecoration(new SimpleDividerItemLineDecoration(getContext()));
    rvNews.setAdapter(videoCategoryNewsAdapter);
  }

  @Override public void showVideos(List<Video> videos) {
    videoCategoryNewsAdapter.setArticles(videos);
  }
}
