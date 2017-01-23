package inc.itnity.elbilad.utils;

import android.content.Context;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
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
