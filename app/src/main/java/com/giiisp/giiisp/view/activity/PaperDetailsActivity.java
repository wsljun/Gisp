package com.giiisp.giiisp.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.api.UrlConstants;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseApp;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.base.BaseMvpActivity;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.DaoSession;
import com.giiisp.giiisp.entity.DownloadController;
import com.giiisp.giiisp.entity.LiteratureEntity;
import com.giiisp.giiisp.entity.Note;
import com.giiisp.giiisp.entity.NoteDao;
import com.giiisp.giiisp.entity.PaperDatailEntity;
import com.giiisp.giiisp.entity.PaperEntity;
import com.giiisp.giiisp.entity.Song;
import com.giiisp.giiisp.model.ModelFactory;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.DensityUtils;
import com.giiisp.giiisp.utils.FileUtils;
import com.giiisp.giiisp.utils.ImageLoader;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.adapter.ClickEntity;
import com.giiisp.giiisp.view.adapter.ItemClickAdapter;
import com.giiisp.giiisp.view.adapter.ViewPagerAdapter;
import com.giiisp.giiisp.view.fragment.BannerRecyclerViewFragment;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.FloatDragView;
import com.giiisp.giiisp.widget.FullScreenPopupWindow;
import com.giiisp.giiisp.widget.ProgressPopupWindow;
import com.giiisp.giiisp.widget.WrapVideoView;
import com.giiisp.giiisp.widget.recording.AppCache;
import com.giiisp.giiisp.widget.recording.OnPlayerEventListener;
import com.giiisp.giiisp.widget.recording.PlayService;
import com.giiisp.giiisp.widget.recording.Util;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.shuyu.waveview.AudioPlayer;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import razerdp.basepopup.BasePopupWindow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.db.DataBaseHelper;
import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.giiisp.giiisp.widget.recording.AppCache.getPlayService;


/**
 * 论文详细页面
 * Created by Thinkpad on 2017/5/10.
 */

