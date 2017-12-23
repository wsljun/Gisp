package com.giiisp.giiisp.view.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.utils.ImageLoader;

import java.util.List;

/**
 * 可以拖动的适配器
 * Created by luoxw on 2016/6/20.
 */
public class ItemDragAdapter extends BaseQuickAdapter<ClickEntity, BaseViewHolder> {
    BaseActivity activity;
    public ItemDragAdapter(BaseActivity activity, List<ClickEntity> data) {
        super(R.layout.item_download_finished, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, ClickEntity item) {
        List<ClickEntity> subItems = item.getSubItems();
        if (subItems != null) {
            StringBuilder buffer = new StringBuilder();
            for (ClickEntity subItem : subItems) {
                int photoNumber = subItem.getPhotoNumber();
                buffer.append(photoNumber);
                buffer.append(",");
            }
            buffer.delete(buffer.length() - 1, buffer.length());
            helper.setText(R.id.tv_download_page, "总下载" + subItems.size() + "页");
            helper.setText(R.id.tv_page_number, buffer.toString());

        }
        helper.setText(R.id.tv_title, item.getTitle()+item.getVersion());
        helper.setText(R.id.tv_time, item.getTime());
        helper.setVisible(R.id.iv_bg, !item.getTitle().equals("集思谱"));
        helper.setVisible(R.id.iv_icon, !item.getTitle().equals("集思谱"));
        helper.setVisible(R.id.linear_layout, !item.getTitle().equals("集思谱"));
        helper.setVisible(R.id.view, !item.getTitle().equals("集思谱"));

        ImageLoader.getInstance().displayCricleImage(activity,item.getUrl(), (ImageView) helper.getView(R.id.iv_icon));


        if (!TextUtils.isEmpty(item.getVersion())) {
            switch (item.getVersion()) {
                case "0":
                    helper.setText(R.id.tv_version, "完整版");
                    break;
                case "1":
                    helper.setText(R.id.tv_version, "精华版");
                    break;
                case "2":
                    helper.setText(R.id.tv_version, "摘要版");
                    break;
            }
        }
    }
}
