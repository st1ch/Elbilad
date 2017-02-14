package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;

/**
 * Created by st1ch on 15.01.17.
 */

public class Video implements ArticleItem {

  @SerializedName("id") private String id;
  @SerializedName("titre") private String title;
  @SerializedName("id_video_categorie") private int categoryId;
  @SerializedName("categorie_titre") private String categoryTitle;
  @SerializedName("description") private String description;
  @SerializedName("date") private String date;
  @SerializedName("image") private String image;
  @SerializedName("une") private String une;
  @SerializedName("type_video") private String videoType;
  @SerializedName("youtube_id") private String youtubeId;
  @SerializedName("link") protected String link;
  //@SerializedName("local_video") private String localVideo;

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

  public String getUne() {
    return une;
  }

  public void setUne(String une) {
    this.une = une;
  }

  public String getVideoType() {
    return videoType;
  }

  public void setVideoType(String videoType) {
    this.videoType = videoType;
  }

  public String getYoutubeId() {
    return youtubeId;
  }

  public void setYoutubeId(String youtubeId) {
    this.youtubeId = youtubeId;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override public int getType() {
    return TYPE.VIDEO;
  }

  @Override public String toString() {
    return "Video{"
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
        + ", une='"
        + une
        + '\''
        + ", videoType='"
        + videoType
        + '\''
        + ", youtubeId='"
        + youtubeId
        + '\''
        + ", link='"
        + link
        + '\''
        + '}';
  }
}
