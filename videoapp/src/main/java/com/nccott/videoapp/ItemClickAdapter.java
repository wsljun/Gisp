package com.nccott.videoapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

/**
 * 重用的适配器
 */
public class ItemClickAdapter extends BaseQuickAdapter<PhotoDao, BaseViewHolder> {
    private int layoutResId;
    private String type = "";
    private int selectedPosition = 0;
    private List<PhotoDao> photodaos ;
    private Context mContext;
    private MyVideoThumbLoader mVideoThumbLoader;

    public ItemClickAdapter(Context context ,int layoutId, @Nullable List<PhotoDao> data) {
        super(layoutId, data);
        layoutResId = layoutId;
        photodaos = data;
        mVideoThumbLoader = new MyVideoThumbLoader();
        this.mContext = context;
    }

    public ItemClickAdapter(@Nullable List<PhotoDao> data) {
        super(data);
    }

    public ItemClickAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, PhotoDao item) {
        MyImageView imageView = helper.getView(R.id.iv_pic); // TODO
        if("2".equals(item.getType())){ // 视频
//             imageView.setImageBitmap(createVideoThumbnail(item.getPath(),0,0));
            imageView.setImageBitmap(createVideoThumbnail(item.getPath(),1));
//             imageView.setTag(item.getPath());
//             mVideoThumbLoader.showThumbByAsynctack(item.getPath(), imageView);
        }else{ // png ,gif
            Glide.with(mContext).load(item.getPath())
                    .into(imageView);

        }
        helper.addOnClickListener(R.id.iv_pic);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private Bitmap createVideoThumbnail(String url, int width, int height) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        int kind = MediaStore.Video.Thumbnails.MINI_KIND;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(url, new HashMap<String, String>());
            } else {
                retriever.setDataSource(url);
            }
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }
//        if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
//            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
//        }
        return bitmap;
    }


    public static Bitmap createVideoThumbnail(String filePath, int kind)
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

}
