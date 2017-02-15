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
import inc.itnity.elbilad.domain.models.article.Bookmark;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;
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

public class BookmarksFragment extends AbstractBaseFragment implements BookmarksView {

  public static BookmarksFragment newInstance() {
    return new BookmarksFragment();
  }

  @BindView(R.id.rv_news) RecyclerView rvNews;

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
    ((AbstractBaseActivity) getActivity()).showHomeToolbar();
    ((AbstractBaseActivity) getActivity()).hideTitleLogo();
    ((AbstractBaseActivity) getActivity()).setTitleToolBar(getString(R.string.bookmarks));

    View rootVIew = super.onCreateView(inflater, container, savedInstanceState);

    initContent();

    presenter.onCreate();

    return rootVIew;
  }

  private void initContent() {
    rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvNews.addItemDecoration(new VerticalSpaceItemDecoration());
    rvNews.setAdapter(bookmarksAdapter);
  }

  @Override public void showBookmarks(List<Bookmark> bookmarks) {
    bookmarksAdapter.setArticles(bookmarks);
  }
}
