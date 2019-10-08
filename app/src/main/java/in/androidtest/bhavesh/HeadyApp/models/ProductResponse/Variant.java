
package in.androidtest.bhavesh.HeadyApp.models.ProductResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variant implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("size")
    @Expose
    private int size;
    @SerializedName("price")
    @Expose
    private int price;

    protected Variant(Parcel in) {
        id = in.readInt();
        color = in.readString();
        size = in.readInt();
        price = in.readInt();
    }

    public static final Creator<Variant> CREATOR = new Creator<Variant>() {
        @Override
        public Variant createFromParcel(Parcel in) {
            return new Variant(in);
        }

        @Override
        public Variant[] newArray(int size) {
            return new Variant[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(color);
        parcel.writeInt(size);
        parcel.writeInt(price);
    }
}