public class PaperDetailsActivity extends BaseMvpActivity<BaseImpl, WholePresenter> implements BaseQuickAdapter.OnItemClickListener, ViewPager.OnPageChangeListener, BaseImpl, SeekBar.OnSeekBarChangeListener, OnPlayerEventListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.viewpager_paper)
    ViewPager viewpagerPaper;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.seek_bar_paper)
    SeekBar seekBarPaper;
    @BindView(R.id.iv_back_off)
    ImageView ivBackOff;
    @BindView(R.id.iv_play_stop)
    ImageView ivPlayStop;
    @BindView(R.id.iv_fast_forward)
    ImageView ivFastForward;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tab_layout_paper)
    TabLayout tabLayoutPaper;
    @BindView(R.id.et_comm_post)
    TextView etCommPost;
    @BindView(R.id.tv_liked_number)
    TextView tvLikedNumber;
    @BindView(R.id.iv_liked_icon)
    ImageView ivLikedIcon;
    @BindView(R.id.tv_download_number)
    TextView tvDownloadNumber;
    @BindView(R.id.iv_download_icon)
    ImageView ivDownloadIcon;
    @BindView(R.id.tv_share_number)
    TextView tvShareNumber;
    @BindView(R.id.iv_share_icon)
    ImageView ivShareIcon;
    @BindView(R.id.liner_bottom_comm)
    LinearLayout linerBottomComm;
    @BindView(R.id.viewpager_tab)
    ViewPager viewpagerTab;
    @BindView(R.id.line_banner)
    RelativeLayout lineBanner;
    @BindView(R.id.relative_full)
    RelativeLayout relativeFull;
    @BindView(R.id.iv_fullscreen_button)
    ImageView ivFullscreenButton;
    @BindView(R.id.tv_play_time)
    TextView tvPlayTime;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    @BindView(R.id.tv_playlist_number)
    TextView tvPlaylistNumber;
    @BindView(R.id.iv_play_list)
    ImageView ivPlayList;
    @BindView(R.id.linear_layout_main)
    LinearLayout linearLayoutMain;
    @BindView(R.id.ll_empty_view)
    LinearLayout llEmptyView;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.tv_paper_complete)
    TextView tvPaperComplete;
    @BindView(R.id.tv_paper_marrow)
    TextView tvPaperMarrow;
    @BindView(R.id.tv_paper_abstract)
    TextView tvPaperAbstract;
    @BindView(R.id.tv_cn)
    TextView tvCn;
    @BindView(R.id.tv_en)
    TextView tvEn;
    @BindView(R.id.progress_wheel)
    ProgressWheel progressWheel;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.rl_viewpager_full)
    RelativeLayout rl_viewpager_full;

    private boolean isFulllScreen = false;
    private FullScreenPopupWindow fullScreenPopup;
    private DownloadController mDownloadController;
    private String type;
    private String isFollowed;
    private RxDownload mRxDownload;
    private NoteDao noteDao;
    private Query<Note> notesQuery;
    public static String paperId;
    public static String id;
    private String storageId;
    public static String downloadId="";
    private ItemClickAdapter itemClickAdapter;
    private ArrayList<String> photoList;
    private ArrayList<String> recordOneList;
    private ArrayList<String> recordTwoList;
    BaseImpl baseImpl;
    OnPlayerEventListener onPlayerEventListener;
    private List<PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.PhotosBean.RowsBean> photosBeanRows;
    private List<PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.RecordOneBean.RowsBeanX> recordsBeanOneRows;
    private List<PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.RecordOneBean.RowsBeanX> recordsBeanTwoRows;
    private int position;
    private int positionPic;
    List<String> imageId = new ArrayList<>();
    private BannerRecyclerViewFragment paperQA;
    Note note = new Note();
    private ArrayList<String> version = new ArrayList<>();
    private String string = "";
    private List<Song> queueCN = new ArrayList<>();
    private List<Song> queueEN = new ArrayList<>();
    private String language = "";
    private String title = "";
    private String firstPic = "";
    private String realName = "";
    private ProgressPopupWindow progressPopupWindow;
    private int isSave;
    private boolean isMove = false;
    /*** viewpager的根视图数据集合 ***/
    List<View> mViewList;

    /*** 当前页面索引 ***/
    int currentViewPagerItem = 0;

    /*** 上一个页面索引 ***/
    int lastItem = 0;

    /*** 页面的视频控件集合,Integer所处位置 ***/
    static Map<Integer,WrapVideoView> mVideoViewMap;
    static Map<Integer,View> mVideoBgViewMap;
    /*** 页面播放进度控制器集合 ***/
    static Map<Integer,MediaController> mMediaControllerMap;

    /*** 页面视频缓冲图集合 ***/
    static List<View> mCacheViewList;

    /*** 记录每个page页面视频播放的进度 ***/
    static Map<Integer, Integer> mCurrentPositions;

    /*** 记录当前page页面是否为视频 ***/
    static Map<Integer, Boolean> mIsVideo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (type) {
            case "online_paper":
            case "collection_paper":
            case "collection_summary":
            case "play":
            case "plays":
            case "home":
            case "answer":
            case "questions":
                if (imageId.size() > position && position >= 0) {
                    paperQA.setImageId(imageId.get(position));
                    paperQA.initNetwork();
                }
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public OnPlayerEventListener getOnPlayerEventListener() {
        return onPlayerEventListener;
    }

    public void setOnPlayerEventListener(OnPlayerEventListener onPlayerEventListener) {
        this.onPlayerEventListener = onPlayerEventListener;
    }

    public BaseImpl getBaseImpl() {
        return baseImpl;
    }

    public void setBaseImpl(BaseImpl baseImpl) {
        this.baseImpl = baseImpl;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_paperdetails;
    }

    public static void actionActivity(Context context, int id, String type) {
        Intent sIntent = new Intent(context, PaperDetailsActivity.class);
        sIntent.putExtra("id", id);
        sIntent.putExtra("type", type);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    public static void actionActivity(Context context, String id, ArrayList<String> version, String type) {
        Intent sIntent = new Intent(context, PaperDetailsActivity.class);
        sIntent.putExtra("id", id);
        sIntent.putExtra("type", type);
        sIntent.putStringArrayListExtra("version", version);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    public static void actionActivity(Context context, String id, ArrayList<String> recordOneRows, ArrayList<String> recordTwoRows, ArrayList<String> photoRows, String type, String title) {
        Intent sIntent = new Intent(context, PaperDetailsActivity.class);
        sIntent.putExtra("id", id);
        sIntent.putExtra("type", type);
        sIntent.putStringArrayListExtra("recordOneRows", recordOneRows);
        sIntent.putStringArrayListExtra("recordTwoRows", recordTwoRows);
        sIntent.putStringArrayListExtra("photoRows", photoRows);
        sIntent.putExtra("title", title);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    public static void actionActivity(Context context) {
        Intent sIntent = new Intent(context, PaperDetailsActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra("id");
        //        downloadId = getIntent().getIntExtra("downloadId", -1);
        type = getIntent().getStringExtra("type");
        title = getIntent().getStringExtra("title");
        version = getIntent().getStringArrayListExtra("version");
        if (version != null && version.size() > 0) {
            string = version.get(0);
            for (int i = 0; i < version.size(); i++) {
                switch (version.get(i)) {
                    case "0":
                        tvPaperComplete.setVisibility(View.VISIBLE);
                        break;
                    case "1":
                        tvPaperMarrow.setVisibility(View.VISIBLE);
                        break;
                    case "2":
                        tvPaperAbstract.setVisibility(View.VISIBLE);
                        break;
                }
            }
        }
//        storageId = id + string;
        storageId = id;
        photoList = getIntent().getStringArrayListExtra("photoRows");
        recordOneList = getIntent().getStringArrayListExtra("recordOneRows");
        recordTwoList = getIntent().getStringArrayListExtra("recordTwoRows");

        if (type == null)
            type = "";

        if(photoList == null){
            photoList = new ArrayList<>();
        }

        DaoSession daoSession = BaseApp.app.getDaoSession();
        noteDao = daoSession.getNoteDao();
        Query<Note> notesId = noteDao.queryBuilder().where(NoteDao.Properties.Id.eq(storageId)).build();
        List<Note> list = notesId.list();
        for (Note note1 : list) {
            if (note1.getId().equals(storageId)) {
                language = note1.getLanguage() + "";
                position = note1.getPlayPosition();
            }
        }


    }

    @Override
    public void initView() {
        fullScreenPopup = new FullScreenPopupWindow(this);

        progressPopupWindow = new ProgressPopupWindow(this);
//                showDialog();

        loadDownloadNunber();


        if (photoList != null && photoList.size() > 0) {
            fullScreenPopup.setStringArrayList(photoList);
            fullScreenPopup.initDownload();
        }

        tvTitle.setText("P1");
        viewpagerPaper.addOnPageChangeListener(this);
        List<ClickEntity> list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        itemClickAdapter = new ItemClickAdapter(this, R.layout.item_paper_pic, list, "");
        recyclerView.setAdapter(itemClickAdapter);
        itemClickAdapter.setOnItemClickListener(this);
        //        coordinatorLayout.addOnAttachStateChangeListener();

        fullScreenPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PaperDetailsActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
        mDownloadController = new DownloadController(new TextView(this), new ImageView(this));
        //        fullScreenPopup.setDismissWhenTouchOuside();
        mRxDownload = RxDownload.getInstance(this);
        seekBarPaper.setOnSeekBarChangeListener(this);
        if (getPlayService() != null) {
            PlayService playService = getPlayService();
            playService.setOnPlayEventListener(this);
            if (storageId.equals(id)) {
                ivPlayStop.setSelected(playService.isPlaying());

                if (playService.getPlayingMusic() != null) {
                    seekBarPaper.setMax(playService.getPlayingMusic().getDuration() * 1000);
                    tvDuration.setText("/ " + Util.formatSeconds(playService.getPlayingMusic().getDuration()));
                }
                switch (language) {
                    case "CN":
                        tvCn.setEnabled(false);
                        tvCn.setSelected(false);
                        tvEn.setEnabled(true);
                        tvEn.setSelected(true);
                        break;
                    case "EN":
                        tvCn.setEnabled(true);
                        tvCn.setSelected(true);
                        tvEn.setEnabled(false);
                        tvEn.setSelected(false);
                        break;
                }

                seekBarPaper.setProgress(playService.getmPlayer().getCurrentPosition());
            }
        }

        switch (type) {

            case "collection_paper":
            case "collection_summary":
            case "online_paper":
            case "play":
            case "plays":
            case "home":
            case "answer":
            case "questions":
                List<BaseFragment> fragments = new ArrayList<>();
                paperQA = BannerRecyclerViewFragment.newInstance("paper_qa", id + "");
                fragments.add(paperQA);
                fragments.add(BannerRecyclerViewFragment.newInstance("paper_literature", id + ""));
                fragments.add(BannerRecyclerViewFragment.newInstance("paper_label", id + ""));
                List<String> mTitles = new ArrayList<>();
                mTitles.add("问答");
                mTitles.add("文献索引");
                mTitles.add("标签");
                viewpagerTab.setOffscreenPageLimit(2);
                tabLayoutPaper.setupWithViewPager(viewpagerTab);
                viewpagerTab.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments, mTitles));
                viewpagerTab.setCurrentItem(0);
                TabLayout.Tab tabAt0 = tabLayoutPaper.getTabAt(0);
                TabLayout.Tab tabAt1 = tabLayoutPaper.getTabAt(1);
                TabLayout.Tab tabAt2 = tabLayoutPaper.getTabAt(2);
                if (tabAt0 != null) {
                    tabAt0.setIcon(R.drawable.bg_tab_mine);
                }
                if (tabAt1 != null)
                    tabAt1.setIcon(R.drawable.bg_tab_center);
                if (tabAt2 != null)
                    tabAt2.setIcon(R.drawable.bg_tab_tag);
                //                initTab();
                ivEmpty.setVisibility(View.GONE);
                progressWheel.setVisibility(View.VISIBLE);
                initNetwork();

                break;
            case "wait_dubbing":
                linerBottomComm.setVisibility(View.GONE);
                ArrayMap<String, Object> dubbingMap = new ArrayMap<>();
//                dubbingMap.put("token", token);
                dubbingMap.put("id", id);
                dubbingMap.put("uid", uid);
                if (!TextUtils.isEmpty(string))
                    dubbingMap.put("version", string);
                presenter.getPaperBaseByIdData(dubbingMap);
                llEmptyView.setVisibility(View.GONE);
                break;
            case "download_paper":
                linerBottomComm.setVisibility(View.GONE);
                llEmptyView.setVisibility(View.GONE);
                if (getPlayService() != null) {
                    PlayService playService = getPlayService();

                    playService.setOnPlayEventListener(this);
                    if (downloadId.equals(storageId)) {
                        Log.i("--->>", "initData: " + playService.getPlayingMusic().getDuration());

                        if (playService.getImageList().size() > 0) {
                            if(photoList!=null)photoList.clear();
                            photoList = (ArrayList<String>) playService.getImageList();
                            for (String photo : photoList) {
                                itemClickAdapter.addData(new ClickEntity(photo));
                            }
                            viewpagerPaper.setAdapter(new ImageAdapter(this, photoList));
                        }
                        viewpagerPaper.setCurrentItem(playService.getPlayingPosition());
                        recyclerView.scrollToPosition(playService.getPlayingPosition());
                        //                        Song playingMusic = playService.getPlayingMusic();
                        ivPlayStop.setSelected(playService.isPlaying());

                        if (playService.getPlayingMusic() != null) {
                            seekBarPaper.setMax(playService.getPlayingMusic().getDuration() * 1000);
                            tvDuration.setText("/ " + Util.formatSeconds(playService.getPlayingMusic().getDuration()));
                        }
                        itemClickAdapter.setSelectedPosition(playService.getPlayingPosition());
                        itemClickAdapter.notifyDataSetChanged();

                    } else {
                        if (photoList != null) {
                            for (String photo : photoList) {
                                itemClickAdapter.addData(new ClickEntity(photo));
                            }
                            viewpagerPaper.setAdapter(new ImageAdapter(this, photoList));
                        }

                        if (recordOneList != null) {
                            for (int i = 0; i < recordOneList.size(); i++) {
                                Song song = new Song();
                                song.setPosition(i);
                                if (photoList != null && photoList.size() > i)
                                    song.setPhotoPath(photoList.get(i));
                                song.setPath(recordOneList.get(i));
                                song.setTitle(title);
                                //                Log.i("--->>", "onSuccess: " + AudioPlayer.getDurationLocation(this, recordList.get(i)));
                                song.setDuration((int) AudioPlayer.getDurationLocation(this, recordOneList.get(i)) / 1000);
                                queueCN.add(song);
                            }
                        }
                        if (recordTwoList != null) {
                            for (int i = 0; i < recordTwoList.size(); i++) {
                                Song song = new Song();
                                song.setPosition(i);
                                song.setTitle(title);
                                if (photoList != null && photoList.size() > i)
                                    song.setPhotoPath(photoList.get(i));
                                song.setPath(recordTwoList.get(i));
                                //                Log.i("--->>", "onSuccess: " + AudioPlayer.getDurationLocation(this, recordList.get(i)));
                                song.setDuration((int) AudioPlayer.getDurationLocation(this, recordTwoList.get(i)) / 1000);
                                queueEN.add(song);
                            }
                        }
                        if (queueEN.size() > 0 && queueCN.size() > 0) {
                            tvCn.setVisibility(View.VISIBLE);
                            tvEn.setVisibility(View.VISIBLE);
                            tvCn.setEnabled(false);
                            tvCn.setSelected(false);
                            tvEn.setEnabled(true);
                            tvEn.setSelected(true);
                        }
                        playService.setmMusicList(queueCN);
                        playService.setImageList(photoList);
                        playService.play(position);
                    }
                    downloadId = string;
                    paperId = "";
                }

                break;
        }

        //添加可拖动悬浮按钮
        FloatDragView.addFloatDragView(this, relativeFull, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpagerPaper.setCurrentItem(getPlayService().getPlayingPosition());
            }
        }, new FloatDragView.OnMyListening() {
            @Override
            public void myListening(int action) {
                //在播放的时候，拖动按钮有问题，暂时这样：在拖动的时候暂停，结束在播放
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        if (getPlayService().isPlaying()) {
                            getPlayService().playPause();
                            isMove = true;
                        }
                        toolbarLayout.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (getPlayService().isPlaying()) {
                            getPlayService().playPause();
                            isMove = true;
                        }
                        toolbarLayout.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (getPlayService().isPausing() && isMove) {
                            getPlayService().playPause();
                            isMove = false;
                        }
                        toolbarLayout.requestDisallowInterceptTouchEvent(false);
                        break;
                }
            }
        });

    }

    @Override
    protected void initNetwork() {
        ArrayMap<String, Object> map = new ArrayMap<>();
//        map.put("token", token);
        map.put("id", id);
        map.put("uid", uid);
        Log.i("--->>", "initNetwork: " + string);
        if (!TextUtils.isEmpty(string)) {
            map.put("version", string);
            presenter.getPaperBaseByIdData(map);
        }
        super.initNetwork();
    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.tv_back, R.id.iv_fullscreen_button, R.id.iv_empty, R.id.tv_empty, R.id.tv_paper_marrow, R.id.tv_paper_complete, R.id.tv_paper_abstract, R.id.tv_cn, R.id.tv_en, R.id.tv_liked_number, R.id.fl_paper_play, R.id.iv_fast_forward, R.id.iv_back_off, R.id.iv_play_stop, R.id.et_comm_post, R.id.fl_download, R.id.fl_collection, R.id.fl_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_empty:
            case R.id.iv_empty:
                initNetwork();
                break;
            case R.id.tv_back:
                if (getPlayService() != null)
                    getPlayService().playPause();
                finish();
                break;
            case R.id.tv_paper_complete:

                ArrayList<String> complete = new ArrayList<>();
                complete.add("0");
                if (version.contains(0))
                    version.remove((Integer) 0);
                //                version.add(0,0);
                for (String string : version) {
                    complete.add(string);
                }
                PaperDetailsActivity.actionActivity(this, id, complete, "online_paper");
                break;
            case R.id.tv_paper_marrow:
                ArrayList<String> marrow = new ArrayList<>();
                marrow.add("1");
                if (version.contains((Integer) 1))
                    version.remove((Integer) 1);
                //                version.add(0,0);
                for (String string : version) {
                    marrow.add(string);
                }
                PaperDetailsActivity.actionActivity(this, id, marrow, "online_paper");

                break;
            case R.id.tv_paper_abstract:
                ArrayList<String> abstracts = new ArrayList<>();
                abstracts.add("2");
                if (version.contains((Integer) 2))
                    version.remove((Integer) 2);
                //                version.add(0,0);
                for (String string : version) {
                    abstracts.add(string);
                }
                PaperDetailsActivity.actionActivity(this, id, abstracts, "online_paper");

                break;
            case R.id.tv_cn:
                if (getPlayService() != null) {
                    language = "CN";
                    PlayService playService = getPlayService();
                    playService.setmMusicList(queueCN);
                    playService.play(0);
                    tvCn.setEnabled(false);
                    tvCn.setSelected(false);
                    tvEn.setEnabled(true);
                    tvEn.setSelected(true);
                }
                Log.i("--->>", "onViewClicked: tv_cn");
                break;
            case R.id.tv_en:
                Log.i("--->>", "onViewClicked: tv_en");
                if (getPlayService() != null) {
                    language = "EN";
                    PlayService playService = getPlayService();
                    playService.setmMusicList(queueEN);
                    playService.play(0);
                    tvCn.setEnabled(true);
                    tvCn.setSelected(true);
                    tvEn.setEnabled(false);
                    tvEn.setSelected(false);
                }
                break;
            case R.id.iv_fast_forward:
                if (getPlayService() != null)
                    getPlayService().next();

                break;
            case R.id.iv_back_off:
                if (getPlayService() != null)
                    getPlayService().prev();

                break;
            case R.id.iv_play_stop:
                if (getPlayService() != null)
                    getPlayService().playPause();

                break;
            case R.id.iv_fullscreen_button:
                //                FullscreenActivity.actionActivity(this);
//                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
//                    //切换竖屏
//                    fullScreenPopup.dismiss();
//                    //                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
//                    PaperDetailsActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//
//                } else {
//                    //切换横屏
//                    PaperDetailsActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
//                    fullScreenPopup.showPopupWindow();
//                    //                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
//                }
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                isFulllScreen = !isFulllScreen;
                break;
            case R.id.et_comm_post:
                if (photosBeanRows == null || photosBeanRows.size() <= position)
                    return;
                if (BaseActivity.uid.equals("15")) {
                    AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(this);
                    normalDialog.setIcon(null);
                    normalDialog.setTitle("需要登录才能执行此操作");
                    normalDialog.setPositiveButton("登录",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    LogInActivity.actionActivity(PaperDetailsActivity.this);
                                }
                            });
                    normalDialog.setNegativeButton("取消", null);
                    // 显示
                    normalDialog.show();
                } else
                    switch (BaseActivity.emailauthen) { //  按照 emailauthen 判断
                        case "0":
                            Utils.showToast("      认证请联系：\n" +
                                    "+86 185 0101 0114 \n" +
                                    " service@giiisp.com");
                            break;
                        case "1":
                            String pcid = photosBeanRows.get(position).getId();
                            ProblemActivity.actionActivity(this, "Problem", pcid, uid);
                            break;
                        case "2":
                            Utils.showToast("等待认证完成");
                            break;
                    }

                break;
            case R.id.fl_collection:
                if (BaseActivity.uid.equals("15")) {
                    AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
                    normalDialog.setIcon(null);
                    normalDialog.setTitle("需要登录才能执行此操作");
                    normalDialog.setPositiveButton("登录",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    LogInActivity.actionActivity(PaperDetailsActivity.this);
                                }
                            });
                    normalDialog.setNegativeButton("取消", null);
                    // 显示
                    normalDialog.show();
                } else {
                    collection();
                }
                break;
            case R.id.fl_paper_play:
                FragmentActivity.actionActivity(this, "play");
                break;
            case R.id.fl_download:
                if (BaseActivity.uid.equals("15")) {
                    AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(this);
                    normalDialog.setIcon(null);
                    normalDialog.setTitle("需要登录才能执行此操作");
                    normalDialog.setPositiveButton("登录",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    LogInActivity.actionActivity(PaperDetailsActivity.this);
                                }
                            });
                    normalDialog.setNegativeButton("取消", null);
                    // 显示
                    normalDialog.show();
                } else {
                    mDownloadController.handleClick(new DownloadController.Callback() {
                        @Override
                        public void startDownload() {
                            start();
                        }

                        @Override
                        public void pauseDownload() {
                            //                        pause();
                            //                        Utils.showToast("下载暂停");
                        }

                        @Override
                        public void install() {
                            //                        installApk();
                            Utils.showToast("下载完成");
                        }
                    });
                    loadDownloadNunber();
                }
                //                presenter.downloadFileWithDynamicUrlSync();
                break;
            case R.id.fl_share:
                new RxPermissions(this).requestEach(WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(@NonNull Permission permission) throws Exception {
                                if (permission.granted) {
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // Denied permission without ask never again
                                    Utils.showToast("取消存储授权,不能存储文件");
                                } else {
                                    // Denied permission with ask never again
                                    // Need to go to the
                                    Utils.showToast("您已经禁止弹出存储的授权操作,请在设置中手动开启");
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                Log.i("--->>", "onError", throwable);
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {

                                UMWeb web = new UMWeb("http://giiisp.com/paper_play.php?id=" + id);
                                UMImage thumb = new UMImage(PaperDetailsActivity.this, firstPic);
                                web.setTitle(title);//标题
                                web.setThumb(thumb);  //缩略图
                                web.setDescription(realName);//描述
                                new ShareAction(PaperDetailsActivity.this).withMedia(web)
                                        .setDisplayList(/*SHARE_MEDIA.QQ, */SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.SINA)
                                        .setCallback(umShareListener).open(new ShareBoardConfig().setIndicatorVisibility(false));
                            }
                        });

                break;
        }
    }

    private void collection() {
/*        ArrayMap<String, Object> map = new ArrayMap<>();
        map.put("pbid", PaperDetailsActivity.id);
        map.put("flag", 1);
        map.put("tabFlag", 1);
        map.put("uid", uid);
        map.put("version", integer);
        //pbid=1&flag=1&tabFlag=1
        presenter.getSaveFollowPaperPictureData(map);*/
        final ArrayMap<String, Object> map = new ArrayMap<>();
        map.put("pbid", id);
        map.put("flag", 1);
        map.put("tabFlag", 1);
        map.put("uid", uid);
        map.put("version", string);
        switch (isFollowed) {
            case "0":
                isSave = 10;
                presenter.getSaveFollowPaperPictureData(map);
                break;
            case "1":
                Utils.showDialog(this, "确定取消收藏", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        isSave = 20;
                        presenter.getCancelFollowPaperPictureData(map);

                    }
                });
                break;
        }

    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            progressPopupWindow.showPopupWindow();
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Utils.showToast(getString(R.string.share_result));
            //uId=1&pid=5&ptype=1&ttype=1
            ArrayMap<String, Object> map = new ArrayMap<>();
            map.put("uid", uid);
            map.put("pid", id);
            map.put("ttype", 1);
            map.put("ptype", 1);
            presenter.getSaveShareData(map);
            progressPopupWindow.dismiss();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Utils.showToast(getString(R.string.share_error));
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
            progressPopupWindow.dismiss();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Utils.showToast(getString(R.string.share_cancel));
            progressPopupWindow.dismiss();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("--->>", "onActivityResult: " + requestCode);
        if (requestCode == 1000) {
            switch (type) {
                case "online_paper":
                case "collection_paper":
                case "collection_summary":
                case "play":
                case "plays":
                case "home":
                case "answer":
                case "questions":
                    if (imageId.size() > position) {
                        paperQA.setImageId(imageId.get(position));
                        paperQA.initNetwork();
                    }
                    break;

            }
        }
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    private void start() {
        if (photosBeanRows == null || photosBeanRows.size() < position)
            return;

        PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.PhotosBean.RowsBean rowsBean = photosBeanRows.get(position);

        if (rowsBean == null)
            return;
        ArrayList<DownloadBean> list = new ArrayList<>();
        String path = rowsBean.getPath();
        if (path.contains(UrlConstants.RequestUrl.QN_ADDRESS)) {
            path += "-watermark";
        }
        if (DataBaseHelper.getSingleton(this).recordNotExists(path)) {
            DownloadBean downloadBean = new DownloadBean
                    .Builder(path)
                    .setSaveName(Utils.fileName(rowsBean.getPath()))
                    .setExtra1(storageId + "")   //save extra info into database.
                    .setExtra2(String.valueOf(rowsBean.getId()))   //save extra info into database.
                    .setExtra3(String.valueOf(rowsBean.getOrder()))   //save extra info into database.
                    .setExtra4("photo")   //save extra info into database.
                    .setTitle(title)
                    .setExtra5(path)
                    .setTime(rowsBean.getCreateTime())
                    .setVersion(string + "")
                    .build();
            list.add(downloadBean);
        }
        if (recordsBeanOneRows != null && recordsBeanOneRows.size() > 0) {
            PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.RecordOneBean.RowsBeanX rowsBeanX = recordsBeanOneRows.get(position);
            if (DataBaseHelper.getSingleton(this).recordNotExists(rowsBeanX.getPath())) {
                DownloadBean downloadBean = new DownloadBean
                        .Builder(rowsBeanX.getPath())
                        .setExtra1(storageId + "")   //save extra info into database.
                        .setExtra2(String.valueOf(rowsBeanX.getPcid()))   //save extra info into database.
                        .setExtra3(String.valueOf(rowsBean.getOrder()))   //save extra info into database.
                        .setExtra4("record")   //save extra info into database.

                        .setExtra5("CN")
                        .setTime(rowsBean.getCreateTime())
                        .setTitle(title)
                        .setVersion(string + "")
                        .build();
                list.add(downloadBean);
            }
        }
        if (recordsBeanTwoRows != null && recordsBeanTwoRows.size() > 0) {
            PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.RecordOneBean.RowsBeanX rowsBeanX = recordsBeanTwoRows.get(position);
            if (DataBaseHelper.getSingleton(this).recordNotExists(rowsBeanX.getPath())) {
                DownloadBean downloadBean = new DownloadBean
                        .Builder(rowsBeanX.getPath())
                        .setExtra1(storageId + "")   //save extra info into database.
                        .setExtra2(String.valueOf(rowsBeanX.getPcid()))   //save extra info into database.
                        .setExtra3(String.valueOf(rowsBean.getOrder()))    //save extra info into database.
                        .setExtra4("record")   //save extra info into database.
                        .setTime(rowsBean.getCreateTime())
                        .setTitle(title)
                        .setExtra5("EN")
                        .setVersion(string + "")
                        .build();
                list.add(downloadBean);
            }
        }
        if (list.size() > 0) {
            new RxPermissions(this)
                    .request(WRITE_EXTERNAL_STORAGE)
                    .doOnNext(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Exception {
                            if (!granted) {
                                throw new RuntimeException("no permission");
                            }
                        }
                    })
                    .compose(mRxDownload.<Boolean>transformMulti(list, rowsBean.getId() + ""))
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            Utils.showToast("下载开始");

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            Log.w("--->>", throwable);
                            Utils.showToast("下载中");
                        }
                    });
        } else {
            Utils.showToast("下载完成");
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setViewState(newConfig);
    }

    /**
     * 设置全屏
     * @param newConfig
     */
    public void setViewState(Configuration newConfig){
        if (relativeFull != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                rl_viewpager_full.setVisibility(View.GONE);
                rl_viewpager_full.removeAllViews();
                toolbarLayout.addView(relativeFull);
                linearLayout.setVisibility(View.VISIBLE);
            } else {
                ViewGroup viewGroup = (ViewGroup) relativeFull.getParent();
                if (viewGroup == null)
                    return;
                viewGroup.removeAllViews();
                rl_viewpager_full.addView(relativeFull);
                rl_viewpager_full.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
                int mHideFlags =
                        View.SYSTEM_UI_FLAG_LOW_PROFILE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        ;
                rl_viewpager_full.setSystemUiVisibility(mHideFlags);
            }
        } else {
            rl_viewpager_full.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        if (this.position != position) {
            if (getPlayService() != null)
                getPlayService().play(position);
        }


        viewpagerPaper.setCurrentItem(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
        lastItem = currentViewPagerItem;
        currentViewPagerItem = position;
        tvTitle.setText("P" + (position + 1));
        viewpagerPaper.setCurrentItem(position);
//        recyclerView.scrollToPosition(position);
//        itemClickAdapter.setSelectedPosition(position);
//        itemClickAdapter.notifyDataSetChanged();
        if(mIsVideo.get(currentViewPagerItem)){ // 当前是视频
            seekBarPaper.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.GONE);
            mMediaControllerMap.get(currentViewPagerItem).setVisibility(View.VISIBLE);
        }else{
            seekBarPaper.setVisibility(View.VISIBLE);
            if(!isFulllScreen){
                linearLayout.setVisibility(View.VISIBLE);
            }
            if(mIsVideo.get(lastItem)){ // 上一个为视频时
                if(mVideoViewMap.get(lastItem).isPlaying()){
                    mVideoViewMap.get(lastItem).pause();
                }
                if(mMediaControllerMap.get(lastItem).isShowing()){
                    mMediaControllerMap.get(lastItem).setVisibility(View.INVISIBLE);
                }
            }
        }
        switch (type) {
            case "online_paper":
            case "collection_paper":
            case "collection_summary":
            case "play":
            case "plays":
            case "home":
            case "answer":
            case "questions":
                if (imageId.size() > position) {
                    paperQA.setImageId(imageId.get(position));
                    paperQA.initNetwork();
                }
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSuccess(BaseEntity entity) {
        if (viewpagerPaper == null)
            return;

        if (entity instanceof PaperEntity) {
            Log.i("--->>", "onSuccess: " + entity.getInfo());
        } else if (entity instanceof LiteratureEntity) {

        } else if (entity instanceof PaperDatailEntity) {
            if (baseImpl != null)
                baseImpl.onSuccess(entity);
            //            progressPopupWindow.dismiss();
            llEmptyView.setVisibility(View.GONE);
            PaperDatailEntity.PaperBaseBean paperBase = ((PaperDatailEntity) entity).getPaperBase();
            if (paperBase == null)
                return;
            if (!TextUtils.isEmpty(paperBase.getTitle()))
                title = paperBase.getTitle();
            if (!TextUtils.isEmpty(paperBase.getRealName()))
                realName = paperBase.getRealName();
            PaperDatailEntity.PaperBaseBean.PhotoOneBean photo = null;
            switch (string) {
                case "0":
                    photo = paperBase.getPhotoOne();
                    break;
                case "1":
                    photo = paperBase.getPhotoTwo();
                    break;
                case "2":
                    photo = paperBase.getPhotoThree();
                    break;
            }

            if (photo != null && photo.getRows() != null && photo.getRows().size() > 0) {
                PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX rowsBeanXX = photo.getRows().get(0);
                firstPic = rowsBeanXX.getFirstPic();
                isFollowed = rowsBeanXX.getIsFollowed();
                ivLikedIcon.setSelected("1".equals(isFollowed));
                PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.PhotosBean photos = rowsBeanXX.getPhotos();//  add type 1,png ,2 mp4, 3 gif
                PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.RecordOneBean recordOne = rowsBeanXX.getRecordOne();
                PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.RecordOneBean recordTwo = rowsBeanXX.getRecordTwo();
                if (photos != null && photos.getRows() != null && photos.getRows().size() > 0) {
                    photosBeanRows = photos.getRows();
                    List<String> images = new ArrayList<>();
                    if(photoList!=null){
                        photoList.clear();
                    }else{
                        photoList = new ArrayList<>();
                    }
                    for (PaperDatailEntity.PaperBaseBean.PhotoOneBean.RowsBeanXX.PhotosBean.RowsBean photosBeanRow : photosBeanRows) {
                        itemClickAdapter.addData(new ClickEntity(photosBeanRow.getPath(), photosBeanRow.getId()));
                        photoList.add(photosBeanRow.getPath());
                        imageId.add(photosBeanRow.getId());
                    }
                    if (imageId.size() > position && paperQA != null) {
                        paperQA.setImageId(imageId.get(position));
                        paperQA.initNetwork();

                    }
                    note.setPath(photos.getRows().get(0).getPath());
//                    long time3 = System.currentTimeMillis(); //  time test 3
                    viewpagerPaper.setAdapter(new ImageAdapter(this,photoList));
                    viewpagerPaper.setCurrentItem(position);
                    recyclerView.scrollToPosition(position);
                    itemClickAdapter.setSelectedPosition(position);
                    itemClickAdapter.notifyDataSetChanged();
//                    long result3 = (System.currentTimeMillis()-time3);
//                    Log.e("time","result3= "+result3);
                }

                if (recordOne != null && recordOne.getRows() != null && recordOne.getRows().size() > 0) {
                    recordsBeanOneRows = recordOne.getRows();
                    for (int i = 0; i < recordsBeanOneRows.size(); i++) {
                        Song song = new Song();
                        song.setPosition(i);
                        song.setTitle(paperBase.getTitle());
                        if (photosBeanRows != null && photosBeanRows.size() > i) {
                            song.setPhotoPath(photosBeanRows.get(i).getPath());
                        }
                        song.setPath(recordsBeanOneRows.get(i).getPath());
                        song.setType(recordsBeanOneRows.get(i).getType());
                        song.setDuration(Integer.parseInt(recordsBeanOneRows.get(i).getDuration()));
                        queueCN.add(song);
                    }
                }
                if (recordTwo != null && recordTwo.getRows() != null && recordTwo.getRows().size() > 0) {
                    recordsBeanTwoRows = recordTwo.getRows();
                    for (int i = 0; i < recordsBeanTwoRows.size(); i++) {
                        Song song = new Song();
                        song.setPosition(i);
                        song.setTitle(paperBase.getTitle());
                        if (photosBeanRows != null && photosBeanRows.size() > i) {
                            song.setPhotoPath(photosBeanRows.get(i).getPath());
                        }
                        song.setPath(recordsBeanTwoRows.get(i).getPath());
                        song.setType(recordsBeanOneRows.get(i).getType());
                        song.setDuration(Integer.parseInt(recordsBeanTwoRows.get(i).getDuration()));
                        queueEN.add(song);
                    }
                }
                if (queueEN.size() > 0 && queueCN.size() > 0) {
                    tvCn.setVisibility(View.VISIBLE);
                    tvEn.setVisibility(View.VISIBLE);
                }

            /*    if (recordTwo != null && recordTwo.getRows() != null && recordTwo.getRows().size() > 0) {
                    recordsBeanTwoRows = recordTwo.getRows();
                    for (int i = 0; i < recordsBeanTwoRows.size(); i++) {
                        Song song = new Song();
                        song.setPosition(i);
                        song.setPath(recordsBeanTwoRows.get(i).getPath());
                        song.setDuration(recordsBeanTwoRows.get(i).getDuration());
                        queueEN.add(song);
                    }
                }*/
                note.setTitle(paperBase.getTitle());

                note.setType("play");
                note.setId(storageId);
                note.setCommentNum(paperBase.getCommentNum() + "");
                note.setFollowedNum(paperBase.getFollowedNum() + "");
                note.setCreateTime(paperBase.getUpdateTime());
                note.setReadNum(paperBase.getReadNum() + "");
                note.setLikedNum(paperBase.getLikedNum() + "");
                note.setVersions(string);

                if (getPlayService() != null) {
                    PlayService playService = getPlayService();
                    if (storageId.equals(id)) {
                        switch (language) {
                            case "CN":
                                tvCn.setEnabled(false);
                                tvCn.setSelected(false);
                                tvEn.setEnabled(true);
                                tvEn.setSelected(true);
                                playService.setmMusicList(queueCN);
                                break;
                            case "EN":
                                tvCn.setEnabled(true);
                                tvCn.setSelected(true);
                                tvEn.setEnabled(false);
                                tvEn.setSelected(false);
                                playService.setmMusicList(queueEN);
                                break;
                            default:
                                if (queueCN.size() > 0) {
                                    tvCn.setEnabled(false);
                                    tvCn.setSelected(false);
                                    tvEn.setEnabled(true);
                                    tvEn.setSelected(true);
                                    playService.setmMusicList(queueCN);
                                } else if (queueEN.size() > 0) {
                                    tvCn.setEnabled(true);
                                    tvCn.setSelected(true);
                                    tvEn.setEnabled(false);
                                    tvEn.setSelected(false);
                                    playService.setmMusicList(queueEN);
                                }
                                break;
                        }
                        playService.play(position);
                    }
                    switch (language) {
                        case "CN":
                            tvCn.setEnabled(false);
                            tvCn.setSelected(false);
                            tvEn.setEnabled(true);
                            tvEn.setSelected(true);
                            break;
                        case "EN":
                            tvCn.setEnabled(true);
                            tvCn.setSelected(true);
                            tvEn.setEnabled(false);
                            tvEn.setSelected(false);
                            break;
                        default:
                            if (queueCN.size() > 0) {
                                tvCn.setEnabled(false);
                                tvCn.setSelected(false);
                                tvEn.setEnabled(true);
                                tvEn.setSelected(true);
                            } else if (queueEN.size() > 0) {
                                tvCn.setEnabled(true);
                                tvCn.setSelected(true);
                                tvEn.setEnabled(false);
                                tvEn.setSelected(false);
                            }
                            break;
                    }
                    paperId = string;
                    downloadId = "";
                }
            }
            //            isFollowed = ((PaperDatailEntity) entity).getIsFollowed();

            tvShareNumber.setText(String.valueOf(paperBase.getShareNum()));
            tvLikedNumber.setText(String.valueOf(paperBase.getLikedNum()));


        } else {
            Utils.showToast(entity.getInfo());
            if (isSave == 10) {
                isFollowed = "1";
            } else if (isSave == 20) {
                isFollowed = "0";
            }
            ivLikedIcon.setSelected("1".equals(isFollowed));
        }
    }

    @Override
    public void onFailure(String msg, Exception ex) {
        if (ivEmpty == null)
            return;
        ivEmpty.setVisibility(View.VISIBLE);
        progressWheel.setVisibility(View.GONE);

    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        tvPlayTime.setText(Util.formatSeconds(progress / 1000));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //手动调节进度
        //seekbar的拖动位置
        int dest = seekBar.getProgress();
        //seekbar的最大值
        int max = seekBar.getMax();
        //调用service调节播放进度
 /*       PlayEvent playEvent = new PlayEvent();
        playEvent.setAction(PlayEvent.Action.SEEK);
        Log.i("--->>", "onStopTrackingTouch: max" + max + "  dest" + dest);
        playEvent.setMax(max);
        playEvent.setDest(dest);
        EventBus.getDefault().post(playEvent);*/
        Log.i("--->>", "onStopTrackingTouch: " + max + "    " + dest);
        if (getPlayService() != null)
            getPlayService().seekTo(dest);
    }

    @Override
    public void onPublish(int progress) {
        if (onPlayerEventListener != null)
            onPlayerEventListener.onPublish(progress);
        tvPlayTime.setText(Util.formatSeconds(progress / 1000));
        seekBarPaper.setProgress(progress);
    }

    @Override
    public void onDuration(int duration) {
        if (onPlayerEventListener != null)
            onPlayerEventListener.onDuration(duration);
        //        seekBarPaper.setMax(duration);
        Log.i("--->>", "onDuration: " + duration);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (onPlayerEventListener != null)
            onPlayerEventListener.onCompletion(mp);

        List<Song> songs = AppCache.getPlayService().getmMusicList();
        Log.i("--->>", "2onCompletion: " + songs.size() + "   " + position);
        if (songs.size() == (position + 1)) {
            Log.i("--->>", "1onCompletion: " + songs.size() + "   " + position);
            note.setType("plays");
            note.setId(storageId);
            note.setPlayPosition(position);
            note.setSongsSize(songs.size());
            noteDao.insertOrReplace(note);
        }

    }

    @Override
    public void onBufferingUpdate(int percent) {
        if (onPlayerEventListener != null)
            onPlayerEventListener.onBufferingUpdate(percent);
        Log.i("--->>", "onBufferingUpdate: " + percent);
        int max = seekBarPaper.getMax();
        seekBarPaper.setSecondaryProgress(max / 100 * percent);
    }

    @Override
    public void onChange(Song music) {
        if (onPlayerEventListener != null)
            onPlayerEventListener.onChange(music);

        if (music == null) {
            return;
        }
        if (getPlayService() != null) {
            progressPopupWindow.showPopupWindow();
            if (AppCache.getPlayService().isPlaying() || AppCache.getPlayService().isPreparing()) {
                ivPlayStop.setSelected(true);
            } else {
                ivPlayStop.setSelected(false);
            }
            int position = music.getPosition();
            viewpagerPaper.setCurrentItem(position);
            tvDuration.setText("/ " + Util.formatSeconds(music.getDuration()));

            recyclerView.scrollToPosition(position);
            itemClickAdapter.setSelectedPosition(position);
            itemClickAdapter.notifyDataSetChanged();
            seekBarPaper.setMax(music.getDuration() * 1000);
            seekBarPaper.setProgress(0);
            List<Song> songs = AppCache.getPlayService().getmMusicList();
            note.setType("play");
            note.setId(storageId);
            note.setLanguage(language);
            Log.i("--->>", "onChange: " + position);
            Log.i("--->>", "onChange: " + language);
            note.setPlayPosition(position);
            note.setSongsSize(songs.size());
            note.setTime(String.valueOf(System.currentTimeMillis()));
            if (note.getTitle() != null)
                noteDao.insertOrReplace(note);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        progressPopupWindow.dismiss();
        if (onPlayerEventListener != null)
            onPlayerEventListener.onPrepared(mp);
    }

    @Override
    public void onPlayerPause() {
        if (onPlayerEventListener != null)
            onPlayerEventListener.onPlayerPause();
        ivPlayStop.setSelected(false);
    }

    @Override
    public void onPlayerResume() {
        if (onPlayerEventListener != null)
            onPlayerEventListener.onPlayerResume();
        ivPlayStop.setSelected(true);
    }

    @Override
    public void onTimer(long remain) {
        if (onPlayerEventListener != null)
            onPlayerEventListener.onTimer(remain);
    }

    @Override
    public void onMusicListUpdate() {
        if (onPlayerEventListener != null)
            onPlayerEventListener.onMusicListUpdate();
    }


    private class ImageAdapter extends PagerAdapter {

        private List<String> viewpathlist;
        private Activity activity;
        private View view;
        private VideoView videoView;

        public ImageAdapter(Activity activity, ArrayList<String> viewpathlist) {
            this.viewpathlist = viewpathlist;
            this.activity = activity;
            initImageView();
        }

        private void initImageView() {
            mViewList = new ArrayList<View>();
            mVideoViewMap = new HashMap<Integer, WrapVideoView>();
            mVideoBgViewMap = new HashMap<Integer, View>();
            mMediaControllerMap = new HashMap<Integer, MediaController>();
            mCacheViewList = new ArrayList<View>();

            mCurrentPositions = new HashMap<>();
            mIsVideo = new HashMap<>();
            // mIsPageFirstAvaliable = new HashMap<Integer, Boolean>();

            for (int i = 0; i < viewpathlist.size(); i++) {
                String path= viewpathlist.get(i);
                if("mp4".equals(FileUtils.parseSuffix(path))){
                    path = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";
                    View videoview_layout = (View) View.inflate(activity, R.layout.item_paper_videoview,
                            null);
                    WrapVideoView videoview = (WrapVideoView) videoview_layout.findViewById(R.id.videoview);
                    View mVideoBgView = (View) videoview_layout.findViewById(R.id.iv_bg);
                    ImageButton imPlayBtn =videoview_layout.findViewById(R.id.imbtn_video_play);
                    imPlayBtn.setOnClickListener(new ImgBtnClickLister());
                    mVideoBgView.setBackground(new BitmapDrawable(getVideoBitmap(path)));

                    MediaController mpc = new MediaController(activity);
                    videoview.setVideoPath(path);
                    videoview.setMediaController(mpc);
                    videoview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(videoview.isPlaying()){
                                mpc.show();
                            }else{
                                mpc.hide();
                                imPlayBtn.setVisibility(View.VISIBLE);
                            }
                        }
                    });
//                    setListener(vv);
                    mViewList.add(videoview_layout);
                    mVideoViewMap.put(i,videoview);
                    mMediaControllerMap.put(i,mpc);
                    mVideoBgViewMap.put(i,mVideoBgView);
                    mCurrentPositions.put(i, 0);// 每个页面的初始播放进度为0
                    mIsVideo.put(i, true);// 每个页面的初始播放状态false
                }else{
                    mIsVideo.put(i, false);
                    ImageView imageView = new ImageView(activity);
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    ImageLoader.getInstance().displayImage((BaseActivity) activity, path, imageView);
                    ViewParent vp = imageView.getParent();
                    if (vp != null) {
                        ViewGroup parent = (ViewGroup) vp;
                        parent.removeView(imageView);
                    }
                    mViewList.add(imageView);
                    imageView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            mGestureDetector.onTouchEvent(motionEvent);
                            return true;
                        }
                    });
                }
            }
        }

        class ImgBtnClickLister implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                if(v.getVisibility() == View.VISIBLE ){
                    mVideoViewMap.get(currentViewPagerItem).start();
                    v.setVisibility(View.GONE);
                    mVideoBgViewMap.get(currentViewPagerItem).setVisibility(View.GONE);
                }
            }
        }

        public Bitmap getVideoBitmap(String mVideoUrl){
            Bitmap bitmap = null;
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            int kind = MediaStore.Video.Thumbnails.MINI_KIND;
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(mVideoUrl, new HashMap<String, String>());
            } else {
                retriever.setDataSource(mVideoUrl);
            }
            bitmap = retriever.getFrameAtTime();
            retriever.release();

            return bitmap;
        }

        @Override
        public int getCount() {
            //设置成最大，使用户看不到边界
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            ((ViewPager) container).removeView(mViewList.get(position % mViewList.size()));
            //Warning：不要在这里调用removeView
//            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        GestureDetector mGestureDetector = new GestureDetector(PaperDetailsActivity.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (getPlayService() != null)
                    getPlayService().play(position);
                return true;
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("--->>", "onKeyDown: " + keyCode);
        if (getPlayService() != null)
            getPlayService().playPause();
        return super.onKeyDown(keyCode, event);

    }


    public void loadDownloadNunber() {
        RxDownload.getInstance(this).getTotalDownloadRecords()
                .map(new Function<List<DownloadRecord>, List<String>>() {
                    @Override
                    public List<String> apply(List<DownloadRecord> downloadRecords) throws Exception {
                        List<String> missionIds = new ArrayList<>();
                        for (DownloadRecord each : downloadRecords) {
                            if (each.getFlag() != DownloadFlag.COMPLETED && each.getExtra1() != null && !missionIds.contains(each.getExtra1())) {
                                missionIds.add(each.getExtra1());
                            }
                        }
                        return missionIds;
                    }
                })
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> downloadBeen) throws Exception {
                        downloadNunber = downloadBeen.size();
                        tvDownloadNumber.setText(downloadNunber + "");
                    }

                });
    }
  /*  public View getTabItemView(int position) {//                mTitles.add("问答");
        TabLayout.Tab tabAt0 = tabLayoutPaper.getTabAt(0);
        TabLayout.Tab tabAt1 = tabLayoutPaper.getTabAt(1);
        TabLayout.Tab tabAt2 = tabLayoutPaper.getTabAt(2);
        if (tabAt0 != null) {
            tabAt0.setIcon(R.drawable.bg_tab_mine);
        }
        if (tabAt1 != null)
            tabAt1.setIcon(R.drawable.bg_tab_center);
        if (tabAt2 != null)
            tabAt2.setIcon(R.drawable.bg_tab_tag);
        String[] tabTitle = {"问答", "文献索引","标签"};
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab_paper, tabLayoutPaper, false);
        TextView textView = view.findViewById(R.id.tv_tab_text);
        textView.setText(tabTitle[position]);
        textView.setCompoundDrawables(null,getDrawable());
        return view;
    }*/

    /**
     * 设置Tablayout上的标题的角标
     */
    private void initTab() {

        // 1. 最简单
        //        for (int i = 0; i < mFragmentList.size(); i++) {
        //            mBadgeViews.get(i).setTargetView(((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(i));
        //            mBadgeViews.get(i).setText(formatBadgeNumber(mBadgeCountList.get(i)));
        //        }

        // 2. 最实用
        for (int i = 0; i < tabLayoutPaper.getChildCount(); i++) {
            TabLayout.Tab tab = tabLayoutPaper.getTabAt(i);

            // 更新Badge前,先remove原来的customView,否则Badge无法更新
            View customView = null;
            if (tab == null) {
                return;
            }

            customView = tab.getCustomView();
            if (customView != null) {
                ViewParent parent = customView.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(customView);
                }
            }

            // 更新CustomView
            tab.setCustomView(getTabItemView(i));
        }

        // 需加上以下代码,不然会出现更新Tab角标后,选中的Tab字体颜色不是选中状态的颜色
        //        mTabLayout.getTabAt(mTabLayout.getSelectedTabPosition()).getCustomView().setSelected(true);
    }


    public View getTabItemView(int position) {
        String[] tabTitle = {getString(R.string.questions_answers), getString(R.string.literature_index), getString(R.string.label)};
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab_paper, tabLayoutPaper, false);
        TextView textView = view.findViewById(R.id.tv_tab_text);
        textView.setText(tabTitle[position]);

        return view;
    }


    public static void checkPwd(Context context, String pid, ArrayList<String> v, String type) {
        final EditText inputServer = new EditText(context);
        inputServer.setPadding(50, 50, 50, 50);
        inputServer.setFocusable(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.title_checkpwd)).setView(inputServer).setNegativeButton(R.string.cancel, null);
        builder.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String inputName = inputServer.getText().toString();
                        ArrayMap<String, Object> map = new ArrayMap<>();
                        map.put("pwd", inputName);
                        map.put("pid", pid);
                        ModelFactory.getBaseModel().checkPaperPwd(map, new Callback<BaseEntity>() {
                            @Override
                            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                                if (response.isSuccessful()) {
                                    // response.body() 返回 ResponseBody
                                    BaseEntity entity = response.body();
                                    if(entity.getResult()==1){
                                        PaperDetailsActivity.actionActivity(context, pid, v, type);
                                    }else{
                                        Utils.showToast(entity.getInfo());
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseEntity> call, Throwable t) {

                            }
                        });
                    }
                });
        builder.show();
    }


}
