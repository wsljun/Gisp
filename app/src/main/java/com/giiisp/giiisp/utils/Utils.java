package com.giiisp.giiisp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Environment;
import android.support.annotation.StringRes;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据库存储    文件存储   Sp      内容提供者
 * <p>
 * <p>
 * false 表示不是第一次运行
 * true 表示第一运行
 */
public class Utils {
    public static void showDialog(BaseActivity activity, String title, DialogInterface.OnClickListener listener) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题 Attempt to invoke virtual method 'android.content.res.Resources$Theme' on a null object reference
	at android.app.AlertDialog.resolveDialogTheme(AlertDialog.java:225)
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */

        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(activity);
        normalDialog.setIcon(null);
        normalDialog.setTitle(title);
        normalDialog.setPositiveButton(R.string.confirm,
                listener);
        normalDialog.setNegativeButton(R.string.cancel, null);
        // 显示
        normalDialog.show();
    }


    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else {
                String[] filePaths = file.list();
                for (String path : filePaths) {
                    deleteFile(filePath + File.separator + path);
                }
                file.delete();
            }
        }
    }

    public static String fileName(String fileName) {
        fileName = fileName.substring(fileName.lastIndexOf('/') + 1);
        if (fileName.startsWith("\"")) {
            fileName = fileName.substring(1);
        }
        if (fileName.endsWith("\"")) {
            fileName = fileName.substring(0, fileName.length() - 1);
        }
        return fileName;
    }

    public static boolean isFirst(Context context) {
        SharedPreferences sp = context.getSharedPreferences("isFirst", Context.MODE_PRIVATE);
        boolean run = sp.getBoolean("run", true);
        if (run) {
            sp.edit().putBoolean("run", false).commit();
        }
        return run;
    }

    //    public static String toDateStringFromIso(String sdate) {
    //        if ("null".equals(sdate) || "NULL".equals(sdate) || "".equals(sdate) || sdate == null) {
    //            return "";
    //        }
    //        Date d1 = null;
    //        try {
    //            d1 = dateFormaterIsodate.get().parse(sdate);
    //            sharecalendar.setTime(d1);
    //            sharecalendar.set(Calendar.HOUR_OF_DAY,sharecalendar.get(Calendar.HOUR_OF_DAY)+8);
    //            d1=sharecalendar.getTime();
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            return "";
    //        }
    //        return dateFormater4.get().format(d1);
    //    }

    /**
     * 验证手机号码1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}
     *
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            if (!email.contains("@"))
                return flag;
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    private static Toast mToast;

    public static void showToast(@StringRes int resId) {
        if (mToast != null)
            mToast.cancel();
/*        mToast = Toast.makeText(BaseApp.app.getApplicationContext(), resId, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER,0,0);
        View view = mToast.getView();*/
        mToast = new Toast(BaseApp.app.getApplicationContext());
        View custonView = LayoutInflater.from(BaseApp.app.getApplicationContext()).inflate(R.layout.layout_custom_toast, null);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        TextView viewById = (TextView) custonView.findViewById(R.id.text);
        viewById.setText(resId);
        mToast.setView(custonView);
        mToast.show();
    }

    public static void showToast(String resId) {
        if (mToast != null)
            mToast.cancel();
/*        mToast = Toast.makeText(BaseApp.app.getApplicationContext(), resId, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER,0,0);
        View view = mToast.getView();*/
        mToast = new Toast(BaseApp.app.getApplicationContext());
        View custonView = LayoutInflater.from(BaseApp.app.getApplicationContext()).inflate(R.layout.layout_custom_toast, null);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        TextView viewById = (TextView) custonView.findViewById(R.id.text);
        viewById.setText(resId);
        mToast.setView(custonView);
        mToast.show();
    }
    public static void showToast(String resId,Boolean b) {
        if (mToast != null)
            mToast.cancel();
/*        mToast = Toast.makeText(BaseApp.app.getApplicationContext(), resId, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER,0,0);
        View view = mToast.getView();*/
        mToast = new Toast(BaseApp.app.getApplicationContext());
        View custonView = LayoutInflater.from(BaseApp.app.getApplicationContext()).inflate(R.layout.layout_custom_toast, null);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_LONG);
        TextView viewById = (TextView) custonView.findViewById(R.id.text);
        viewById.setText(resId);
        mToast.setView(custonView);
        mToast.show();
    }



