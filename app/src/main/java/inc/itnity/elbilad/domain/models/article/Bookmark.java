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

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Bookmark bookmark = (Bookmark) o;

    if (video != null ? !video.equals(bookmark.video) : bookmark.video != null) return false;
    if (photo != null ? !photo.equals(bookmark.photo) : bookmark.photo != null) return false;
    return article != null ? article.equals(bookmark.article) : bookmark.article == null;
  }

  @Override public int hashCode() {
    int result = video != null ? video.hashCode() : 0;
    result = 31 * result + (photo != null ? photo.hashCode() : 0);
    result = 31 * result + (article != null ? article.hashCode() : 0);
    return result;
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

