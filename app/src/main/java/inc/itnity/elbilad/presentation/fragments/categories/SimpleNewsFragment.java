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
import inc.itnity.elbilad.presentation.adapters.SimpleNewsAdapter;
import inc.itnity.elbilad.presentation.custom.VerticalSpaceItemDecoration;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.SimpleNewsPresenter;
import inc.itnity.elbilad.presentation.views.SimpleNewsView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 26.01.17.
 */

public class SimpleNewsFragment extends AbstractBaseFragment implements SimpleNewsView {

  private static final String ARG_CATEGORY_ID = "category_id_arg";

  @BindView(R.id.rv_news) RecyclerView rvNews;

  @Inject SimpleNewsPresenter presenter;

  @Inject SimpleNewsAdapter simpleNewsAdapter;

  public static SimpleNewsFragment newInstance(int categoryId) {
    Bundle args = new Bundle();
    args.putInt(ARG_CATEGORY_ID, categoryId);
    SimpleNewsFragment fragment = new SimpleNewsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public int getContentView() {
    return R.layout.fragment_news;
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

    presenter.onCreate(getArguments().getInt(ARG_CATEGORY_ID));

    return rootView;
  }

  private void initContent() {
    rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvNews.addItemDecoration(new VerticalSpaceItemDecoration());
    rvNews.setAdapter(simpleNewsAdapter);
  }

  @Override public void showArticles(List<Article> articles) {
    simpleNewsAdapter.setArticles(articles);
  }
}
