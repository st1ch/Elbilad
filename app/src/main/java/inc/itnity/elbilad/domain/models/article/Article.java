package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;

/**
 * Created by st1ch on 15.01.17.
 */

public class Article implements ArticleItem {

  @SerializedName("id") protected String id;
  @SerializedName("titre") protected String title;
  @SerializedName("categorie_id") protected int categoryId;
  @SerializedName("categorie_titre") protected String categoryTitle;
  @SerializedName("date") protected String date;
  @SerializedName("image") protected String image;
  @SerializedName("imageText") protected String imageText;
  @SerializedName("auteur") protected String author;
  @SerializedName("resume") protected String preview;
  @SerializedName("text") protected String text;
  @SerializedName("youtube_code") protected String youtubeCode;
  //@SerializedName("_links") private Links links;

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

  public String getImageText() {
    return imageText;
  }

  public void setImageText(String imageText) {
    this.imageText = imageText;
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

  public String getYoutubeCode() {
    return youtubeCode;
  }

  public void setYoutubeCode(String youtubeCode) {
    this.youtubeCode = youtubeCode;
  }

  @Override public String toString() {
    return "Article{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", categoryId="
        + categoryId
        + ", categoryTitle="
        + categoryTitle
        + ", date="
        + date
        + ", image='"
        + image
        + '\''
        + ", imageText='"
        + imageText
        + '\''
        + ", author='"
        + author
        + '\''
        + ", preview='"
        + preview
        + '\''
        + ", text='"
        + text
        + '\''
        + ", youtubeCode='"
        + youtubeCode
        + '\''
        + '}';
  }

  @Override public int getType() {
    return -1;
  }
}
