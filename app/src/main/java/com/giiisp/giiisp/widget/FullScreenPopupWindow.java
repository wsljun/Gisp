package com.giiisp.giiisp.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.PaperDatailEntity;
import com.giiisp.giiisp.entity.Song;
import com.giiisp.giiisp.utils.ImageLoader;
import com.giiisp.giiisp.view.activity.PaperDetailsActivity;
import com.giiisp.giiisp.view.adapter.ClickEntity;
import com.giiisp.giiisp.view.adapter.ItemClickAdapter;
import com.giiisp.giiisp.view.adapter.ViewPagerImage;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.recording.OnPlayerEventListener;
import com.giiisp.giiisp.widget.recording.Util;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

import static com.giiisp.giiisp.widget.recording.AppCache.getPlayService;

/**
 * 全屏的Window
 * Created by Thinkpad on 2017/6/27.
 */

public class FullScreenPopupWindow extends BasePopupWindow implements View.OnClickListener, OnPlayerEventListener, BaseImpl, ViewPager.OnPageChangeListener, BaseQuickAdapter.OnItemClickListener, SeekBar.OnSeekBarChangeListener {


    private ImageView ivFullscreenButton;
    private TextView tvPlayTime;
    private TextView tvDuration;
    private SeekBar seekBarPaper;
    private RecyclerView recyclerview;
    private ViewPager viewPagerFull;
    private ItemClickAdapter itemClickAdapter;
    private ArrayList<String> stringArrayList = null;
    private PaperDetailsActivity context;

    public ArrayList<String> getStringArrayList() {
        return stringArrayList;
    }

    public void setStringArrayList(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }

    public FullScreenPopupWindow(PaperDetailsActivity context) {
        super(context);
        this.context = context;
        /**全屏popup*/
        setPopupWindowFullScreen(true);
        context.setBaseImpl(this);
        context.setOnPlayerEventListener(this);
        initViews(context);
    }

    private void initViews(PaperDetailsActivity context) {
        viewPagerFull = (ViewPager) findViewById(R.id.viewpager_paper);
        recyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        tvPlayTime = (TextView) findViewById(R.id.tv_play_time);
        tvDuration = (TextView) findViewById(R.id.tv_duration);
        seekBarPaper = (SeekBar) findViewById(R.id.seek_bar_paper);
        ivFullscreenButton = (ImageView) findViewById(R.id.iv_fullscreen_button);
        ivFullscreenButton.setOnClickListener(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        itemClickAdapter = new ItemClickAdapter(context, R.layout.item_paperpull_pic, null, "");
        recyclerview.setAdapter(itemClickAdapter);
        itemClickAdapter.setOnItemClickListener(this);
    }


    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public Animator initShowAnimator() {
        AnimatorSet set;
        set = new AnimatorSet();
        ObjectAnimator transAnimator = ObjectAnimator.ofFloat(mAnimaView, "translationY", 250, 0).setDuration(600);
        transAnimator.setInterpolator(new OverShootInterpolator());
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mAnimaView, "alpha", 0.4f, 1).setDuration(250 * 3 / 2);
        set.playTogether(transAnimator, alphaAnimator);
        return null;
    }

