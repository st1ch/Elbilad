package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;

/**
 * Created by st1ch on 15.01.17.
 */

public class Video extends BaseArticle {

  @SerializedName("id_video_categorie") private int categoryId;
  @SerializedName("description") private String preview;
  @SerializedName("une") private String une;
  @SerializedName("type_video") private String videoType;
  @SerializedName("youtube_id") private String youtubeId;
  @SerializedName("local_video") private String localVideo;
  private boolean isArticle;

  public Video() {
    super.setType(TYPE.VIDEO);
  }

  public Video(ArticleVideo articleVideo) {
    id = articleVideo.getId();
    title = articleVideo.getTitle();
    categoryTitle = articleVideo.getCategoryTitle();
    date = articleVideo.getFullDate();
    image = articleVideo.getImage();
    link = articleVideo.getLink();

    categoryId = articleVideo.getCategoryId();
    preview = articleVideo.getPreview();
    videoType = "remote";
    youtubeId = articleVideo.getYoutubeCode();
    isArticle = true;
  }

  @Override public String getPreview() {
    return preview;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public String getUne() {
    return une;
  }

  public String getVideoType() {
    return videoType;
  }

  public String getYoutubeId() {
    return youtubeId;
  }

  public String getLocalVideo() {
    return localVideo;
  }

  public boolean isArticle() {
    return isArticle;
  }

  @Override public String toString() {
    return super.toString()
        + " Video{"
        + "categoryId="
        + categoryId
        + ", preview='"
        + preview
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
        + ", localVideo='"
        + localVideo
        + '\''
        + '}';
  }
}
