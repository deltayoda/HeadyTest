package in.androidtest.bhavesh.HeadyApp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;
import java.io.IOException;

import in.androidtest.bhavesh.HeadyApp.MyApplication;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkClient {

    public static Retrofit retrofit;

    public static Retrofit getRetrofit(){

        //----Caching-------------------------
        File httpCacheDirectory = new File(MyApplication.getAppContext().getCacheDir(), "offlineCache");

        //20 MB
        Cache cache = new Cache(httpCacheDirectory, 20 * 1024 * 1024);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(offlineInterceptor)
                .addNetworkInterceptor(onlineInterceptor)
                .cache(cache)
                .build();

        //----------------------------------------------------------
        if(retrofit==null) {

             retrofit = new Retrofit.Builder()
                     .baseUrl("https://stark-spire-93433.herokuapp.com/")//https://newsapi.org/v2/
                     .addConverterFactory(GsonConverterFactory.create())
                     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                     .client(okHttpClient)
                     .build();
        }

        return retrofit;
    }


    static Interceptor onlineInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response response = chain.proceed(chain.request());
            int maxAge = 60; // read from cache for 60 seconds even if there is internet connection
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        }
    };


    static Interceptor offlineInterceptor= new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!isConnectedToInternet(MyApplication.getAppContext())) {
                int maxStale = 60 * 60 * 24 * 30; // Offline cache available for 30 days
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return chain.proceed(request);
        }
    };

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