/*    public static class MD5 {

        public static String getMD5(String val) throws NoSuchAlgorithmException {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            md5.update(val.getBytes());
            byte[] m = md5.digest();//加密
            byte[] digest = md5.digest(m);
            return getString(m);
        }
        private static String getString(byte[] b){
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < b.length; i ++){
                sb.append(b[i]);
            }
            return sb.toString();
        }
    }*/

    /**
     * 设备名称
     *
     * @return
     */
    public static String getDeviceName() {
        Context sContext = BaseApp.app.getApplicationContext();
        String deviceName = SharedPreferencesHelper.getInstance(sContext).getStringValue("DeviceName");
        if (TextUtils.isEmpty(deviceName)) {
            deviceName = android.os.Build.MODEL;
            if (isNumber(deviceName) || TextUtils.isEmpty(deviceName))
                //                deviceName = ShellUtils.runCmd("hideapi_hook device");
                if (!TextUtils.isEmpty(deviceName)) {
                    SharedPreferencesHelper.getInstance(sContext).putStringValue("DeviceName", deviceName);
                }
        }
        return deviceName;
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 软件版本
     *
     * @return
     */
    public static String getAppVersion() {
        try {
            PackageManager manager = BaseApp.app.getApplicationContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(BaseApp.app.getApplicationContext()
                    .getPackageName(), 0);
            String sVersion = String.valueOf(info.versionName);
            return sVersion;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 软件版本号
     *
     * @return
     */
    public static int getAppVersionCode() {
        try {
            PackageManager manager = BaseApp.app.getApplicationContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(BaseApp.app.getApplicationContext().getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getIMSI() {
        Context sContext = BaseApp.app.getApplicationContext();
        String imsi = SharedPreferencesHelper.getInstance(sContext).getStringValue("IMSI");
        if (!TextUtils.isEmpty(imsi))
            return imsi;
        try {
            TelephonyManager tm = (TelephonyManager) sContext
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imsi = tm.getSubscriberId();
            if (!TextUtils.isEmpty(imsi))
                SharedPreferencesHelper.getInstance(sContext).putStringValue("IMSI", imsi);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return imsi;
    }

    /**
     * 判断本地图片是否是Gif图
     *
     * @param file Gif图文件路径
     * @return
     */
    public static boolean isGifFile(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            int[] flags = new int[5];
            flags[0] = inputStream.read();
            flags[1] = inputStream.read();
            flags[2] = inputStream.read();
            flags[3] = inputStream.read();
            inputStream.skip(inputStream.available() - 1);
            flags[4] = inputStream.read();
            inputStream.close();
            return flags[0] == 71 && flags[1] == 73 && flags[2] == 70 && flags[3] == 56 && flags[4] == 0x3B;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //使用Bitmap加Matrix来缩放
    public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float scaleWidth = ((float) w) / width;
        float scaleHeight = ((float) h) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        return Bitmap.createBitmap(bitmap, 0, 0, width,
                height, matrix, true);
    }

    public static Bitmap mergeBitmap(Bitmap firstBitmap, Bitmap secondBitmap) {
        Bitmap bitmap = Bitmap.createBitmap(firstBitmap.getWidth(), firstBitmap.getHeight(),
                firstBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(firstBitmap, new Matrix(), null);
        canvas.drawBitmap(secondBitmap, 4, 4, null);
        return bitmap;
    }

  /*  public static void showDialog(Activity context) {
        View view = context.getLayoutInflater().inflate(R.layout.layout_dialog, null);

        Dialog dialog = new Dialog(context, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 40;
        wl.y = DensityUtils.dp2px(context, 100f);
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }
    */
   /* *//**
     * 版本24以上
     *//*
    public Uri get24MediaFileUri(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "相册名字");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //创建Media File
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == TYPE_TAKE_PHOTO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return FileProvider.getUriForFile(this, getPackageName()+".fileprovider", mediaFile);
    }*/
    /**
     * 保存方法
     */
    public void saveBitmap(Bitmap bm) {
        File f = new File(Environment.getExternalStorageDirectory(), "mapIcon");
        if (!f.exists()) {
            f.getParentFile().mkdir();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // 状态栏高度
    private static  int statusBarHeight = 0;
    // 屏幕像素点
    private static final Point screenSize = new Point();

    // 获取屏幕像素点
    public static Point getScreenSize(Activity context) {

        if (context == null) {
            return screenSize;
        }
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            DisplayMetrics mDisplayMetrics = new DisplayMetrics();
            Display diplay = wm.getDefaultDisplay();
            if (diplay != null) {
                diplay.getMetrics(mDisplayMetrics);
                int W = mDisplayMetrics.widthPixels;
                int H = mDisplayMetrics.heightPixels;
                if (W * H > 0 && (W > screenSize.x || H > screenSize.y)) {
                    screenSize.set(W, H);
                }
            }
        }
        return screenSize;
    }

    // 获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        if (statusBarHeight <= 0) {
            Rect frame = new Rect();
            ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            statusBarHeight = frame.top;
        }
        if (statusBarHeight <= 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = context.getResources().getDimensionPixelSize(x);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return statusBarHeight;
    }

}
