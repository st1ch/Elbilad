package inc.itnity.elbilad.presentation.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.categorie.Category;
import inc.itnity.elbilad.presentation.fragments.categories.BookmarksFragment;
import inc.itnity.elbilad.presentation.fragments.categories.HomeFragment;
import inc.itnity.elbilad.presentation.fragments.categories.LastNewsFragment;
import inc.itnity.elbilad.presentation.fragments.categories.SimpleNewsFragment;
import java.util.ArrayList;
import java.util.List;

import static inc.itnity.elbilad.constants.Constants.STATIC_TABS_COUNT;

public class HomeScreenPagerAdapter extends FragmentStatePagerAdapter {
  private final int PAGE_HOME = 0;
  private final int PAGE_LAST_NEWS = 1;
  private final int PAGE_HZ = 2;
  private final int PAGE_BOOKMARKS = 3;
  private final int PAGE_VIDEOS = 4;
  private final int PAGE_PHOTOS = 5;
  private final int pagesCount;

  private ArrayList<String> titles = new ArrayList<>();
  private List<Category> categories = new ArrayList<>();

  public HomeScreenPagerAdapter(Context context, FragmentManager fm, List<Category> categories) {
    super(fm);
    this.categories = categories;
    pagesCount = STATIC_TABS_COUNT + categories.size();

    titles.add(context.getString(R.string.home));
    titles.add(context.getString(R.string.bookmarks));
    titles.add(context.getString(R.string.last_news));
    titles.add(context.getString(R.string.last_news));
    titles.add(context.getString(R.string.last_news));
    titles.add(context.getString(R.string.last_news));
    titles.trimToSize();
  }

  @Override public int getCount() {
    return pagesCount;
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case PAGE_HOME:
        return HomeFragment.newInstance();
      case PAGE_LAST_NEWS:
        return LastNewsFragment.newInstance();
      case PAGE_HZ:
        return SimpleNewsFragment.newInstance(-10);
      case PAGE_BOOKMARKS:
        return BookmarksFragment.newInstance();
      case PAGE_VIDEOS:
        return SimpleNewsFragment.newInstance(-100);
      case PAGE_PHOTOS:
        return SimpleNewsFragment.newInstance(-1000);
      default:
        return SimpleNewsFragment.newInstance(categories.get(position - STATIC_TABS_COUNT).getId());
    }
  }

  @Override public CharSequence getPageTitle(int position) {
    if (position > STATIC_TABS_COUNT - 1) {
      return categories.get(position - STATIC_TABS_COUNT).getTitle();
    }
    return titles.get(position);
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
