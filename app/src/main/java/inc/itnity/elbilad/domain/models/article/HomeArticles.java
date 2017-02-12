package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by st1ch on 07.02.17.
 */

public class HomeArticles {
  @SerializedName("articleSlide") private List<ArticleTop5> top5Articles;
  @SerializedName("important") private List<ArticleImportant> importantArticles;
  @SerializedName("articleVideo") private List<ArticleVideo> videoArticles;
  @SerializedName("international") private List<ArticleInternational> internationalArticles;
  @SerializedName("sport") private List<ArticleSport> sportArticles;
  @SerializedName("musique") private List<ArticleMusic> musicArticles;
  @SerializedName("culture") private List<ArticleCulture> cultureArticles;
  @SerializedName("rasid") private List<ArticleRasid> rasidArticles;
  @SerializedName("mahakim") private List<ArticleMahakim> mahakimArticles;
  @SerializedName("video") private List<Video> videos;
  @SerializedName("galerie") private List<Image> gallery;

  public List<ArticleTop5> getTop5Articles() {
    return top5Articles;
  }

  public void setTop5Articles(List<ArticleTop5> top5Articles) {
    this.top5Articles = top5Articles;
  }

  public List<ArticleImportant> getImportantArticles() {
    return importantArticles;
  }

  public void setImportantArticles(List<ArticleImportant> importantArticles) {
    this.importantArticles = importantArticles;
  }

  public List<ArticleVideo> getVideoArticles() {
    return videoArticles;
  }

  public void setVideoArticles(List<ArticleVideo> videoArticles) {
    this.videoArticles = videoArticles;
  }

  public List<ArticleInternational> getInternationalArticles() {
    return internationalArticles;
  }

  public void setInternationalArticles(List<ArticleInternational> internationalArticles) {
    this.internationalArticles = internationalArticles;
  }

  public List<ArticleSport> getSportArticles() {
    return sportArticles;
  }

  public void setSportArticles(List<ArticleSport> sportArticles) {
    this.sportArticles = sportArticles;
  }

  public List<ArticleMusic> getMusicArticles() {
    return musicArticles;
  }

  public void setMusicArticles(List<ArticleMusic> musicArticles) {
    this.musicArticles = musicArticles;
  }

  public List<ArticleCulture> getCultureArticles() {
    return cultureArticles;
  }

  public void setCultureArticles(List<ArticleCulture> cultureArticles) {
    this.cultureArticles = cultureArticles;
  }

  public List<ArticleRasid> getRasidArticles() {
    return rasidArticles;
  }

  public void setRasidArticles(List<ArticleRasid> rasidArticles) {
    this.rasidArticles = rasidArticles;
  }

  public List<ArticleMahakim> getMahakimArticles() {
    return mahakimArticles;
  }

  public void setMahakimArticles(List<ArticleMahakim> mahakimArticles) {
    this.mahakimArticles = mahakimArticles;
  }

  public List<Video> getVideos() {
    return videos;
  }

  public void setVideos(List<Video> videos) {
    this.videos = videos;
  }

  public List<Image> getGallery() {
    return gallery;
  }

  public void setGallery(List<Image> gallery) {
    this.gallery = gallery;
  }

  @Override public String toString() {
    return "HomeArticles{"
        + "top5Articles="
        + top5Articles
        + ", importantArticles="
        + importantArticles
        + ", videoArticles="
        + videoArticles
        + ", internationalArticles="
        + internationalArticles
        + ", sportArticles="
        + sportArticles
        + ", musicArticles="
        + musicArticles
        + ", cultureArticles="
        + cultureArticles
        + ", rasidArticles="
        + rasidArticles
        + ", mahakimArticles="
        + mahakimArticles
        + ", videos="
        + videos
        + ", gallery="
        + gallery
        + '}';
  }
}
