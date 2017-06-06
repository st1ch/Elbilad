package inc.itnity.elbilad.data.repositories.remote;

import inc.itnity.elbilad.domain.models.Journal;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.Gallery;
import inc.itnity.elbilad.domain.models.article.HomeArticles;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.domain.models.categorie.Category;
import java.util.List;
import rx.Observable;

/**
 * Created by st1ch on 15.01.17.
 */

public interface ElbiladRemoteDataSource {

  Observable<List<Category>> getCategories();

  Observable<HomeArticles> getHomeArticles();

  Observable<List<Article>> getArticles();

  Observable<List<Article>> getMostReadArticles();

  Observable<List<Article>> getCategoryArticles(int categoryId);

  Observable<Article> getArticle(boolean isFlash, String articleId);

  Observable<List<Article>> getLastNews();

  Observable<List<Article>> getLast6News();

  Observable<List<Video>> getVideos();

  Observable<List<Image>> getGallery();

  Observable<Gallery> getGallery(int id);

  Observable<Journal> getJournalData();

  Observable<String> downloadJournal(String url, String filename);
}
