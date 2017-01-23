package inc.itnity.elbilad.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.categorie.Category;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.views.MainMenuView;
import java.util.List;

/**
 * Created by st1ch on 14.01.17.
 */

public class MainMenuFragment extends AbstractBaseFragment implements MainMenuView {

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View fragmentView = super.onCreateView(inflater, container, savedInstanceState);

    return fragmentView;
  }

  @Override public int getContentView() {
    return R.layout.fragment_main_menu;
  }

  @Override public void injectComponent() {

  }

  @Override protected void bindPresenter() {

  }

  @Override protected void unbindPresenter() {

  }

  @Override public void showLoadedCategories(List<Category> categories) {

  }

  @OnClick(R.id.iv_menu_arrow)
  protected void onMenuCloseClick(){
    ((MainActivity) getActivity()).openCloseDrawer();
  }
}
