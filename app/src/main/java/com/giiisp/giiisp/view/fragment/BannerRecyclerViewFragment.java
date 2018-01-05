package com.giiisp.giiisp.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseApp;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.AnswerBean;
import com.giiisp.giiisp.entity.AnswerEntity;
import com.giiisp.giiisp.entity.AnswerQUizXBean;
import com.giiisp.giiisp.entity.AnswerQuizRowsBean;
import com.giiisp.giiisp.entity.AntistopEntity;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.DaoSession;
import com.giiisp.giiisp.entity.DownloadController;
import com.giiisp.giiisp.entity.FansConcernedEntity;
import com.giiisp.giiisp.entity.HomeSearchEntity;
import com.giiisp.giiisp.entity.LiteratureEntity;
import com.giiisp.giiisp.entity.MsgEntity;
import com.giiisp.giiisp.entity.MyScholarBean;
import com.giiisp.giiisp.entity.Note;
import com.giiisp.giiisp.entity.NoteDao;
import com.giiisp.giiisp.entity.QAEntity;
import com.giiisp.giiisp.entity.QuizBean;
import com.giiisp.giiisp.entity.QuizEntity;
import com.giiisp.giiisp.entity.ScholarEntity;
import com.giiisp.giiisp.entity.SearchHistoryEntity;
import com.giiisp.giiisp.entity.SubscribeEntity;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.entity.WaitRecordPaperEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.Log;
import com.giiisp.giiisp.utils.PackageUtil;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.DubbingActivity;
import com.giiisp.giiisp.view.activity.FragmentActivity;
import com.giiisp.giiisp.view.activity.LogInActivity;
import com.giiisp.giiisp.view.activity.PaperDetailsActivity;
import com.giiisp.giiisp.view.adapter.ClickEntity;
import com.giiisp.giiisp.view.adapter.ExpandableItemAdapter;
import com.giiisp.giiisp.view.adapter.ItemClickAdapter;
import com.giiisp.giiisp.view.adapter.ItemDragAdapter;
import com.giiisp.giiisp.view.adapter.MultipleItemQuickAdapter;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.recording.Util;
import com.shuyu.waveview.AudioPlayer;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.ContentValues.TAG;
import static com.giiisp.giiisp.base.BaseActivity.uid;

/**
 * 众多Fragment的基类
 * Created by Thinkpad on 2017/5/19.
 */

public class BannerRecyclerViewFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemLongClickListener, BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_rt)
    TextView tvTitleRt;
    @BindView(R.id.line_banner)
    FrameLayout lineBanner;
    @BindView(R.id.ll_download)
    LinearLayout llDownload;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_back_rt)
    TextView tvBackRt;
    @BindView(R.id.tv_download_number)
    TextView tvDownloadNumber;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rl_banner)
    RelativeLayout rlBanner;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.tv_news_spot)
    ImageView tvNewsSpot;
    @BindView(R.id.tv_home_news)
    ImageView tvHomeNews;
    @BindView(R.id.fl_news)
    FrameLayout flNews;
    ArrayList<ClickEntity> list = new ArrayList<>();
    ItemClickAdapter itemClickAdapter = null;
    private ArrayList<String> listSearch = new ArrayList<>();
    private RxDownload mRxDownload;
    private ExpandableItemAdapter expandableItemAdapter;
    private ItemDragAdapter mDragAdapter;
    private MultipleItemQuickAdapter multipleItemQuickAdapter;
    private String string;
    private String imageId = "";
    int downloadNunber;
    private ExpandableItemAdapter dubbingAdapter;
    private int page = 1;
    protected AudioPlayer audioPlayer;
    private boolean mIsPlay;
    private boolean isPause;
    private String filePath = "";
    private String filePathTwo = "";
    private String searchContent;
    private TextView tvVoice;

    private int isSave = -1;
    private int changePosition;
    private int version = -1;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public ItemClickAdapter getItemClickAdapter() {
        return itemClickAdapter;
    }

    public void setItemClickAdapter(ItemClickAdapter itemClickAdapter) {
        this.itemClickAdapter = itemClickAdapter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_banner_recyclerview;
    }

