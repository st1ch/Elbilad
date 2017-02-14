package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.models.article.ArticleItem;
import inc.itnity.elbilad.domain.subscribers.BaseProgressSubscriber;
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

  public BookmarksPresenterImpl(GetBookmarksUseCase getBookmarksUseCase) {
    this.getBookmarksUseCase = getBookmarksUseCase;
  }

  @Override public void onCreate() {
    getBookmarksUseCase.execute(bookmarksSubscriber());
  }

  @Override public void onDestroy() {
    getBookmarksUseCase.unsubscribe();
    super.onDestroy();
  }

  private BaseProgressSubscriber<List<ArticleItem>> bookmarksSubscriber() {
    return new BaseProgressSubscriber<List<ArticleItem>>(this) {
      @Override public void onNext(List<ArticleItem> articleItems) {
        super.onNext(articleItems);

        try {
          checkViewBound();

          getView().showBookmarks(articleItems);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }
}
