package com.giiisp.giiisp.view.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.api.UrlConstants;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseApp;
import com.giiisp.giiisp.entity.DaoSession;
import com.giiisp.giiisp.entity.Note;
import com.giiisp.giiisp.entity.NoteDao;
import com.giiisp.giiisp.entity.SubscribeEntity;
import com.giiisp.giiisp.utils.FloatingManager;
import com.giiisp.giiisp.utils.ImageLoader;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.LogInActivity;
import com.giiisp.giiisp.widget.DurationSizePopupWindow;
import com.tbruyelle.rxpermissions2.RxPermissions;


import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.db.DataBaseHelper;
import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadStatus;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.giiisp.giiisp.R.id.iv_add;

/**
 * 可扩展的适配器
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<ClickEntity, BaseViewHolder> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_PERSON = 2;
    private Fragment fragment;
    private String type;
    BaseActivity activity;
    private int type_level_0;
    private int type_level_1;
    private RxDownload mRxDownload;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data AnswerEntity new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(BaseActivity activity, int type_level_0, int type_level_1, List<ClickEntity> data, String type) {
        super(data);
        this.activity = activity;
        addItemType(TYPE_LEVEL_0, type_level_0);
        addItemType(TYPE_LEVEL_1, type_level_1);
        this.type_level_0 = type_level_0;
        this.type_level_1 = type_level_1;
        this.type = type;
        mRxDownload = RxDownload.getInstance(activity);
        //        addItemType(TYPE_PERSON, R.layout.item_download_download);
    }

    public ExpandableItemAdapter(BaseActivity activity, Fragment fragment, int type_level_0, int type_level_1, int type_level_2, List<ClickEntity> data, String type) {
        super(data);
        this.activity = activity;
        addItemType(TYPE_LEVEL_0, type_level_0);
        addItemType(TYPE_LEVEL_1, type_level_1);
        addItemType(TYPE_PERSON, type_level_2);
        this.type_level_0 = type_level_0;
        this.type_level_1 = type_level_1;
        this.type = type;
        this.fragment = fragment;
        mRxDownload = RxDownload.getInstance(activity);
        //        addItemType(TYPE_PERSON, R.layout.item_download_download);
    }


    @Override
    protected void convert(final BaseViewHolder holder, final ClickEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                switch (type_level_0) {
                    case R.layout.item_download_finished:
                        holder.setText(R.id.tv_title, item.getTitle());
                        holder.setText(R.id.tv_time, item.getTime());
                        holder.setVisible(R.id.iv_bg, !item.getPhotoRecord().equals("appInfo"));
                        holder.setVisible(R.id.iv_icon, !item.getPhotoRecord().equals("appInfo"));
                        holder.setVisible(R.id.linear_layout, !item.getPhotoRecord().equals("appInfo"));
                        holder.setVisible(R.id.view, !item.getPhotoRecord().equals("appInfo"));


                        ImageLoader.getInstance().displayCricleImage(activity, item.getUrl(), (ImageView) holder.getView(R.id.iv_icon));
                        if (!TextUtils.isEmpty(item.getVersion())) {
                            switch (item.getVersion()) {
                                case "0":
                                    holder.setText(R.id.tv_version, "完整版");
                                    break;
                                case "1":
                                    holder.setText(R.id.tv_version, "精华版");
                                    break;
                                case "2":
                                    holder.setText(R.id.tv_version, "摘要版");
                                    break;
                            }
                        }
                        List<ClickEntity> subItems = item.getSubItems();
                        if (subItems != null) {
                            StringBuilder buffer = new StringBuilder();
                            for (ClickEntity subItem : subItems) {
                                List<ClickEntity> subItems1 = subItem.getSubItems();
                                boolean download = true;
                                for (ClickEntity clickEntity : subItems1) {
                                    if (clickEntity.record.getFlag() != DownloadFlag.COMPLETED) {
                                        download = false;
                                    }
                                }
                                if (!download) {
                                    int photoNumber = subItem.getPhotoNumber();
                                    buffer.append(photoNumber);
                                    buffer.append(",");
                                }

                            }
                            if (buffer.length() > 0)
                                buffer.delete(buffer.length() - 1, buffer.length());
                            holder.setText(R.id.tv_download_page, "下载" + subItems.size() + "页");
                            holder.setText(R.id.tv_page_number, buffer.toString());

                        }
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = holder.getAdapterPosition();
                                Log.d(TAG, "Level 0 item pos: " + pos);
                                if (item.isExpanded()) {
                                    //                                    collapse(pos);
                                } else {
                                    //                            if (pos % 3 == 0) {
                                    //                                expandAll(pos, false);
                                    //                            } else {
                                    //                                    expand(pos);
                                    //                            }
                                }
                            }
                        });

                        break;
                    case R.layout.item_paper_child:

                        initPaperChild(holder, item);


                        break;
                    case R.layout.item_title_dubbing:
                        SubscribeEntity.PageInfoBean.RowsBeanXXXXX subscribeEntityRows = item.getSubscribeEntityRows();
                        if (subscribeEntityRows == null)
                            return;
                        holder.setText(R.id.tv_title, subscribeEntityRows.getTitle());
                        holder.setText(R.id.tv_time, subscribeEntityRows.getUpdateTime());

                        break;
                }
            /*    final ProgressBar progressBar = ;
                final TextView size = holder.getView(R.id.tv_download_size);
                updateProgressStatus(progressBar, downloadEvent.getDownloadStatus(), size);*/
                break;
            case TYPE_LEVEL_1:
                switch (type_level_1) {
                    case R.layout.item_waiting_dubbing:

                        if (!TextUtils.isEmpty(item.getVersion())) {
                            switch (item.getVersion()) {
                                case "0":
                                    holder.setText(R.id.tv_version, "完整版");
                                    holder.setText(R.id.tv_version_complete, "完整版");
                                    break;
                                case "1":
                                    holder.setText(R.id.tv_version, "精华版");
                                    holder.setText(R.id.tv_version_complete, "精华版");
                                    break;
                                case "2":
                                    holder.setText(R.id.tv_version, "摘要版");
                                    holder.setText(R.id.tv_version_complete, "摘要版");
                                    break;
                            }
                        }
                        ImageView ivIcon = holder.getView(R.id.iv_icon);
                        ProgressBar progressBar = holder.getView(R.id.progressbar_dubbing);
                        ImageLoader.getInstance().displayCricleImage(activity, item.getUrl(), ivIcon);
                        progressBar.setMax(item.getPhotoNumber());
                        progressBar.setProgress(item.getRecordNumber());
                        double rint = Math.rint((item.getRecordNumber() / (float) item.getPhotoNumber()) * 100);
                        holder.setText(R.id.tv_progress, String.valueOf(rint) + "%");

                        holder.setText(R.id.tv_language, item.getLanguage());
                        holder.setText(R.id.tv_language_complete, item.getLanguage());

                        holder.addOnClickListener(R.id.tv_preview_dubbing)
                                .addOnClickListener(R.id.tv_edit_dubbing)
                                .addOnClickListener(R.id.tv_release_dubbing);
                        holder.setVisible(R.id.ll_waiting_dubbing, !(item.getPhotoNumber() == item.getRecordNumber()));
                        holder.setVisible(R.id.ll_dubbing_complete, item.getPhotoNumber() == item.getRecordNumber());

                        break;
                    case R.layout.item_paper_page:

                     /*   holder.setText(R.id.tv_title, "图片id: " + item.getString() + "  " + holder.getLayoutPosition());
                        holder.setText(R.id.tv_photo_flag, "录音: " + initFlag(item.getRecordFlag()) + " 图片: " + initFlag(item.getPhotoFlag()));
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = holder.getAdapterPosition();
                                Log.d(TAG, "Level 1 item pos: " + pos);
                                if (item.isExpanded()) {
                                    //                                    collapse(pos, false);
                                } else {
                                    //                                    expand(pos, false);
                                }
                            }
                        });
                       *//* if (item.getRecordFlag() == DownloadFlag.COMPLETED && item.getPhotoFlag() == DownloadFlag.COMPLETED) {
                            int parentPosition = getParentPosition(item);
                            if (parentPosition != -1) {
                                ExpandableItemAdapter.this.notifyItemChanged(parentPosition);
                            }
                            Log.i("--->>", "convert: " + parentPosition);
                        }*/
                        break;
                    case R.layout.item_paper_child:
                        initPaperChild(holder, item);
                        break;
                    default:
                }
                break;

            case TYPE_PERSON:
                //                holder.setText(R.id.tv_title, item.record.getSaveName());

                if (item.record.getExtra4().contains("photo")) {
                    holder.setText(R.id.tv_version, "图片" + item.getPhotoOrder());
                } else if (item.record.getExtra4().contains("record")) {
                    if (item.getLanguage().equals("EN")) {
                        holder.setText(R.id.tv_version, "录音EN " + item.getPhotoOrder());
                    } else {
                        holder.setText(R.id.tv_version, "录音CN " + item.getPhotoOrder());
                    }
                } else {
                    holder.setText(R.id.tv_version, item.getVersion());
                }
                final int[] flag = new int[1];
                //                    f.setmDownloadController(mDownloadController);
              /*      helper.getView(R.id.tv_download_size).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });*/

                String url = item.record.getUrl();
                final int adapterPosition = holder.getAdapterPosition();
                ProgressBar progressBar = holder.getView(R.id.pb_download_progress);
                TextView size = holder.getView(R.id.tv_download_size);
                TextView downloadStatus = holder.getView(R.id.tv_download_status);
                ImageView downloadIcon = holder.getView(R.id.tv_download_icon);

                item.setProgressBar(progressBar);
                item.setTextView(size);
                item.setDownloadStatus(downloadStatus);
                item.setDownloadIcon(downloadIcon);
                item.setActivity(activity);
                item.setSelected(true);
         /*
                if (item.record.getFlag(

                ) != DownloadFlag.COMPLETED)
                    item.disposable = RxDownload.getInstance(activity).receiveDownloadStatus(url)
                            .subscribe(new Consumer<DownloadEvent>() {
                                int fa1 = -1;
                                int fa2 = -1;

                                @Override
                                public void accept(DownloadEvent downloadEvent) throws Exception {
                                    ProgressBar progressBar = holder.getView(R.id.pb_download_progress);
                                    TextView size = holder.getView(R.id.tv_download_size);
                                    DownloadController mDownloadController = new DownloadController((TextView) holder.getView(R.id.tv_download_status), (ImageView) holder.getView(R.id.tv_download_icon));
                                    String title = item.getPhotoRecord();
                                    item.setDownloadController(mDownloadController);

                                    if (flag[0] != downloadEvent.getFlag()) {
                                        flag[0] = downloadEvent.getFlag();
                                        log(flag[0] + "");
                                    }

                                    if (downloadEvent.getFlag() == DownloadFlag.FAILED) {
                                        Throwable throwable = downloadEvent.getError();
                                        Log.w("TAG", throwable);
                                    }
                                    Log.i("--->>", "RxDownload: " + holder.getLayoutPosition() + "    " + downloadEvent.getFlag());
                                    mDownloadController.setEvent(downloadEvent);
                                    updateProgressStatus(progressBar, downloadEvent.getDownloadStatus(), size);
                                    item.record.setFlag(downloadEvent.getFlag());

                             *//*   if (item.record.getUrl().contains(".mp3")) {
                                    item.getClickEntity().setRecordFlag(downloadEvent.getFlag());
                                } else {
                                    item.getClickEntity().setPhotoFlag(downloadEvent.getFlag());
                                }*//*
                                    fa1 = downloadEvent.getFlag();
                                    if (fa1 == DownloadFlag.COMPLETED && fa2 == DownloadFlag.STARTED) {
                                        if ("appInfo".equals(title)) {
                                            PackageUtil.installApkNormal(activity, item.record.getSavePath() + "/" + item.record.getSaveName());
                                        }
                                        int parentPosition = getParentPosition(item);
                                        if (parentPosition != -1) {
                                            ClickEntity parentItem = getItem(parentPosition);
                                            if (parentItem != null) {
                                                int position = getParentPosition(parentItem);
                                                List<ClickEntity> parentSubItems = parentItem.getSubItems();
                                                if (parentSubItems != null) {
                                                    boolean isdownload = false;
                                                    for (ClickEntity clickEntity : parentSubItems) {
                                                        if (clickEntity.record.getFlag() != DownloadFlag.COMPLETED) {
                                                            isdownload = true;
                                                            break;
                                                        }
                                                    }
                                                    parentItem.setSelected(isdownload);
                                                    if (!isdownload) {
                                                        collapse(parentPosition, false);
                                                        ExpandableItemAdapter.this.remove(parentPosition);
                                                        ExpandableItemAdapter.this.notifyItemChanged(position);
                                                    }

                                                }

                                                boolean isdownload = false;
                                                ClickEntity item1 = getItem(position);
                                                if (item1 != null) {
                                                    List<ClickEntity> subItems = item1.getSubItems();
                                                    for (ClickEntity subItem : subItems) {
                                                        if (subItem.isSelected()) {
                                                            isdownload = true;
                                                            break;
                                                        }
                                                    }
                                                    Log.i("--->>", "convert: " + isdownload + fragment);
                                                    if (!isdownload) {
                                                        activity.downloadCompleted();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    fa2 = fa1;
                                    //                                if (downloadEvent.getFlag() == DownloadFlag.COMPLETED)
                                    //                                    item.getClickEntity().removeSubItem(item);
                                }
                            });*/
                break;
        }
    }

    private void initPaperChild(BaseViewHolder holder, ClickEntity item) {
        double totalSizeCN = 0;
        int totalDurationCN = 0;
        double totalSizeEN = 0;
        int totalDurationEN = 0;


        if (item.getPaperBan() != null) {

            final SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX paperBan = item.getPaperBan();
            if (paperBan != null) {
                if (!TextUtils.isEmpty(item.getVersion())) {
                    switch (item.getVersion()) {
                        case "0":
                            holder.setText(R.id.tv_version, "完整版");
                            break;
                        case "1":
                            holder.setText(R.id.tv_version, "精华版");
                            break;
                        case "2":
                            holder.setText(R.id.tv_version, "摘要版");
                            break;
                    }
                }
                holder.setText(R.id.tv_paper_browse, paperBan.getReadNum() + "");
                holder.setText(R.id.tv_paper_collected, paperBan.getFollowedNum() + "");
                holder.setText(R.id.tv_paper_download, "");
                holder.setText(R.id.tv_paper_problem, paperBan.getQuizNum() + "");
                Integer integer = Integer.valueOf(item.getVersion());
                String id = paperBan.getId();
                final String downloadUrl = String.valueOf(id + (integer * 10000000));
                final ArrayList<DownloadBean> list = new ArrayList<>();
                List<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.PhotosBean.RowsBeanXX> photoRows = paperBan.getPhotos().getRows();
                List<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.RecordOneBean.RowsBeanXXX> recordOneRows = paperBan.getRecordOne().getRows();
                List<SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.RecordOneBean.RowsBeanXXX> recordTwoRows = paperBan.getRecordTwo().getRows();
                if (photoRows != null) {
                    for (int i = 0; i < photoRows.size(); i++) {
                        String path = photoRows.get(i).getPath();
                        if (path.contains(UrlConstants.RequestUrl.QN_ADDRESS)) {
                            path += "-watermark";
                        }
                        if (DataBaseHelper.getSingleton(activity).recordNotExists(path)) {
                            DownloadBean downloadBean = new DownloadBean
                                    .Builder(path)
                                    .setExtra1(downloadUrl)   //save extra info into database.
                                    .setExtra2(String.valueOf(photoRows.get(i).getId()))   //save extra info into database.
                                    .setExtra3(String.valueOf(photoRows.get(i).getOrder()))   //save extra info into database.
                                    .setExtra4("photo")   //save extra info into database.
                                    .setExtra5(paperBan.getFirstPic())   //save extra info into database.
                                    .setTitle(item.getTitle())
                                    .setTime(paperBan.getCreateTime())
                                    .setVersion(item.getVersion())
                                    .build();
                            list.add(downloadBean);
                        }

                        if (recordOneRows != null && recordOneRows.size() > i) {
                            totalSizeCN += Integer.parseInt(recordOneRows.get(i).getSize());
                            totalDurationCN += Integer.parseInt(recordOneRows.get(i).getDuration());
                            if (DataBaseHelper.getSingleton(activity).recordNotExists(recordOneRows.get(i).getPath())) {
                                DownloadBean downloadBean = new DownloadBean
                                        .Builder(recordOneRows.get(i).getPath())
                                        .setExtra1(downloadUrl)   //save extra info into database.
                                        .setExtra2(String.valueOf(recordOneRows.get(i).getPcid()))   //save extra info into database.
                                        .setExtra3(String.valueOf(photoRows.get(i).getOrder()))   //save extra info into database.
                                        .setExtra4("record")
                                        .setExtra5("CN")
                                        .setTitle(item.getTitle())
                                        .setTime(paperBan.getCreateTime())
                                        .setVersion(item.getVersion())//save extra info into database.
                                        .build();
                                list.add(downloadBean);
                            }

                        }

                        if (recordTwoRows != null && recordTwoRows.size() > i) {
                            totalSizeEN += Integer.parseInt(recordTwoRows.get(i).getSize());
                            totalDurationEN += Integer.parseInt(recordTwoRows.get(i).getDuration());
                            if (DataBaseHelper.getSingleton(activity).recordNotExists(recordTwoRows.get(i).getPath())) {
                                DownloadBean downloadBean = new DownloadBean
                                        .Builder(recordTwoRows.get(i).getPath())
                                        .setExtra1(downloadUrl)   //save extra info into database.
                                        .setExtra2(String.valueOf(recordTwoRows.get(i).getPcid()))   //save extra info into database.
                                        .setExtra3(String.valueOf(photoRows.get(i).getOrder()))  //save extra info into database.
                                        .setExtra4("record")
                                        .setExtra5("EN")
                                        .setTitle(item.getTitle())
                                        .setTime(item.getTime())
                                        .setVersion(item.getVersion())//save extra info into database.
                                        .build();
                                list.add(downloadBean);
                            }
                        }
                    }
                    List<String> missionIdAll = mRxDownload.getMissionIdAll(activity);
                    ImageView view = holder.getView(R.id.iv_paper_download);
                    for (String s : missionIdAll) {
                        if (!view.isSelected())
                            view.setSelected(s.equals(downloadUrl));
                        if (!view.isSelected())
                            for (SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX.PhotosBean.RowsBeanXX photoRow : photoRows) {
                                if (!view.isSelected())
                                    view.setSelected(s.equals(photoRow.getId() + ""));
                            }
                    }
                }

                final ArrayList<ClickEntity> popup = new ArrayList<>();
                if (totalSizeCN != 0 || totalDurationCN != 0) {
                    ClickEntity clickEntityCN = new ClickEntity();
                    clickEntityCN.setSize(totalSizeCN);
                    clickEntityCN.setDurations(totalDurationCN);
                    clickEntityCN.setLanguage("CN");
                    popup.add(clickEntityCN);
                }
                if (totalSizeEN != 0 || totalDurationEN != 0) {
                    ClickEntity clickEntityEN = new ClickEntity();
                    clickEntityEN.setSize(totalSizeEN);
                    clickEntityEN.setDurations(totalDurationEN);
                    clickEntityEN.setLanguage("EN");
                    popup.add(clickEntityEN);

                }

                final int pos = holder.getAdapterPosition();
                final boolean b = item.getSubItems() != null && item.getSubItems().size() > 0;
                final boolean expanded = item.isExpanded();
                holder.getView(R.id.iv_menu).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch (type) {
                            case "collection_paper":
                            case "collection_summary":
                                DurationSizePopupWindow popupWindow = new DurationSizePopupWindow(activity, popup);
                                popupWindow.showPopupWindow(v);
                                break;
                            default:
                                if (b) {
                                    if (expanded) {
                                        collapse(pos, false);
                                    } else {
                                        expand(pos, false);
                                    }
                                } else {
                                    if (popup.size() > 0) {
                                        DurationSizePopupWindow durationSizePopupWindow = new DurationSizePopupWindow(activity, popup);
                                        durationSizePopupWindow.showPopupWindow(v);
                                    }
                                }
                                break;
                        }

                    }

                });

                switch (type) {
                    case "collection_paper":
                    case "collection_summary":
                        holder.setVisible(R.id.iv_add, false);
                        break;
                    default:
                        if (item.getSubItems() == null || item.getSubItems().size() <= 0) {
                            ImageLoader.getInstance().displayImage(activity, R.mipmap.subscribe_item_spot, (ImageView) holder.getView(R.id.iv_menu));
                        } else {
                            ImageLoader.getInstance().displayImage(activity, R.mipmap.subscribe_item_menu, (ImageView) holder.getView(R.id.iv_menu));

                        }

                        break;
                }

                final String paperId = item.getPaperId();
                final String version = item.getVersion();
                final String isFollowed = paperBan.getIsFollowed();
                final int parentPosition = item.getPosition();
                final int position = holder.getAdapterPosition();
                if (!TextUtils.isEmpty(isFollowed)) {
                    holder.getView(R.id.iv_paper_collected).setSelected(Objects.equals("1", isFollowed));
                    Log.i("--->>", "initPaperChild: " + Objects.equals("1", isFollowed));
                }
                holder.getView(R.id.iv_paper_collected).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("--->>", "onClick: " + view.isSelected());
                        if (BaseActivity.uid.equals("15")) {
                            AlertDialog.Builder normalDialog =
                                    new AlertDialog.Builder(activity);
                            normalDialog.setIcon(null);
                            normalDialog.setTitle("需要登录才能执行此操作");
                            normalDialog.setPositiveButton("登录",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            LogInActivity.actionActivity(activity);
                                        }
                                    });
                            normalDialog.setNegativeButton("取消", null);
                            // 显示
                            normalDialog.show();
                        } else {
                            Log.i("--->>", "onClick: " + type);
                            if (!TextUtils.isEmpty(paperId) && !TextUtils.isEmpty(version) && !TextUtils.isEmpty(isFollowed))
                                activity.collection(Integer.valueOf(paperId), Integer.valueOf(version), type, isFollowed, parentPosition, position);
                        }

                    }
                });
                holder.getView(R.id.iv_paper_download).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        if (BaseActivity.uid.equals("15")) {
                            AlertDialog.Builder normalDialog =
                                    new AlertDialog.Builder(activity);
                            normalDialog.setIcon(null);
                            normalDialog.setTitle("需要登录才能执行此操作");
                            normalDialog.setPositiveButton("登录",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            LogInActivity.actionActivity(activity);
                                        }
                                    });
                            normalDialog.setNegativeButton("取消", null);
                            // 显示
                            normalDialog.show();
                        } else if (list.size() > 0) {
                            new RxPermissions(activity)
                                    .request(WRITE_EXTERNAL_STORAGE)
                                    .doOnNext(new Consumer<Boolean>() {
                                        @Override
                                        public void accept(Boolean granted) throws Exception {
                                            if (!granted) {
                                                throw new RuntimeException("no permission");
                                            }
                                        }
                                    })
                                    .compose(mRxDownload.<Boolean>transformMulti(list, downloadUrl))
                                    .subscribe(new Consumer<Object>() {
                                        @Override
                                        public void accept(Object o) throws Exception {
                                            Utils.showToast("下载开始");
                                            FloatingManager.getInstance(activity).showDownloadAnimator((View) v.getParent(), paperBan.getFirstPic(), true);
                                            activity.addDownload();
                                            v.setSelected(true);
                                        }
                                    }, new Consumer<Throwable>() {
                                        @Override
                                        public void accept(@NonNull Throwable throwable) throws Exception {
                                            Log.w(TAG, throwable);
                                            Utils.showToast("下载中");
                                        }
                                    });

                        } else {
                            Utils.showToast("已加入下载队列");
                        }
                    }
                });

                ImageView ivIcon = holder.getView(R.id.iv_icon);
                ImageLoader.getInstance().displayCricleImage(activity, paperBan.getFirstPic(), ivIcon);

                DaoSession daoSession = BaseApp.app.getDaoSession();
                NoteDao noteDao = daoSession.getNoteDao();


                Query<Note> notesId = noteDao.queryBuilder().where(NoteDao.Properties.Id.eq(downloadUrl)).build();
                List<Note> listNote = notesId.list();
                for (Note note1 : listNote) {
                    if ((note1.getId() + "").equals(downloadUrl)) {
                        holder.getView(iv_add).setSelected(true);
                    }
                }
                final String firstPic = paperBan.getFirstPic();
                final int quizNum = Integer.parseInt(paperBan.getQuizNum());
                final int downloadNum = Integer.parseInt(paperBan.getDownloadNum());
                final String photoOrder = item.getPhotoOrder();
                final int readNum = Integer.parseInt(paperBan.getReadNum());
                final int followedNum = Integer.parseInt(paperBan.getFollowedNum());
                final String photoRecord = item.getPhotoRecord();

                holder.getView(iv_add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!v.isSelected()) {

                            DaoSession daoSession = BaseApp.app.getDaoSession();
                            NoteDao noteDao = daoSession.getNoteDao();
                            Note note = new Note();
                            note.setTitle(photoRecord + "");
                            note.setTime(String.valueOf(System.currentTimeMillis()));
                            note.setPath(firstPic + "");
                            note.setId(downloadUrl);
                            note.setCommentNum(quizNum + "");
                            note.setFollowedNum(downloadNum + "");
                            note.setCreateTime(photoOrder);
                            note.setReadNum(readNum + "");
                            note.setLikedNum(followedNum + "");
                            note.setType("play");
                            note.setPlayPosition(0);
                            note.setSongsSize(0);
                            if (version != null)
                                note.setVersions(version);
                            noteDao.insertOrReplace(note);
                            Utils.showToast("添加成功");
                            FloatingManager.getInstance(activity).showDownloadAnimator((View) v.getParent(), paperBan.getFirstPic(), false);

                            v.setSelected(true);
                        } else {
                            Utils.showToast("已添加");
                        }
                    }
                });
            }

        }


    }

    private void updateProgressStatus(ProgressBar mProgress, DownloadStatus status, TextView size) {
        mProgress.setIndeterminate(status.isChunked);
        mProgress.setMax((int) status.getTotalSize());
        mProgress.setProgress((int) status.getDownloadSize());
        size.setText(status.getFormatStatusString());
    }
/*

    private String initFlag(int flag) {
        String download = "";
        switch (flag) {
            case DownloadFlag.NORMAL:
                download = "未下载";
                break;
            case DownloadFlag.WAITING:
                download = "等待中";
                break;
            case DownloadFlag.STARTED:
                download = "已开始下载";
                break;
            case DownloadFlag.PAUSED:
                download = "已暂停";
                break;
            case DownloadFlag.COMPLETED:
                download = "已完成";
                break;
            case DownloadFlag.FAILED:
                download = "下载失败";
                break;
            case DownloadFlag.DELETED:
                download = "已删除";
                break;
        }
        return download;
    }
*/


}
