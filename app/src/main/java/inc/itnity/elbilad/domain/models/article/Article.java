package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;

/**
 * Created by st1ch on 15.02.17.
 */

public class Article extends BaseArticle {

  @SerializedName("categorie_id") protected int categoryId;
  @SerializedName("imageText") protected String imageText;
  @SerializedName("resume") protected String preview;
  @SerializedName("auteur") protected String author;
  @SerializedName("text") protected String text;
  @SerializedName("youtube_code") protected String youtubeCode;
  @SerializedName("nb_vue") private String numberViews;

  public String getNumberViews() {
    return numberViews;
  }

  public Article() {
    super.setType(TYPE.SIMPLE);
  }

  public Article(int type) {
    super.setType(type);
  }

  @Override public String getPreview() {
    return preview;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public String getImageText() {
    return imageText;
  }

  public String getAuthor() {
    return author;
  }

  public String getText() {
    return text;
  }

  public String getYoutubeCode() {
    return youtubeCode;
  }

  @Override public String toString() {
    return "Article{"
        + "categoryId="
        + categoryId
        + ", imageText='"
        + imageText
        + '\''
        + ", preview='"
        + preview
        + '\''
        + ", author='"
        + author
        + '\''
        + ", text='"
        + text
        + '\''
        + ", youtubeCode='"
        + youtubeCode
        + '\''
        + ", numberViews='"
        + numberViews
        + '\''
        + '}';
  }
}
