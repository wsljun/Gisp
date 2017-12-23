package com.giiisp.giiisp.widget;

import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.entity.UpDateAppEntity;
import com.giiisp.giiisp.utils.PackageUtil;
import com.giiisp.giiisp.utils.Utils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import razerdp.basepopup.BasePopupWindow;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.db.DataBaseHelper;
import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * 版本更新
 * Created by Thinkpad on 2017/6/1.
 */

public class UpdatePopupWindow extends BasePopupWindow implements View.OnClickListener {
    private final RxDownload mRxDownload;
    private View popupView;
    private UpDateAppEntity.AppInfoBean appInfo;
    private Activity context;
    private DownloadRecord record;

    public UpdatePopupWindow(Activity context, UpDateAppEntity.AppInfoBean appInfo) {
        super(context);
        bindEvent();
        this.appInfo = appInfo;
        this.context = context;
        setDismissWhenTouchOuside(false);
        mRxDownload = RxDownload.getInstance(context);
        ininView(appInfo);
    }

    private void ininView(UpDateAppEntity.AppInfoBean appInfo) {
        if (appInfo == null)
            return;
        TextView updateInfo = popupView.findViewById(R.id.update_info);
        TextView message = popupView.findViewById(R.id.message);
        final TextView tvUpgrade = popupView.findViewById(R.id.tv_upgrade);
        message.setMovementMethod(new ScrollingMovementMethod());
        String apkSize = appInfo.getApkSize();
        String versionName = appInfo.getVersionName();
        String detailDesc = appInfo.getDetailDesc();
        message.setText(detailDesc);
        updateInfo.setText("发现新版本:" + versionName + "/" + apkSize);
        RxDownload.getInstance(context).getDownloadRecord(appInfo.getApkUrl()).subscribe(new Consumer<DownloadRecord>() {
            @Override
            public void accept(@NonNull DownloadRecord downloadRecord) throws Exception {
                record = downloadRecord;
                if (downloadRecord.getFlag() == DownloadFlag.COMPLETED) {
                    tvUpgrade.setText("安装");
                }
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_upgrade:
                if (record!=null&&record.getFlag() == DownloadFlag.COMPLETED) {
                    PackageUtil.installApkNormal(context, record.getSavePath() + "/" + record.getSaveName());
                } else {
                    initDownload();
                }

                break;
            case R.id.tv_later:

                break;
        }
        dismiss();

    }

    private void initDownload() {
        if (DataBaseHelper.getSingleton(context).recordNotExists(appInfo.getApkUrl())) {
            DownloadBean downloadBean = new DownloadBean
                    .Builder(appInfo.getApkUrl())
                    .setExtra3("0")  //save extra info into database.
                    .setExtra5(appInfo.getPackageName())
                    .setTime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.getDefault()).format(new Date()) + "")
                    .setVersion(appInfo.getVersionName())//save extra info into database.
                    .setExtra1(appInfo.getVersionCode() + "")   //save extra info into database.
                    .setExtra2(appInfo.getVersionCode() + "")   //save extra info into database.
                    .setExtra4("appInfo")   //save extra info into database.
                    .setTitle("集思谱")
                    .build();
            ArrayList<DownloadBean> list = new ArrayList<>();
            list.add(downloadBean);

            new RxPermissions(context)
                    .request(WRITE_EXTERNAL_STORAGE)
                    .doOnNext(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Exception {
                            if (!granted) {
                                throw new RuntimeException("no permission");
                            }
                        }
                    })
                    .compose(mRxDownload.<Boolean>transformMulti(list, appInfo.getVersionCode() + ""))
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            Utils.showToast("下载开始");

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            Log.w("--->>", throwable);
                            Utils.showToast("下载中");
                        }
                    });

        }
    }

    @Override
    protected Animation initShowAnimation() {
        return getDefaultScaleAnimation();
    }


    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_update, null);
        return popupView;
    }

    private void bindEvent() {
        if (popupView != null) {
            popupView.findViewById(R.id.tv_later).setOnClickListener(this);
            popupView.findViewById(R.id.tv_upgrade).setOnClickListener(this);
        }

    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }
}
