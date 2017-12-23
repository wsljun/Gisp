package com.giiisp.giiisp.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.api.UrlConstants;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.model.GlideApp;

import java.io.File;


public class ImageLoader {
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static class ImageLoaderHolder {
        private static final ImageLoader INSTANCE = new ImageLoader();
    }

    private ImageLoader() {
    }

    public static final ImageLoader getInstance() {
        return ImageLoaderHolder.INSTANCE;
    }


    //直接加载网络图片
    public void displayImage(BaseActivity context, String url, ImageView imageView) {
        if (url.contains(UrlConstants.RequestUrl.QN_ADDRESS)){
            url +="-watermark";
        }
        if (context!=null)
        GlideApp.with(context)
                .load(url)
                .placeholder(R.mipmap.placeholder_icon)
                .error(R.mipmap.ic_launcher)
//                .centerInside()
                .into(imageView);
    }
    //直接加载网络图片
    public void displayImage(BaseActivity context, String url, ImageView imageView,Boolean b) {
        if (context!=null)
        GlideApp.with(context)
                .load(url)
                .placeholder(R.mipmap.placeholder_icon)
                .error(R.mipmap.ic_launcher)
//                .centerInside()
                .into(imageView);
    }


    //加载SD卡图片
    public void displayImage(BaseActivity context, File file, ImageView imageView) {
        GlideApp.with(context)
                .load(file)
                .centerCrop()
                .into(imageView);

    }

    //加载SD卡图片并设置大小
    public void displayImage(BaseActivity context, File file, ImageView imageView, int width, int height) {
        GlideApp.with(context)
                .load(file)
                .override(width, height)
                .centerCrop()
                .into(imageView);

    }

    //加载网络图片并设置大小
    public void displayImage(BaseActivity context, String url, ImageView imageView, int width, int height) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .override(width, height)
                .into(imageView);
    }

    //加载drawable图片
    public void displayImage(BaseActivity context, int resId, ImageView imageView) {
        GlideApp.with(context)
                .load(resourceIdToUri(context, resId))
                .into(imageView);
    }

    //加载drawable图片显示为圆形图片
    public void displayCricleImage(BaseActivity context, int resId, ImageView imageView) {
        GlideApp.with(context)
                .load(resourceIdToUri(context, resId))
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    //加载网络图片显示为圆形图片
    public void displayCricleImage(BaseActivity context, String url, ImageView imageView) {
        if (context==null)return;
        GlideApp
                .with(context)
                .load(url)
                //.centerCrop()//网友反馈，设置此属性可能不起作用,在有些设备上可能会不能显示为圆形。
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    //加载SD卡图片显示为圆形图片
    public void displayCricleImage(BaseActivity context, File file, ImageView imageView) {
        GlideApp.with(context)
                .load(file)
                //.centerCrop()
                .transform(new GlideCircleTransform(context))
                .into(imageView);

    }
    //将资源ID转为Uri
    public Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }


    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}