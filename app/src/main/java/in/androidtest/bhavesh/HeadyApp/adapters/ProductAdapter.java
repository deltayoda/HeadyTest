package in.androidtest.bhavesh.HeadyApp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.androidtest.bhavesh.HeadyApp.R;
import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Category;
import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mContext;
    private List<Product> mProductList;

    public ProductAdapter(Context mContext,List<Product> mProductList)
    {
        this.mContext = mContext;
        this.mProductList = mProductList;
        Log.e("Size",""+mProductList.size());
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.product_each_row,viewGroup,false);
        return new ProductAdapter.ProductViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        Log.e("Name: "+i,mProductList.get(i).getName());
        productViewHolder.txtProduct.setText(mProductList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtProduct)
        TextView txtProduct;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
