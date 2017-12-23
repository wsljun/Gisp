package com.giiisp.giiisp.view.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Thinkpad on 2017/7/10.
 */

public class ViewPagerImage extends PagerAdapter {

    private List<ImageView> viewlist;
    private Activity activity;

    public ViewPagerImage(Activity activity, List<ImageView> viewlist) {
        this.viewlist = viewlist;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        //设置成最大，使用户看不到边界
        return viewlist.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        ((ViewPager) container).removeView(viewlist.get(position % viewlist.size()));
        //Warning：不要在这里调用removeView
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
         /*   //对ViewPager页号求模取出View列表中要显示的项
            position %= viewlist.size();
            if (position < 0) {
                position = viewlist.size() + position;
            }
            ImageView path = viewlist.get(position);
            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。

            //            view.setImageURI(Uri.parse(path));
            Log.i("--->>", "instantiateItem: " + container.getChildCount());
            container.addView(path);
            //add listeners here if necessary
            return path;*/
        position %= viewlist.size();
        if (position < 0) {
            position = viewlist.size() + position;
        }

        ImageView imageView = viewlist.get(position);

        ViewParent vp = imageView.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(imageView);
        }
        ((ViewPager) container).addView((imageView), 0);
        return imageView;
    }
}