package com.giiisp.giiisp.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.api.ApiStore;
import com.giiisp.giiisp.api.UrlConstants;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.model.GlideApp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;


public class ImageLoader {
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";
    public static final String TAG = "ImageLoader";
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
                 .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
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



    /**
     *获取视频第一帧，
     *
     * @param filePath
     * @param kind
     * @return Bitmap
     */
    @Nullable
    public  Bitmap createVideoThumbnail(String filePath, int kind)
    {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try
        {
            if (filePath.startsWith("http://")
                    || filePath.startsWith("https://")
                    || filePath.startsWith("widevine://"))
            {
                retriever.setDataSource(filePath, new Hashtable<String, String>());
            }
            else
            {
                retriever.setDataSource(filePath);
            }
//            bitmap = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST_SYNC); //retriever.getFrameAtTime(-1);
            bitmap = retriever.getFrameAtTime();
        }
        catch (IllegalArgumentException ex){
            // Assume this is a corrupt video file
            ex.printStackTrace();
        }
        catch (RuntimeException ex){
            // Assume this is a corrupt video file.
            ex.printStackTrace();
        }
        finally{
            try
            {
                retriever.release();
            }
            catch (RuntimeException ex)
            {
                // Ignore failures while cleaning up.
                ex.printStackTrace();
            }
        }

        if (bitmap == null)
        {
            return null;
        }

        if (kind == MediaStore.Images.Thumbnails.MINI_KIND)
        {//压缩图片 开始处
            // Scale down the bitmap if it's too large.
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int max = Math.max(width, height);
            if (max > 512)
            {
                float scale = 512f / max;
                int w = Math.round(scale * width);
                int h = Math.round(scale * height);
                bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);
            }//压缩图片 结束处
        }
        else if (kind == MediaStore.Images.Thumbnails.MICRO_KIND)
        {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap,
                    96,
                    96,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }
    /**
     * Bitmap转换成byte[]并且进行压缩,压缩到不大于maxkb
     * @param bitmap
     * @param maxkb
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int maxkb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > maxkb&& options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
//        bitmap = BitmapFactory.decodeByteArray(output.toByteArray(),0,output.toByteArray().length);
        return output.toByteArray();
    }

    /**
     * 根据一个网络连接(String)获取bitmap图像
     *
     * @param imageUri
     * @return
     */
    static Bitmap bitmap ;
    public static Bitmap getbitmap(String imageUri) {
        Log.setShow(true);
        Log.v(TAG, "getbitmap:" + imageUri);
        // 显示网络上的图片
        try {
            URL myFileUrl = new URL(imageUri);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); //设置请求方法
            conn.setConnectTimeout(10000); //设置连接服务器超时时间
            conn.setReadTimeout(5000);  //设置读取数据超时时间
            conn.connect(); //开始连接
            int responseCode = conn.getResponseCode(); //得到服务器的响应码
            if (responseCode == 200) {
                //访问成功
                InputStream is = conn.getInputStream(); //获得服务器返回的流数据
                bitmap = BitmapFactory.decodeStream(is); //根据流数据 创建一个bitmap对象
                is.close();
                Log.d(TAG, "responseCode 200 = ：" +  bitmap.getByteCount());
                return bitmap;

            } else {
                //访问失败
                Log.d(TAG, "访问失败===responseCode：" + responseCode);
            }
            Log.v(TAG, "image download finished." + imageUri);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            bitmap = null;
        } catch (IOException e) {
            e.printStackTrace();
            Log.v(TAG, "getbitmap bmp fail---");
            bitmap = null;
        }
        return bitmap;
    }
}