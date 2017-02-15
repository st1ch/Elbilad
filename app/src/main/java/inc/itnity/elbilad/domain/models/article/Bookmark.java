package inc.itnity.elbilad.domain.models.article;

/**
 * Created by st1ch on 15.02.17.
 */

public class Bookmark {

  private Video video;
  private Image photo;
  private Article article;

  private TYPE type;

  public Bookmark(Video video) {
    this.video = video;
    this.type = TYPE.VIDEO;
  }

  public Bookmark(Image photo) {
    this.photo = photo;
    this.type = TYPE.PHOTO;
  }

  public Bookmark(Article article) {
    this.article = article;
    this.type = TYPE.ARTICLE;
  }

  public TYPE getType() {
    return type;
  }

  public Video getVideo() {
    return video;
  }

  public Image getPhoto() {
    return photo;
  }

  public Article getArticle() {
    return article;
  }

  public enum TYPE {
    VIDEO, PHOTO, ARTICLE
  }

  @Override public String toString() {
    return "Bookmark{"
        + "video="
        + video
        + ", photo="
        + photo
        + ", article="
        + article
        + ", type="
        + type
        + '}';
  }
}

