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
import inc.itnity.elbilad.presentation.adapters.HomeAdapter;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.HomeScreenPresenter;
import inc.itnity.elbilad.presentation.views.HomeScreenView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 18.01.17.
 */

public class HomeFragment extends AbstractBaseFragment implements HomeScreenView {

  @BindView(R.id.rv_news)
  RecyclerView rvNews;

  @Inject
  HomeScreenPresenter presenter;

  @Inject
  HomeAdapter adapter;

  public static HomeFragment newInstance() {
    return new HomeFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View fragmentView = super.onCreateView(inflater, container, savedInstanceState);

    initContent();

    presenter.onCreate();
    return fragmentView;
  }

  private void initContent() {
    rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvNews.setAdapter(adapter);
  }

  @Override public int getContentView() {
    return R.layout.fragment_home;
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

  @Override public void showLoadedArticles(List<Article> articles) {
    adapter.setArticles(articles);
  }
}
