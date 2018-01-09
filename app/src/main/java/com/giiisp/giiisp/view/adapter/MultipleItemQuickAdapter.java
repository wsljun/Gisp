package com.giiisp.giiisp.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.entity.HomeEntity;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.utils.ImageLoader;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.FragmentActivity;
import com.giiisp.giiisp.view.activity.PaperDetailsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.giiisp.giiisp.R.layout.item_user_info;
import static com.giiisp.giiisp.base.BaseActivity.uid;


/**
 * 多个item的适配器
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * modify by AllenCoder
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<ClickEntity, BaseViewHolder> {
    private BaseActivity context;

    public MultipleItemQuickAdapter(BaseActivity context, List data) {
        super(data);
        this.context = context;
        addItemType(R.layout.item_home_head, R.layout.item_home_head);
        addItemType(R.layout.item_home_recycler, R.layout.item_home_recycler);
        addItemType(R.layout.item_witness_head, R.layout.item_witness_head);
        addItemType(R.layout.item_witness_people, R.layout.item_witness_people);
        addItemType(R.layout.item_witness_end, R.layout.item_witness_end);
        addItemType(R.layout.item_user_info, item_user_info);
        addItemType(R.layout.item_recycler_head, R.layout.item_recycler_head);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClickEntity item) {
        String title = "";
        switch (helper.getItemViewType()) {
            case R.layout.item_user_info:
                if (item.getUserInfoEntity() != null) {
                    UserInfoEntity userInfoEntity = item.getUserInfoEntity();
                    UserInfoEntity.UserInfoBean userInfo = userInfoEntity.getUserInfo();
                    UserInfoEntity.NumBean num = userInfoEntity.getNum();
                    helper.setText(R.id.tv_user_name, userInfo.getRealName());
                    helper.setText(R.id.tv_paper_number, num.getPaperNum() + "");
                    helper.setText(R.id.tv_review_number, num.getSummarizeNum() + "");
                    helper.setText(R.id.tv_fans_number, num.getFollowedNum() + "");
                    helper.setText(R.id.tv_follow_number, num.getFollowNum() + "");
                    if (!TextUtils.isEmpty(userInfoEntity.getIsFollowed())) {
                        helper.getView(R.id.iv_attention).setSelected(userInfoEntity.getIsFollowed().equals("0"));
                    }
                    if (userInfo.getSex() == 1) {
                        ImageLoader.getInstance().displayImage(context, R.mipmap.ic_sex_male, helper.getView(R.id.iv_sex));
                    }
                    if (userInfo.getSex() == 2) {
                        ImageLoader.getInstance().displayImage(context, R.mipmap.ic_sex_female, helper.getView(R.id.iv_sex));
                    }
                    if (TextUtils.isEmpty(userInfo.getWeb())) {
                        helper.setText(R.id.tv_user_web, "未添加个人网址");
                    } else {
                        helper.setText(R.id.tv_user_web, userInfo.getWeb());
                    }
                    if (Utils.checkMobileNumber(userInfo.getMobile())) {
                        helper.setText(R.id.tv_user_phone, userInfo.getMobile());
                    } else {
                        helper.setText(R.id.tv_user_phone, "未绑定手机号码");
                    }
                    if (Utils.checkEmail(userInfo.getEmail())) {
                        helper.setText(R.id.tv_user_email, userInfo.getEmail());
                    } else {
                        helper.setText(R.id.tv_user_email, "未绑定邮箱");
                    }
                    helper.setVisible(R.id.iv_attention, !Objects.equals(item.getPaperId(), uid + ""));
                    helper.setVisible(R.id.tv_user_phone, Objects.equals(item.getPaperId(), uid + ""));
                    helper.getView(R.id.iv_attention).setSelected(Objects.equals("0", userInfoEntity.getIsFollowed())); // todo getIsFollowed
                    ImageLoader.getInstance().displayCricleImage(context, userInfo.getAvatar(), (ImageView) helper.getView(R.id.iv_user_icon));
                    helper.setText(R.id.tv_prompt, userInfo.getSchool() + " " + userInfo.getDegree());
                    helper.setVisible(R.id.tv_user_position, !TextUtils.isEmpty(userInfo.getDomain()) || !TextUtils.isEmpty(userInfo.getPosition()));
                    helper.setText(R.id.tv_user_position, userInfo.getDomain() + "  " + userInfo.getPosition());
                }

                helper.addOnClickListener(R.id.iv_attention)
                        .addOnClickListener(R.id.tv_paper_number)
                        .addOnClickListener(R.id.tv_paper)
                        .addOnClickListener(R.id.tv_review_number)
                        .addOnClickListener(R.id.tv_review)
                        .addOnClickListener(R.id.tv_follow_number)
                        .addOnClickListener(R.id.tv_follow)
                        .addOnClickListener(R.id.tv_fans_number)
                        .addOnClickListener(R.id.tv_fans);

                break;
            case R.layout.item_recycler_head:
                List<ClickEntity> arrayList = new ArrayList<>();
                RecyclerView recyclerView = helper.getView(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                if (item.getUserInfoEntity() != null) {
                    UserInfoEntity userInfoEntity = item.getUserInfoEntity();

                    switch (item.getString()) {
                        case "authentication_info":
                            if (!TextUtils.isEmpty(userInfoEntity.getAuthen().getOrganization()))
                                arrayList.add(new ClickEntity("机　　构:", userInfoEntity.getAuthen().getOrganization()));
                            if (!TextUtils.isEmpty(userInfoEntity.getAuthen().getDepartment()))
                                arrayList.add(new ClickEntity("部　　门:", userInfoEntity.getAuthen().getDepartment()));
                            if (!TextUtils.isEmpty(userInfoEntity.getAuthen().getPosition()))
                                arrayList.add(new ClickEntity("职　　称:", userInfoEntity.getAuthen().getPosition()));
                            if (!TextUtils.isEmpty(userInfoEntity.getAuthen().getMajor()))
                                arrayList.add(new ClickEntity("研究领域:", userInfoEntity.getAuthen().getMajor()));

                            if (arrayList.size() > 0) {
                                title = "认证信息";
                                recyclerView.setAdapter(new ItemClickAdapter(context, R.layout.item_authentication_info, arrayList, item.getString()));
                            } else {
                                helper.setVisible(R.id.tv_review_list, false);
                            }


                            break;
                        case "scholar_education":
                            List<UserInfoEntity.IntroductionBean> introduction = userInfoEntity.getIntroduction();
                            if (introduction != null && introduction.size() > 0) {
                                for (UserInfoEntity.IntroductionBean introductionBean : introduction) {
                                    StringBuilder buffer = new StringBuilder();
                                    if (!TextUtils.isEmpty(introductionBean.getSchool())) {
                                        buffer.append(introductionBean.getSchool());
                                    }
                                    if (!TextUtils.isEmpty(introductionBean.getMajor())) {
                                        buffer.append(" ");
                                        buffer.append(introductionBean.getMajor());
                                    }
                                    if (!TextUtils.isEmpty(introductionBean.getDegree())) {
                                        buffer.append(" ");
                                        buffer.append(introductionBean.getDegree());
                                    }
                                    if (!TextUtils.isEmpty(introductionBean.getEduBackground())) {
                                        buffer.append(" ");
                                        buffer.append(introductionBean.getEduBackground());
                                    }
//                                    arrayList.add(new ClickEntity(introductionBean.getTimeStart() + "~" + introductionBean.getTimeEnd(), buffer.toString()));
                                    // TODO 学者详情页
                                    arrayList.add(new ClickEntity(introductionBean));
                                }
                            }

                            title = "学者简介";
                            recyclerView.setAdapter(new ItemClickAdapter(context, R.layout.item_scholar_education, arrayList, item.getString()));

                            break;
                        case "paper_indexes":
                            UserInfoEntity.SummarizeBean paper = userInfoEntity.getPaper();
                            title = "最新论文";
                            arrayList.add(new ClickEntity(paper));
                            initChildRecylerView(item, arrayList, recyclerView);
                            break;
                        case "summarize_indexes":
                            UserInfoEntity.SummarizeBean summarize = userInfoEntity.getSummarize();
                            arrayList.add(new ClickEntity(summarize));
                            initChildRecylerView(item, arrayList, recyclerView);
                            title = "最新综述";
                            break;

                    }
                    helper.setText(R.id.tv_review_list, title);
                }

                break;
            case R.layout.item_home_head:
                if (item.getHomeEntity() != null) {
                    HomeEntity homeEntity = item.getHomeEntity();
                    List<HomeEntity.PageInfoBean> pageInfo = homeEntity.getPageInfo();

                    ViewPager viewPager = helper.getView(R.id.viewPager);
                    List<HomeEntity.PageInfoBean.ActivityBean.RowsBeanXX> rows = null;
                    if (pageInfo.get(0).getActivity().getRows() != null && pageInfo.get(0).getActivity().getRows().size() > 0) {
                        rows = pageInfo.get(0).getActivity().getRows();
                    }
                    List<String> imageUrl = new ArrayList<>();
                    List<String> pathUrl = new ArrayList<>();
                    if (rows != null && rows.size() > 0) {
                        for (HomeEntity.PageInfoBean.ActivityBean.RowsBeanXX row : rows) {
                            imageUrl.add(row.getPath());
                            pathUrl.add(row.getLink());
                        }
                        viewPager.setAdapter(new ImageAdapter(context, imageUrl, pathUrl));
                    }
                }

                RecyclerView recyclerview = helper.getView(R.id.recycler_view);
                recyclerview.setLayoutManager(new GridLayoutManager(context, 4));
                List<ClickEntity> list_head = new ArrayList<>();
                list_head.add(new ClickEntity("综述", R.mipmap.home_summary));
                list_head.add(new ClickEntity("论文", R.mipmap.home_paper));
                list_head.add(new ClickEntity("学者", R.mipmap.home_scholar));
                list_head.add(new ClickEntity("会议", R.mipmap.home_meeting));
                ItemClickAdapter itemClickAdapter = new ItemClickAdapter(context, R.layout.item_home_tab, list_head, "");
                itemClickAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (position) {
                            case 0:
                                FragmentActivity.actionActivity(context, "summary_list");
                                break;
                            case 1:
                                FragmentActivity.actionActivity(context, "paper_list");
                                break;
                            case 2:
                                FragmentActivity.actionActivity(context, "scholar_list");
                                break;
                            case 3:
                                Utils.showToast("暂未开放");
                                //                                DubbingActivity.actionActivity(context);
                                break;


                        }
                    }
                });
                recyclerview.setAdapter(itemClickAdapter);
                break;
            case R.layout.item_home_recycler:
                helper.setText(R.id.tv_item_title, item.getString());
                RecyclerView view = helper.getView(R.id.recycler_view_child);
                List<ClickEntity> list = new ArrayList<>();
                if (item.getHomeEntity() != null) {
                    HomeEntity homeEntity = item.getHomeEntity();
                    List<HomeEntity.PageInfoBean> pageInfo = homeEntity.getPageInfo();
                    List<HomeEntity.PageInfoBean.PaperBean.RowsBeanX> rows = null;
                    if (pageInfo != null && pageInfo.size() > 0) {
                        switch (item.getString()) {
                            case "综述专栏":
                                if (pageInfo.get(0).getSummarize() != null && pageInfo.get(0).getSummarize().getRows() != null)
                                    rows = pageInfo.get(0).getSummarize().getRows();
                                break;
                            case "热门推荐":
                                if (pageInfo.get(0).getPaper() != null && pageInfo.get(0).getPaper().getRows() != null)
                                    rows = pageInfo.get(0).getPaper().getRows();
                                break;
                        }
                        if (rows != null)
                            for (HomeEntity.PageInfoBean.PaperBean.RowsBeanX row : rows) {
                                ClickEntity clickEntity = new ClickEntity(row.getTitle(), row.getPath());
                                clickEntity.setPaperId(row.getId() + "");
                                clickEntity.setVersion(row.getVersion());
                                clickEntity.setIsEncrypt(row.getIsEncrypt());
                                list.add(clickEntity);

                            }

                    }
                }


                LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                view.setLayoutManager(mLayoutManager);
                ItemClickAdapter itemClickAdapterChild = new ItemClickAdapter(context, R.layout.item_home_child, list, "");
                view.setAdapter(itemClickAdapterChild);
                itemClickAdapterChild.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ClickEntity clickEntity = (ClickEntity) adapter.getItem(position);
                        if (clickEntity != null) {
                            String paperId = clickEntity.getPaperId();
                            String version = clickEntity.getVersion();
                            ArrayList<String> arrayVersion = new ArrayList<>();
                            arrayVersion.add(version);
//                            PaperDetailsActivity.actionActivity(context, paperId, arrayVersion, "home");

                            switch (item.getString()) {
                                case "综述专栏":
                                case "热门推荐":
                                    if (clickEntity.getIsEncrypt().equals("0")) {
                                        PaperDetailsActivity.checkPwd(context, paperId, arrayVersion, "home");
                                    }else{
                                        PaperDetailsActivity.actionActivity(context, paperId, arrayVersion, "home");
                                    }
                                    break;
                                default:
                                    break;

                            }
                        }


                    }
                });
                break;
        }
    }


    private void initChildRecylerView(ClickEntity item, List<ClickEntity> arrayList, RecyclerView recyclerView) {
        ItemClickAdapter itemClickAdapter = new ItemClickAdapter(context, R.layout.item_paper_indexes, arrayList, item.getString());
        itemClickAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ClickEntity multipleItemQuickAdapterItem = (ClickEntity) adapter.getItem(position);
                if (multipleItemQuickAdapterItem != null && multipleItemQuickAdapterItem.getSummarizeBean() != null) {
                    UserInfoEntity.SummarizeBean paper = multipleItemQuickAdapterItem.getSummarizeBean();
                    if (paper != null) {
                        String id = paper.getId();
                        String version = paper.getVersion();
                        ArrayList<String> list = new ArrayList<>();
                        if (!TextUtils.isEmpty(version))
                            list.add(version);
                        if (list.size() > 0 && !TextUtils.isEmpty(id))
                            PaperDetailsActivity.actionActivity(context, id, list, "online_paper");
                    }
                }
            }
        });
        recyclerView.setAdapter(itemClickAdapter);
    }

    private static class ImageAdapter extends PagerAdapter {

        private List<String> viewlist;
        private List<String> pathlist;
        private BaseActivity activity;

        public ImageAdapter(BaseActivity activity, List<String> viewlist, List<String> pathlist) {
            this.viewlist = viewlist;
            this.pathlist = pathlist;
            this.activity = activity;
        }

        @Override
        public int getCount() {
            //设置成最大，使用户看不到边界
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            //Warning：不要在这里调用removeView
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //对ViewPager页号求模取出View列表中要显示的项
            ImageView imageView = new ImageView(activity);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            position %= viewlist.size();
            if (position < 0) {
                position = viewlist.size() + position;
            }
            final String path = viewlist.get(position);
            final String link = pathlist.get(position);
            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent vp = imageView.getParent();
            if (vp != null) {
                ViewGroup parent = (ViewGroup) vp;
                parent.removeView(imageView);
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("--->>", "onClick: " + link);
                    FragmentActivity.actionActivity(activity, "webView_home", link);
                    //                    helper.savePicture(System.currentTimeMillis() + ".jpg", path);
                }
            });
            ImageLoader.getInstance().displayImage(activity, path, imageView);
            //            view.setImageURI(Uri.parse(path));
            container.addView(imageView);
            //add listeners here if necessary
            return imageView;
        }
    }
}
