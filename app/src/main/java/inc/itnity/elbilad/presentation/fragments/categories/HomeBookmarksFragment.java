package inc.itnity.elbilad.presentation.fragments.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Bookmark;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.adapters.BookmarksAdapter;
import inc.itnity.elbilad.presentation.custom.VerticalSpaceItemDecoration;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.BookmarksPresenter;
import inc.itnity.elbilad.presentation.views.BookmarksView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 18.01.17.
 */

public class HomeBookmarksFragment extends AbstractBaseFragment implements BookmarksView {

  public static HomeBookmarksFragment newInstance() {
    return new HomeBookmarksFragment();
  }

  @BindView(R.id.rv_news) RecyclerView rvNews;

  @BindView(R.id.swipe_layout) SwipeRefreshLayout swipeRefreshLayout;

  @Inject BookmarksAdapter bookmarksAdapter;

  @Inject BookmarksPresenter presenter;

  @Override public int getContentView() {
    return R.layout.fragment_bookmarks;
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
    View rootVIew = super.onCreateView(inflater, container, savedInstanceState);

    initContent();

    presenter.onCreate();

    return rootVIew;
  }

  private void initContent() {
    swipeRefreshLayout.setEnabled(false);

    rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvNews.addItemDecoration(new VerticalSpaceItemDecoration());
    rvNews.setAdapter(bookmarksAdapter);
  }

  @Override public void showBookmarks(List<Bookmark> bookmarks) {
    bookmarksAdapter.setArticles(bookmarks);
  }

  @Override public void showProgress() {
    swipeRefreshLayout.setRefreshing(true);
  }

  @Override public void hideProgress() {
    swipeRefreshLayout.setRefreshing(false);
  }
}
