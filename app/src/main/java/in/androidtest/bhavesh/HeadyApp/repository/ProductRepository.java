package in.androidtest.bhavesh.HeadyApp.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Category;
import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.ProductResponse;
import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Ranking;
import in.androidtest.bhavesh.HeadyApp.network.NetworkClient;
import in.androidtest.bhavesh.HeadyApp.network.NetworkInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ProductRepository {

    private static final String TAG = getInstance().getClass().getSimpleName();

    private static ProductRepository instance;
    private ArrayList<Category> categoryDataSet = new ArrayList<>();
    private ArrayList<Ranking> rankingDataSet = new ArrayList<>();

    public static ProductRepository getInstance(){
        if(instance == null){
            instance = new ProductRepository();
        }
        return instance;
    }


    public MutableLiveData<List<Category>> getCategoryList() {
        MutableLiveData<List<Category>> categoryList = new MutableLiveData<>();
        categoryList.setValue(categoryDataSet);
        return categoryList;
    }

    public MutableLiveData<List<Ranking>> getRankingList() {
        MutableLiveData<List<Ranking>> rankingList = new MutableLiveData<>();
        rankingList.setValue(rankingDataSet);
        return rankingList;
    }

    public void getProductResonse() {
        getProductResponseObservable().subscribeWith(getProductResponseObserver());
    }

    private Observable<Response<ProductResponse>> getProductResponseObservable(){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getProductResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    private DisposableObserver<Response<ProductResponse>> getProductResponseObserver(){
        return new DisposableObserver<Response<ProductResponse>>() {

            @Override
            public void onNext(@NonNull Response<ProductResponse> productResponse) {
                if (productResponse.raw().cacheResponse() != null) {
                    Log.e("Network", "response came from cache");
                }

                if (productResponse.raw().networkResponse() != null) {
                    Log.e("Network", "response came from server");
                }
                Log.e("Size Category",""+productResponse.body().getCategories().size());
                categoryDataSet.clear();
                rankingDataSet.clear();
                categoryDataSet.addAll(productResponse.body().getCategories());
                rankingDataSet.addAll(productResponse.body().getRankings());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }
}
