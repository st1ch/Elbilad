package inc.itnity.elbilad.di.modules;

import android.support.v7.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;
import inc.itnity.elbilad.di.MainActivityScope;
import inc.itnity.elbilad.domain.usecases.AddArticleBookmarkUseCase;
import inc.itnity.elbilad.domain.usecases.AddPhotoBookmarkUseCase;
import inc.itnity.elbilad.domain.usecases.AddVideoBookmarkUseCase;
import inc.itnity.elbilad.domain.usecases.GetArticleUseCase;
import inc.itnity.elbilad.domain.usecases.GetBookmarksUseCase;
import inc.itnity.elbilad.domain.usecases.GetCategoriesUseCase;
import inc.itnity.elbilad.domain.usecases.GetCategoryArticlesUseCase;
import inc.itnity.elbilad.domain.usecases.GetHomeArticlesUseCase;
import inc.itnity.elbilad.domain.usecases.GetLast6NewsArticlesUseCase;
import inc.itnity.elbilad.domain.usecases.GetLastNewsUseCase;
import inc.itnity.elbilad.domain.usecases.GetLastVideosUseCase;
import inc.itnity.elbilad.domain.usecases.GetPhotosUseCase;
import inc.itnity.elbilad.domain.usecases.GetVideoUseCase;
import inc.itnity.elbilad.domain.usecases.GetVideosUseCase;
import inc.itnity.elbilad.presentation.presenters.ArticleDetailsPresenter;
import inc.itnity.elbilad.presentation.presenters.ArticleDetailsPresenterImpl;
import inc.itnity.elbilad.presentation.presenters.BaseHomePresenter;
import inc.itnity.elbilad.presentation.presenters.BaseHomePresenterImpl;
import inc.itnity.elbilad.presentation.presenters.BookmarksPresenter;
import inc.itnity.elbilad.presentation.presenters.BookmarksPresenterImpl;
import inc.itnity.elbilad.presentation.presenters.HomeScreenPresenter;
import inc.itnity.elbilad.presentation.presenters.HomeScreenPresenterImpl;
import inc.itnity.elbilad.presentation.presenters.LastNewsPresenter;
import inc.itnity.elbilad.presentation.presenters.LastNewsPresenterImpl;
import inc.itnity.elbilad.presentation.presenters.MainMenuPresenter;
import inc.itnity.elbilad.presentation.presenters.MainMenuPresenterImpl;
import inc.itnity.elbilad.presentation.presenters.PhotoCategoryPresenter;
import inc.itnity.elbilad.presentation.presenters.PhotoCategoryPresenterImpl;
import inc.itnity.elbilad.presentation.presenters.PhotoDetailsPresenter;
import inc.itnity.elbilad.presentation.presenters.PhotoDetailsPresenterImpl;
import inc.itnity.elbilad.presentation.presenters.SimpleNewsPresenter;
import inc.itnity.elbilad.presentation.presenters.SimpleNewsPresenterImpl;
import inc.itnity.elbilad.presentation.presenters.VideoCategoryPresenter;
import inc.itnity.elbilad.presentation.presenters.VideoCategoryPresenterImpl;
import inc.itnity.elbilad.presentation.presenters.VideoDetailsPresenter;
import inc.itnity.elbilad.presentation.presenters.VideoDetailsPresenterImpl;
import inc.itnity.elbilad.utils.DialogHelper;

/**
 * Created by st1ch on 20.01.17.
 */
@Module public class MainActivityModule {

  @Provides @MainActivityScope DialogHelper provideDialogHelper(AppCompatActivity activity) {
    return new DialogHelper(activity);
  }

  @Provides HomeScreenPresenter provideHomeScreenPresenter(
      GetHomeArticlesUseCase getHomeArticlesUseCase) {
    return new HomeScreenPresenterImpl(getHomeArticlesUseCase);
  }

  @Provides MainMenuPresenter provideMainMenuPresenter(GetCategoriesUseCase getCategoriesUseCase) {
    return new MainMenuPresenterImpl(getCategoriesUseCase);
  }

  @Provides BaseHomePresenter provideBaseHomePresenter(GetCategoriesUseCase getCategoriesUseCase) {
    return new BaseHomePresenterImpl(getCategoriesUseCase);
  }

  @Provides SimpleNewsPresenter provideSimpleNewsPresenter(
      GetCategoryArticlesUseCase getCategoryArticlesUseCase) {
    return new SimpleNewsPresenterImpl(getCategoryArticlesUseCase);
  }

  @Provides ArticleDetailsPresenter provideArticleDetailsPresenter(
      GetArticleUseCase getArticleUseCase, GetLastVideosUseCase getLastVideosUseCase,
      GetLast6NewsArticlesUseCase getLast6NewsArticlesUseCase,
      AddArticleBookmarkUseCase addArticleBookmarkUseCase) {
    return new ArticleDetailsPresenterImpl(getArticleUseCase, getLastVideosUseCase,
        getLast6NewsArticlesUseCase, addArticleBookmarkUseCase);
  }

  @Provides VideoCategoryPresenter provideVideoCategoryPresenter(
      GetVideosUseCase getVideosUseCase) {
    return new VideoCategoryPresenterImpl(getVideosUseCase);
  }

  @Provides PhotoCategoryPresenter providePhotoCategoryPresenter(
      GetPhotosUseCase getPhotosUseCase) {
    return new PhotoCategoryPresenterImpl(getPhotosUseCase);
  }

  @Provides BookmarksPresenter provideBookmarksPresenter(GetBookmarksUseCase getBookmarksUseCase) {
    return new BookmarksPresenterImpl(getBookmarksUseCase);
  }

  @Provides LastNewsPresenter provideLastNewsPresenter(GetLastNewsUseCase getLastNewsUseCase) {
    return new LastNewsPresenterImpl(getLastNewsUseCase);
  }

  @Provides VideoDetailsPresenter provideVideoDetailsPresenter(GetVideoUseCase getVideosUseCase,
      AddVideoBookmarkUseCase addArticleBookmarkUseCase) {
    return new VideoDetailsPresenterImpl(getVideosUseCase, addArticleBookmarkUseCase);
  }

  @Provides PhotoDetailsPresenter providePhotoDetailsPresenter(GetPhotosUseCase getPhotosUseCase,
      AddPhotoBookmarkUseCase addPhotoBookmarkUseCase) {
    return new PhotoDetailsPresenterImpl(getPhotosUseCase, addPhotoBookmarkUseCase);
  }
}
