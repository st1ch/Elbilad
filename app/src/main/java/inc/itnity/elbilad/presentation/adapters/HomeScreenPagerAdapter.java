package inc.itnity.elbilad.presentation.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.fragments.categories.HomeBookmarksFragment;
import inc.itnity.elbilad.presentation.fragments.categories.HomeLastNewsFragment;
import inc.itnity.elbilad.presentation.fragments.categories.HomeMainFragment;
import inc.itnity.elbilad.presentation.fragments.categories.MostReadNewsFragment;
import java.util.ArrayList;

public class HomeScreenPagerAdapter extends FragmentStatePagerAdapter {
  private final int PAGE_HOME = 0;
  private final int PAGE_LAST_NEWS = 1;
  private final int PAGE_MOST_READ_NEWS = 2;
  private final int PAGE_BOOKMARKS = 3;
  //private final int PAGE_VIDEOS = 3;
  //private final int PAGE_PHOTOS = 4;
  //private final int pagesCount;
  private final int pagesCount = 4;

  private ArrayList<String> titles = new ArrayList<>();
  //private List<Category> categories = new ArrayList<>();

  //public HomeScreenPagerAdapter(Context context, FragmentManager fm, List<Category> categories) {
  //  super(fm);
  //  this.categories = categories;
  //  pagesCount = STATIC_TABS_COUNT + categories.size();
  //
  //  titles.add(context.getString(R.string.home));
  //  titles.add(context.getString(R.string.last_news));
  //  titles.add(context.getString(R.string.bookmarks));
  //  titles.add(context.getString(R.string.videos));
  //  titles.add(context.getString(R.string.photos));
  //  titles.trimToSize();
  //}

  public HomeScreenPagerAdapter(Context context, FragmentManager fm) {
    super(fm);
    titles.add(context.getString(R.string.home));
    titles.add(context.getString(R.string.last_news));
    titles.add(context.getString(R.string.news_most_read));
    titles.add(context.getString(R.string.bookmarks));
    titles.trimToSize();
  }

  @Override public int getCount() {
    return pagesCount;
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case PAGE_HOME:
        return HomeMainFragment.newInstance();
      case PAGE_LAST_NEWS:
        return HomeLastNewsFragment.newInstance();
      case PAGE_MOST_READ_NEWS:
        return MostReadNewsFragment.newInstance();
      case PAGE_BOOKMARKS:
        return HomeBookmarksFragment.newInstance();
      //case PAGE_VIDEOS:
      //  return HomeVideosFragment.newInstance();
      //case PAGE_PHOTOS:
      //  return HomePhotosFragment.newInstance();
      //default:
      //  return SimpleNewsFragment.newInstance(categories.get(position - STATIC_TABS_COUNT).getId());
      default:
        return null;
    }
  }

  @Override public CharSequence getPageTitle(int position) {
    //if (position > STATIC_TABS_COUNT - 1) {
    //  return categories.get(position - STATIC_TABS_COUNT).getTitle();
    //}
    return titles.get(position);
  }

  //public void setCategories(List<Category> categories) {
  //  this.categories = categories;
  //}
}
