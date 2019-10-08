package in.androidtest.bhavesh.HeadyApp.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Category;
import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Ranking;
import in.androidtest.bhavesh.HeadyApp.repository.ProductRepository;

public class ProductActivityViewModel extends ViewModel {

    private MutableLiveData<List<Category>> mCategoryList;

    private MutableLiveData<List<Ranking>> mRankingList;

    private ProductRepository productRepository;

    public void init()
    {
        if (mCategoryList != null)
        {
            return;
        }
        if (mRankingList != null)
        {
            return;
        }

        productRepository = ProductRepository.getInstance();
        productRepository.getProductResonse();

        mCategoryList = productRepository.getCategoryList();
        mRankingList = productRepository.getRankingList();

    }

    public LiveData<List<Category>> getCategoryList() {
        return mCategoryList;
    }

    public LiveData<List<Ranking>> getRankingList() {
        return mRankingList;
    }
}
