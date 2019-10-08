
package in.androidtest.bhavesh.HeadyApp.models.ProductResponse;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResponse implements Parcelable
{

    @SerializedName("categories")
    @Expose
    private ArrayList<Category> categories = null;
    @SerializedName("rankings")
    @Expose
    private ArrayList<Ranking> rankings = null;

    protected ProductResponse(Parcel in) {
        categories = in.createTypedArrayList(Category.CREATOR);
        rankings = in.createTypedArrayList(Ranking.CREATOR);
    }

    public static final Creator<ProductResponse> CREATOR = new Creator<ProductResponse>() {
        @Override
        public ProductResponse createFromParcel(Parcel in) {
            return new ProductResponse(in);
        }

        @Override
        public ProductResponse[] newArray(int size) {
            return new ProductResponse[size];
        }
    };

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(ArrayList<Ranking> rankings) {
        this.rankings = rankings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(categories);
        parcel.writeTypedList(rankings);
    }
}
