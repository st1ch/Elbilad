package inc.itnity.elbilad.di.components;

import android.content.Context;
import com.google.gson.Gson;
import dagger.Component;
import inc.itnity.elbilad.ElbiladApplication;
import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.data.repositories.remote.ElbiladRemoteDataSource;
import inc.itnity.elbilad.data.rest.ApiManager;
import inc.itnity.elbilad.data.rest.api.ElbiladAPI;
import inc.itnity.elbilad.data.sync.SyncAdapter;
import inc.itnity.elbilad.data.sync.SyncAdapterManager;
import inc.itnity.elbilad.data.sync.SyncService;
import inc.itnity.elbilad.di.modules.ApplicationModule;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import inc.itnity.elbilad.presentation.custom.ElbiladTextView;
import inc.itnity.elbilad.utils.FragmentNavigator;
import inc.itnity.elbilad.utils.PreferenceHelper;
import io.reactivecache.ReactiveCache;
import javax.inject.Singleton;

/**
 * Created by st1ch on 15.01.17.
 */
@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  void inject(ElbiladApplication elbiladApplication);

  void inject(SyncService syncService);
  void inject(SyncAdapter syncAdapter);

  void inject(ElbiladTextView elbiladTextView);

  FragmentNavigator fragmentNavigator();

  PreferenceHelper preferenceHelper();

  Context context();

  SubscribeOn subscribeOn();

  ObserveOn observeOn();

  ReactiveCache reactiveCache();

  Gson gson();

  ApiManager apiManager();

  ElbiladAPI elbiladApi();

  ElbiladRemoteDataSource elbiladRemoteDataSource();

  ElbiladRepository elbiladRepository();

  SyncAdapterManager syncAdapterManager();
}
