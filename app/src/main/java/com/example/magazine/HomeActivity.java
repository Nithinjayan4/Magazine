package com.example.magazine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.magazine.adapter.CategoryAdapter;
import com.example.magazine.model.category.CategoryBase;
import com.example.magazine.model.category.CategoryItem;
import com.example.magazine.utils.GlobalPreference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryClickedListner{

    CategoryAdapter mCategoryAdapter;
    
    @BindView(R.id.recycler_view_category)
    RecyclerView mRecyclerViewCategory;

    private GlobalPreference mGlobalPreference;
    private ApiInterface mApiInterface;
    private ArrayList<CategoryItem> mCategoryArrayList=new ArrayList<>();
    private static final String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setUp();
        getData();
    }


    public void setUp(){
        mRecyclerViewCategory.setLayoutManager(new GridLayoutManager(this,2));
        mGlobalPreference=new GlobalPreference(getApplicationContext());
        mCategoryAdapter=new CategoryAdapter(mCategoryArrayList,this);
        mRecyclerViewCategory.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setOnCategoryClicked(this);
    }
    public void getData(){
        mApiInterface = ApiClient.getRetrofit(mGlobalPreference.RetrieveIp()).create(ApiInterface.class);
        Call<CategoryBase> mCategoryBaseCall=mApiInterface.getCategory();
        mCategoryBaseCall.enqueue(new Callback<CategoryBase>() {
            @Override
            public void onResponse(Call<CategoryBase> call, Response<CategoryBase> response) {
                List<CategoryItem> mCategoryItemList=response.body().getData();
                mCategoryArrayList.clear();
                mCategoryArrayList.addAll(mCategoryItemList);
                mCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CategoryBase> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    @Override
    public void onCategoryClicked(CategoryItem mCategoryItem) {

        Intent intent=new Intent(HomeActivity.this,MagazineActivity.class);
        intent.putExtra("category",(Serializable)mCategoryItem);
        startActivity(intent);
    }
}
