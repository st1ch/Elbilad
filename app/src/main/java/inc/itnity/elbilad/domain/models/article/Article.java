package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;
import inc.itnity.elbilad.domain.models.Links;
import java.util.Date;

/**
 * Created by st1ch on 15.01.17.
 */

public class Article {

  @SerializedName("id") private int id;
  @SerializedName("titre") private String title;
  @SerializedName("categorie_id") private int categoryId;
  @SerializedName("date") private Date date;
  @SerializedName("image") private String image;
  @SerializedName("auteur") private String author;
  @SerializedName("resume") private String preview;
  @SerializedName("text") private String text;
  @SerializedName("_links") private Links links;

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPreview() {
    return preview;
  }

  public void setPreview(String preview) {
    this.preview = preview;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Links getLinks() {
    return links;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  @Override public String toString() {
    return "Article{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", categoryId=" + categoryId +
        ", date=" + date +
        ", image='" + image + '\'' +
        ", author='" + author + '\'' +
        ", preview='" + preview + '\'' +
        ", text='" + text + '\'' +
        ", links=" + links +
        '}';
  }
}
