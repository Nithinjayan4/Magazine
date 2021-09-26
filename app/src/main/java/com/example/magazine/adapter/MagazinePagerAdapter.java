package com.example.magazine.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.magazine.MagazineActivity;
import com.example.magazine.R;
import com.example.magazine.model.magazine.Magazinetem;
import com.example.magazine.utils.GlobalPreference;
import com.example.magazine.utils.TouchImageView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class MagazinePagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Magazinetem> magazineitemsList;
    private LayoutInflater layoutInflater;
    private ImageView imageViewPreview;
    private ImageView magazineAudioImageView;
    private GlobalPreference mGlobalPreference;
    MediaPlayer player = new MediaPlayer();
    public static final String TAG = MagazinePagerAdapter.class.getSimpleName();
  private OnAudioClickedListner mOnAudioClickedListner;
    public MagazinePagerAdapter(Context mContext, List<Magazinetem> magazineitemsList) {
        this.mContext = mContext;
        this.magazineitemsList = magazineitemsList;
        mGlobalPreference=new GlobalPreference(mContext);
    }


    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row_magazine_data, container, false);
        imageViewPreview = view.findViewById(R.id.image_magazineitem);
         magazineAudioImageView=view.findViewById(R.id.image_mgazine_audio);

         final Magazinetem magazinetem=magazineitemsList.get(position);



        String url="http://"+mGlobalPreference.RetrieveIp()+"/magazine/"+magazinetem.getImagePath();
        String audioUri="http://"+mGlobalPreference.RetrieveIp()+"/magazine/"+magazinetem.getAudio();
        Log.d(TAG, "instantiateItem: "+url);

        Glide.with(mContext)
                .load(url)
                .into(imageViewPreview);


        magazineAudioImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MagazineActivity.isPlaying){
                    MagazineActivity.isPlaying=false;
                }else{
                    MagazineActivity.isPlaying=true;
                }

            mOnAudioClickedListner.onAudioClicked(position);
            }
        });

        container.addView(view);
        return view;
    }
    public int getCount() {
        return magazineitemsList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    public interface OnAudioClickedListner{
        void onAudioClicked(int postion);
    }

    public void setmOnAudioClickedListner(OnAudioClickedListner mOnAudioClickedListner){
        this.mOnAudioClickedListner=mOnAudioClickedListner;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == ((View) object);
    }
}
