package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.Journal;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.Bookmark;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.AddArticleBookmarkUseCase;
import inc.itnity.elbilad.domain.usecases.DownloadJournalUseCase;
import inc.itnity.elbilad.domain.usecases.GetArticleUseCase;
import inc.itnity.elbilad.domain.usecases.GetJournalDataUseCase;
import inc.itnity.elbilad.domain.usecases.GetLast6NewsArticlesUseCase;
import inc.itnity.elbilad.domain.usecases.GetLastVideosUseCase;
import inc.itnity.elbilad.domain.usecases.RemoveArticleBookmarkUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.ArticleDetailsView;
import java.util.List;

/**
 * Created by st1ch on 05.02.17.
 */

public class ArticleDetailsPresenterImpl extends ProgressConnectionPresenter<ArticleDetailsView>
    implements ArticleDetailsPresenter {

  private GetArticleUseCase getArticleUseCase;
  private GetJournalDataUseCase getJournalDataUseCase;
  private DownloadJournalUseCase downloadJournalUseCase;
  private GetLastVideosUseCase getLastVideosUseCase;
  private GetLast6NewsArticlesUseCase getLast6NewsArticlesUseCase;
  private AddArticleBookmarkUseCase addArticleBookmarkUseCase;
  private RemoveArticleBookmarkUseCase removeArticleBookmarkUseCase;

  public ArticleDetailsPresenterImpl(GetArticleUseCase getArticleUseCase,
      GetJournalDataUseCase getJournalDataUseCase, DownloadJournalUseCase downloadJournalUseCase,
      GetLastVideosUseCase getLastVideosUseCase,
      GetLast6NewsArticlesUseCase getLast6NewsArticlesUseCase,
      AddArticleBookmarkUseCase addArticleBookmarkUseCase,
      RemoveArticleBookmarkUseCase removeArticleBookmarkUseCase) {
    this.getArticleUseCase = getArticleUseCase;
    this.getJournalDataUseCase = getJournalDataUseCase;
    this.downloadJournalUseCase = downloadJournalUseCase;
    this.getLastVideosUseCase = getLastVideosUseCase;
    this.getLast6NewsArticlesUseCase = getLast6NewsArticlesUseCase;
    this.addArticleBookmarkUseCase = addArticleBookmarkUseCase;
    this.removeArticleBookmarkUseCase = removeArticleBookmarkUseCase;
  }

  @Override public void onCreate(String articleId) {
    try {
      checkViewBound();
      checkConnection();

      getArticleUseCase.setRefresh(true);
      getArticleUseCase.setArticleId(articleId);
      getArticleUseCase.execute(articleSubscriber());

      getLastVideosUseCase.setRefresh(true);
      getLastVideosUseCase.execute(videosSubscriber());

      getLast6NewsArticlesUseCase.setRefresh(true);
      getLast6NewsArticlesUseCase.execute(lastNewsSubscriber());

      getJournalDataUseCase.setRefresh(true);
      getJournalDataUseCase.execute(journalSubscriber());
    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();
      getArticleUseCase.setRefresh(false);
      getArticleUseCase.setArticleId(articleId);
      getArticleUseCase.execute(articleSubscriber());

      getLastVideosUseCase.setRefresh(false);
      getLastVideosUseCase.execute(videosSubscriber());

      getLast6NewsArticlesUseCase.setRefresh(false);
      getLast6NewsArticlesUseCase.execute(lastNewsSubscriber());

      getJournalDataUseCase.setRefresh(false);
      getJournalDataUseCase.execute(journalSubscriber());
    }
  }

  @Override public void addToBookmarks(Article article) {
    addArticleBookmarkUseCase.setArticle(article);
    addArticleBookmarkUseCase.execute(addBookmarkSubscriber());
  }

  @Override public void downloadJournal(Journal journal) {
    try {
      checkViewBound();
      checkConnection();

      downloadJournalUseCase.setJournal(journal);
      downloadJournalUseCase.execute(downloadJournalSubscriber());
    } catch (ViewNotBoundException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();
    }
  }

  @Override public void removeBookmark(Article article) {
    removeArticleBookmarkUseCase.setArticle(article);
    removeArticleBookmarkUseCase.execute(removeBookmarkSubscriber());
  }

  @Override public void onDestroy() {
    getArticleUseCase.unsubscribe();
    getLast6NewsArticlesUseCase.unsubscribe();
    getLastVideosUseCase.unsubscribe();
    addArticleBookmarkUseCase.unsubscribe();
    getJournalDataUseCase.unsubscribe();
    downloadJournalUseCase.unsubscribe();
    removeArticleBookmarkUseCase.unsubscribe();
    super.onDestroy();
  }

  private BaseProgressSubscriber<Article> articleSubscriber() {
    return new BaseProgressSubscriber<Article>(this) {
      @Override public void onNext(Article article) {
        super.onNext(article);

        try {
          checkViewBound();

          getView().showArticle(article);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseProgressSubscriber<Bookmark> addBookmarkSubscriber() {
    return new BaseProgressSubscriber<Bookmark>(this) {
      @Override public void onNext(Bookmark article) {
        super.onNext(article);

        try {
          checkViewBound();

          getView().showAddedToBookmarks();
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseProgressSubscriber<Bookmark> removeBookmarkSubscriber() {
    return new BaseProgressSubscriber<Bookmark>(this) {
      @Override public void onNext(Bookmark article) {
        super.onNext(article);

        try {
          checkViewBound();

          getView().showRemovedFromBookmarks();
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseProgressSubscriber<List<Video>> videosSubscriber() {
    return new BaseProgressSubscriber<List<Video>>(this) {
      @Override public void onNext(List<Video> videos) {
        super.onNext(videos);

        try {
          checkViewBound();

          getView().showVideoNews(videos);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseProgressSubscriber<List<Article>> lastNewsSubscriber() {
    return new BaseProgressSubscriber<List<Article>>(this) {
      @Override public void onNext(List<Article> articles) {
        super.onNext(articles);

        try {
          checkViewBound();

          getView().showLastNews(articles);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseUseCaseSubscriber<Journal> journalSubscriber() {
    return new BaseUseCaseSubscriber<Journal>() {
      @Override public void onNext(Journal journal) {
        super.onNext(journal);

        try {
          checkViewBound();

          getView().showJournal(journal);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseUseCaseSubscriber<String> downloadJournalSubscriber() {
    return new BaseUseCaseSubscriber<>();
  }
}
