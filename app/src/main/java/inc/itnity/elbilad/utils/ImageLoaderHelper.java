package inc.itnity.elbilad.utils;

import android.content.Context;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.constants.ApiConfig;
import javax.inject.Inject;

public class ImageLoaderHelper {
  private Picasso picasso;
  //private Context context;
  //private ObserveOn observeOn;

  @Inject public ImageLoaderHelper(Context context) {
    //this.context = context;
    picasso = Picasso.with(context);
    //this.observeOn = observeOn;
  }

  //public void loadFileAvatar(File imageFile, boolean withBorder, ImageView targetImageView) {
  //  picasso.load(imageFile)
  //      .fit()
  //      .centerCrop()
  //      .transform(withBorder ? CropCircleBorderTransformation.getInstance(context)
  //          : CropCircleTransformation.getInstance())
  //      .into(targetImageView);
  //}
  //
  //public void loadFileAvatarClearCache(File imageFile, boolean withBorder,
  //    ImageView targetImageView) {
  //  picasso.invalidate(imageFile);
  //  picasso.load(imageFile)
  //      .fit()
  //      .centerCrop()
  //      .transform(withBorder ? CropCircleBorderTransformation.getInstance(context)
  //          : CropCircleTransformation.getInstance())
  //      .into(targetImageView);
  //}
  //
  //public void loadUrlAvatar(String imageUrl, boolean withBorder, ImageView targetImageView) {
  //  picasso.load(imageUrl)
  //      .fit()
  //      .centerCrop()
  //      .transform(withBorder ? CropCircleBorderTransformation.getInstance(context)
  //          : CropCircleTransformation.getInstance())
  //      .into(targetImageView);
  //}

  public void loadUrlImage(String imageUrl, ImageView targetImageView) {
    picasso.load(imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadUrlImageUne(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.IMAGE_BASE_URL + ApiConfig.UNE + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadVideoImageUne(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.VIDEO_IMAGE_BASE_URL + ApiConfig.VIDEO_UNE + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadGalleryImageUne(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.GALERY_IMAGE_BASE_URL + ApiConfig.VIDEO_UNE + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadUrlImageThumb(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.IMAGE_BASE_URL + ApiConfig.THUMB + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadVideoImageThumb(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.VIDEO_IMAGE_BASE_URL + ApiConfig.VIDEO_UNE + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadGalleryImageThumb(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.GALERY_IMAGE_BASE_URL + ApiConfig.VIDEO_UNE + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadUrlImageThumbCat(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.IMAGE_BASE_URL + ApiConfig.THUMB_CAT + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadUrlImageLargeCat(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.IMAGE_BASE_URL + ApiConfig.LARGE_CAT + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadUrlImageRep(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.IMAGE_BASE_URL + ApiConfig.REP + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadUrlImageLarge(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.IMAGE_BASE_URL + ApiConfig.LARGE + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadVideoImageLarge(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.VIDEO_IMAGE_BASE_URL + ApiConfig.VIDEO_UNE + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadGalleryImageLarge(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.GALERY_IMAGE_BASE_URL + ApiConfig.VIDEO_UNE + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadUrlImageUneSlide(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.IMAGE_BASE_URL + ApiConfig.UNE_SLIDE + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  public void loadUrlImageUnethSlide(String imageUrl, ImageView targetImageView) {
    picasso.load(ApiConfig.IMAGE_BASE_URL + ApiConfig.UNETH_SLIDE + imageUrl)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.large_placeholder)
        .into(targetImageView);
  }

  //public Observable<Bitmap> loadBitmap(String imageUrl) {
  //
  //  return Observable.create(new Observable.OnSubscribe<Bitmap>() {
  //    @Override public void call(Subscriber<? super Bitmap> subscriber) {
  //      Target target = new Target() {
  //        @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
  //          subscriber.onNext(bitmap);
  //          subscriber.onCompleted();
  //        }
  //
  //        @Override public void onBitmapFailed(Drawable errorDrawable) {
  //          subscriber.onError(new Exception("failed to load " + imageUrl));
  //        }
  //
  //        @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
  //        }
  //      };
  //      subscriber.add(new Subscription() {
  //        private boolean unSubscribed;
  //
  //        @Override public void unsubscribe() {
  //          picasso.cancelRequest(target);
  //          unSubscribed = true;
  //        }
  //
  //        @Override public boolean isUnsubscribed() {
  //          return unSubscribed;
  //        }
  //      });
  //      Observable.create(subscriber1 -> {
  //        picasso.load(imageUrl).into(target);
  //      }).subscribeOn(observeOn.getScheduler()).subscribe(o -> {
  //      }, throwable -> {
  //      });
  //    }
  //  });
  //}
}
