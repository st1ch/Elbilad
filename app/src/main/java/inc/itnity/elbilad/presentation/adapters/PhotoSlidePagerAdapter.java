package inc.itnity.elbilad.presentation.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 30.01.17.
 */

public class PhotoSlidePagerAdapter extends PagerAdapter {

  private LayoutInflater mLayoutInflater;
  private ImageLoaderHelper imageLoaderHelper;

  private List<String> urls = new ArrayList<>();

  @Inject
  PhotoSlidePagerAdapter(Context context, ImageLoaderHelper imageLoaderHelper) {
    this.imageLoaderHelper = imageLoaderHelper;
    mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    urls.add("http://enigma-project.ru/wp-content/uploads/2015/12/priroda-1.jpg?x42318");
    urls.add("http://enigma-project.ru/wp-content/uploads/2015/12/priroda-3.jpg?x42318");
    urls.add("http://cdn.fishki.net/upload/post/201411/26/1334081/zhivaja-priroda.jpg");
    urls.add("http://www.yourfreedom.ru/custom-content/uploads/Priroda-1920x1200.jpg");
  }

  @Override
  public int getCount() {
    return 4;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == ((FrameLayout) object);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    View itemView = mLayoutInflater.inflate(R.layout.item_category_news, container, false);

    ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_image);

    imageLoaderHelper.loadUrlImage(urls.get(position), imageView);

    container.addView(itemView);

    return itemView;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeViewAt(position);
  }
}
