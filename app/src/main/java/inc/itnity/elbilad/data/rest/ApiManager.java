package inc.itnity.elbilad.data.rest;

import com.google.gson.Gson;
import inc.itnity.elbilad.constants.ApiConfig;
import inc.itnity.elbilad.domain.interceptors.HeaderAuthInterceptor;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by st1ch on 11.01.17.
 */

public class ApiManager {
  private static final String BASE_URL = ApiConfig.BASE_URL;
  private Retrofit mRetrofit;

  public ApiManager(Gson gson) {

    HttpLoggingInterceptor interceptorBody = new HttpLoggingInterceptor();
    interceptorBody.setLevel(HttpLoggingInterceptor.Level.BODY);

    /**
     * Add Auth header interceptor
     */

    HeaderAuthInterceptor headerAuthInterceptor = new HeaderAuthInterceptor();

    OkHttpClient okHttpClient =
        new OkHttpClient.Builder().connectTimeout(ApiConfig.HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(ApiConfig.HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(ApiConfig.HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptorBody)
            .addInterceptor(headerAuthInterceptor)
            .build();

    mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build();
  }

  public Retrofit getRetrofit() {
    return mRetrofit;
  }
}