/*    public void setmDownloadController(DownloadController mDownloadController) {
        this.mDownloadController = mDownloadController;
    }*/

    public static BannerRecyclerViewFragment newInstance(String param1, String param2) {
        BannerRecyclerViewFragment fragment = new BannerRecyclerViewFragment();
        Bundle args = new Bundle();
        args.putString("1005", param1);
        args.putString("1006", param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void loadDownloadNunber() {
        RxDownload.getInstance(getContext()).getTotalDownloadRecords()
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
                        if (downloadNunber != 0) {
                            tvDownloadNumber.setText("" + downloadNunber);
                        } else {
                            tvDownloadNumber.setText("");
                        }
                        if (type.equals("download")) {
                            if (getParentFragment() != null && getParentFragment() instanceof CollectionDownloadFragment) {
                                if (downloadNunber != 0) {
                                    ((CollectionDownloadFragment) getParentFragment()).setUpTabBadge("" + downloadNunber);
                                } else {
                                    ((CollectionDownloadFragment) getParentFragment()).setUpTabBadge("");
                                }
                            }
                        }
                        Log.i("--->>11", "loadDownloadNunber: " + downloadNunber);
                    }
                });
        Log.i("--->>22", "loadDownloadNunber: " + downloadNunber);

    }

    public void loadDownloadData() {
        ArrayList<ClickEntity> res0 = new ArrayList<>();
        ArrayList<ClickEntity> res1 = new ArrayList<>();
        ArrayList<ClickEntity> res2 = new ArrayList<>();
        List<String> missionIdAll = RxDownload.getInstance(getContext()).getMissionIdAll(getContext());
/*
        for (String s : missionIdAll) {
            ClickEntity lv0 = new ClickEntity(0);
            lv0.setUrl(s);
            lv0.setPaperId(s);
            lv0.setItemType(0);
            List<DownloadRecord> missionIdDownloadRecords = RxDownload.getInstance(getContext()).getMissionIdDownloadRecords(getContext(), s);

            for (DownloadRecord missionIdDownloadRecord : missionIdDownloadRecords) {
                ClickEntity lv1 = new ClickEntity(1);
                lv1.setString(missionIdDownloadRecord.getExtra2());
                lv1.setUrl(missionIdDownloadRecord.getExtra1());
                lv1.setPaperId(missionIdDownloadRecord.getExtra1());
                lv1.setItemType(1);
                if (missionIdDownloadRecord.getExtra4().equals("photo"))
                    lv0.addSubItem(lv1);
            }
            for (DownloadRecord missionIdDownloadRecord : missionIdDownloadRecords) {
                for (ClickEntity clickEntity : lv0.getSubItems()) {
                    if (clickEntity.getString().equals(missionIdDownloadRecord.getExtra2())) {
                        ClickEntity lv2 = new ClickEntity(2);
                        lv2.setString(missionIdDownloadRecord.getExtra2());
                        lv2.setUrl(missionIdDownloadRecord.getExtra1());
                        lv2.record = missionIdDownloadRecord;
                        lv2.setPhotoRecord(missionIdDownloadRecord.getExtra4());
                        lv2.setPhotoOrder(missionIdDownloadRecord.getExtra3());
                        lv2.setPaperId(missionIdDownloadRecord.getExtra1());
                        lv2.setItemType(2);
                        clickEntity.addSubItem(lv2);
                    }
                }
            }
            res0.add(lv0);
        }
*/


        RxDownload.getInstance(getContext()).getTotalDownloadRecords()
                .map(new Function<List<DownloadRecord>, List<ClickEntity>>() {
                    @Override
                    public List<ClickEntity> apply(List<DownloadRecord> downloadRecords) throws Exception {
                        List<String> missionIds = new ArrayList<>();
                        List<String> missionIdDownloads = new ArrayList<>();
                        List<String> paperIds = new ArrayList<>();
                        ArrayList<ClickEntity> res0 = new ArrayList<>();
                        ArrayList<ClickEntity> res1 = new ArrayList<>();
                        ArrayList<ClickEntity> res2 = new ArrayList<>();
                        ArrayList<ClickEntity> res11 = new ArrayList<>();
                        ArrayList<ClickEntity> downloads = new ArrayList<>();
                        ArrayList<ClickEntity> downloadCompleted = new ArrayList<>();

                        for (DownloadRecord each : downloadRecords) {

                            if (each.getExtra1() != null && !missionIds.contains(each.getExtra1())) {
                                missionIds.add(each.getExtra1());
                                ClickEntity lv0 = new ClickEntity(0);
                                lv0.setString(each.getExtra2());
                                lv0.setUrl(each.getUrl());
                                lv0.setPaperId(each.getExtra1());
                                lv0.setItemType(0);
                                lv0.setString(each.getExtra5());
                                lv0.setTime(each.getTime());
                                lv0.setTitle(each.getTitle());
                                lv0.setPhotoRecord(each.getExtra4());
                                lv0.setVersion(each.getVersion());
                                res0.add(lv0);
                            }
                            if (each.getExtra1() != null && !missionIdDownloads.contains(each.getExtra1())) {
                                missionIdDownloads.add(each.getExtra1());
                                ClickEntity lv0 = new ClickEntity(0);
                                lv0.setString(each.getExtra2());
                                lv0.setUrl(each.getUrl());
                                lv0.setPaperId(each.getExtra1());
                                lv0.setItemType(0);
                                lv0.setString(each.getExtra5());
                                lv0.setTime(each.getTime());
                                lv0.setTitle(each.getTitle());
                                lv0.setVersion(each.getVersion());
                                lv0.setPhotoRecord(each.getExtra4());
                                downloads.add(lv0);
                            }


                            if (each.getExtra2() != null && !paperIds.contains(each.getExtra2())) {
                                paperIds.add(each.getExtra2());
                                ClickEntity lv1 = new ClickEntity(1);
                                lv1.setString(each.getExtra2());
                                lv1.setUrl(each.getExtra1());
                                lv1.setPhotoRecord(each.getExtra4());
                                lv1.setPaperId(each.getExtra1());
                                lv1.setItemType(1);
                                lv1.setPhotoNumber(Integer.valueOf(each.getExtra3()));
                                res1.add(lv1);
                            }
                            ClickEntity lv2 = new ClickEntity(2);
                            lv2.setString(each.getExtra2());
                            lv2.setUrl(each.getExtra1());
                            lv2.record = each;
                            lv2.setPhotoRecord(each.getExtra4());
                            lv2.setPhotoOrder(each.getExtra3());
                            lv2.setPaperId(each.getExtra1());
                            lv2.setItemType(2);
                            lv2.setLanguage(each.getExtra5());
                            lv2.setVersion(each.getVersion());
                            lv2.setAdapter(expandableItemAdapter);
                            lv2.setTitle(each.getTitle());
                            //                            lv2.setActivity((BaseActivity) getActivity());
                            res2.add(lv2);

                        }


                   /*     for (ClickEntity clickEntity : res0) {
                            for (DownloadRecord downloadRecord : downloadRecords) {
                                if (Objects.equals(clickEntity.getPaperId(), downloadRecord.getExtra1()) && downloadRecord.getFlag() != DownloadFlag.COMPLETED && !downloads.contains(clickEntity)) {
                                    downloads.add(clickEntity);
                                }
                            }
                        }
                        downloadCompleted = res0;
                        for (ClickEntity download : downloads) {
                            downloadCompleted.remove(download);
                        }*/
                        for (ClickEntity clickEntity : res2) {
                            for (ClickEntity entity : res1) {
                                if (Objects.equals(entity.getString(), clickEntity.getString())) {
                                    entity.addSubItem(clickEntity);
                                    clickEntity.setClickEntity(entity);

                                }
                            }
                        }
                        for (ClickEntity entity : res1) {
                            for (ClickEntity clickEntitys : res0) {
                                if (Objects.equals(clickEntitys.getPaperId(), entity.getPaperId())) {
                                    if (entity.getSubItems() != null) {
                                        List<ClickEntity> subItems = entity.getSubItems();
                                        boolean isDownload = true;
                                        for (ClickEntity subItem : subItems) {
                                            if (subItem.record.getFlag() != DownloadFlag.COMPLETED) {
                                                isDownload = false;
                                                break;
                                            }
                                        }

                                        if (isDownload) {
                                            clickEntitys.addSubItem(entity);
                                        }
                                      /*  if (subItems.size()==2){
                                            if (subItems.get(0).getRecordFlag() == DownloadFlag.COMPLETED && subItems.get(1).getPhotoFlag() == DownloadFlag.COMPLETED)
                                                clickEntitys.addSubItem(entity);
                                        }else if(subItems.size()==3){
                                            if (subItems.get(0).getRecordFlag() == DownloadFlag.COMPLETED && subItems.get(1).getPhotoFlag() == DownloadFlag.COMPLETED)
                                                clickEntitys.addSubItem(entity);
                                        }else{
                                            if (entity.getRecordFlag() == DownloadFlag.COMPLETED && entity.getPhotoFlag() == DownloadFlag.COMPLETED)
                                                clickEntitys.addSubItem(entity);
                                        }*/

                                    }

                                }
                            }
                            for (ClickEntity clickEntitys : downloads) {
                                if (Objects.equals(clickEntitys.getPaperId(), entity.getPaperId())) {
                                    if (entity.getSubItems() != null) {
                                        List<ClickEntity> subItems = entity.getSubItems();
                                        boolean isDownload = true;
                                        for (ClickEntity subItem : subItems) {
                                            if (subItem.record.getFlag() != DownloadFlag.COMPLETED) {
                                                isDownload = false;
                                                break;
                                            }
                                        }

                                        if (!isDownload) {
                                            clickEntitys.addSubItem(entity);
                                        }
                                    }
                                }
                            }
                       /* for (ClickEntity entity : res1) {
                            for (ClickEntity clickEntitys : downloads) {
                                if (Objects.equals(clickEntitys.getPaperId(), entity.getPaperId())) {
                                    if (entity.getRecordFlag() != DownloadFlag.COMPLETED || entity.getPhotoFlag() != DownloadFlag.COMPLETED)
                                        clickEntitys.addSubItem(entity);
                                    entity.setClickEntity(clickEntitys);
                                }
                            }
                        }*/
            /*            for (ClickEntity clickEntity : res0) {
                            List<ClickEntity> subItems = clickEntity.getSubItems();
                            if (subItems == null || subItems.size() <= 0) {
                                res0.remove(clickEntity);
                            }
                        }
                        for (ClickEntity clickEntity : downloads) {
                            List<ClickEntity> subItems = clickEntity.getSubItems();
                            if (subItems == null || subItems.size() <= 0) {
                                downloads.remove(clickEntity);
                            }*/
                        }
                        Log.i("--->>", "apply:1 " + Thread.currentThread().getName() + tvDownloadNumber.getText() + type);

                        switch (type) {
                            case "paper_download":
                            case "summary_download":
                                return res0;
                            case "download":
                            case "summary_list":

                                return downloads;
                        }
                        Log.i("--->", "apply: " + res0.size());
                        return res0;
                    }
                })
                .subscribe(new Consumer<List<ClickEntity>>() {
                    @Override
                    public void accept(List<ClickEntity> downloadBeen) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        switch (type) {
                            case "paper_download":

                            case "summary_download":
                                mDragAdapter.setNewData(null);
                                for (ClickEntity clickEntity : downloadBeen) {
                                    List<ClickEntity> subItems = clickEntity.getSubItems();
                                    if (subItems != null && subItems.size() > 0) {
                                        mDragAdapter.addData(clickEntity);
                                    }
                                }

                                break;
                            case "download":
                                expandableItemAdapter.setNewData(null);
                                for (ClickEntity clickEntity : downloadBeen) {
                                    List<ClickEntity> subItems = clickEntity.getSubItems();
                                    if (subItems != null && subItems.size() > 0) {
                                        expandableItemAdapter.addData(clickEntity);
                                    }
                                }
                                expandableItemAdapter.expandAll();
                        /*        List<ClickEntity> data = expandableItemAdapter.getData();
                                for (ClickEntity clickEntity : data) {
                                    List<ClickEntity> subItems = clickEntity.getSubItems();
                                    for (ClickEntity subItem : subItems) {
                                        List<ClickEntity> subItems1 = subItem.getSubItems();
                                        for (ClickEntity entity : subItems1) {

                                        }
                                    }
                                }
*/
                                break;

                            default:
                                Log.i("--->>", "apply:2 " + Thread.currentThread().getName());
                                break;
                        }
                    }
                });
    }

    OnItemDragListener listener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "drag start");
            BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            //                holder.setTextColor(R.id.tv, Color.WHITE);
        }

        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
            Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
        }

        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "drag end");
            BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            //                holder.setTextColor(R.id.tv, Color.BLACK);
        }
    };
    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "view swiped start: " + pos);
            BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            //                holder.setTextColor(R.id.tv, Color.WHITE);
        }

        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "View reset: " + pos);
            BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            //                holder.setTextColor(R.id.tv, Color.BLACK);
        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "View Swiped: " + pos);
            ClickEntity item = mDragAdapter.getItem(pos);
            if (item != null) {
                final String url = mDragAdapter.getItem(pos).getPaperId();


                mRxDownload.deleteAll(url, true).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        Utils.showToast(url);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
                List<ClickEntity> subItems = item.getSubItems();
                if (subItems != null && subItems.size() > 0) {
                    for (ClickEntity subItem : subItems) {

                        mRxDownload.deleteAll(subItem.getString(), true).subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(@NonNull Object o) throws Exception {
                                //                                Utils.showToast(url);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {

                            }
                        });
                    }
                }
            }
        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
            canvas.drawColor(ContextCompat.getColor(getActivity(), R.color.colorBackground));
            //                canvas.drawText("Just some text", 0, 40, paint);
        }
    };

    @Override
    public void initView() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAuxiliary);
        swipeRefreshLayout.setOnRefreshListener(this);
        //        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setRefreshing(true);
        mRxDownload = RxDownload.getInstance(getContext());

        View notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        switch (type) {
            case "he":

                lineBanner.setVisibility(View.VISIBLE);
                tvTitle.setText(R.string.scholars_details_page);
                list.clear();


                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                multipleItemQuickAdapter = new MultipleItemQuickAdapter((BaseActivity) getActivity(), list);
                multipleItemQuickAdapter.setOnItemChildClickListener(this);
                multipleItemQuickAdapter.setOnItemClickListener(this);
                multipleItemQuickAdapter.setEmptyView(notDataView);
                recyclerView.setAdapter(multipleItemQuickAdapter);
                break;
            case "scholar_list":
                tvTitle.setText(R.string.scholars);
                lineBanner.setVisibility(View.VISIBLE);
                GridLayoutManager grid = new GridLayoutManager(getContext(), 3);
                recyclerView.setLayoutManager(grid);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_scholar, null, type);
                recyclerView.setAdapter(itemClickAdapter);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "plays":

                lineBanner.setVisibility(View.VISIBLE);
                tvBack.setVisibility(View.VISIBLE);
                ivMenu.setVisibility(View.VISIBLE);
                ivMenu.setImageResource(R.mipmap.record_play_empty);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_collection, this.list, type);
                tvTitle.setText(R.string.plays);
                itemClickAdapter.setOnItemLongClickListener(this);
                break;
            case "play":

                lineBanner.setVisibility(View.VISIBLE);
                tvBack.setVisibility(View.VISIBLE);
                ivMenu.setVisibility(View.VISIBLE);
                ivMenu.setImageResource(R.mipmap.record_play_empty);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_collection, this.list, type);
                itemClickAdapter.setOnItemLongClickListener(this);
                if (Objects.equals("giiisp", string)) {
                    tvBack.setVisibility(View.GONE);
                }
                tvTitle.setText(R.string.play);
                break;
            case "paper_qa":
                list.clear();
                swipeRefreshLayout.setEnabled(false);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_questions_answers, this.list, type);
                itemClickAdapter.setOnItemChildClickListener(this);
                initpaly();
                break;
            case "paper_literature":
                swipeRefreshLayout.setEnabled(false);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_paper_indexes, this.list, type);
                break;
            case "paper_label":
                swipeRefreshLayout.setEnabled(false);
                this.list.clear();
                this.list.add(new ClickEntity(getString(R.string.technology_label)));
                this.list.add(new ClickEntity(getString(R.string.mathematics_label)));
                this.list.add(new ClickEntity(getString(R.string.data_labels)));
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_paper_label, this.list, type);

                break;
            case "summary_list":
                tvTitleRt.setVisibility(View.VISIBLE);
                rlBanner.setVisibility(View.VISIBLE);
                lineBanner.setVisibility(View.GONE);
                tvTitleRt.setText(R.string.review_list);
                tvBackRt.setVisibility(View.VISIBLE);
                list.clear();
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_paper, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "paper_list":
                tvTitleRt.setVisibility(View.VISIBLE);
                tvBackRt.setVisibility(View.VISIBLE);
                tvTitleRt.setText(R.string.paper_list);
                rlBanner.setVisibility(View.VISIBLE);
                lineBanner.setVisibility(View.GONE);
                list.clear();
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_paper, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "paper_download":
            case "summary_download":
                list.clear();
                mDragAdapter = new ItemDragAdapter((BaseActivity) getActivity(), list);
                //                ItemDragAndSwipeCallback mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mDragAdapter);
                //                ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
                //                mItemTouchHelper.attachToRecyclerView(recyclerView);
                //mItemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);
                //                mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
                //                mDragAdapter.enableSwipeItem();
                //                mDragAdapter.setOnItemSwipeListener(onItemSwipeListener);
                //                mDragAdapter.enableDragItem(mItemTouchHelper);
                //                mDragAdapter.setOnItemDragListener(listener);
                mDragAdapter.setOnItemClickListener(this);
                mDragAdapter.setOnItemLongClickListener(this);
                mDragAdapter.setEmptyView(notDataView);
                recyclerView.setAdapter(mDragAdapter);
                break;
            case "download":
                expandableItemAdapter = new ExpandableItemAdapter((BaseActivity) getActivity(), this, R.layout.item_download_finished, R.layout.item_paper_page, R.layout.item_download_progress, list, type);
                recyclerView.setAdapter(expandableItemAdapter);
                expandableItemAdapter.setOnItemClickListener(this);
                expandableItemAdapter.setEmptyView(notDataView);
                llDownload.setVisibility(View.VISIBLE);
                break;
            case "my_paper":
                tvTitle.setText(R.string.my_paper_list);
                lineBanner.setVisibility(View.VISIBLE);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_paper, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "my_review":
                tvTitle.setText(R.string.my_review_list);
                lineBanner.setVisibility(View.VISIBLE);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_paper, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "collection_paper":
            case "collection_summary":
                list.clear();
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_collectionchild, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.setOnItemChildClickListener(this);
                break;
            case "popular":
                tvTitle.setText(R.string.play);
                tvBack.setVisibility(View.GONE);
                lineBanner.setVisibility(View.VISIBLE);
                DaoSession daoSession1 = BaseApp.app.getDaoSession();
                NoteDao noteDao2 = daoSession1.getNoteDao();
                Query<Note> notesQuery3 = noteDao2.queryBuilder().where(NoteDao.Properties.Type.eq("play")).orderAsc(NoteDao.Properties.Id).build();
                List<Note> listnote4 = notesQuery3.list();
                list.clear();
                for (int i = 0; i < listnote4.size(); i++) {
                    list.add(new ClickEntity(listnote4.get(i)));
                }
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_collection, this.list, type);
                break;
            case "wait_dubbing": //  test
                tvTitle.setText(R.string.voice_file_list);
                ivMenu.setImageResource(R.mipmap.dubbing_refresh);
                ivMenu.setVisibility(View.VISIBLE);
                list.clear();
                dubbingAdapter = new ExpandableItemAdapter((BaseActivity) getActivity(), R.layout.item_title_dubbing, R.layout.item_waiting_dubbing, this.list, type);
                recyclerView.setAdapter(dubbingAdapter);
                dubbingAdapter.setOnItemChildClickListener(this);
                dubbingAdapter.setOnItemClickListener(this);
                dubbingAdapter.setOnLoadMoreListener(this, recyclerView);
                dubbingAdapter.disableLoadMoreIfNotFullPage();
                dubbingAdapter.setEmptyView(notDataView);
                lineBanner.setVisibility(View.VISIBLE);
                break;
            case "subscribe":
            case "newest":
                this.list.clear();
                tvTitleRt.setText(R.string.subscribe);
                rlBanner.setVisibility(View.VISIBLE);
                lineBanner.setVisibility(View.GONE);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_paper, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "notice":
                list.clear();
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_message_notification, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "interactive":
                list.clear();
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_message_interaction, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "answer":
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_message_answers, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "questions":
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_message_questions, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "search_hint":
                swipeRefreshLayout.setEnabled(false);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_search, this.list, type);
                break;
            case "search_result":
                this.list.clear();
                swipeRefreshLayout.setEnabled(false);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_search_result, this.list, type);
                break;
            case "search_scholar":
                swipeRefreshLayout.setEnabled(false);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_search_scholar, this.list, type);
                break;
            case "mine_scholar":
                lineBanner.setVisibility(View.VISIBLE);
                if (Objects.equals("" + uid, string)) {
                    tvTitle.setText(R.string.my_fans);
                } else {
                    tvTitle.setText(R.string.ta_fans);
                }
                list.clear();
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_search_scholar, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "mine_follow":
                lineBanner.setVisibility(View.VISIBLE);
                if (Objects.equals("" + uid, string)) {
                    tvTitle.setText(R.string.my_follow);
                } else {
                    tvTitle.setText(R.string.ta_follow);
                }
                list.clear();
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_search_scholar, this.list, type);
                itemClickAdapter.setOnLoadMoreListener(this, recyclerView);
                itemClickAdapter.disableLoadMoreIfNotFullPage();
                break;
            case "search_paper":
                swipeRefreshLayout.setEnabled(false);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_paper_indexes, this.list, type);
                break;
            default:
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_paper, this.list, type);

        }
        if (itemClickAdapter != null) {
            itemClickAdapter.setEmptyView(notDataView);
            itemClickAdapter.setOnItemChildClickListener(this);
            itemClickAdapter.setOnItemClickListener(this);
            recyclerView.setAdapter(itemClickAdapter);
        }
        //        if (itemClickAdapter != null)
        //            itemClickAdapter.disableLoadMoreIfNotFullPage();
        initNetwork();
        loadDownloadNunber();
    }

    /**
     * 网络请求数据
     */
    @Override
    public void initNetwork() {
        if (swipeRefreshLayout == null)
            return;
        ArrayMap<String, Object> map = new ArrayMap<>();
//        map.put("token", token);
        switch (type) {
            case "he":
                ArrayMap<String, Object> userMap = new ArrayMap<>();
                //        userMap.put("token", "A760880003E7DDEDFEF56ACB3B09697F");
//                userMap.put("token", token);
                userMap.put("oid", string);
                userMap.put("uid", uid);
                presenter.getUserInfoData(userMap);
                break;
            case "scholar_list":
                map.put("page", page);
                presenter.getListScholarData(map);
                break;
            case "plays":
                DaoSession daoSessions = BaseApp.app.getDaoSession();
                NoteDao noteDaos = daoSessions.getNoteDao();
                Query<Note> notesQuerys = noteDaos.queryBuilder().where(NoteDao.Properties.Type.eq("plays")).orderDesc(NoteDao.Properties.Time).build();
                list.clear();
                List<Note> listnotes = notesQuerys.list();
                for (int i = 0; i < listnotes.size(); i++) {
                    list.add(new ClickEntity(listnotes.get(i)));
                }
                itemClickAdapter.setNewData(list);
                swipeRefreshLayout.setRefreshing(false);
                break;
            case "play":
                DaoSession daoSession = BaseApp.app.getDaoSession();
                NoteDao noteDao = daoSession.getNoteDao();
                Query<Note> notesQuery = noteDao.queryBuilder().where(NoteDao.Properties.Type.eq("play")).orderDesc(NoteDao.Properties.Time).build();
                List<Note> listnote = notesQuery.list();
                list.clear();
                for (int i = 0; i < listnote.size(); i++) {
                    list.add(new ClickEntity(listnote.get(i)));
                }
                itemClickAdapter.setNewData(list);
                swipeRefreshLayout.setRefreshing(false);
                break;
            case "paper_qa":
                if (TextUtils.isEmpty(imageId))
                    return;
                map.put("id", imageId);
                if (swipeRefreshLayout != null) {
                    presenter.getListOfQuizAndAnswerData(map);
                }
                break;
            case "paper_literature":
                map.put("id", string);
                presenter.getListOfLiteratureData(map);
                break;
            case "paper_label":
                map.put("id", string);
                presenter.getListOfAntistopData(map);
                break;
            case "summary_list":
                map.put("uid", uid);
                map.put("page", page);
                map.put("paperOrSummarize", 2);
                presenter.getListSummarizeData(map);
                break;
            case "paper_list":
                map.put("uid", uid);
                map.put("page", page);
                map.put("paperOrSummarize", 1);
                presenter.getListSummarizeData(map);
                break;
            case "paper_download":
            case "summary_download":
                loadDownloadData();
                break;
            case "download":
                loadDownloadData();
                break;
            case "my_paper":
                map.put("uid", uid);
                map.put("page", page);
                map.put("isOneOrTwo", 1);
                presenter.getListNewPaperData(map);
                break;
            case "my_review":
                map.put("uid", uid);
                map.put("page", page);

                map.put("isOneOrTwo", 2);
                presenter.getListNewPaperData(map);
                break;
            case "collection_paper":
                map.put("uid", uid);
                map.put("page", page);
                //                map.put("upTime", "asc");
                map.put("isOneOrTwo", 1);
                presenter.getListFollowedPaperData(map);
                break;
            case "collection_summary":
                map.put("uid", uid);
                map.put("page", page);
                //                map.put("upTime", "asc");
                map.put("isOneOrTwo", 2);
                presenter.getListFollowedPaperData(map);
                break;
            case "popular":
                break;
            case "wait_dubbing":
                map.put("uid", uid);
                map.put("page", page);
                presenter.getWaitRecordPaperListData(map);
                break;
            case "subscribe":
            case "newest":
                this.list.clear();
                map.put("uid", uid);
                map.put("page", page);
                map.put("upTime", "asc");
                presenter.getlistFollowPaperData(map);
                break;
            case "notice":
                map.put("uid", uid);
                map.put("page", page);
                map.put("type", 1);
                presenter.getMsgListData(map);
                break;
            case "interactive":
                map.put("uid", uid);
                map.put("page", page);
                map.put("type", 2);
                presenter.getMsgListData(map);
                break;
            case "answer":
                map.put("page", page);
                map.put("uid", uid);
                presenter.getListAnswerData(map);
                break;
            case "questions":
                map.put("uid", uid);
                map.put("page", page);
                presenter.getListQuizData(map);
                break;
            case "search_hint":
                map.put("uid", uid);
                map.put("page", page);
                presenter.getSearchHistoryData(map);
                break;
            case "search_result":
                break;
            case "search_scholar":
                break;
            case "mine_scholar":
                map.put("page", page);
                map.put("uid", string);
                presenter.getListUserFollowedData(map);
                break;
            case "mine_follow":
                map.put("page", page);
                map.put("uid", string);
                presenter.getListUserFollowData(map);
                break;
            case "search_paper":
                break;
            default:

        }

    }

    private void start(String url) {
        new RxPermissions(getActivity())
                .request(WRITE_EXTERNAL_STORAGE)
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
                            throw new RuntimeException("no permission");
                        }
                    }
                })
                .compose(mRxDownload.<Boolean>transformService(url))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Utils.showToast(R.string.download_begins);
                    }
                });

    }

    private void pause(String url) {
        mRxDownload.pauseServiceDownload(url).subscribe();
    }

    @Override
    public void initData() {
        super.initData();
        if (getArguments() == null) {
            throw new NullPointerException("Arguments is null!!!");
        }
        type = getArguments().getString("1005");
        string = getArguments().getString("1006");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Subscribe
    public void onMessageSearch(String userInfoEntity) {
        if (!TextUtils.isEmpty(userInfoEntity))
            searchContent = userInfoEntity;
        if (Objects.equals(type, "search_result")) {
            ArrayMap<String, Object> map = new ArrayMap<>();
//            map.put("token", token);
            map.put("uid", uid);
            map.put("content", searchContent);
            presenter.getHomeSearchData(map);
        } else if (Objects.equals(type, "search_hint")) {
            initNetwork();
        }
    }

    @Subscribe
    public void onMessage(int userInfoEntity) {
        Log.i(TAG, "onMessage: download");
        if (type.equals("download")) {
            loadDownloadData();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        switch (type) {
            case "summary_list":
            case "paper_list":
            case "paper_download":
            case "summary_download":
            case "download":
            case "collection_paper":
            case "collection_summary":
            case "subscribe":
            case "search_paper":
                loadDownloadNunber();
                break;
            default:

        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (expandableItemAdapter != null) {
            List<ClickEntity> list = expandableItemAdapter.getData();
            for (ClickEntity each : list) {
                zlc.season.rxdownload2.function.Utils.dispose(each.disposable);
            }
        }
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @OnClick({R.id.tv_back, R.id.fl_menu, R.id.fl_news, R.id.tv_back_rt, R.id.iv_play, R.id.iv_download, R.id.tv_all_start, R.id.tv_all_suspended})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_all_start:
                if (expandableItemAdapter == null)
                    return;
                List<ClickEntity> data = expandableItemAdapter.getData();
                if (data != null && data.size() > 0) {
                    for (ClickEntity clickEntity : data) {
                        List<ClickEntity> subItems = clickEntity.getSubItems();
                        if (subItems != null && subItems.size() > 0) {
                            for (ClickEntity subItem : subItems) {
                                List<ClickEntity> items = subItem.getSubItems();
                                if (items != null && items.size() > 0) {
                                    for (ClickEntity item : items) {
                                        if (item != null && item.record != null && item.record.getUrl() != null) {
                                            mRxDownload.serviceDownload(item.record.getUrl())
                                                    .subscribe(new Consumer<Object>() {
                                                        @Override
                                                        public void accept(Object o) throws Exception {

                                                        }
                                                    }, new Consumer<Throwable>() {
                                                        @Override
                                                        public void accept(Throwable throwable) throws Exception {
                                                            Log.d("--->>", throwable.toString());
                                                        }
                                                    });
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Log.i("--->>", "onViewClicked: startAll");
                break;
            case R.id.tv_all_suspended:
                if (expandableItemAdapter == null)
                    return;
                List<ClickEntity> aata = expandableItemAdapter.getData();
                if (aata != null && aata.size() > 0) {
                    for (ClickEntity clickEntity : aata) {
                        List<ClickEntity> subItems = clickEntity.getSubItems();
                        if (subItems != null && subItems.size() > 0) {
                            for (ClickEntity subItem : subItems) {
                                List<ClickEntity> items = subItem.getSubItems();
                                if (items != null && items.size() > 0) {
                                    for (ClickEntity item : items) {
                                        if (item != null && item.record != null && item.record.getUrl() != null) {
                                            mRxDownload.pauseServiceDownload(item.record.getUrl())
                                                    .subscribe(new Consumer<Object>() {
                                                        @Override
                                                        public void accept(Object o) throws Exception {

                                                        }
                                                    }, new Consumer<Throwable>() {
                                                        @Override
                                                        public void accept(Throwable throwable) throws Exception {
                                                            Log.d("--->>", throwable.toString());
                                                        }
                                                    });
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Log.i("--->>", "onViewClicked: pauseAll");
                break;
            case R.id.tv_back:
            case R.id.tv_back_rt:
                getActivity().finish();
                break;
            case R.id.fl_news:
                switch (type) {
                    case "plays":
                        FragmentActivity.actionActivity(getContext(), "news");
                        break;
                }
                break;
            case R.id.fl_menu:
                switch (type) {
                    case "plays":
                    case "play":
                        showPlayDialog();
                        //                        itemClickAdapter.setNewData(null);
                        break;
                    case "wait_dubbing":
                        page = 1;
                        initNetwork();
                        break;
                }
                break;
            case R.id.iv_play:
                FragmentActivity.actionActivity(getActivity(), "play");
                break;
            case R.id.iv_download:
                FragmentActivity.actionActivity(getContext(), "download_activity");
                break;
        }
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        if (swipeRefreshLayout == null)
            return;
        swipeRefreshLayout.setRefreshing(false);
        switch (type) {
            case "he":
                if (entity instanceof UserInfoEntity) {
                    UserInfoEntity userInfoEntity = (UserInfoEntity) entity;
                    multipleItemQuickAdapter.setNewData(null);
                    if (userInfoEntity.getUserInfo() != null) {
                        ClickEntity clickEntity = new ClickEntity(R.layout.item_user_info, "head");
                        clickEntity.setUserInfoEntity(userInfoEntity);
                        clickEntity.setPaperId(string);
                        multipleItemQuickAdapter.addData(clickEntity);
                    }
                    if (userInfoEntity.getAuthen() != null) {
                        ClickEntity clickEntity = new ClickEntity(R.layout.item_recycler_head, "authentication_info");
                        clickEntity.setUserInfoEntity(userInfoEntity);
                        multipleItemQuickAdapter.addData(clickEntity);
                    }
                    if (userInfoEntity.getIntroduction() != null && userInfoEntity.getIntroduction().size() > 0) {
                        ClickEntity clickEntity = new ClickEntity(R.layout.item_recycler_head, "scholar_education");
                        clickEntity.setUserInfoEntity(userInfoEntity);
                        multipleItemQuickAdapter.addData(clickEntity);
                    }

                    if (userInfoEntity.getPaper() != null && !TextUtils.isEmpty(userInfoEntity.getPaper().getId())) {
                        ClickEntity clickEntity = new ClickEntity(R.layout.item_recycler_head, "paper_indexes");
                        clickEntity.setUserInfoEntity(userInfoEntity);
                        multipleItemQuickAdapter.addData(clickEntity);

                    }
                    if (userInfoEntity.getSummarize() != null && !TextUtils.isEmpty(userInfoEntity.getSummarize().getId())) {
                        ClickEntity clickEntity = new ClickEntity(R.layout.item_recycler_head, "summarize_indexes");
                        clickEntity.setUserInfoEntity(userInfoEntity);
                        multipleItemQuickAdapter.addData(clickEntity);

                    }
                } else {
                    onRefresh();
                }
                break;
            case "scholar":
                break;

            case "plays":
                break;
            case "play":
                break;
            case "paper_qa":
                if (entity instanceof QAEntity) {
                    QAEntity.QuizInfoBean quizInfo = ((QAEntity) entity).getQuizInfo();
                    if (quizInfo != null) {
                        List<QAEntity.QuizInfoBean.RowsBeanXXXX> rows = quizInfo.getRows();
                        itemClickAdapter.setNewData(null);
                        for (QAEntity.QuizInfoBean.RowsBeanXXXX row : rows) {
                            ClickEntity clickEntity = new ClickEntity(row);
                            clickEntity.setPaperId(((QAEntity) entity).getAid() + "");
                            itemClickAdapter.addData(clickEntity);
                        }
                    }
                }
                break;
            case "paper_literature":
                if (entity instanceof LiteratureEntity) {
                    itemClickAdapter.setNewData(null);
                    List<LiteratureEntity.LiteratureInfoBean> literatureInfo = ((LiteratureEntity) entity).getLiteratureInfo();
                    for (LiteratureEntity.LiteratureInfoBean literatureInfoBean : literatureInfo) {
                        itemClickAdapter.addData(new ClickEntity(literatureInfoBean));
                    }
                }
                break;
            case "paper_label":
                if (entity instanceof AntistopEntity) {
                    AntistopEntity.AntistopInfoBean antistopInfo = ((AntistopEntity) entity).getAntistopInfo();
                    List<AntistopEntity.AntistopInfoBean.RowsBeanXX> rows = antistopInfo.getRows();
                    for (AntistopEntity.AntistopInfoBean.RowsBeanXX row : rows) {
                        for (AntistopEntity.AntistopInfoBean.RowsBeanXX.DataBean.RowsBeanX rowsBeanXX : row.getData().getRows()) {
                            if (itemClickAdapter.getItemCount() >= 3) {
                                ClickEntity item = itemClickAdapter.getItem(2);
                                if (item != null) {
                                    item.getList().add(rowsBeanXX.getAntistop());
                                }
                            }
                        }
                        for (AntistopEntity.AntistopInfoBean.RowsBeanXX.DataBean.RowsBeanX rowsBeanX : row.getMaths().getRows()) {
                            if (itemClickAdapter.getItemCount() >= 3) {
                                ClickEntity item = itemClickAdapter.getItem(1);
                                if (item != null)
                                    item.getList().add(rowsBeanX.getAntistop());
                            }
                        }
                        for (AntistopEntity.AntistopInfoBean.RowsBeanXX.DataBean.RowsBeanX rowsBean : row.getScience().getRows()) {
                            if (itemClickAdapter.getItemCount() >= 3) {
                                ClickEntity item = itemClickAdapter.getItem(0);
                                if (item != null)
                                    item.getList().add(rowsBean.getAntistop());
                            }
                        }

                    }
                    itemClickAdapter.notifyDataSetChanged();
                }
                break;
            case "paper_download":
            case "summary_download":
                break;
            case "download":
                break;
            case "release_paper":
                break;
            case "collection_paper":
            case "collection_summary":
                if (entity instanceof SubscribeEntity) {
                    itemClickAdapter.loadMoreComplete();
                    if (itemClickAdapter == null || ((SubscribeEntity) entity).getPageInfo() == null || ((SubscribeEntity) entity).getPageInfo().getRows() == null)
                        return;
                    if (page == 1) {
                        itemClickAdapter.setNewData(null);
                    }
                    List<SubscribeEntity.PageInfoBean.RowsBeanXXXXX> rows = ((SubscribeEntity) entity).getPageInfo().getRows();
                    for (SubscribeEntity.PageInfoBean.RowsBeanXXXXX row : rows) {
                        itemClickAdapter.addData(new ClickEntity(row));
                    }
                    if (itemClickAdapter.getItemCount() < ((SubscribeEntity) entity).getTotal()) {
                        page++;
                    } else {
                        itemClickAdapter.loadMoreEnd(false);
                    }
                } else {
                    if (itemClickAdapter.getItemCount() > changePosition) {
                        ClickEntity item = itemClickAdapter.getItem(changePosition);
                        if (item != null && item.getSubscribeEntityRows() != null) {
                            SubscribeEntity.PageInfoBean.RowsBeanXXXXX subscribeEntityRows = item.getSubscribeEntityRows();
                            String isFollowed = "";
                            if (isSave == 10) {
                                isFollowed = "1";
                            } else if (isSave == 20) {
                                isFollowed = "0";
                            }
                            switch (version) {
                                case 0:
                                    if (subscribeEntityRows.getPhotoOne() != null && subscribeEntityRows.getPhotoOne().getRows() != null && subscribeEntityRows.getPhotoOne().getRows().size() == 1 && subscribeEntityRows.getPhotoOne().getRows().get(0) != null) {
                                        subscribeEntityRows.getPhotoOne().getRows().get(0).setIsFollowed(isFollowed);
                                    }
                                    break;
                                case 1:
                                    if (subscribeEntityRows.getPhotoTwo() != null && subscribeEntityRows.getPhotoTwo().getRows() != null && subscribeEntityRows.getPhotoTwo().getRows().size() == 1 && subscribeEntityRows.getPhotoTwo().getRows().get(0) != null) {
                                        subscribeEntityRows.getPhotoTwo().getRows().get(0).setIsFollowed(isFollowed);
                                    }
                                    break;
                                case 2:
                                    if (subscribeEntityRows.getPhotoThree() != null && subscribeEntityRows.getPhotoThree().getRows() != null && subscribeEntityRows.getPhotoThree().getRows().size() == 1 && subscribeEntityRows.getPhotoThree().getRows().get(0) != null) {
                                        subscribeEntityRows.getPhotoThree().getRows().get(0).setIsFollowed(isFollowed);
                                    }
                                    break;
                            }
                            itemClickAdapter.notifyItemChanged(changePosition);
                        }
                    }
                    //                    onRefresh();
                }
                break;
            case "popular":
                break;
            case "wait_dubbing":
                if (entity instanceof WaitRecordPaperEntity) {
                    dubbingAdapter.loadMoreComplete();
                    if (dubbingAdapter == null || ((WaitRecordPaperEntity) entity).getWaitRecordPaper() == null || ((WaitRecordPaperEntity) entity).getWaitRecordPaper().getRows() == null) {
                        dubbingAdapter.loadMoreEnd(false);
                        return;
                    }
                    if (page == 1) {
                        dubbingAdapter.setNewData(null);
                    }

                    List<SubscribeEntity.PageInfoBean.RowsBeanXXXXX> rows = ((WaitRecordPaperEntity) entity).getWaitRecordPaper().getRows();
                    if (rows != null && rows.size() > 0) {
                        for (SubscribeEntity.PageInfoBean.RowsBeanXXXXX subscribeEntityRows : rows) {
                            ClickEntity clickEntity0 = new ClickEntity(subscribeEntityRows);
                            SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean photoOne = subscribeEntityRows.getPhotoOne();
                            SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean photoTwo = subscribeEntityRows.getPhotoTwo();
                            SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean photoThree = subscribeEntityRows.getPhotoThree();
                            List<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX> photoThreeRows = photoThree.getRows();
                            List<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX> photoTwoRows = photoTwo.getRows();
                            List<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX> photoOneRows = photoOne.getRows();
                            if (photoThreeRows != null && photoThreeRows.size() == 1) {
                                SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX rowsBeanXXXX = photoThreeRows.get(0);
                                if (!Objects.equals("1", rowsBeanXXXX.getStatus()))
                                    initEntity(subscribeEntityRows, clickEntity0, photoThreeRows, rowsBeanXXXX, "2");

                            }
                            if (photoTwoRows != null && photoTwoRows.size() == 1) {
                                SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX rowsBeanXXXX = photoTwoRows.get(0);
                                if (!Objects.equals("1", rowsBeanXXXX.getStatus()))
                                    initEntity(subscribeEntityRows, clickEntity0, photoTwoRows, rowsBeanXXXX, "1");
                            }
                            if (photoOneRows != null && photoOneRows.size() == 1) {
                                SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX rowsBeanXXXX = photoOneRows.get(0);
                                if (!Objects.equals("1", rowsBeanXXXX.getStatus()))
                                    initEntity(subscribeEntityRows, clickEntity0, photoOneRows, rowsBeanXXXX, "0");
                            }
                            clickEntity0.setItemType(0);
                            clickEntity0.setLevel(0);
                            dubbingAdapter.addData(clickEntity0);
                        }
                        if (dubbingAdapter.getItemCount() < ((WaitRecordPaperEntity) entity).getWaitRecordPaper().getTotal()) {
                            page++;
                        } else {
                            dubbingAdapter.loadMoreEnd(false);
                        }
                        dubbingAdapter.expandAll();
                    } else {
                        dubbingAdapter.loadMoreEnd(false);
                    }
                } else {
                    onRefresh();
                }
                break;
            case "collection":
                break;
            case "subscribe":
            case "paper_list":
            case "my_review":
            case "my_paper":
            case "summary_list":
                if (entity instanceof SubscribeEntity) {
                    itemClickAdapter.loadMoreComplete();
                    if (itemClickAdapter == null || ((SubscribeEntity) entity).getPageInfo() == null || ((SubscribeEntity) entity).getPageInfo().getRows() == null) {
                        itemClickAdapter.loadMoreEnd(false);
                        return;
                    }
                    if (page == 1) {
                        itemClickAdapter.setNewData(null);
                    }
                    SubscribeEntity.PageInfoBean pageInfo = ((SubscribeEntity) entity).getPageInfo();
                    if (pageInfo != null) {
                        List<SubscribeEntity.PageInfoBean.RowsBeanXXXXX> rows = pageInfo.getRows();
                        if (rows != null && rows.size() > 0) {
                            for (SubscribeEntity.PageInfoBean.RowsBeanXXXXX row : rows) {
                                itemClickAdapter.addData(new ClickEntity(row));
                            }
                            if (itemClickAdapter.getItemCount() < pageInfo.getTotal()) {
                                page++;
                            } else {
                                itemClickAdapter.loadMoreEnd(false);
                            }
                        } else {
                            itemClickAdapter.loadMoreEnd(false);
                        }

                    }
                } else if (entity.getResult() == 1) {
                    if (itemClickAdapter.getItemCount() > changePosition) {
                        ClickEntity item = itemClickAdapter.getItem(changePosition);
                        if (item != null && item.getSubscribeEntityRows() != null) {
                            SubscribeEntity.PageInfoBean.RowsBeanXXXXX subscribeEntityRows = item.getSubscribeEntityRows();
                            String isFollowed = "";
                            int followedNum = 0;
                            if (isSave == 10) {
                                isFollowed = "1";
                                followedNum = 1;
                            } else if (isSave == 20) {
                                isFollowed = "0";
                                followedNum = -1;
                            }
                            switch (version) {
                                case 0:
                                    if (subscribeEntityRows.getPhotoOne() != null && subscribeEntityRows.getPhotoOne().getRows() != null && subscribeEntityRows.getPhotoOne().getRows().size() == 1 && subscribeEntityRows.getPhotoOne().getRows().get(0) != null) {
                                        SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX rowsBeanXXXX = subscribeEntityRows.getPhotoOne().getRows().get(0);
                                        rowsBeanXXXX.setIsFollowed(isFollowed);
                                        rowsBeanXXXX.setFollowedNum(rowsBeanXXXX.getFollowedNum() + followedNum);
                                    }
                                    break;
                                case 1:
                                    if (subscribeEntityRows.getPhotoTwo() != null && subscribeEntityRows.getPhotoTwo().getRows() != null && subscribeEntityRows.getPhotoTwo().getRows().size() == 1 && subscribeEntityRows.getPhotoTwo().getRows().get(0) != null) {
                                        SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX rowsBeanXXXX = subscribeEntityRows.getPhotoTwo().getRows().get(0);
                                        rowsBeanXXXX.setIsFollowed(isFollowed);
                                        rowsBeanXXXX.setFollowedNum(rowsBeanXXXX.getFollowedNum() + followedNum);
                                    }
                                    break;
                                case 2:
                                    if (subscribeEntityRows.getPhotoThree() != null && subscribeEntityRows.getPhotoThree().getRows() != null && subscribeEntityRows.getPhotoThree().getRows().size() == 1 && subscribeEntityRows.getPhotoThree().getRows().get(0) != null) {
                                        SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX rowsBeanXXXX = subscribeEntityRows.getPhotoThree().getRows().get(0);
                                        rowsBeanXXXX.setIsFollowed(isFollowed);
                                        rowsBeanXXXX.setFollowedNum(rowsBeanXXXX.getFollowedNum() + followedNum);
                                    }
                                    break;
                            }
                            itemClickAdapter.notifyItemChanged(changePosition);
                        }
                    }
                    //                    onRefresh();
                }
                break;
            case "newest":
                break;
            case "notice":
            case "interactive":

                if (entity instanceof MsgEntity) {
                    itemClickAdapter.loadMoreComplete();
                    if (itemClickAdapter == null || ((MsgEntity) entity).getPageInfo() == null || ((MsgEntity) entity).getPageInfo().getRows() == null) {
                        itemClickAdapter.loadMoreEnd(false);
                        return;
                    }
                    if (page == 1) {
                        itemClickAdapter.setNewData(null);
                    }
                    MsgEntity.PageInfoBean pageInfo = ((MsgEntity) entity).getPageInfo();
                    if (pageInfo != null) {
                        List<MsgEntity.PageInfoBean.RowsBean> rows = pageInfo.getRows();
                        if (rows != null && rows.size() > 0) {
                            for (MsgEntity.PageInfoBean.RowsBean row : rows) {
                                itemClickAdapter.addData(new ClickEntity(row));
                            }
                            if (itemClickAdapter.getItemCount() < pageInfo.getTotal()) {
                                page++;
                            } else {
                                itemClickAdapter.loadMoreEnd(false);
                            }
                        } else {
                            itemClickAdapter.loadMoreEnd(false);
                        }


                    }
                }
                break;
            case "answer":
                if (entity instanceof AnswerEntity) {
                    itemClickAdapter.loadMoreComplete();
                    if (itemClickAdapter == null || ((AnswerEntity) entity).getAnswer() == null || ((AnswerEntity) entity).getAnswer().getRows() == null) {
                        itemClickAdapter.loadMoreEnd(false);
                        return;
                    }

                    AnswerBean answer = ((AnswerEntity) entity).getAnswer();
                    if (answer != null) {
                        if (page == 1) {
                            itemClickAdapter.setNewData(null);
                        }
                        List<AnswerQuizRowsBean> rows = answer.getRows();
                        if (rows != null && rows.size() > 0) {
                            for (AnswerQuizRowsBean row : rows) {
                                itemClickAdapter.addData(new ClickEntity(row));
                            }
                            if (itemClickAdapter.getItemCount() < answer.getTotal()) {
                                page++;
                            } else {
                                itemClickAdapter.loadMoreEnd(false);
                            }
                        } else {
                            itemClickAdapter.loadMoreEnd(false);
                        }
                    }
                }
                break;
            case "questions":
                if (entity instanceof QuizEntity) {
                    itemClickAdapter.loadMoreComplete();
                    if (itemClickAdapter == null || ((QuizEntity) entity).getQuiz() == null || ((QuizEntity) entity).getQuiz().getRows() == null) {
                        itemClickAdapter.loadMoreEnd(false);
                        return;
                    }
                    QuizBean answer = ((QuizEntity) entity).getQuiz();
                    if (answer != null) {
                        if (page == 1) {
                            itemClickAdapter.setNewData(null);
                        }
                        List<AnswerQUizXBean> rows = answer.getRows();
                        if (rows != null && rows.size() > 0) {
                            for (AnswerQUizXBean row : rows) {
                                itemClickAdapter.addData(new ClickEntity(row));
                            }
                            if (itemClickAdapter.getItemCount() < answer.getTotal()) {
                                page++;
                            } else {
                                itemClickAdapter.loadMoreEnd(false);
                            }
                        } else {
                            itemClickAdapter.loadMoreEnd(false);
                        }

                    }
                }
                break;
            case "search_hint":
                if (entity instanceof SearchHistoryEntity) {

                    itemClickAdapter.setNewData(null);

                    SearchHistoryEntity.MySearchBean mySearch = ((SearchHistoryEntity) entity).getMySearch();
                    List<SearchHistoryEntity.MySearchBean.RowsBeanX> mySearchRows = mySearch.getRows();
                    SearchHistoryEntity.OtherSearchBean otherSearch = ((SearchHistoryEntity) entity).getOtherSearch();
                    List<SearchHistoryEntity.OtherSearchBean.RowsBean> otherRows = otherSearch.getRows();
                    List<String> stringList = new ArrayList<>();
                    for (SearchHistoryEntity.MySearchBean.RowsBeanX mySearchRow : mySearchRows) {
                        stringList.add(mySearchRow.getContent());
                    }
                    if (stringList.size() > 0)
                        itemClickAdapter.addData(new ClickEntity(getString(R.string.you_search), getString(R.string.empty), stringList));
                    List<String> stringList2 = new ArrayList<>();
                    for (SearchHistoryEntity.OtherSearchBean.RowsBean otherRow : otherRows) {
                        stringList2.add(otherRow.getContent());
                    }
                    if (stringList2.size() > 0)
                        this.itemClickAdapter.addData(new ClickEntity(getString(R.string.everyone_search), getString(R.string.in_a_batch), stringList2));


                } else {
                    Utils.showToast(entity.getInfo());
                    itemClickAdapter.remove(0);
                }
                break;
            case "search_result":
                if (entity instanceof HomeSearchEntity) {
                    itemClickAdapter.setNewData(null);
                    HomeSearchEntity.PaperBean paper = ((HomeSearchEntity) entity).getPaper();
                    HomeSearchEntity.ScholarBean scholar = ((HomeSearchEntity) entity).getScholar();
                    if (scholar != null && scholar.getRows() != null && scholar.getRows().size() > 0) {
                        ClickEntity clickEntity = new ClickEntity(getString(R.string.list_of_scholars), getString(R.string.more_scholars));
                        clickEntity.setScholarBean(scholar);
                        itemClickAdapter.addData(clickEntity);
                    }
                    if (paper != null && paper.getRows() != null && paper.getRows().size() > 0) {
                        ClickEntity clickEntity = new ClickEntity(getString(R.string.paper_list), getString(R.string.more_papers));
                        clickEntity.setPaperBean(paper);
                        itemClickAdapter.addData(clickEntity);
                    }

                } else {
                    onRefresh();
                }
                break;
            case "search_scholar":
                break;

            case "search_paper":
                break;
            case "mine_scholar":
            case "mine_follow":
                if (entity instanceof FansConcernedEntity) {
                    itemClickAdapter.loadMoreComplete();
                    if (((FansConcernedEntity) entity).getPageInfo() == null) {
                        itemClickAdapter.loadMoreEnd(false);
                        return;
                    }
                    if (page == 1) {
                        itemClickAdapter.setNewData(null);
                    }
                    List<FansConcernedEntity.PageInfoBean.RowsBean> rows = ((FansConcernedEntity) entity).getPageInfo().getRows();

                    if (rows != null && rows.size() > 0) {
                        for (FansConcernedEntity.PageInfoBean.RowsBean row : rows) {
                            itemClickAdapter.addData(new ClickEntity(row));
                        }
                        if (itemClickAdapter.getItemCount() < ((FansConcernedEntity) entity).getPageInfo().getTotal()) {
                            page++;
                        } else {
                            itemClickAdapter.loadMoreEnd(false);
                        }
                    } else {
                        itemClickAdapter.loadMoreEnd(false);
                    }


                } else {
                    //
                    onRefresh();
                }
                break;
            case "scholar_list":

                if (entity instanceof ScholarEntity) {
                    itemClickAdapter.loadMoreComplete();
                    List<MyScholarBean> scholar = ((ScholarEntity) entity).getScholar();
                    if (scholar != null && scholar.size() > 0) {
                        if (page == 1) {
                            itemClickAdapter.setNewData(null);
                        }
                        for (MyScholarBean myScholarBean : scholar) {
                            itemClickAdapter.addData(new ClickEntity(myScholarBean));
                        }
                        if (((ScholarEntity) entity).getTotal() > itemClickAdapter.getItemCount()) {
                            page++;
                        } else {
                            itemClickAdapter.loadMoreEnd(false);
                        }
                    } else {
                        itemClickAdapter.loadMoreEnd(false);
                    }


                }
                break;
            default:
                break;

        }
    }

    private void initEntity(SubscribeEntity.PageInfoBean.RowsBeanXXXXX subscribeEntityRows, ClickEntity clickEntity0, List<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX> photoThreeRows, SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX rowsBeanXXXX, String version) {
        if (rowsBeanXXXX != null && rowsBeanXXXX.getRecordOne() != null && rowsBeanXXXX.getRecordTwo() != null) {
            ClickEntity clickEntityCN = new ClickEntity();
            clickEntityCN.setPhotoRecord(subscribeEntityRows.getTitle());
            clickEntityCN.setPhotoOrder(subscribeEntityRows.getCreateTime());
            clickEntityCN.setTitle(subscribeEntityRows.getTitle());
            clickEntityCN.setTime(photoThreeRows.get(0).getCreateTime());
            clickEntityCN.setVersion(version);
            if (rowsBeanXXXX.getPhotos() != null && rowsBeanXXXX.getPhotos().getRows() != null)
                clickEntityCN.setPhotoNumber(rowsBeanXXXX.getPhotos().getRows().size());
            if (rowsBeanXXXX.getRecordOne() != null && rowsBeanXXXX.getRecordOne().getRows() != null)
                clickEntityCN.setRecordNumber(rowsBeanXXXX.getRecordOne().getRows().size());
            if (rowsBeanXXXX.getRecordTwo() != null && rowsBeanXXXX.getRecordTwo().getRows() != null)
                clickEntityCN.setRecordTwoNumber(rowsBeanXXXX.getRecordTwo().getRows().size());
            clickEntityCN.setItemType(1);
            clickEntityCN.setLevel(1);
            clickEntityCN.setPaperBan(rowsBeanXXXX);
            clickEntityCN.setUrl(rowsBeanXXXX.getFirstPic());
            clickEntityCN.setLanguage("CN");
            clickEntityCN.setPaperId(subscribeEntityRows.getId() + "");
            clickEntity0.addSubItem(clickEntityCN);


            ClickEntity clickEntityEN = new ClickEntity();
            clickEntityEN.setPhotoRecord(subscribeEntityRows.getTitle());
            clickEntityEN.setPhotoOrder(subscribeEntityRows.getCreateTime());
            clickEntityEN.setTitle(subscribeEntityRows.getTitle());
            clickEntityEN.setTime(photoThreeRows.get(0).getCreateTime());
            if (rowsBeanXXXX.getPhotos() != null && rowsBeanXXXX.getPhotos().getRows() != null)
                clickEntityEN.setPhotoNumber(rowsBeanXXXX.getPhotos().getRows().size());
            if (rowsBeanXXXX.getRecordOne() != null && rowsBeanXXXX.getRecordOne().getRows() != null)
                clickEntityEN.setRecordTwoNumber(rowsBeanXXXX.getRecordOne().getRows().size());
            if (rowsBeanXXXX.getRecordTwo() != null && rowsBeanXXXX.getRecordTwo().getRows() != null)
                clickEntityEN.setRecordNumber(rowsBeanXXXX.getRecordTwo().getRows().size());
            clickEntityEN.setItemType(1);
            clickEntityEN.setVersion(version);
            clickEntityEN.setLevel(1);
            clickEntityEN.setPaperBan(rowsBeanXXXX);
            clickEntityEN.setUrl(rowsBeanXXXX.getFirstPic());
            clickEntityEN.setLanguage("EN");
            clickEntityEN.setPaperId(subscribeEntityRows.getId() + "");
            clickEntity0.addSubItem(clickEntityEN);
        }
    }

    @Override
    public void onFailure(String msg, Exception ex) {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
        if (itemClickAdapter != null)
            itemClickAdapter.loadMoreFail();
        if (dubbingAdapter != null)
            dubbingAdapter.loadMoreFail();
        super.onFailure(msg, ex);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (type) {

            case "he":
                break;
            case "scholar":
                FragmentActivity.actionActivity(getContext(), "he");
                break;
            case "plays":
            case "play":
                ClickEntity playsItem = itemClickAdapter.getItem(position);
                if (playsItem != null && playsItem.getNote() != null) {
                    String id = playsItem.getNote().getId();

                    String version = playsItem.getNote().getVersions();

                    String paperId = id; // 取消 版本拼接 + version
                    ArrayList<String> list = new ArrayList<>();
                    list.add(version);
                    if (list.size() > 0 && !TextUtils.isEmpty(paperId))
                        PaperDetailsActivity.actionActivity(getContext(), paperId, list, type);
                }
                break;
            case "paper_qa":

                break;
            case "paper_literature":
                break;
            case "paper_label":
                break;

            case "scholar_list":
                ClickEntity scholarList = itemClickAdapter.getItem(position);
                if (scholarList != null) {
                    String oid = scholarList.getMyScholarBean().getId() + "";
                    if (!TextUtils.isEmpty(oid))
                        FragmentActivity.actionActivity(getContext(), "he", oid);
                }
                break;
            case "paper_download":
                ClickEntity paperDownloadItem = mDragAdapter.getItem(position);
                if (paperDownloadItem != null) {
                    String title = paperDownloadItem.getTitle();
                    List<ClickEntity> subItems = paperDownloadItem.getSubItems();
                    ArrayList<String> recordOneRows = new ArrayList<>();
                    ArrayList<String> recordTwoRows = new ArrayList<>();
                    ArrayList<String> photoRows = new ArrayList<>();
                    for (ClickEntity subItem : subItems) {
                        List<ClickEntity> subItems1 = subItem.getSubItems();
                        for (ClickEntity clickEntity : subItems1) {
                            if (clickEntity.getPhotoRecord().equals("appInfo")) {
                                PackageUtil.installApkNormal(getActivity(), clickEntity.record.getSavePath() + "/" + clickEntity.record.getSaveName());
                            } else if (clickEntity.getPhotoRecord().equals("photo")) {
                                photoRows.add(clickEntity.record.getSavePath() + "/" + clickEntity.record.getSaveName());
                            } else {
                                if (clickEntity.record.getExtra5().equals("CN")) {
                                    recordOneRows.add(clickEntity.record.getSavePath() + "/" + clickEntity.record.getSaveName());
                                } else if ((clickEntity.record.getExtra5().equals("EN"))) {
                                    recordTwoRows.add(clickEntity.record.getSavePath() + "/" + clickEntity.record.getSaveName());
                                }
                            }
                        }

                    }
                    if (paperDownloadItem.getPaperId() != null && photoRows.size() > 0 && (recordOneRows.size() > 0 || recordTwoRows.size() > 0)) {
                        PaperDetailsActivity.actionActivity(getContext(), paperDownloadItem.getPaperId(), recordOneRows, recordTwoRows, photoRows, "download_paper", title);
                    }
                }

                break;
            case "summary_download":
                //                PaperDetailsActivity.actionActivity(getContext());
                break;
            case "download":
                if (expandableItemAdapter.getItemCount() > position) {
                    ClickEntity clickEntity = expandableItemAdapter.getData().get(position);
                    final TextView size = (TextView) view.findViewById(R.id.tv_download_size);
                    final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.pb_download_progress);
                    if (clickEntity.record == null)
                        return;
                    final String url = clickEntity.record.getUrl();
                    if (TextUtils.isEmpty(url))
                        return;
                    Log.i("--->>", "onItemClick: " + clickEntity.record.getUrl());
                    DownloadController mDownloadController = clickEntity.getDownloadController();
                    Log.i("--->>", "onItemClick: " + mDownloadController);
                    if (mDownloadController != null)
                        mDownloadController.handleClick(new DownloadController.Callback() {
                            @Override
                            public void startDownload() {
                                start(url);
                            }

                            @Override
                            public void pauseDownload() {
                                pause(url);
                                Utils.showToast(R.string.paused);
                            }

                            @Override
                            public void install() {
                                //                                        loadDownloadData();
                                //                        installApk();
                                Utils.showToast(R.string.download_completes);
                            }
                        });
                }
                break;

            case "release_paper":
                //                PaperDetailsActivity.actionActivity(getContext());
                break;
    /*        case "collection_paper":
            case "collection_summary":
                ClickEntity itemCollection = itemClickAdapter.getItem(position);
                if (itemCollection != null) {
                    CollectionEntity.PageInfoBean.RowsBean collectionEntity = itemCollection.getCollectionEntity();
                    if (collectionEntity != null)
                        PaperDetailsActivity.actionActivity(getContext(), collectionEntity.getId(), type);

                }

                break;*/
            case "popular":
                //                PaperDetailsActivity.actionActivity(getContext());
                break;
            case "wait_dubbing":
                ClickEntity dubbing = dubbingAdapter.getItem(position);
                if (dubbing != null) {
                    SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX paperBan = dubbing.getPaperBan();
                    if (paperBan != null) {
                        ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.PhotosBean.RowsBeanXX> photoRows = (ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.PhotosBean.RowsBeanXX>) paperBan.getPhotos().getRows();
                        ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.RecordOneBean.RowsBeanXXX> recordOneRows = (ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.RecordOneBean.RowsBeanXXX>) paperBan.getRecordOne().getRows();
                        ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.RecordOneBean.RowsBeanXXX> recordTwoRows = (ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.RecordOneBean.RowsBeanXXX>) paperBan.getRecordTwo().getRows();
                        switch (dubbing.getLanguage()) {
                            case "CN":
                                if (photoRows != null && recordOneRows != null && photoRows.size() > 0 && !TextUtils.isEmpty(paperBan.getId())) {
                                    if (photoRows.size() != recordOneRows.size()) {
                                        DubbingActivity.actionActivity(getActivity(), paperBan.getId(), photoRows, recordOneRows, 0, type);
                                    }
                                }
                                break;
                            case "EN":
                                if (photoRows != null && recordTwoRows != null) {
                                    if (photoRows.size() != recordTwoRows.size() && photoRows.size() > 0 && !TextUtils.isEmpty(paperBan.getId())) {
                                        DubbingActivity.actionActivity(getActivity(), paperBan.getId(), photoRows, recordTwoRows, 1, type);
                                    }
                                }
                                break;
                        }

                    }
                }

                break;
            case "collection":
                //                PaperDetailsActivity.actionActivity(getContext());
                break;
            case "subscribe":
            case "paper_list":
            case "summary_list":
            case "my_review":
            case "my_paper":
            case "collection_paper":
            case "collection_summary":
                ClickEntity item = itemClickAdapter.getItem(position);
                if (item != null && item.getSubscribeEntityRows() != null) {
                    SubscribeEntity.PageInfoBean.RowsBeanXXXXX subscribeEntityRows = item.getSubscribeEntityRows();
                    String id = subscribeEntityRows.getId();
                    SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean photoOne = subscribeEntityRows.getPhotoOne();
                    SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean photoTwo = subscribeEntityRows.getPhotoTwo();
                    SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean photoThree = subscribeEntityRows.getPhotoThree();
                    ArrayList<String> version = new ArrayList<>();

                    if (photoOne != null && photoOne.getRows() != null && photoOne.getRows().size() > 0) {
                        version.add("0");
                    }
                    if (photoTwo != null && photoTwo.getRows() != null && photoTwo.getRows().size() > 0) {
                        version.add("1");
                    }
                    if (photoThree != null && photoThree.getRows() != null && photoThree.getRows().size() > 0) {
                        version.add("2");
                    }
                    if (version.size() > 0 && !TextUtils.isEmpty(id)) {
                        if (null != subscribeEntityRows.getIsEncrypt() && subscribeEntityRows.getIsEncrypt().equals("0")) { // TODO checkpwd
                            PaperDetailsActivity.checkPwd(getContext(), id, version, "online_paper");
                        } else {
                            PaperDetailsActivity.actionActivity(getContext(), id, version, "online_paper");
                        }
                    }

                }
                break;
            case "newest":
                //                PaperDetailsActivity.actionActivity(getContext());
                break;
            case "notice":


                break;
            case "interactive":

                break;
            case "answer":
                ClickEntity itemAnswer = itemClickAdapter.getItem(position);
                if (itemAnswer != null) {
                    AnswerQuizRowsBean answerQuizRowsBean = itemAnswer.getAnswerQuizRowsBean();
                    String id = answerQuizRowsBean.getPbid();
                    String version = answerQuizRowsBean.getVersion();
                    ArrayList<String> list = new ArrayList<>();
                    if (!TextUtils.isEmpty(version))
                        list.add(version);
                    if (list.size() > 0 && !TextUtils.isEmpty(id))
                        PaperDetailsActivity.actionActivity(getContext(), id, list, type);
                }
                break;
            case "questions":
                ClickEntity itemQuestions = itemClickAdapter.getItem(position);
                if (itemQuestions != null) {

                    AnswerQUizXBean answerQUizXBean = itemQuestions.getAnswerQUizXBean();
                    String id = answerQUizXBean.getPbid();
                    String version = answerQUizXBean.getVersion();
                    ArrayList<String> list = new ArrayList<>();
                    if (!TextUtils.isEmpty(version))
                        list.add(version);
                    if (list.size() > 0 && !TextUtils.isEmpty(id))
                        PaperDetailsActivity.actionActivity(getContext(), id, list, type);
                }
                break;
            case "search_hint":
                break;
            case "search_result":

                break;
            case "search_scholar":
                break;
            case "search_paper":
                //                if ()
                //                PaperDetailsActivity.actionActivity(getContext());
                break;
            default:
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        final ArrayMap<String, Object> map = new ArrayMap<>();
//        map.put("token", token);
        switch (view.getId()) {
            case R.id.tv_paper_collected:
                ClickEntity clickEntityCollected = itemClickAdapter.getItem(position);
                if (clickEntityCollected != null && clickEntityCollected.getSubscribeEntityRows() != null) {
                    SubscribeEntity.PageInfoBean.RowsBeanXXXXX subscribeEntityRows = clickEntityCollected.getSubscribeEntityRows();
                    String id = subscribeEntityRows.getId();
                    if (TextUtils.isEmpty(id)) {


                     /*   ArrayMap<String, Object> mapCancel = new ArrayMap<>();
                        mapCancel.put("", );
                        mapCancel.put("", );
                        mapCancel.put("", );
                        mapCancel.put("", );
                        presenter.getCancelFollowPaperPictureData();*/
                    }
                }
                //
                break;
            case R.id.tv_voice:
                ClickEntity clickEntity = itemClickAdapter.getItem(position);
                if (clickEntity != null) {
                    QAEntity.QuizInfoBean.RowsBeanXXXX quizInfoBean = clickEntity.getQuizInfoBean();
                    if (quizInfoBean != null && quizInfoBean.getAnswer() != null && quizInfoBean.getAnswer().getRows() != null && quizInfoBean.getAnswer().getRows().size() > 0) {
                        filePath = quizInfoBean.getAnswer().getRows().get(0).getRecord();
                        tvVoice = (TextView) view;
                        togglePlaying(view);
                    }
                }
                break;
            case R.id.tv_voice_two:
                ClickEntity clickEntityTwo = itemClickAdapter.getItem(position);
                if (clickEntityTwo != null) {
                    QAEntity.QuizInfoBean.RowsBeanXXXX quizInfoBean = clickEntityTwo.getQuizInfoBean();
                    if (quizInfoBean != null && quizInfoBean.getAnswerTwo() != null && quizInfoBean.getAnswerTwo().getRows() != null && quizInfoBean.getAnswerTwo().getRows().size() > 0) {
                        filePath = quizInfoBean.getAnswerTwo().getRows().get(0).getRecord();
                        tvVoice = (TextView) view;
                        togglePlaying(view);
                    }
                }
                break;
            case R.id.tv_more:
                //                           itemClickAdapter.getItem()
                break;

            case R.id.tv_release_dubbing:  //  发布

                if (dubbingAdapter != null) {
                    ClickEntity item = dubbingAdapter.getItem(position);
                    if (item != null) {
                        String paperId = item.getPaperId();
                        if (!TextUtils.isEmpty(paperId)) {
                            String id = paperId;
                            String version = item.getVersion();
                            int photoNumber = item.getPhotoNumber();
                            int recordNumber = item.getRecordNumber();
                            int recordTwoNumber = item.getRecordTwoNumber();
                            if (!TextUtils.isEmpty(version) && photoNumber != 0 && !TextUtils.isEmpty(id) && recordNumber != 0) {
                                if (recordTwoNumber == 0) {
                                    map.put("id", id);
                                    map.put("uid", uid);
                                    map.put("version", version);
                                    presenter.getUpdatePaperData(map);
                                    Utils.showToast(R.string.version_only_language);
                                } else if (recordTwoNumber == photoNumber) {
                                    map.put("id", id);
                                    map.put("uid", uid);
                                    map.put("version", version);
                                    presenter.getUpdatePaperData(map);
                                } else {
                                    Utils.showToast(R.string.unfinished);
                                }

                            }
                        }

                    }
                }

                break;
            case R.id.tv_preview_dubbing: //  预览按钮
                if (dubbingAdapter != null) {
                    ClickEntity item = dubbingAdapter.getItem(position);
                    if (item != null) {
                        String paperId = item.getPaperId();
                        if (!TextUtils.isEmpty(paperId)) {
                            String id = paperId;
                            String version = item.getVersion();
                            ArrayList<String> list = new ArrayList<>();
                            list.add(version);
                            if (!TextUtils.isEmpty(id) && list.size() > 0)
                                PaperDetailsActivity.actionActivity(getContext(), id, list, type);
                        }

                    }
                }
                break;
            case R.id.tv_edit_dubbing: //  编辑
                if (dubbingAdapter == null)
                    return;
                ClickEntity dubbing = dubbingAdapter.getItem(position);
                if (dubbing != null) {
                    SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX paperBan = dubbing.getPaperBan();
                    if (paperBan != null) {
                        ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.PhotosBean.RowsBeanXX> photoRows = (ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.PhotosBean.RowsBeanXX>) paperBan.getPhotos().getRows();
                        ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.RecordOneBean.RowsBeanXXX> recordOneRows = (ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.RecordOneBean.RowsBeanXXX>) paperBan.getRecordOne().getRows();
                        ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.RecordOneBean.RowsBeanXXX> recordTwoRows = (ArrayList<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.RecordOneBean.RowsBeanXXX>) paperBan.getRecordTwo().getRows();
                        switch (dubbing.getLanguage()) {
                            case "CN":
                                if (photoRows != null && recordOneRows != null && photoRows.size() > 0) {
                                    if (photoRows.size() == recordOneRows.size()) {
                                        DubbingActivity.actionActivity(getActivity(), paperBan.getId(), photoRows, recordOneRows, 0, "edit_dubbing");
                                    }
                                }
                                break;
                            case "EN":
                                if (photoRows != null && recordTwoRows != null && photoRows.size() > 0) {
                                    if (photoRows.size() == recordTwoRows.size()) {
                                        DubbingActivity.actionActivity(getActivity(), paperBan.getId(), photoRows, recordTwoRows, 1, "edit_dubbing");
                                    }
                                }
                                break;
                        }

                    }
                }
                //                           itemClickAdapter.getItem()
                break;
            case R.id.tv_empty:
                ClickEntity item = itemClickAdapter.getItem(position);

                if (item != null) {
                    if (Objects.equals(item.getString(), getString(R.string.you_search))) {
                        presenter.getCleanHistoryData(map);
                    } else if (Objects.equals(item.getString(), getString(R.string.everyone_search))) {
                        map.put("uid", uid);
                        map.put("page", 1);
                        presenter.getSearchHistoryData(map);
                    }

                }
                break;
            case R.id.tv_paper_number:
            case R.id.tv_paper:
            case R.id.tv_review_number:
            case R.id.tv_review:
            case R.id.tv_follow_number:
            case R.id.tv_follow:
            case R.id.tv_fans_number:
            case R.id.tv_fans:
                ClickEntity itemHe = multipleItemQuickAdapter.getItem(position);
                if (itemHe != null) {
                    UserInfoEntity userInfoEntity = itemHe.getUserInfoEntity();
                    if (userInfoEntity != null && userInfoEntity.getIsFollowed() != null && userInfoEntity.getUserInfo() != null) {
                        UserInfoEntity.UserInfoBean userInfo = userInfoEntity.getUserInfo();
                        final String id = userInfo.getId();
                        if ("".equals(id))
                            return;
                        switch (view.getId()) {
                            case R.id.tv_paper_number:
                            case R.id.tv_paper:
                                Utils.showToast(R.string.temporarily_not_opened);
                                //                                FragmentActivity.actionActivity(getContext(), "my_paper", id + "");
                                break;
                            case R.id.tv_review_number:
                            case R.id.tv_review:
                                Utils.showToast(R.string.temporarily_not_opened);
                                //                                FragmentActivity.actionActivity(getContext(), "my_review", id + "");
                                break;
                            case R.id.tv_follow_number:
                            case R.id.tv_follow:
                                FragmentActivity.actionActivity(getContext(), "mine_follow", id + "");
                                break;
                            case R.id.tv_fans_number:
                            case R.id.tv_fans:
                                FragmentActivity.actionActivity(getContext(), "mine_scholar", id + "");
                        }
                    }
                }

                break;
            case R.id.iv_attention: // TODO  iv_attention
                ClickEntity item1 = multipleItemQuickAdapter.getItem(position);
                if (BaseActivity.uid.equals("15")) {
                    AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(getActivity());
                    normalDialog.setIcon(null);
                    normalDialog.setTitle(R.string.need_login);
                    normalDialog.setPositiveButton(R.string.register,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    LogInActivity.actionActivity(getActivity());
                                }
                            });
                    normalDialog.setNegativeButton(R.string.cancel, null);
                    // 显示
                    normalDialog.show();
                } else if (item1 != null) {
                    UserInfoEntity userInfoEntity = item1.getUserInfoEntity();
                    if (userInfoEntity != null && userInfoEntity.getIsFollowed() != null && userInfoEntity.getUserInfo() != null) {
                        UserInfoEntity.UserInfoBean userInfo = userInfoEntity.getUserInfo();
                        final String id = userInfo.getId();
//                        map.put("token", token);
                        map.put("oid", id);
                        map.put("uid", uid);
                        switch (userInfoEntity.getIsFollowed()) { // TODO getIsFollowed 1 未关注。0以关注
                            case "1":
                                presenter.getSaveFollowUserData(map);
                                break;
                            case "0":
                                Utils.showDialog((BaseActivity) getActivity(), getString(R.string.determine_cancel_attention), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ArrayMap<String, Object> map = new ArrayMap<>();
//                                        map.put("token", token);
                                        map.put("uid", uid);
                                        map.put("oid", id);
                                        presenter.getCancelFollowUserData(map);
                                    }
                                });

                                break;

                        }


                    }

                }

                //                                presenter.getCancelFollowUserData(map);
                break;
        }
    }

    @Override
    public void addDownload() {
        Log.i("--->>", "addDownload: ");
        String text = tvDownloadNumber.getText().toString();
        Integer integer;
        if (TextUtils.isEmpty(text)) {
            integer = 1;
        } else {
            integer = Integer.valueOf(text) + 1;
        }
        tvDownloadNumber.setText(String.valueOf(integer));
        super.addDownload();
    }

    @Override
    public void downloadCompleted() {
        super.downloadCompleted();
        loadDownloadData();
        loadDownloadNunber();
    }

    private void showNormalDialog(final String id, final int position) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题 Attempt to invoke virtual method 'android.content.res.Resources$Theme' on a null object reference
	at android.app.AlertDialog.resolveDialogTheme(AlertDialog.java:225)
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        if (getContext() == null)
            return;
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(getContext());
        normalDialog.setIcon(null);
        normalDialog.setTitle(R.string.confirm_delete_this_entry);
        normalDialog.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DaoSession daoSessions = BaseApp.app.getDaoSession();
                        NoteDao noteDaos = daoSessions.getNoteDao();
//                        noteDaos.deleteByKey(id);
                        loadDownloadData();
                        itemClickAdapter.remove(position);
                    }
                });
        normalDialog.setNegativeButton(R.string.cancel, null);
        // 显示
        normalDialog.show();
    }

    private void showDownloadDialog(final ClickEntity itemDownload, final int position) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题 Attempt to invoke virtual method 'android.content.res.Resources$Theme' on a null object reference
	at android.app.AlertDialog.resolveDialogTheme(AlertDialog.java:225)
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        if (getContext() == null)
            return;

        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getContext());
        normalDialog.setIcon(null);
        normalDialog.setTitle(R.string.confirm_delete_this_entry);
        normalDialog.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String url = itemDownload.getPaperId();


                        mRxDownload.deleteAll(url, true).subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(@NonNull Object o) throws Exception {
                                //                            Utils.showToast(url);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {

                            }
                        });
                        List<ClickEntity> subItems = itemDownload.getSubItems();
                        if (subItems != null && subItems.size() > 0) {
                            for (ClickEntity subItem : subItems) {

                                mRxDownload.deleteAll(subItem.getString(), true).subscribe(new Consumer<Object>() {
                                    @Override
                                    public void accept(@NonNull Object o) throws Exception {
                                        //                                Utils.showToast(url);
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(@NonNull Throwable throwable) throws Exception {

                                    }
                                });
                            }
                        }
                        mDragAdapter.remove(position);

                    }
                });
        normalDialog.setNegativeButton(R.string.cancel, null);
        // 显示
        normalDialog.show();
    }

    private void showPlayDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题 Attempt to invoke virtual method 'android.content.res.Resources$Theme' on a null object reference
	at android.app.AlertDialog.resolveDialogTheme(AlertDialog.java:225)
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        if (getContext() == null)
            return;

        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getContext());
        normalDialog.setIcon(null);
        normalDialog.setTitle(R.string.sure_delete_alldata);
        normalDialog.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DaoSession daoSessions = BaseApp.app.getDaoSession();
                        NoteDao noteDaos = daoSessions.getNoteDao();
                        Query<Note> notesQuery = noteDaos.queryBuilder().where(NoteDao.Properties.Type.eq(type)).orderAsc(NoteDao.Properties.Id).build();
                        List<Note> listnote = notesQuery.list();
                        for (Note note : listnote) {
                            noteDaos.delete(note);
                        }
                        initNetwork();
                    }
                });
        normalDialog.setNegativeButton(R.string.cancel, null);
        // 显示
        normalDialog.show();
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        switch (type) {
            case "plays":
            case "play":
                ClickEntity item = (ClickEntity) adapter.getItem(position);
                if (item != null) {
                    Note note = item.getNote();
                    if (note != null) {
                        String id = note.getId();
                        showNormalDialog(id, position);
                    }
                }
                break;
            case "paper_download":
                ClickEntity itemDownload = mDragAdapter.getItem(position);
                if (itemDownload != null) {
                    showDownloadDialog(itemDownload, position);

                }
                break;
        }

        return false;
    }

    @Override
    public void onRefresh() {
        if (itemClickAdapter != null)
            itemClickAdapter.setEnableLoadMore(false);
        if (dubbingAdapter != null)
            dubbingAdapter.setEnableLoadMore(false);
        page = 1;
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(true);
        initNetwork();
    }

    @Override
    public void onLoadMoreRequested() {
        initNetwork();
    }

    protected void initpaly() {
        audioPlayer = new AudioPlayer(getContext(), new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case AudioPlayer.HANDLER_CUR_TIME://更新的时间
                        tvVoice.setText(Util.formatSecond((int) msg.obj / 1000));
                        break;
                    case AudioPlayer.HANDLER_COMPLETE://播放结束
                        break;
                    case AudioPlayer.HANDLER_PREPARED://播放开始
                        break;
                    case AudioPlayer.HANDLER_ERROR://播放错误
                        //                        resolveResetPlay();
                        break;
                }
                return false;
            }
        }));
    }

    /**
     * 播放
     */
    protected void resolvePlayRecord() {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }

        if (isPause) {
            mIsPlay = true;
            audioPlayer.play();
        } else {
            mIsPlay = true;
            android.util.Log.i("--->>", "resolvePlayRecord: " + filePath);
            audioPlayer.playUrl(filePath);
        }
    }

    protected void resolvePausePlayRecord() {

        mIsPlay = false;
        isPause = true;
        audioPlayer.pause();
    }

    public void togglePlaying(View v) {
        Util.wait(100, new Runnable() {
            @Override
            public void run() {
                if (mIsPlay) {
                    if (Objects.equals(filePath, filePathTwo)) {
                        resolvePausePlayRecord();
                    } else {
                        resolvePlayRecord();
                    }
                    //                    tvDubbingDudition.setSelected(false);
                } else {
                    if (Objects.equals(filePath, filePathTwo)) {
                        resolvePlayRecord();
                    } else {
                        isPause = false;
                        resolvePlayRecord();
                    }
                    //                    tvDubbingDudition.setSelected(true);
                }
                filePathTwo = filePath;
            }
        });
    }

    public void submitFollow(String isFollowed, String id) {
        ArrayMap<String, Object> map = new ArrayMap<>();
//        map.put("token", token);
        map.put("uid", uid);
        map.put("oid", id);
        switch (isFollowed) {
            case "0":
                presenter.getSaveFollowUserData(map);
                break;
            case "1":
            case "2":
                presenter.getCancelFollowUserData(map);
                break;
        }
    }

    @Override
    public void collection(int id, int integer, final String type, String isFollowed, int parentPosition, int position) {
        changePosition = parentPosition;
        version = integer;
        final ArrayMap<String, Object> map = new ArrayMap<>();
        map.put("pbid", id);
        map.put("flag", 1);
        map.put("tabFlag", 1);
        map.put("uid", uid);
        map.put("version", integer);
      /*  Log.i(TAG, "collection: " + type);
        switch (type) {
            case "collection_paper":
            case "collection_summary":

                //pbid=1&flag=1&tabFlag=1
                presenter.getCancelFollowPaperPictureData(map);
                break;
            default:
                map.put("pbid", id);
                map.put("flag", 1);
                map.put("tabFlag", 1);
                map.put("uid", uid);
                map.put("version", integer);
                //pbid=1&flag=1&tabFlag=1
                presenter.getSaveFollowPaperPictureData(map);
                break;
        }*/
        switch (isFollowed) {
            case "0":
                isSave = 10;
                presenter.getSaveFollowPaperPictureData(map);
                break;
            case "1":
                Utils.showDialog((BaseActivity) getActivity(), getString(R.string.determine_cancel_collection), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        isSave = 20;
                        presenter.getCancelFollowPaperPictureData(map);

                    }
                });
                break;
        }
    }
}
