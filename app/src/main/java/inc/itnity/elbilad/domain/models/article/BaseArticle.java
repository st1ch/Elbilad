package inc.itnity.elbilad.domain.models.article;

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
    return date.split("T")[0];
  }

  public String getTime() {
    return date.split("T")[1];
  }

  public String getFullDate(){
    return date;
  }

  public String getImage() {
    return image;
  }

  public String getLink() {
    return link;
  }

  @Override public int getType() {
    return type;
  }

  protected void setType(int type) {
    this.type = type;
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
