
package in.androidtest.bhavesh.HeadyApp.models.ProductResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product_ implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("view_count")
    @Expose
    private int viewCount;
    @SerializedName("order_count")
    @Expose
    private int orderCount;
    @SerializedName("shares")
    @Expose
    private int shares;

    protected Product_(Parcel in) {
        id = in.readInt();
        viewCount = in.readInt();
        orderCount = in.readInt();
        shares = in.readInt();
    }

    public static final Creator<Product_> CREATOR = new Creator<Product_>() {
        @Override
        public Product_ createFromParcel(Parcel in) {
            return new Product_(in);
        }

        @Override
        public Product_[] newArray(int size) {
            return new Product_[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(viewCount);
        parcel.writeInt(orderCount);
        parcel.writeInt(shares);
    }
}
