package inc.itnity.elbilad.di.modules;

import android.support.v7.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by st1ch on 04.11.2016.
 */

@Module
public class ActivityContextModule {

    private AppCompatActivity activityContext;

    public ActivityContextModule(AppCompatActivity activityContext) {
        this.activityContext = activityContext;
    }

    @Provides AppCompatActivity provideActivityContext(){
        return activityContext;
    }
}
