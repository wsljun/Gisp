package com.giiisp.giiisp.view.fragment;

import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.ImageLoader;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.AttentionActivity;
import com.giiisp.giiisp.view.activity.FragmentActivity;
import com.giiisp.giiisp.view.activity.GiiispActivity;
import com.giiisp.giiisp.view.activity.SettingActivity;
import com.giiisp.giiisp.view.activity.VerifiedActivity;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.sina.weibo.sdk.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

import static com.giiisp.giiisp.base.BaseActivity.isVip;
import static com.giiisp.giiisp.base.BaseActivity.uid;
import static com.giiisp.giiisp.base.BaseActivity.emailauthen;

/**
 * '我的'页面
 * Created by Thinkpad on 2017/5/4.
 */

public class MineFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.iv_attention)
    ImageView ivAttention;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_prompt)
    TextView tvPrompt;
    @BindView(R.id.tv_user_position)
    TextView tvUserPosition;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.tv_recording_authentication)
    TextView tvRecordinAuthentication;
    @BindView(R.id.tv_user_email)
    TextView tvUserEmail;
    @BindView(R.id.tv_paper_number)
    TextView tvPaperNumber;
    @BindView(R.id.tv_mine_download)
    TextView tvMineDownload;
    @BindView(R.id.tv_follow_number)
    TextView tvFollowNumber;
    @BindView(R.id.tv_review_number)
    TextView tvReviewNumber;
    @BindView(R.id.tv_fans_number)
    TextView tvFansNumber;
    @BindView(R.id.tv_arrow)
    TextView tvArrow;
    @BindView(R.id.fl_mine_qa)
    FrameLayout flMineQa;
    @BindView(R.id.fl_mine_download)
    FrameLayout flMineDownload;
    @BindView(R.id.fl_mine_subscribe)
    FrameLayout flMineSubscribe;
    @BindView(R.id.fl_mine_news)
    FrameLayout flMineNews;
    @BindView(R.id.fl_mine_contacts)
    FrameLayout flMineContacts;
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.fl_mine_collection)
    FrameLayout flMineCollection;
    @BindView(R.id.fl_mine_setting)
    FrameLayout flMineSetting;
    @BindView(R.id.ll_empty_view)
    LinearLayout emptyView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.tv_user_web)
    TextView tvUserWeb;
    @BindView(R.id.tv_verified)
    TextView tvVerified;
    private int downloadNunber;
    private String imageUrl = "";

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_mine;
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (entity instanceof UserInfoEntity) {
            UserInfoEntity userInfoEntity = (UserInfoEntity) entity;
            if (userInfoEntity.getUserInfo() != null) {
                initUser(userInfoEntity);
            }
        }
    }

    private void initUser(UserInfoEntity userInfoEntity) {
        if (context == null)
            return;
        timeout = false;
        if (emptyView == null) {
            return;
        }
        emptyView.setVisibility(View.GONE);
        EventBus.getDefault().post(userInfoEntity);
        UserInfoEntity.UserInfoBean userInfo = userInfoEntity.getUserInfo();
        String nickName = userInfo.getRealName();
        String avatar = userInfo.getAvatar();
        if (!avatar.equals(imageUrl))
            ImageLoader.getInstance().displayCricleImage((BaseActivity) getActivity(), avatar, ivUserIcon);
        imageUrl = avatar;
        tvUserName.setText(nickName);
        tvUserEmail.setText(userInfo.getEmail());
        int sex = userInfo.getSex();
        String web = userInfo.getWeb();
        if (sex == 1) {
            ivSex.setImageResource(R.mipmap.ic_sex_male);
        }
        if (sex == 2) {
            ivSex.setImageResource(R.mipmap.ic_sex_female);
        }
        if (TextUtils.isEmpty(web)) {
            tvUserWeb.setText("未添加个人网址");
        } else {
            tvUserWeb.setText(web);
        }
        ArrayMap<String, Object> map = new ArrayMap<>();
        map.put("uid", uid);
//        map.put("token", token);
        map.put("mobile", userInfo.getMobile() + "");
        map.put("loginType", 2);
//        presenter.saveClientTypeData(map);
        if (Utils.checkMobileNumber(userInfo.getMobile())) {
            tvUserPhone.setText(userInfo.getMobile());
        } else {
            tvUserPhone.setText("未绑定手机号码");
        }
        if (Utils.checkEmail(userInfo.getEmail())) {
            tvUserEmail.setText(userInfo.getEmail());
        } else {
            tvUserEmail.setText("未绑定邮箱");
        }
        if (TextUtils.isEmpty(userInfo.getEmailauthen())) {  //  Test TextUtils.isEmpty(userInfo.getIsVIP()) 修改字段 isvip 替换
            Log.d("Presenter", "initUser: isIVP: " + userInfo.getIsVIP());
            emailauthen = "0";
            tvRecordinAuthentication.setText("去认证");
            tvRecordinAuthentication.setCompoundDrawables(null, null, null, null);
        } else {
            emailauthen = userInfo.getEmailauthen(); // isvip = 1,2 认证完成 （身份认证判断），0 ：身份认证，3：认证中；
            isVip = userInfo.getIsVIP();
            switch (emailauthen) { // 新认证字段 // userInfo.getEmailauthen()
                case "0":
                    tvRecordinAuthentication.setText("去认证");
                    tvRecordinAuthentication.setCompoundDrawables(null, null, null, null);
                    break;
                case "1":
                    tvRecordinAuthentication.setText("开始配音");
                    break;
                case "2":
                    tvRecordinAuthentication.setText("正在认证中");
                    break;
                default:
                    tvRecordinAuthentication.setText("未认证");
                    break;
            }
            switch (isVip) {
                case "0":
                    tvVerified.setVisibility(View.VISIBLE);
                    tvVerified.setText("身份认证");
                    break;
                case "1":
                    tvVerified.setVisibility(View.GONE);
                    break;
                case "2":
                    tvVerified.setVisibility(View.GONE);
                    break;
                case "3":
                    tvVerified.setVisibility(View.VISIBLE);
                    tvVerified.setText("认证中");
                    break;
            }
        }
        tvPrompt.setText(userInfo.getSchool() + " " + userInfo.getDegree());
        if (!TextUtils.isEmpty(userInfo.getDomain()) && TextUtils.isEmpty(userInfo.getPosition())) {
            tvUserPosition.setText(userInfo.getDomain() + " " + userInfo.getPosition());
        } else {
            tvUserPosition.setVisibility(View.GONE);
        }
        if (userInfoEntity.getNum() != null) {
            tvFollowNumber.setText(String.valueOf(userInfoEntity.getNum().getFollowNum()));
            tvFansNumber.setText(String.valueOf(userInfoEntity.getNum().getFollowedNum()));
            tvPaperNumber.setText(String.valueOf(userInfoEntity.getNum().getPaperNum()));
            tvReviewNumber.setText(String.valueOf(userInfoEntity.getNum().getSummarizeNum()));
        }
    }

    @Override
    public void onFailure(String msg, Exception ex) {
        super.onFailure(msg, ex);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }


    @Override
    public void initView() {
        tvBack.setVisibility(View.GONE);
        tvTitle.setText(R.string.mine);
        ivAttention.setVisibility(View.GONE);
        tvArrow.setVisibility(View.VISIBLE);
        //        emptyView.setVisibility(View.VISIBLE);
        loadDownloadNunber();
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAuxiliary);
        swipeRefreshLayout.setOnRefreshListener(this);
        //        ivMenu.setVisibility(View.VISIBLE);
        //        ivMenu.setBackgroundResource(android.R.drawable.ic_menu_manage);
    }

    @Override
    public void initData() {
        super.initData();
        initNetwork();

    }

    @Override
    public void initNetwork() {
        ArrayMap<String, Object> userMap = new ArrayMap<>();
        //        userMap.put("token", "A760880003E7DDEDFEF56ACB3B09697F");
//        userMap.put("token", token);
        //        userMap.put("oid", 1);
        userMap.put("uid", uid);
        if (presenter != null)
            presenter.getUserInfoData(userMap);
        //        presenter.getUserNumsData(uid+"", token);
        Log.i("--->>", "initNetwork: " + "MINE");
        super.initNetwork();
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void addDownload() {
        Log.i("--->>", "addDownload: ");
        String text = tvMineDownload.getText().toString();
        Integer integer;
        if (TextUtils.isEmpty(text)) {
            integer = 1;
        } else {
            integer = Integer.valueOf(text) + 1;
        }
        tvMineDownload.setText(String.valueOf(integer));
        super.addDownload();
    }


    @OnClick({R.id.tv_verified,R.id.tv_paper_number, R.id.tv_paper, R.id.iv_empty, R.id.tv_empty, R.id.tv_review_number, R.id.tv_review, R.id.fl_mine_history, R.id.tv_follow_number, R.id.tv_follow, R.id.rl_user_info, R.id.tv_recording_authentication, R.id.tv_fans_number, R.id.tv_fans, R.id.fl_mine_qa, R.id.fl_mine_download, R.id.fl_mine_subscribe, R.id.fl_mine_news, R.id.fl_mine_contacts, R.id.fl_mine_collection, R.id.fl_mine_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_empty:
            case R.id.tv_empty:
                initNetwork();
                break;
            case R.id.rl_user_info:
                FragmentActivity.actionActivity(getContext(), "he", uid + "");
                break;
            case R.id.fl_mine_history:
                FragmentActivity.actionActivity(getContext(), "plays");
                break;
            case R.id.tv_recording_authentication:
                //
                switch (emailauthen) { //  开始录音是否Ok  test 1 BaseActivity.emailauthen
                    case "0":

                       /* Utils.showToast("      认证请联系：\n" +
                                "+86 185 0101 0114 \n" +
                                " service@giiisp.com");*/
                        FragmentActivity.actionActivity(getContext(), "mailbox_authentication");
//                        VerifiedActivity.actionActivity(getContext());
                        break;
                    case "1":
                        FragmentActivity.actionActivity(getContext(), "wait_dubbing"); //  认证完成开始录音
                        break;
                    case "2":
                        Utils.showToast(R.string.in_authentication);
                        break;
                    default:
                        //FragmentActivity.actionActivity(getContext(), "wait_dubbing");
                        break;
                }

                break;
            case R.id.tv_paper_number:
            case R.id.tv_paper:
                FragmentActivity.actionActivity(getContext(), "my_paper");
                break;
            case R.id.tv_review_number:
            case R.id.tv_review:
                FragmentActivity.actionActivity(getContext(), "my_review");
                break;
            case R.id.tv_follow_number:
            case R.id.tv_follow:
                FragmentActivity.actionActivity(getContext(), "mine_follow", uid + "");
                break;
            case R.id.tv_fans_number:
            case R.id.tv_fans:
                FragmentActivity.actionActivity(getContext(), "mine_scholar", uid + "");
                break;
            case R.id.fl_mine_qa:
                FragmentActivity.actionActivity(getContext(), "qa");
                break;
            case R.id.fl_mine_download:
                FragmentActivity.actionActivity(getContext(), "download_activity");
                break;
            case R.id.fl_mine_subscribe:
                AttentionActivity.actionActivity(getContext(), "mine");
                break;
            case R.id.fl_mine_news:
                FragmentActivity.actionActivity(getContext(), "news");
                break;
            case R.id.fl_mine_contacts:
                break;
            case R.id.fl_mine_collection:
                GiiispActivity giiispActivity = getGiiispActivity();
                if (giiispActivity != null) {
                    giiispActivity.getViewPagerGiiisp().setCurrentItem(3);
                }
                break;
            case R.id.fl_mine_setting:
                SettingActivity.actionActivity(getContext());
                break;
            case R.id.tv_verified:
                switch (isVip) {
                    case "0":
                        VerifiedActivity.actionActivity(context);
                        break;
                    case "1":
                        break;
                    case "2":
                        break;
                    case "3":
                        Utils.showToast(R.string.in_authentication);
                        break;
                }
                break;
        }
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
                            tvMineDownload.setText("" + downloadNunber);
                        } else {
                            tvMineDownload.setText("");
                        }

                    }
                });

    }

    @Override
    public void onRefresh() {
        initNetwork();
        loadDownloadNunber();
    }
}
