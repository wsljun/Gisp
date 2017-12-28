package com.giiisp.giiisp.view.adapter;

import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.entity.AnswerQUizXBean;
import com.giiisp.giiisp.entity.AnswerQuizRowsBean;
import com.giiisp.giiisp.entity.CollectionEntity;
import com.giiisp.giiisp.entity.DownloadController;
import com.giiisp.giiisp.entity.FansConcernedEntity;
import com.giiisp.giiisp.entity.HomeEntity;
import com.giiisp.giiisp.entity.HomeSearchEntity;
import com.giiisp.giiisp.entity.LiteratureEntity;
import com.giiisp.giiisp.entity.MsgEntity;
import com.giiisp.giiisp.entity.MyScholarBean;
import com.giiisp.giiisp.entity.Note;
import com.giiisp.giiisp.entity.PbTypeBean;
import com.giiisp.giiisp.entity.QAEntity;
import com.giiisp.giiisp.entity.SubscribeEntity;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.utils.PackageUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;
import zlc.season.rxdownload2.entity.DownloadStatus;

import static android.R.attr.fragment;

/**
 * 实体类的基类
 * Created by Thinkpad on 2017/4/25.
 */

public class ClickEntity extends AbstractExpandableItem<ClickEntity> implements MultiItemEntity {
    private String string;
    private int itemType;
    private String url = "1";
    private int rid;
    private int level;
    private String photoRecord;
    private String photoOrder;
    private int photoNumber;
    private int recordNumber;
    private int recordTwoNumber;
    private boolean isSelected;
    private String title;
    private String version;
    private int position;
    BaseActivity activity;

    public BaseActivity getActivity() {
        return activity;
    }

    public void setActivity(BaseActivity activity) {
        this.activity = activity;
        initDownload(activity);
    }

    public int getPosition() {
        return position;
    }

