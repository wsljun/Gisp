package com.nccott.videoapp;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,BaseQuickAdapter.OnItemClickListener {


    private ViewPager viewPager ;
    private RecyclerView recyclerView;
    private ItemClickAdapter itemClickAdapter;
    private List<PhotoDao> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        initData();
    }

    private void initView() {
        list = new ArrayList<>();
        list.add(new PhotoDao("3",PhotoDao.GifPath));
        list.add(new PhotoDao("1",PhotoDao.PngPath));
        list. add(new PhotoDao("2",PhotoDao.Mp4Path));
        list.add(new PhotoDao("3",PhotoDao.GifPath));
        list.add(new PhotoDao("1",PhotoDao.PngPath));
        list. add(new PhotoDao("2",PhotoDao.Mp4Path));
        list.add(new PhotoDao("3",PhotoDao.GifPath));
        list.add(new PhotoDao("1",PhotoDao.PngPath));
        list. add(new PhotoDao("2",PhotoDao.Mp4Path));

        viewPager = findViewById(R.id.viewpager_paper);
        recyclerView = findViewById(R.id.recycler_view);
        viewPager.addOnPageChangeListener(this);

        // 图片，视频列表
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        itemClickAdapter = new ItemClickAdapter(this, R.layout.item_pic, list);
        itemClickAdapter.setOnItemChildClickListener(this::onItemClick);
        itemClickAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(itemClickAdapter);


        // viewPager 展示 recyclerView 列表项内容
        viewPager.setAdapter(new ImageAdapter(this,list)); // todo fix photo and video
        viewPager.setCurrentItem(0);
    }

    private void initData() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        viewPager.setCurrentItem(position);
    }

    @Override
    public void onPageSelected(int position) {
        recyclerView.scrollToPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        viewPager.setCurrentItem(position);
        recyclerView.scrollToPosition(position);
        Toast.makeText(this,"vi: "+position,Toast.LENGTH_SHORT).show();
    }

    private class ImageAdapter extends PagerAdapter {

        private List<PhotoDao> viewlist; // todo 图片和视频混排
        private Activity activity;
        public ImageAdapter(Activity activity) {
            this.viewlist = viewlist;
            this.activity = activity;
        }
        public ImageAdapter(Activity activity, List<PhotoDao> viewlist) {
            this.viewlist = viewlist;
            this.activity = activity;
        }

        @Override
        public int getCount() {
            //设置成最大，使用户看不到边界
            return viewlist.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
//            ((ViewPager) container).removeView(viewlist.get(position % viewlist.size()));
            //Warning：不要在这里调用removeView
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (viewlist.get(position).getType() !="2") {//图片,gif
                final ImageView imageView = new ImageView(activity.getApplicationContext());
                Glide.with(activity).load(viewlist.get(position).getPath())
                        .into(imageView);
                container.addView(imageView);
                return imageView;
            }else{//视频
                final VideoView videoView = new VideoView(activity.getApplicationContext());
                videoView.setVideoURI(Uri.parse(viewlist.get(position).getPath()));
                //开始播放
//                MediaController mediaController = new MediaController(activity);
//                videoView.setMediaController(mediaController);
                videoView.start();
////                Sc
//                SurfaceView surfaceView = new SurfaceView(activity);
//                surfaceView.getHolder().addCallback(new LmnSurfaceCallback());
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
//                        , LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER);
//                surfaceView.setLayoutParams(layoutParams);
//
//
//                IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
//                ijkMediaPlayer.setSurface(surfaceView);
                container.addView(videoView);

                return videoView;
            }
        }

    }






}
