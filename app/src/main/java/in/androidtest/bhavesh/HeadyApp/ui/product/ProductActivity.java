package in.androidtest.bhavesh.HeadyApp.ui.product;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.androidtest.bhavesh.HeadyApp.R;
import in.androidtest.bhavesh.HeadyApp.adapters.CategoryAdapter;
import in.androidtest.bhavesh.HeadyApp.adapters.ProductAdapter;
import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Category;
import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Product;
import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Product_;
import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.Ranking;
import in.androidtest.bhavesh.HeadyApp.viewModels.ProductActivityViewModel;

public class ProductActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerList)
    RecyclerView recyclerList;

    @BindView(R.id.relativeProgress)
    RelativeLayout relativeProgress;

    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;

    private List<Ranking> rankingsList;
    private List<Category> categoryList;

    private ProductActivityViewModel productActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        productActivityViewModel = ViewModelProviders.of(this).get(ProductActivityViewModel.class);

        productActivityViewModel.init();

        productActivityViewModel.getCategoryList().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                Log.e("Size New", "" + categories.size());
                categoryList = categories;
                categoryAdapter.notifyDataSetChanged();
            }
        });

        productActivityViewModel.getRankingList().observe(this, new Observer<List<Ranking>>() {
            @Override
            public void onChanged(@Nullable List<Ranking> rankings) {
                rankingsList = rankings;
            }
        });

        initRecyclerView();

    }

    private void initRecyclerView() {
        categoryAdapter = new CategoryAdapter(this, productActivityViewModel.getCategoryList().getValue());
        recyclerList.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerList.setAdapter(categoryAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (rankingsList != null && rankingsList.size() > 0) {
            for (int i = 0; i < rankingsList.size(); i++) {
                menu.add(0, i, 0, rankingsList.get(i).getRanking());
            }
            menu.add(0, rankingsList.size(), 0, "All");
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ((item.getTitle().toString()).equals("All")) {
            categoryAdapter = new CategoryAdapter(this, productActivityViewModel.getCategoryList().getValue());
            recyclerList.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerList.setAdapter(categoryAdapter);
        } else {
            productAdapter = new ProductAdapter(this, filteredList(item.getTitle().toString()));
            recyclerList.setLayoutManager(new LinearLayoutManager(this));
            recyclerList.setAdapter(productAdapter);
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Product> filteredList(String title) {
        List<Product_> productsList  = null;
        for (Ranking ranking: rankingsList) {
            if (ranking.getRanking().equals(title)) {
                productsList = ranking.getProducts();
            }
        }

        List<Product> allProductList = new ArrayList<Product>();
        List<Product> filteredProductList = new ArrayList<Product>();

        if (categoryList != null && categoryList.size() > 0) {
            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getProducts() != null && categoryList.get(i).getProducts().size() > 0) {
                    for (int j = 0; j < categoryList.get(i).getProducts().size(); j++)
                        allProductList.add(categoryList.get(i).getProducts().get(j));
                }
            }

            for (int i = 0; i < productsList.size(); i++) {
                for (int j = 0; j < allProductList.size(); j++) {
                    if (productsList.get(i).getId() == allProductList.get(j).getId()) {
                        filteredProductList.add(allProductList.get(j));
                    }
                }
            }
        }
        return filteredProductList;
    }
}
