package com.giiisp.giiisp.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * at 链接 搜索页面
 * Created by Thinkpad on 2017/6/7.
 */

public class AtLinkFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String type;

    public static AtLinkFragment newInstance(String param1, String param2) {
        AtLinkFragment fragment = new AtLinkFragment();
        Bundle args = new Bundle();
        args.putString("1005", param1);
        args.putString("1006", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_at;
    }

    @Override
    public void initView() {
        switch (type) {
            case "at":
                tvTitle.setText(R.string.contacts);
                etSearch.setHint(R.string.search);
                break;
            case "link":
                tvTitle.setText(R.string.link_page);
                etSearch.setHint(R.string.journalname_author_keyword);
                break;
        }
    }

    @Override
    public void initData() {
        super.initData();
        type = getArguments().getString("1005");
        String string = getArguments().getString("1006");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_back, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                getActivity().finish();
                break;
            case R.id.iv_close:
                etSearch.setText("");
                break;
        }
    }
}
