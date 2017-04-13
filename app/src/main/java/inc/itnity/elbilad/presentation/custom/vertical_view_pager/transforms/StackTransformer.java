package inc.itnity.elbilad.presentation.custom.vertical_view_pager.transforms;

import android.support.v4.view.ViewPager;
import android.view.View;

public class StackTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        page.setTranslationX(page.getWidth() * -position);
        page.setTranslationY(position < 0 ? position * page.getHeight() : 0f);
    }
}