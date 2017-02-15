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
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.adapters.CategoryNewsAdapter;
import inc.itnity.elbilad.presentation.custom.VerticalSpaceItemDecoration;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.LastNewsPresenter;
import inc.itnity.elbilad.presentation.views.LastNewsView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 18.01.17.
 */

public class HomeLastNewsFragment extends AbstractBaseFragment implements LastNewsView {

  public static HomeLastNewsFragment newInstance() {
    return new HomeLastNewsFragment();
  }

  @BindView(R.id.rv_news) RecyclerView rvNews;

  @Inject LastNewsPresenter presenter;

  @Inject CategoryNewsAdapter categoryNewsAdapter;

  @Override public int getContentView() {
    return R.layout.fragment_last_news;
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
    rvNews.addItemDecoration(new VerticalSpaceItemDecoration());
    rvNews.setAdapter(categoryNewsAdapter);
  }

  @Override public void showArticles(List<Article> articles) {
    categoryNewsAdapter.setArticles(articles);
  }
}
