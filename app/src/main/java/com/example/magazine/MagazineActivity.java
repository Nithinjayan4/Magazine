package com.example.magazine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.magazine.adapter.MagazinePagerAdapter;
import com.example.magazine.model.category.CategoryBase;
import com.example.magazine.model.category.CategoryItem;
import com.example.magazine.model.magazine.MagazineBase;
import com.example.magazine.model.magazine.Magazinetem;
import com.example.magazine.utils.GlobalPreference;

import java.util.ArrayList;
import java.util.List;

public class MagazineActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,MagazinePagerAdapter.OnAudioClickedListner {

    int selectedPosition = 0;
    private ViewPager viewPager;
    private TextView count;
    Boolean pageChanged = false;
    MagazinePagerAdapter magazinePagerAdapter;
    private List<Magazinetem> magazinetemsList=new ArrayList<>();
    Toolbar mToolbar;
     private CategoryItem mCategoryItem;
     private ApiInterface mApiInterface;
     private GlobalPreference mGlobalPreference;
     MediaPlayer player;

     public static boolean isPlaying=false;

    private static final String TAG = "MagazineActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazine);
        initData();

    }

    private void initData(){
        player = new MediaPlayer();
        mGlobalPreference=new GlobalPreference(this);
        mToolbar=findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back); // your drawable
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        count=findViewById(R.id.text_magazine_count);
        viewPager=findViewById(R.id.viewpager_magazine);
        viewPager.addOnPageChangeListener(this);
        setCurrentItem(selectedPosition);
        mCategoryItem=(CategoryItem)getIntent().getSerializableExtra("category");
        getData(mCategoryItem);

    }

    private void getData(CategoryItem categoryItem){
        mApiInterface = ApiClient.getRetrofit(mGlobalPreference.RetrieveIp()).create(ApiInterface.class);
        Call<MagazineBase> mCategoryBaseCall=mApiInterface.getData(categoryItem.getId());
        mCategoryBaseCall.enqueue(new Callback<MagazineBase>() {
            @Override
            public void onResponse(Call<MagazineBase> call, Response<MagazineBase> response) {
                setData(response.body().getData());
                displayMetaInfo(selectedPosition);
            }

            @Override
            public void onFailure(Call<MagazineBase> call, Throwable t) {

            }
        });
    }

    public void setData(List<Magazinetem>  magazinetemList){
        magazinetemsList.clear();
        magazinetemsList.addAll(magazinetemList);
        magazinePagerAdapter=new MagazinePagerAdapter(this,magazinetemList);
        viewPager.setAdapter(magazinePagerAdapter);
        magazinePagerAdapter.setmOnAudioClickedListner(this);
        displayMetaInfo(selectedPosition);

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void playAudio(String audioUri){
          player=new MediaPlayer();
        try {
            Uri uri = Uri.parse(audioUri);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(this, uri);
            player.prepare();
            player.start();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }


    public void stopAudio(){
        if(player.isPlaying()){
            player.stop();
        }
        isPlaying=false;
    }

    @Override
    public void onPageSelected(int position) {
        displayMetaInfo(position);
        stopAudio();
        isPlaying=false;
//        String audioUri="http://"+mGlobalPreference.RetrieveIp()+"/magazine/"+magazinetemsList.get(position).getAudio();
//        playAudio(audioUri);
    }

    private void setCurrentItem(int position) {
        viewPager.setCurrentItem(position, false);
        displayMetaInfo(selectedPosition);
    }

    private void displayMetaInfo(int position) {
        pageChanged = true;
        count.setText((position + 1) + " of " + magazinetemsList.size());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopAudio();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onAudioClicked(int postion) {

        if(magazinetemsList.get(postion).getStatus().equals("0")){
             if(MagazineActivity.isPlaying){
                 stopAudio();
                 String audioUri="http://"+mGlobalPreference.RetrieveIp()+"/magazine/"+magazinetemsList.get(postion).getAudio();

                 playAudio(audioUri);
             }else{
                 stopAudio();
             }

        }else {
            Toast.makeText(this, "No Audio Found", Toast.LENGTH_SHORT).show();
        }

    }
}
