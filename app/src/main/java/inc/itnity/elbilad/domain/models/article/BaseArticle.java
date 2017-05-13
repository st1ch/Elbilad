package inc.itnity.elbilad.domain.models.article;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by st1ch on 15.01.17.
 */

public abstract class BaseArticle implements ArticleItem {

  @SerializedName("id") protected String id;
  @SerializedName("titre") protected String title;
  @SerializedName("categorie_titre") protected String categoryTitle;
  @SerializedName("date") protected String date;
  @SerializedName("image") protected String image;
  @SerializedName("link") protected String link;
  private boolean isBookmarked;

  private int type;

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getCategoryTitle() {
    return categoryTitle;
  }

  public String getDate() {
    if (!TextUtils.isEmpty(date)) {
      return date.split("T")[0];
    } else {
      return "";
    }
  }

  public String getTime() {
    if (!TextUtils.isEmpty(date)) {
      return date.split("T")[1];
    } else {
      return "";
    }
  }

  public String getFullDate() {
    return date;
  }

  public String getImage() {
    return image;
  }

  public String getLink() {
    return link;
  }

  public boolean isBookmarked() {
    return isBookmarked;
  }

  public void setBookmarked(boolean bookmarked) {
    isBookmarked = bookmarked;
  }

  @Override public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BaseArticle article = (BaseArticle) o;

    return id.equals(article.id);
  }

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public String toString() {
    return "BaseArticle{"
        + "id='"
        + id
        + '\''
        + ", title='"
        + title
        + '\''
        + ", categoryTitle='"
        + categoryTitle
        + '\''
        + ", date='"
        + date
        + '\''
        + ", image='"
        + image
        + '\''
        + ", link='"
        + link
        + '\''
        + ", type="
        + type
        + '}';
  }
}
