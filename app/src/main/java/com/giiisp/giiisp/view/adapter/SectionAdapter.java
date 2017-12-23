package com.giiisp.giiisp.view.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.entity.MySection;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
    private BaseActivity activity;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             AnswerEntity new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(BaseActivity activity, int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
        this.activity = activity;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.tv_review_list, item.header);
        helper.setVisible(R.id.tv_review_list, item.isHeader());

    }


    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        List<ClickEntity> list = new ArrayList<>();

        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        list.add(new ClickEntity());

        switch (helper.getLayoutPosition()) {
            case 1:
                helper.setVisible(R.id.tv_edit, true);
                list.add(new ClickEntity());
                list.add(new ClickEntity());
                list.add(new ClickEntity());
                recyclerView.setAdapter(new ItemClickAdapter(activity, R.layout.item_authentication_info, list,""));
                break;
            case 3:
                list.add(new ClickEntity());
                list.add(new ClickEntity());
                list.add(new ClickEntity());
                recyclerView.setAdapter(new ItemClickAdapter(activity, R.layout.item_editinfo_education, list,""));
                break;
            case 5:
                recyclerView.setAdapter(new ItemClickAdapter(activity, R.layout.item_paper_indexes, list,""));
                break;
            case 7:
                recyclerView.setAdapter(new ItemClickAdapter(activity, R.layout.item_paper_indexes, list,""));
                break;

        }

    }
}
