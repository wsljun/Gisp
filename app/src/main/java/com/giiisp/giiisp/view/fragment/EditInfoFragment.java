package com.giiisp.giiisp.view.fragment;

import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.ImageLoader;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.ExperienceActivity;
import com.giiisp.giiisp.view.activity.FragmentActivity;
import com.giiisp.giiisp.view.activity.VerifiedActivity;
import com.giiisp.giiisp.view.adapter.ClickEntity;
import com.giiisp.giiisp.view.adapter.ItemClickAdapter;
import com.giiisp.giiisp.view.impl.BaseImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;

import static com.giiisp.giiisp.base.BaseActivity.uid;

/**
 * 编辑信息页面
 * Created by Thinkpad on 2017/5/8.
 */

public class EditInfoFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_prompt)
    TextView tvPrompt;
    @BindView(R.id.tv_user_position)
    TextView tvUserPosition;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.tv_user_email)
    TextView tvUserEmail;
    @BindView(R.id.tv_user_web)
    TextView tvUserWeb;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private ItemClickAdapter editinfoEducation;
    private String imageUrl = "";
    ItemClickAdapter itemClickAdapter = null;
    UserInfoEntity.IntroductionBean introductionBean;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_editinfo;
    }


    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ClickEntity> list = new ArrayList<>();
        View notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
        editinfoEducation = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_editinfo_education, list, "editinfo_education");
        editinfoEducation.setEmptyView(notDataView);
        recyclerView.setAdapter(editinfoEducation);

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAuxiliary);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        initNetwork();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("--->>", " : requestCode" + requestCode + "  resultCode" + resultCode);
    }

    @OnClick({R.id.tv_edit, R.id.tv_add, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                FragmentActivity.actionActivity(getActivity(), "verified_edit_info");
                break;
            case R.id.tv_add:
                ExperienceActivity.actionActivity(getActivity(), "add", null);
                break;
            case R.id.tv_next:
                String self = editText.getText().toString();
                if (!TextUtils.isEmpty(self)) {
                    MultipartBody.Part part1 = MultipartBody.Part.createFormData("self_introduction", self);
                    ((VerifiedActivity) getActivity()).parts.add(part1);
                }
                getVerifiedActivity().getViewPagerVerified().setCurrentItem(1);
                break;
        }
    }

    @Override
    public void initNetwork() {
        ArrayMap<String, Object> userMap = new ArrayMap<>();
        //        userMap.put("token", "A760880003E7DDEDFEF56ACB3B09697F");
//        userMap.put("token", token);
        //        userMap.put("oid", 1);
        userMap.put("uid", uid);
        if (presenter != null) {
            swipeRefreshLayout.setRefreshing(true);
            presenter.getUserInfoData(userMap);
        }
        //        presenter.getUserNumsData(uid+"", token);
        Log.i("--->>", "initNetwork: " + "EditInfo");

        super.initNetwork();
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
        EventBus.getDefault().post(userInfoEntity);
        UserInfoEntity.UserInfoBean userInfo = userInfoEntity.getUserInfo();
        String nickName = userInfo.getRealName();
        String avatar = userInfo.getAvatar();
        int sex = userInfo.getSex();
        String web = userInfo.getWeb();
        if (sex == 1) {
            ivSex.setImageResource(R.mipmap.ic_sex_male);
        }
        if (sex == 2) {
            ivSex.setImageResource(R.mipmap.ic_sex_female);
        }
        if (TextUtils.isEmpty(web)) {
            tvUserWeb.setText(getString(R.string.not_binding_web));
        } else {
            tvUserWeb.setText(web);
        }
        if (!avatar.equals(imageUrl))
            ImageLoader.getInstance().displayCricleImage((BaseActivity) getActivity(), avatar, ivUserIcon);
        imageUrl = avatar;
        tvUserName.setText(nickName);
        tvUserEmail.setText(userInfo.getEmail());
        ArrayMap<String, Object> map = new ArrayMap<>();
        map.put("uid", uid);
//        map.put("token", token);
        map.put("mobile", userInfo.getMobile() + "");
        map.put("loginType", 2);
//        presenter.saveClientTypeData(map);
        if (Utils.checkMobileNumber(userInfo.getMobile())) {
            tvUserPhone.setText(userInfo.getMobile());
        } else {
            tvUserPhone.setText(getString(R.string.not_binding_mobile));
        }
        if (Utils.checkEmail(userInfo.getEmail())) {
            tvUserEmail.setText(userInfo.getEmail());
        } else {
            tvUserEmail.setText(getString(R.string.not_binding_email));
        }
        tvPrompt.setText(userInfo.getSchool() + " " + userInfo.getDegree());
        if (!TextUtils.isEmpty(userInfo.getDomain()) && TextUtils.isEmpty(userInfo.getPosition())) {
            tvUserPosition.setText(userInfo.getDomain() + " " + userInfo.getPosition());
        } else {
            tvUserPosition.setVisibility(View.GONE);
        }

        List<UserInfoEntity.IntroductionBean> introduction = userInfoEntity.getIntroduction();
        if (null != introduction && introduction.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            List<ClickEntity> list = new ArrayList<>();
            for (UserInfoEntity.IntroductionBean introductionBean : introduction) {
                list.add(new ClickEntity(introductionBean));
            }
            itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_scholar_education, list, type);
            recyclerView.setAdapter(itemClickAdapter);
        }else{
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure(String msg, Exception ex) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @Override
    public void onRefresh() {
        initNetwork();
    }
}
