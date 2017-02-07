package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by st1ch on 07.02.17.
 */

public class HomeArticles {
  @SerializedName("articleSlide") private List<Article> slideArticles;
  @SerializedName("important") private List<Article> importantArticles;
  @SerializedName("articleVideo") private List<Article> videoArticles;
  @SerializedName("international") private List<Article> internationalArticles;
  @SerializedName("sport") private List<Article> sportArticles;
  @SerializedName("musique") private List<Article> musiqueArticles;
  @SerializedName("culture") private List<Article> cultureArticles;
  @SerializedName("rasid") private List<Article> rasidArticles;
  @SerializedName("mahakim") private List<Article> mahakimArticles;
  @SerializedName("video") private List<ArticleVideo> videos;

  public List<Article> getSlideArticles() {
    return slideArticles;
  }

  public void setSlideArticles(List<Article> slideArticles) {
    this.slideArticles = slideArticles;
  }

  public List<Article> getImportantArticles() {
    return importantArticles;
  }

  public void setImportantArticles(List<Article> importantArticles) {
    this.importantArticles = importantArticles;
  }

  public List<Article> getVideoArticles() {
    return videoArticles;
  }

  public void setVideoArticles(List<Article> videoArticles) {
    this.videoArticles = videoArticles;
  }

  public List<Article> getInternationalArticles() {
    return internationalArticles;
  }

  public void setInternationalArticles(List<Article> internationalArticles) {
    this.internationalArticles = internationalArticles;
  }

  public List<Article> getSportArticles() {
    return sportArticles;
  }

  public void setSportArticles(List<Article> sportArticles) {
    this.sportArticles = sportArticles;
  }

  public List<Article> getMusiqueArticles() {
    return musiqueArticles;
  }

  public void setMusiqueArticles(List<Article> musiqueArticles) {
    this.musiqueArticles = musiqueArticles;
  }

  public List<Article> getCultureArticles() {
    return cultureArticles;
  }

  public void setCultureArticles(List<Article> cultureArticles) {
    this.cultureArticles = cultureArticles;
  }

  public List<Article> getRasidArticles() {
    return rasidArticles;
  }

  public void setRasidArticles(List<Article> rasidArticles) {
    this.rasidArticles = rasidArticles;
  }

  public List<Article> getMahakimArticles() {
    return mahakimArticles;
  }

  public void setMahakimArticles(List<Article> mahakimArticles) {
    this.mahakimArticles = mahakimArticles;
  }

  public List<ArticleVideo> getVideos() {
    return videos;
  }

  public void setVideos(List<ArticleVideo> videos) {
    this.videos = videos;
  }

  @Override public String toString() {
    return "HomeArticles{"
        + "slideArticles="
        + slideArticles
        + ", importantArticles="
        + importantArticles
        + ", videoArticles="
        + videoArticles
        + ", internationalArticles="
        + internationalArticles
        + ", sportArticles="
        + sportArticles
        + ", musiqueArticles="
        + musiqueArticles
        + ", cultureArticles="
        + cultureArticles
        + ", rasidArticles="
        + rasidArticles
        + ", mahakimArticles="
        + mahakimArticles
        + ", videos="
        + videos
        + '}';
  }
}
