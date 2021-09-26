package com.example.magazine.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.magazine.R;
import com.example.magazine.model.category.CategoryBase;
import com.example.magazine.model.category.CategoryItem;
import com.example.magazine.utils.GlobalPreference;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private List<CategoryItem> mCategoryItemList;
    private Context mContext;
    private static final String TAG = "CategoryAdapter";

    GlobalPreference mGlobalPreference;
    OnCategoryClickedListner mOnCategoryClickedListner;

    public CategoryAdapter(List<CategoryItem> mCategoryItemList, Context mContext) {
        this.mCategoryItemList = mCategoryItemList;
        this.mContext = mContext;
        mGlobalPreference=new GlobalPreference(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+mCategoryItemList.get(position).getCategoryName());
        String url="http://"+mGlobalPreference.RetrieveIp()+"/magazine/"+mCategoryItemList.get(position).getImage();
        holder.mTitleTextView.setText(mCategoryItemList.get(position).getCategoryName());
        Glide.with(mContext)
                .load(url)
                .into(holder.mCategoryImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCategoryClickedListner.onCategoryClicked(mCategoryItemList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCategoryItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitleTextView;
        private ImageView mCategoryImageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTextView=itemView.findViewById(R.id.category_item_text);
            mCategoryImageView=itemView.findViewById(R.id.image_itemview_category);
        }
    }

  public  interface OnCategoryClickedListner{
       void  onCategoryClicked(CategoryItem mCategoryItem);
    }

    public  void  setOnCategoryClicked(OnCategoryClickedListner mOnCategoryClicked){
        this.mOnCategoryClickedListner=mOnCategoryClicked;
    }
}

