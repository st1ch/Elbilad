package inc.itnity.elbilad.presentation.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.presentation.fragments.categories.BookmarksFragment;
import inc.itnity.elbilad.presentation.fragments.categories.HomeFragment;
import inc.itnity.elbilad.presentation.fragments.categories.LastNewsFragment;
import java.util.ArrayList;

public class HomeScreenPagerAdapter extends FragmentStatePagerAdapter {
    private final int PAGE_HOME = 0;
    private final int PAGE_LAST_NEWS= 1;
    private final int PAGE_BOOKMARKS = 2;
    private final int COUNT_PAGES = 3;

    private ArrayList<String> titles = new ArrayList<>();

    public HomeScreenPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        titles.add(context.getString(R.string.home));
        titles.add(context.getString(R.string.bookmarks));
        titles.add(context.getString(R.string.last_news));
        titles.trimToSize();
    }

    @Override
    public int getCount() {
        return COUNT_PAGES;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case PAGE_HOME:
                return HomeFragment.newInstance();
            case PAGE_LAST_NEWS:
                return LastNewsFragment.newInstance();
            case PAGE_BOOKMARKS:
                return BookmarksFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
