package com.giiisp.giiisp.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.giiisp.giiisp.base.BaseApp;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by root on 16-6-3.
 */
public class PackageUtil {


    /**
     *
     * 普通安装
     * @param context
     * @param apkPath
     */
    public static void installApkNormal(Context context, String apkPath) {
        if (context == null || TextUtils.isEmpty(apkPath)) {
            return;
        }


        File file = new File(apkPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            //provider authorities
            Uri apkUri = FileProvider.getUriForFile(context, "com.giiisp.giiisp.fileprovider", file);
            //Granting Temporary Permissions to a URI
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void installApkNormal(String title, String filePath) {
        try {
            Context sContext = BaseApp.app.getApplicationContext();
            File file = new File(filePath);
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            sContext.startActivity(intent);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    /**
     * 判断是否本机已安装了packageName指定的应用
     *
     * @param packageName
     * @return
     */
    public static boolean isInstalledApk(Context context, String packageName) {
        try {
            if (TextUtils.isEmpty(packageName)) {
                return false;
            }

            PackageInfo sPackageInfo = context.getPackageManager().getPackageInfo(packageName, 0);

            return sPackageInfo != null && sPackageInfo.applicationInfo.enabled;
        } catch (PackageManager.NameNotFoundException e) {
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断是否本机已安装了packageName指定的应用
     *
     * @param packageName
     * @param versionCode
     * @return
     */
    public static boolean isInstalledApk(String packageName, int versionCode) {
        try {
            if (TextUtils.isEmpty(packageName)) {
                return false;
            }

            Context sContext = BaseApp.app.getApplicationContext();
            PackageInfo sPackageInfo = sContext.getPackageManager().getPackageInfo(packageName, 0);

            if (sPackageInfo != null) {
                return sPackageInfo.versionCode >= versionCode;
            } else {
                return false;
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 启动应用的某个页面
     */
    public static boolean startAppActivity(String packageName, String className) {
        ComponentName componetName = new ComponentName(packageName, className);
        // 这个是另外一个应用程序的包名 ,这个参数是要启动的Activity
        try {
            Context sContext = BaseApp.app
                    .getApplicationContext();
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            intent.setComponent(componetName);
            sContext.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isUserApp(Context context, String pkg) {
        try {
            PackageManager sPackageManager = context.getPackageManager();
            PackageInfo sPackageInfo = sPackageManager.getPackageInfo(pkg, 0);
            if (sPackageInfo == null)
                return false;
            if (((sPackageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                    || ((sPackageInfo.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) > 0)) { // 个人软件
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isUserApp(String pkg) {
        if (TextUtils.isEmpty(pkg)) return false;
        Context context =  BaseApp.app.getApplicationContext();
        return isUserApp(context, pkg);
    }

    /**
     * 卸载应用
     *
     * @param packageName
     */
    public static void uninstallApp(Context context, String packageName) {
        try {
            if (isInstalledApk(packageName)) {
//                BaseApp.app.mUninstallingPkg = packageName;
                Uri packageUri = Uri.parse("package:" + packageName);
                Intent intent = new Intent(Intent.ACTION_DELETE, packageUri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否本机已安装了packageName指定的应用
     *
     * @param packageName
     * @return
     */
    public static boolean isInstalledApk(String packageName) {
        try {
            if (TextUtils.isEmpty(packageName)) {
                return false;
            }
            Context sContext =   BaseApp.app.getApplicationContext();
            PackageInfo sPackageInfo = sContext.getPackageManager().getPackageInfo(packageName, 0);

            if (sPackageInfo != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.d("", e.toString());
        }
        return false;
    }


    static public class AppSnippet {
        public CharSequence label;
        public Drawable icon;
        public String packageName;
        public String versionName;
        public int versionCode;
        // public String[] permissions;
    }

    public static AppSnippet getAppSnippet(Context context, Uri packageURI) {
        AppSnippet sAppSnippet = null;

        try {
            String apkPath = packageURI.getPath();
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
            Resources sResources = getUninstalledApkResources(context, apkPath);

            // 读取apk文件的信息
            if (packageInfo != null) {
                sAppSnippet = new AppSnippet();
                sAppSnippet.icon = sResources.getDrawable(packageInfo.applicationInfo.icon);// 图标
                sAppSnippet.packageName = packageInfo.packageName; // 包名
                sAppSnippet.versionName = packageInfo.versionName; // 版本号
                sAppSnippet.versionCode = packageInfo.versionCode; // 版本码
                // sAppSnippet.permissions = packageInfo.requestedPermissions;

                try {
                    sAppSnippet.label = (String) sResources.getText(packageInfo.applicationInfo.labelRes);// 名字
                } catch (Exception e) {
                    try {
                        sAppSnippet.label = pm.getApplicationLabel(packageInfo.applicationInfo);
                    } catch (Exception e2) {
                    }
                }
            }
        } catch (Exception e) {
        }
        return sAppSnippet;
    }

    public static Resources getUninstalledApkResources(Context context, String apkPath) {
        if (TextUtils.isEmpty(apkPath)) {
            return context.getResources();
        }

        File apkFile = new File(apkPath);
        if (!apkFile.exists()) {
            return context.getResources();
        }

        // AppInfoData appInfoData;
        String PATH_AssetManager = "android.content.res.AssetManager";

        try {
            // 反射得到pkgParserCls对象并实例化,有参数
            Class<?>[] typeArgs = {String.class};
            Object[] valueArgs = {apkPath};

            // 反射得到assetMagCls对象并实例化,无参
            Class<?> assetMagCls = Class.forName(PATH_AssetManager);
            Object assetMag = assetMagCls.newInstance();
            // 从assetMagCls类得到addAssetPath方法
            typeArgs = new Class[1];
            typeArgs[0] = String.class;
            Method assetMag_addAssetPathMtd = assetMagCls.getDeclaredMethod("addAssetPath", typeArgs);
            valueArgs = new Object[1];
            valueArgs[0] = apkPath;
            // 执行assetMag_addAssetPathMtd方法
            assetMag_addAssetPathMtd.invoke(assetMag, valueArgs);

            // 得到Resources对象并实例化,有参数
            Resources res = context.getResources();
            typeArgs = new Class[3];
            typeArgs[0] = assetMag.getClass();
            typeArgs[1] = res.getDisplayMetrics().getClass();
            typeArgs[2] = res.getConfiguration().getClass();
            Constructor<Resources> resCt = Resources.class.getConstructor(typeArgs);
            valueArgs = new Object[3];
            valueArgs[0] = assetMag;
            valueArgs[1] = res.getDisplayMetrics();
            valueArgs[2] = res.getConfiguration();
            // 这个是重点
            // 得到Resource对象后可以有很多用处
            res = (Resources) resCt.newInstance(valueArgs);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context.getResources();
    }

    /**
     * 获取apk签名
     */
    public static String getApkSignatureWithPath(Context context, String path) {
        try {
            Signature[] signatures = getSignature(context, path);

            if (signatures != null && signatures.length > 0) {
                return getSignatureMD5(signatures[0].toByteArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取apk签名
     */
    public static String getApkSignatureWithPackageName(Context context, String pkg) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(pkg, PackageManager.GET_SIGNATURES);

            if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                return getSignatureMD5(packageInfo.signatures[0].toByteArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Signature[] getSignature(Context context, String apkPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_SIGNATURES);
        return packageInfo.signatures;
    }

    private static String getSignatureMD5(byte[] signature) {
        try {
            return MD5.getMD5(signature);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isRunningInFront(String packageName) {
        Context context = BaseApp.app.getApplicationContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();

        if (infos.size() == 0)
            return false;
        for (ActivityManager.RunningAppProcessInfo rapi : infos) {
            if (rapi.processName.equals(packageName)
                    && (rapi.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND ||
                    rapi.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRunning(String packageName) {
        Context context = BaseApp.app.getApplicationContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();

        if (infos.size() == 0)
            return false;
        for (ActivityManager.RunningAppProcessInfo rapi : infos) {
            if (rapi.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static String getTopActivity(int type) {
        Context context =BaseApp.app.getApplicationContext();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null) {
            switch (type) {
                default:
                case 0:
                    return runningTaskInfos.get(0).topActivity.getClassName();
                case 1:
                    return runningTaskInfos.get(0).topActivity.getPackageName();
            }
        } else {
            return null;
        }
    }

    public static String getTopRunningApp() {
        String result = "";
        result = getTopActivity(1);
        return result;
    }

}
