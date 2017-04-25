package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.buses.RefreshTabRxBus;
import inc.itnity.elbilad.domain.models.article.Bookmark;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.GetBookmarksUseCase;
import inc.itnity.elbilad.presentation.presenters.base.ProgressPresenter;
import inc.itnity.elbilad.presentation.views.BookmarksView;
import java.util.List;

/**
 * Created by st1ch on 14.02.17.
 */

public class BookmarksPresenterImpl extends ProgressPresenter<BookmarksView>
    implements BookmarksPresenter {

  private GetBookmarksUseCase getBookmarksUseCase;

  private RefreshTabRxBus refreshTabRxBus;
  private BaseUseCaseSubscriber<Boolean> refreshTabSubscriber;

  public BookmarksPresenterImpl(GetBookmarksUseCase getBookmarksUseCase,
      RefreshTabRxBus refreshTabRxBus) {
    this.getBookmarksUseCase = getBookmarksUseCase;
    this.refreshTabRxBus = refreshTabRxBus;
  }

  @Override public void onCreate() {
    getBookmarksUseCase.execute(bookmarksSubscriber());

    if (refreshTabSubscriber == null) {
      refreshTabSubscriber = refreshTabSubscriber();
      refreshTabRxBus.getOpenTabObservable().subscribe(refreshTabSubscriber);
    }
  }

  @Override public void onDestroy() {
    getBookmarksUseCase.unsubscribe();
    if (refreshTabSubscriber != null && !refreshTabSubscriber.isUnsubscribed()) {
      refreshTabSubscriber.unsubscribe();
      refreshTabSubscriber = null;
    }
    super.onDestroy();
  }

  private BaseProgressSubscriber<List<Bookmark>> bookmarksSubscriber() {
    return new BaseProgressSubscriber<List<Bookmark>>(this) {
      @Override public void onNext(List<Bookmark> articleItems) {
        super.onNext(articleItems);

        try {
          checkViewBound();

          if(articleItems != null && !articleItems.isEmpty()){
            getView().showBookmarks(articleItems);
          } else {
            getView().showEmptyView();
          }
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseUseCaseSubscriber<Boolean> refreshTabSubscriber() {
    return new BaseUseCaseSubscriber<Boolean>() {
      @Override public void onNext(Boolean aBoolean) {
        super.onNext(aBoolean);

        onCreate();
      }
    };
  }
}
