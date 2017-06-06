package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Artem Getman on 06.06.17.
 * a.e.getman@gmail.com
 */

public class Gallery extends Image {
  @SerializedName("photos") private List<Photo> photos;

  public List<Photo> getPhotos() {
    return photos;
  }

  @Override public String toString() {
    return "Gallery{" + "photos=" + photos + '}';
  }
}
