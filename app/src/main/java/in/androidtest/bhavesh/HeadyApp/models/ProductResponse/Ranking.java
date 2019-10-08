
package in.androidtest.bhavesh.HeadyApp.models.ProductResponse;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Product_;

public class Ranking implements Parcelable
{

    @SerializedName("ranking")
    @Expose
    private String ranking;
    @SerializedName("products")
    @Expose
    private ArrayList<Product_> products = null;

    protected Ranking(Parcel in) {
        ranking = in.readString();
        products = in.createTypedArrayList(Product_.CREATOR);
    }

    public static final Creator<Ranking> CREATOR = new Creator<Ranking>() {
        @Override
        public Ranking createFromParcel(Parcel in) {
            return new Ranking(in);
        }

        @Override
        public Ranking[] newArray(int size) {
            return new Ranking[size];
        }
    };

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public ArrayList<Product_> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product_> products) {
        this.products = products;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ranking);
        parcel.writeTypedList(products);
    }
}
