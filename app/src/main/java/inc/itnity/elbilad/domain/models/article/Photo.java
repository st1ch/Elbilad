package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Artem Getman on 06.06.17.
 * a.e.getman@gmail.com
 */

public class Photo {
  @SerializedName("id") private String id;
  @SerializedName("titre") private String title;
  @SerializedName("description") private String description;
  @SerializedName("image") private String image;

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getImage() {
    return image;
  }

  @Override public String toString() {
    return "Photo{"
        + "id='"
        + id
        + '\''
        + ", title='"
        + title
        + '\''
        + ", description='"
        + description
        + '\''
        + ", image='"
        + image
        + '\''
        + '}';
  }
}
