package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * Created by st1ch on 15.01.17.
 */

public class ArticleVideo {

  @SerializedName("id") private int id;
  @SerializedName("titre") private String title;
  @SerializedName("id_video_categorie") private int categoryId;
  @SerializedName("categorie_titre") private int categoryTitle;
  @SerializedName("description") private String description;
  @SerializedName("date") private Date date;
  @SerializedName("image") private String image;
  @SerializedName("une") private String une;
  @SerializedName("type_video") private String videoType;
  @SerializedName("youtube_id") private String youtubeId;
  //@SerializedName("local_video") private String localVideo;
  //@SerializedName("_links") private Links links;

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

  public int getCategoryTitle() {
    return categoryTitle;
  }

  public void setCategoryTitle(int categoryTitle) {
    this.categoryTitle = categoryTitle;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  @Override public String toString() {
    return "ArticleVideo{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", categoryId="
        + categoryId
        + ", categoryTitle="
        + categoryTitle
        + ", description='"
        + description
        + '\''
        + ", date="
        + date
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
        + '}';
  }
}
