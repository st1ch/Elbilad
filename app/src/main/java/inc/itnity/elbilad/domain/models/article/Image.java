package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;

/**
 * Created by st1ch on 10.02.17.
 */

public class Image implements ArticleItem {

  @SerializedName("id") private String id;
  @SerializedName("titre") private String title;
  @SerializedName("id_galerie_categorie") private int categoryId;
  @SerializedName("categorie_titre") private String categoryTitle;
  @SerializedName("description") private String description;
  @SerializedName("date") private String date;
  @SerializedName("image") private String image;
  @SerializedName("link") protected String link;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryTitle() {
    return categoryTitle;
  }

  public void setCategoryTitle(String categoryTitle) {
    this.categoryTitle = categoryTitle;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDate() {
    return date.split("T")[0];
  }

  public String getTime() {
    return date.split("T")[1].split("\\+")[0];
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override public int getType() {
    return TYPE.GALLERY;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override public String toString() {
    return "Image{"
        + "id='"
        + id
        + '\''
        + ", title='"
        + title
        + '\''
        + ", categoryId="
        + categoryId
        + ", categoryTitle='"
        + categoryTitle
        + '\''
        + ", description='"
        + description
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
        + '}';
  }
}
