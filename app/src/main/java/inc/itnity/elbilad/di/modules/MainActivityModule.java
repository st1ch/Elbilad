package inc.itnity.elbilad.di.modules;

import android.support.v7.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;
import inc.itnity.elbilad.di.MainActivityScope;
import inc.itnity.elbilad.domain.buses.RefreshTabRxBus;
import inc.itnity.elbilad.domain.usecases.AddArticleBookmarkUseCase;
import inc.itnity.elbilad.domain.usecases.AddVideoBookmarkUseCase;
import inc.itnity.elbilad.domain.usecases.DownloadJournalUseCase;
import inc.itnity.elbilad.domain.usecases.GetArticleUseCase;
import inc.itnity.elbilad.domain.usecases.GetBookmarksUseCase;
import inc.itnity.elbilad.domain.usecases.GetCategoriesUseCase;
import inc.itnity.elbilad.domain.usecases.GetCategoryArticlesUseCase;
import inc.itnity.elbilad.domain.usecases.GetGalleryUseCase;
import inc.itnity.elbilad.domain.usecases.GetHomeArticlesUseCase;
import inc.itnity.elbilad.domain.usecases.GetJournalDataUseCase;
import inc.itnity.elbilad.domain.usecases.GetLast6NewsArticlesUseCase;
import inc.itnity.elbilad.domain.usecases.GetLastNewsUseCase;
import inc.itnity.elbilad.domain.usecases.GetLastVideosUseCase;
import inc.itnity.elbilad.domain.usecases.GetMostReadNewsUseCase;
import inc.itnity.elbilad.domain.usecases.GetPhotosUseCase;
import inc.itnity.elbilad.domain.usecases.GetVideoArticleUseCase;
import inc.itnity.elbilad.domain.usecases.GetVideoUseCase;
import inc.itnity.elbilad.domain.usecases.GetVideosUseCase;
import inc.itnity.elbilad.domain.usecases.RemoveArticleBookmarkUseCase;
import inc.itnity.elbilad.domain.usecases.RemoveVideoBookmarkUseCase;
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
import inc.itnity.elbilad.presentation.presenters.MostReadNewsPresenter;
import inc.itnity.elbilad.presentation.presenters.MostReadNewsPresenterImpl;
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
      GetHomeArticlesUseCase getHomeArticlesUseCase, RefreshTabRxBus refreshTabRxBus,
      GetJournalDataUseCase getJournalDataUseCase, DownloadJournalUseCase downloadJournalUseCase) {
    return new HomeScreenPresenterImpl(getHomeArticlesUseCase, getJournalDataUseCase,
        downloadJournalUseCase, refreshTabRxBus);
  }

  @Provides MainMenuPresenter provideMainMenuPresenter(GetCategoriesUseCase getCategoriesUseCase) {
    return new MainMenuPresenterImpl(getCategoriesUseCase);
  }

  @Provides BaseHomePresenter provideBaseHomePresenter(GetCategoriesUseCase getCategoriesUseCase) {
    return new BaseHomePresenterImpl(getCategoriesUseCase);
  }

  @Provides SimpleNewsPresenter provideSimpleNewsPresenter(
      GetCategoryArticlesUseCase getCategoryArticlesUseCase, RefreshTabRxBus refreshTabRxBus) {
    return new SimpleNewsPresenterImpl(getCategoryArticlesUseCase, refreshTabRxBus);
  }

  @Provides ArticleDetailsPresenter provideArticleDetailsPresenter(
      GetArticleUseCase getArticleUseCase, GetLastVideosUseCase getLastVideosUseCase,
      GetLast6NewsArticlesUseCase getLast6NewsArticlesUseCase,
      AddArticleBookmarkUseCase addArticleBookmarkUseCase,
      GetJournalDataUseCase getJournalDataUseCase, DownloadJournalUseCase downloadJournalUseCase,
      RemoveArticleBookmarkUseCase removeArticleBookmarkUseCase) {
    return new ArticleDetailsPresenterImpl(getArticleUseCase, getJournalDataUseCase,
        downloadJournalUseCase, getLastVideosUseCase, getLast6NewsArticlesUseCase,
        addArticleBookmarkUseCase, removeArticleBookmarkUseCase);
  }

  @Provides VideoCategoryPresenter provideVideoCategoryPresenter(GetVideosUseCase getVideosUseCase,
      RefreshTabRxBus refreshTabRxBus) {
    return new VideoCategoryPresenterImpl(getVideosUseCase, refreshTabRxBus);
  }

  @Provides PhotoCategoryPresenter providePhotoCategoryPresenter(GetPhotosUseCase getPhotosUseCase,
      GetGalleryUseCase getGalleryUseCase, RefreshTabRxBus refreshTabRxBus) {
    return new PhotoCategoryPresenterImpl(getPhotosUseCase, getGalleryUseCase, refreshTabRxBus);
  }

  @Provides BookmarksPresenter provideBookmarksPresenter(GetBookmarksUseCase getBookmarksUseCase,
      RefreshTabRxBus refreshTabRxBus) {
    return new BookmarksPresenterImpl(getBookmarksUseCase, refreshTabRxBus);
  }

  @Provides LastNewsPresenter provideLastNewsPresenter(GetLastNewsUseCase getLastNewsUseCase,
      RefreshTabRxBus refreshTabRxBus) {
    return new LastNewsPresenterImpl(getLastNewsUseCase, refreshTabRxBus);
  }

  @Provides MostReadNewsPresenter provideMostReadNewsPresenter(
      GetMostReadNewsUseCase getLastNewsUseCase, RefreshTabRxBus refreshTabRxBus) {
    return new MostReadNewsPresenterImpl(getLastNewsUseCase, refreshTabRxBus);
  }

  @Provides VideoDetailsPresenter provideVideoDetailsPresenter(GetVideoUseCase getVideosUseCase,
      GetVideoArticleUseCase getVideoArticleUseCase,
      AddVideoBookmarkUseCase addArticleBookmarkUseCase,
      RemoveVideoBookmarkUseCase removeVideoBookmarkUseCase) {
    return new VideoDetailsPresenterImpl(getVideosUseCase, getVideoArticleUseCase,
        addArticleBookmarkUseCase, removeVideoBookmarkUseCase);
  }

  @Provides PhotoDetailsPresenter providePhotoDetailsPresenter(
      GetGalleryUseCase getGalleryUseCase) {
    return new PhotoDetailsPresenterImpl(getGalleryUseCase);
  }
}
