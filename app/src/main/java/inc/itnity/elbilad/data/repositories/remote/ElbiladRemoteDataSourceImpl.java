package inc.itnity.elbilad.data.repositories.remote;

import inc.itnity.elbilad.constants.ApiConfig;
import inc.itnity.elbilad.data.rest.api.ElbiladAPI;
import inc.itnity.elbilad.domain.models.Journal;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.ArticleItem;
import inc.itnity.elbilad.domain.models.article.Gallery;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.domain.models.categorie.Category;
import inc.itnity.elbilad.utils.rx_downloader.RxDownloader;
import java.util.List;
import rx.Observable;

/**
 * Created by st1ch on 15.01.17.
 */

public class ElbiladRemoteDataSourceImpl implements ElbiladRemoteDataSource {

  private ElbiladAPI elbiladAPI;
  private RxDownloader rxDownloader;

  public ElbiladRemoteDataSourceImpl(ElbiladAPI elbiladAPI, RxDownloader rxDownloader) {
    this.elbiladAPI = elbiladAPI;
    this.rxDownloader = rxDownloader;
  }

  @Override public Observable<List<Category>> getCategories() {
    return elbiladAPI.getCategories();
  }

  @Override public Observable<HomeArticles> getHomeArticles() {
    return elbiladAPI.getHomeArticles();
  }

  @Override public Observable<List<Article>> getArticles() {
    return elbiladAPI.getArticles();
  }

  @Override public Observable<List<Article>> getMostReadArticles() {
    return elbiladAPI.getMostReadArticles().flatMap(Observable::from).map(article -> {
      article.setType(ArticleItem.TYPE.MOST_READ);
      return article;
    }).toList();
  }

  @Override public Observable<List<Article>> getCategoryArticles(int categoryId) {
    return elbiladAPI.getCategoryArticles(categoryId);
  }

  @Override public Observable<Article> getArticle(boolean isFlash, String articleId) {
    if (isFlash) {
      return elbiladAPI.getFlashArticle(articleId).map(articles -> {
        Article article = articles.get(0);
        article.setType(ArticleItem.TYPE.FLASH);
        return article;
      });
    }
    return elbiladAPI.getArticle(articleId).map(article -> article.get(0));
  }

  @Override public Observable<List<Article>> getLastNews() {
    return elbiladAPI.getLastNews().flatMap(Observable::from).map(article -> {
      article.setType(ArticleItem.TYPE.FLASH);
      return article;
    }).toList();
  }

  @Override public Observable<List<Article>> getLast6News() {
    return elbiladAPI.getLastNews(ApiConfig.LAST_NEWS_LIMIT).flatMap(Observable::from).map(article -> {
      article.setType(ArticleItem.TYPE.FLASH);
      return article;
    }).toList();
  }

  @Override public Observable<List<Video>> getVideos() {
    return elbiladAPI.getVideos();
  }

  @Override public Observable<List<Image>> getGallery() {
    return elbiladAPI.getGallery();
  }

  @Override public Observable<Gallery> getGallery(int id) {
    return elbiladAPI.getGallery(id);
  }

  @Override public Observable<Journal> getJournalData() {
    return elbiladAPI.getJournalData();
  }

  @Override public Observable<String> downloadJournal(String url, String filename) {
    return rxDownloader.download("El Bilad", url, filename, "application/pdf");
  }
}