    @Override
    public Animator initExitAnimator() {
        AnimatorSet set;
        set = new AnimatorSet();
        ObjectAnimator transAnimator = ObjectAnimator.ofFloat(mAnimaView, "translationY", 0, 250).setDuration(600);
        transAnimator.setInterpolator(new OverShootInterpolator(-6));
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mAnimaView, "alpha", 1f, 0).setDuration(800);
        set.playTogether(transAnimator, alphaAnimator);
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.layout_fragment_fullscreen);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.relative_full);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fullscreen_button:
                if (getContext().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
                    //切换竖屏
                    dismiss();
                    //                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                    getContext().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                }
                break;
            case R.id.tv_play_time:
                break;
        }
    }


    @Override
    public void onPublish(int progress) {
        tvPlayTime.setText(Util.formatSeconds(progress / 1000));
        seekBarPaper.setProgress(progress);
    }

    @Override
    public void onDuration(int duration) {
        //        seekBarPaper.setMax(duration);
        Log.i("--->>", "onDuration: " + duration);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onBufferingUpdate(int percent) {
        Log.i("--->>", "onBufferingUpdate: " + percent);
        int max = seekBarPaper.getMax();
        seekBarPaper.setSecondaryProgress(max / 100 * percent);
    }

    @Override
    public void onChange(Song music) {
        if (music == null) {
            return;
        }
        int position = music.getPosition();
        viewPagerFull.setCurrentItem(position);
        Log.i("--->>", "onChange: " + music.getDuration());
        tvDuration.setText("/ " + Util.formatSeconds(music.getDuration()));
        recyclerview.scrollToPosition(position);
        itemClickAdapter.setSelectedPosition(position);
        itemClickAdapter.notifyDataSetChanged();
        seekBarPaper.setMax(music.getDuration() * 1000);
        seekBarPaper.setProgress(0);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public void onPlayerPause() {
    }

    @Override
    public void onPlayerResume() {
    }


    @Override
    public void onTimer(long remain) {

    }

    @Override
    public void onMusicListUpdate() {
        initDownload();
    }

    public void initDownload() {
        List<ImageView> imagesFull = new ArrayList<>();
        if (stringArrayList != null) {
            for (int i = 0; i < stringArrayList.size(); i++) {
                itemClickAdapter.addData(new ClickEntity(stringArrayList.get(i)));
            }
            for (String s : stringArrayList) {
                PhotoView imageView = new PhotoView(getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ImageLoader.getInstance().displayImage(context, s, imageView);
                imagesFull.add(imageView);
            }
            viewPagerFull.setAdapter(new ViewPagerImage(getContext(), imagesFull));
        }
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        if (entity instanceof PaperDatailEntity) {
            List<ImageView> imagesFull = new ArrayList<>();
            if (stringArrayList != null) {
                for (int i = 0; i < stringArrayList.size(); i++) {
                    itemClickAdapter.addData(new ClickEntity(stringArrayList.get(i)));
                }
                for (String s : stringArrayList) {
                    PhotoView imageView = new PhotoView(getContext());
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ImageLoader.getInstance().displayImage(context, s, imageView);
                    imagesFull.add(imageView);
                }
                viewPagerFull.setAdapter(new ViewPagerImage(getContext(), imagesFull));
            } else {
                PaperDatailEntity.PaperBaseBean paperBase = ((PaperDatailEntity) entity).getPaperBase();
                if (paperBase == null)
                    return;
                PaperDatailEntity.PaperBaseBean.PhotoOneBean photoOne = paperBase.getPhotoOne();
                PaperDatailEntity.PaperBaseBean.PhotoOneBean photoTwo = paperBase.getPhotoTwo();
                PaperDatailEntity.PaperBaseBean.PhotoOneBean photoThree = paperBase.getPhotoThree();

                if (photoOne != null && photoOne.getRows() != null && photoOne.getRows().size() > 0) {
                    initView(photoOne);
                } else if (photoTwo != null && photoTwo.getRows() != null && photoTwo.getRows().size() > 0) {
                    initView(photoTwo);
                } else if (photoThree != null && photoThree.getRows() != null && photoThree.getRows().size() > 0) {
                    initView(photoThree);
                }
            }
        }
    }

    private void initView(PaperDatailEntity.PaperBaseBean.PhotoOneBean photoOne) {
        PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX rowsBeanXX = photoOne.getRows().get(0);
        PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.PhotosBean photos = rowsBeanXX.getPhotos();
        if (photos != null && photos.getRows() != null && photos.getRows().size() > 0) {
            List<PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.PhotosBean.RowsBean> photosBeanRows = photos.getRows();
            List<ImageView> images = new ArrayList<>();
            for (PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.PhotosBean.RowsBean photosBeanRow : photosBeanRows) {
                itemClickAdapter.addData(new ClickEntity(photosBeanRow.getPath(), photosBeanRow.getId()));
                PhotoView imageView = new PhotoView(getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ImageLoader.getInstance().displayImage(context, photosBeanRow.getPath(), imageView);
                images.add(imageView);
            }

            viewPagerFull.setAdapter(new ViewPagerImage(getContext(), images));
        }
    }

    @Override
    public void onFailure(String msg, Exception ex) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //手动调节进度
        // TODO Auto-generated method stub
        //seekbar的拖动位置
        int dest = seekBar.getProgress();
        //seekbar的最大值
        int max = seekBar.getMax();
        //调用service调节播放进度
        getPlayService().seekTo(dest);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        viewPagerFull.setCurrentItem(position);
        if (getPlayService() != null)
            getPlayService().play(position);
    }
}
