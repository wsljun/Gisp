package com.giiisp.giiisp.utils;

import android.app.Activity;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class SDFileHelper {

    private Activity context;
    private String file;

    public SDFileHelper() {
    }

    public SDFileHelper(Activity context) {
        super();
        this.context = context;
    }

   /* //Glide保存图片
    public void savePicture(final String fileName, String url) {
        Glide.with(context).asBitmap().load(url).into(new SimpleTarget<byte[]>() {

            @Override
            public void onResourceReady(final byte[] bytes, Transition<? super byte[]> glideAnimation) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            savaFileToSD(fileName, bytes);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        });
    }
*/

    /**
     * 获取指定文件大小
     *
     * @param
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static double FormetFileSize(long fileS) {
        double wrongSize = 0;
        if (fileS == 0) {
            return wrongSize;
        }
        return (double) fileS / 1048576;
    }

    //往SD卡写入文件的方法
    public void savaFileToSD(String filename, byte[] bytes) throws Exception {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            if (externalFilesDir != null) {
                String filePath = externalFilesDir.getAbsolutePath();

                File dir1 = new File(filePath);
                if (!dir1.exists()) {
                    dir1.mkdirs();
                }
                file = filePath + "/" + filename;
                //这里就不要用openFileOutput了,那个是往手机内存中写数据的
                FileOutputStream output = null;
                output = new FileOutputStream(file);
                output.write(bytes);
                output.close();

                //将bytes写入到输出流中
                //关闭输出流
                Log.i("--->>", "savaFileToSD: " + filePath);
                //                Utils.showToast("图片已成功保存到" + file);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "图片已成功保存到" + file, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        } else {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "SD卡不存在或者不可读写" + file, Toast.LENGTH_SHORT).show();
                }
            });
        }


        //            Utils.showToast("SD卡不存在或者不可读写");

/*
        //         其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file, filename, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(file))));*/
    }

}