    private void initDownload(final BaseActivity activity) {
        if (record == null || isSelected)
            return;
        disposable = RxDownload.getInstance(activity).receiveDownloadStatus(record.getUrl())
                .subscribe(new Consumer<DownloadEvent>() {
                    int fa1 = -1;
                    int fa2 = -1;

                    @Override
                    public void accept(DownloadEvent downloadEvent) throws Exception {
                        if (downloadStatus != null && downloadIcon != null) {
                            DownloadController mDownloadController = new DownloadController(downloadStatus, downloadIcon);
                            setDownloadController(mDownloadController);
                            mDownloadController.setEvent(downloadEvent);
                        }


                        if (downloadEvent.getFlag() == DownloadFlag.FAILED) {
                            Throwable throwable = downloadEvent.getError();
                            Log.w("TAG", throwable);
                        }
                        if (progressBar != null && textView != null)
                            updateProgressStatus(progressBar, downloadEvent.getDownloadStatus(), textView);
                        record.setFlag(downloadEvent.getFlag());

                             /*   if (item.record.getUrl().contains(".mp3")) {
                                    item.getClickEntity().setRecordFlag(downloadEvent.getFlag());
                                } else {
                                    item.getClickEntity().setPhotoFlag(downloadEvent.getFlag());
                                }*/
                        fa1 = downloadEvent.getFlag();
                        if (fa1 == DownloadFlag.COMPLETED && fa2 == DownloadFlag.STARTED) {
                            if ("appInfo".equals(getPhotoRecord())) {
                                PackageUtil.installApkNormal(activity, record.getSavePath() + "/" + record.getSaveName());
                            }
                            if (adapter != null) {
                                int parentPosition = adapter.getParentPosition(ClickEntity.this);
                                if (parentPosition != -1) {
                                    ClickEntity parentItem = adapter.getItem(parentPosition);
                                    if (parentItem != null) {
                                        int position = adapter.getParentPosition(parentItem);
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
                                                adapter.collapse(parentPosition, false);
                                                adapter.remove(parentPosition);
                                                adapter.notifyItemChanged(position);
                                            }

                                        }

                                        boolean isdownload = false;
                                        ClickEntity item1 = adapter.getItem(position);
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
                        }
                        fa2 = fa1;
                        //                                if (downloadEvent.getFlag() == DownloadFlag.COMPLETED)
                        //                                    item.getClickEntity().removeSubItem(item);
                    }
                });
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private double size;
    private int durations;
    private int photoFlag;
    private int RecordCNFlag;
    private int RecordENFlag;

    public int getPhotoNumber() {
        return photoNumber;
    }

    public void setPhotoNumber(int photoNumber) {
        this.photoNumber = photoNumber;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public int getRecordTwoNumber() {
        return recordTwoNumber;
    }

    public void setRecordTwoNumber(int recordTwoNumber) {
        this.recordTwoNumber = recordTwoNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPhotoOrder() {
        return photoOrder;
    }

    public int getPhotoFlag() {
        return photoFlag;
    }

    public void setPhotoFlag(int photoFlag) {
        this.photoFlag = photoFlag;
    }

    public int getRecordCNFlag() {
        return RecordCNFlag;
    }

    public void setRecordCNFlag(int recordCNFlag) {
        RecordCNFlag = recordCNFlag;
    }

    public int getRecordENFlag() {
        return RecordENFlag;
    }

    public void setRecordENFlag(int recordENFlag) {
        RecordENFlag = recordENFlag;
    }

    public void setPhotoOrder(String photoOrder) {
        this.photoOrder = photoOrder;
    }

    public String getPhotoRecord() {
        return photoRecord;

    }

    public void setPhotoRecord(String photoRecord) {
        this.photoRecord = photoRecord;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getDurations() {
        return durations;
    }

    public void setDurations(int durations) {
        this.durations = durations;
    }

    private List<String> list = new ArrayList<>();
    private ClickEntity clickEntity;
    private String dubbingUrl;
    private String language;
    private ProgressBar progressBar;
    private TextView textView;
    private TextView downloadStatus;
    private ImageView downloadIcon;
    private ExpandableItemAdapter adapter;
    private int type = 1000;
    private Note note;
    private String paperId;

    private String isEncrypt;

    public String getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(String isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    public TextView getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(TextView downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public ImageView getDownloadIcon() {
        return downloadIcon;
    }

    public void setDownloadIcon(ImageView downloadIcon) {
        this.downloadIcon = downloadIcon;
    }

    public ExpandableItemAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ExpandableItemAdapter adapter) {
        this.adapter = adapter;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paper) {
        this.paperId = paper;
    }

    //领域 关键字 实体类
    private PbTypeBean typeBean;

    //学者列表信息的实体类
    private MyScholarBean myScholarBean;

    //论文的索引实体类
    private LiteratureEntity.LiteratureInfoBean literatureInfoBean;

    //提问和回答的实体类
    private AnswerQUizXBean answerQUizXBean;
    private AnswerQuizRowsBean answerQuizRowsBean;

    //首页搜索的实体类
    private HomeSearchEntity.PaperBean paperBean;
    private HomeSearchEntity.ScholarBean scholarBean;
    private HomeSearchEntity.ScholarBean.RowsBean rowsBeanHomeEntity;
    private HomeSearchEntity.PaperBean.RowsBeanX paperBeanRows;

    //论文列表的 实体类
    private SubscribeEntity.PageInfoBean.RowsBeanXXXXX subscribeEntityRows;
    //收藏的论文或综述的Entity
    private CollectionEntity.PageInfoBean.RowsBean collectionEntity;
    //粉丝关注的实体类
    private FansConcernedEntity.PageInfoBean.RowsBean userEntity;
    //消息互动的实体类
    private MsgEntity.PageInfoBean.RowsBean msgEntity;
    private UserInfoEntity userInfoEntity;
    private QAEntity.QuizInfoBean.RowsBeanXXXX quizInfoBean;
    private HomeEntity homeEntity;

    private SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX paperBan;

    UserInfoEntity.SummarizeBean summarizeBean;

    public ClickEntity(UserInfoEntity.SummarizeBean summarizeBean) {
        this.summarizeBean = summarizeBean;
    }

    public UserInfoEntity.SummarizeBean getSummarizeBean() {
        return summarizeBean;
    }

    public void setSummarizeBean(UserInfoEntity.SummarizeBean summarizeBean) {
        this.summarizeBean = summarizeBean;
    }

    public ClickEntity(SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX paperBan) {
        this.paperBan = paperBan;
    }

    public SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX getPaperBan() {
        return paperBan;
    }

    public void setPaperBan(SubscribeEntity.PageInfoBean.RowsBeanXXXXX.PhotoOneBean.RowsBeanXXXX paperBan) {
        this.paperBan = paperBan;
    }

    public ClickEntity(QAEntity.QuizInfoBean.RowsBeanXXXX quizInfoBean) {
        this.quizInfoBean = quizInfoBean;
    }

    public QAEntity.QuizInfoBean.RowsBeanXXXX getQuizInfoBean() {
        return quizInfoBean;
    }

    public void setQuizInfoBean(QAEntity.QuizInfoBean.RowsBeanXXXX quizInfoBean) {
        this.quizInfoBean = quizInfoBean;
    }

    public HomeEntity getHomeEntity() {
        return homeEntity;
    }

    public void setHomeEntity(HomeEntity homeEntity) {
        this.homeEntity = homeEntity;
    }

    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    public FansConcernedEntity.PageInfoBean.RowsBean getUserEntity() {
        return userEntity;
    }


    public ClickEntity(FansConcernedEntity.PageInfoBean.RowsBean userEntity) {
        this.userEntity = userEntity;
    }

    public ClickEntity(MsgEntity.PageInfoBean.RowsBean msgEntity) {
        this.msgEntity = msgEntity;
    }

    public MsgEntity.PageInfoBean.RowsBean getMsgEntity() {
        return msgEntity;
    }

    public void setMsgEntity(MsgEntity.PageInfoBean.RowsBean msgEntity) {
        this.msgEntity = msgEntity;
    }

    public void setUserEntity(FansConcernedEntity.PageInfoBean.RowsBean userEntity) {
        this.userEntity = userEntity;
    }

    public ClickEntity(SubscribeEntity.PageInfoBean.RowsBeanXXXXX subscribeEntityRows) {
        this.subscribeEntityRows = subscribeEntityRows;
    }

    public SubscribeEntity.PageInfoBean.RowsBeanXXXXX getSubscribeEntityRows() {
        return subscribeEntityRows;
    }

    public void setSubscribeEntityRows(SubscribeEntity.PageInfoBean.RowsBeanXXXXX subscribeEntityRows) {
        this.subscribeEntityRows = subscribeEntityRows;
    }

    public ClickEntity(HomeSearchEntity.ScholarBean.RowsBean rowsBeanHomeEntity) {
        this.rowsBeanHomeEntity = rowsBeanHomeEntity;
    }

    public ClickEntity(HomeSearchEntity.PaperBean.RowsBeanX paperBean) {
        this.paperBeanRows = paperBean;
    }

    public HomeSearchEntity.PaperBean.RowsBeanX getPaperBeanRows() {
        return paperBeanRows;
    }

    public void setPaperBeanRows(HomeSearchEntity.PaperBean.RowsBeanX paperBeanRows) {
        this.paperBeanRows = paperBeanRows;
    }

    public CollectionEntity.PageInfoBean.RowsBean getCollectionEntity() {
        return collectionEntity;
    }

    public void setCollectionEntity(CollectionEntity.PageInfoBean.RowsBean subscribeEntity) {
        this.collectionEntity = subscribeEntity;
    }

    public HomeSearchEntity.ScholarBean.RowsBean getRowsBeanHomeEntity() {
        return rowsBeanHomeEntity;
    }

    public void setRowsBeanHomeEntity(HomeSearchEntity.ScholarBean.RowsBean rowsBeanHomeEntity) {
        this.rowsBeanHomeEntity = rowsBeanHomeEntity;
    }

    public HomeSearchEntity.ScholarBean getScholarBean() {
        return scholarBean;
    }

    public void setScholarBean(HomeSearchEntity.ScholarBean scholarBean) {
        this.scholarBean = scholarBean;
    }

    public HomeSearchEntity.PaperBean getPaperBean() {
        return paperBean;
    }

    public void setPaperBean(HomeSearchEntity.PaperBean paperBean) {
        this.paperBean = paperBean;
    }

    private boolean isSelect;

    public ClickEntity(CollectionEntity.PageInfoBean.RowsBean subscribeEntity) {
        this.collectionEntity = subscribeEntity;
    }

    public LiteratureEntity.LiteratureInfoBean getLiteratureInfoBean() {
        return literatureInfoBean;
    }

    public ClickEntity(LiteratureEntity.LiteratureInfoBean literatureInfoBean) {
        this.literatureInfoBean = literatureInfoBean;
    }

    public void setLiteratureInfoBean(LiteratureEntity.LiteratureInfoBean literatureInfoBean) {
        this.literatureInfoBean = literatureInfoBean;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public ClickEntity(AnswerQUizXBean answerQUizXBean) {
        this.answerQUizXBean = answerQUizXBean;
    }

    public AnswerQUizXBean getAnswerQUizXBean() {
        return answerQUizXBean;
    }

    public void setAnswerQUizXBean(AnswerQUizXBean answerQUizXBean) {
        this.answerQUizXBean = answerQUizXBean;
    }

    public AnswerQuizRowsBean getAnswerQuizRowsBean() {
        return answerQuizRowsBean;
    }

    public ClickEntity(AnswerQuizRowsBean answerQuizRowsBean) {
        this.answerQuizRowsBean = answerQuizRowsBean;
    }

    public void setAnswerQuizRowsBean(AnswerQuizRowsBean answerQuizRowsBean) {
        this.answerQuizRowsBean = answerQuizRowsBean;
    }

    public MyScholarBean getMyScholarBean() {
        return myScholarBean;
    }

    public ClickEntity(MyScholarBean myScholarBean) {
        this.myScholarBean = myScholarBean;
    }

    public void setMyScholarBean(MyScholarBean myScholarBean) {
        this.myScholarBean = myScholarBean;
    }

    public PbTypeBean getTypeBean() {
        return typeBean;
    }

    public void setTypeBean(PbTypeBean typeBean) {
        this.typeBean = typeBean;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public ClickEntity getClickEntity() {
        return clickEntity;
    }

    public void setClickEntity(ClickEntity clickEntity) {
        this.clickEntity = clickEntity;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public ClickEntity(int level) {
        this.level = level;
    }

    public ClickEntity(int level, String string, String url) {
        this.string = string;
        this.url = url;
        this.level = level;
    }

    public ClickEntity(Note note) {
        this.note = note;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String time;
    private String duration;
    public Disposable disposable;
    public DownloadRecord record;
    private DownloadController downloadController;

    public DownloadController getDownloadController() {
        return downloadController;
    }

    public void setDownloadController(DownloadController downloadController) {
        this.downloadController = downloadController;
    }

    public ClickEntity(String dubbingUrl, String language, String time, String duration) {
        this.dubbingUrl = dubbingUrl;
        this.language = language;
        this.time = time;
        this.duration = duration;
    }

    public String getDubbingUrl() {
        return dubbingUrl;
    }

    public void setDubbingUrl(String dubbingUrl) {
        this.dubbingUrl = dubbingUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public ClickEntity(String string, int rid) {
        this.string = string;
        this.rid = rid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ClickEntity(String string, String url) {
        this.string = string;
        this.url = url;
    }

    public ClickEntity(String string, String url, List<String> list) {
        this.string = string;
        this.url = url;
        this.list = list;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getString() {
        return string;
    }

    public ClickEntity(String url, DownloadController downloadController, String s) {
        this.url = url;
        this.string = s;
        this.downloadController = downloadController;
    }

    public ClickEntity() {
    }

    public ClickEntity(PbTypeBean typeBean) {
        this.typeBean = typeBean;
    }

    public ClickEntity(int itemType, String string) {
        this.itemType = itemType;
        this.string = string;
    }

    public ClickEntity(String string) {
        this.string = string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    private void updateProgressStatus(ProgressBar mProgress, DownloadStatus status, TextView size) {
        mProgress.setIndeterminate(status.isChunked);
        mProgress.setMax((int) status.getTotalSize());
        mProgress.setProgress((int) status.getDownloadSize());
        size.setText(status.getFormatStatusString());
    }


    private UserInfoEntity.IntroductionBean introduction;


    public ClickEntity(UserInfoEntity.IntroductionBean introduction) {
        this.introduction = introduction;
    }

    public UserInfoEntity.IntroductionBean getIntroduction() {
        return introduction;
    }

    public void setIntroduction(UserInfoEntity.IntroductionBean introduction) {
        this.introduction = introduction;
    }

    private UserInfoEntity.UserInfoBean userInfo;

    public ClickEntity(UserInfoEntity.UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfoEntity.UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoEntity.UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }
}
