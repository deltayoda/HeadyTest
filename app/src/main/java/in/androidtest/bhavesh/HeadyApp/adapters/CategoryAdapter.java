package in.androidtest.bhavesh.HeadyApp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.androidtest.bhavesh.HeadyApp.R;
import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context mContext;
    private List<Category> mCategoryList = new ArrayList<>();

    public CategoryAdapter(Context mContext,List<Category> mCategoryList)
    {
        this.mContext = mContext;
        this.mCategoryList = mCategoryList;
        Log.e("Size",""+mCategoryList.size());
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.category_each_row,viewGroup,false);
        return new CategoryAdapter.CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        Log.e("Name: "+i,mCategoryList.get(i).getName());
        categoryViewHolder.txtCategory.setText(mCategoryList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.txtCategory)
        TextView txtCategory;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
