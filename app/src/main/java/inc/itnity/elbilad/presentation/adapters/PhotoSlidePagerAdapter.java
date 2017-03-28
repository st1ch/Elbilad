package inc.itnity.elbilad.presentation.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Image;
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

  private List<Image> photos = new ArrayList<>();

  @Inject PhotoSlidePagerAdapter(Context context, ImageLoaderHelper imageLoaderHelper) {
    this.imageLoaderHelper = imageLoaderHelper;
    mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public void setPhotos(List<Image> photos) {
    this.photos.clear();
    this.photos.addAll(photos);
    notifyDataSetChanged();
  }

  public Image getPhoto(int position) {
    return photos.get(position);
  }

  @Override public int getCount() {
    return photos.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == ((ConstraintLayout) object);
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    View itemView = mLayoutInflater.inflate(R.layout.item_photo_details, container, false);

    Image photo = getPhoto(position);

    ImageView slide = (ImageView) itemView.findViewById(R.id.iv_photo_slide);

    imageLoaderHelper.loadGalleryImageLarge(photo.getImage(), slide);

    container.addView(itemView);

    return itemView;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeViewAt(position);
  }
}
