package com.giiisp.giiisp.view.activity;

import android.Manifest;
import android.media.AudioManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.czt.mp3recorder.MP3Recorder;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseMvpActivity;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.ProgressPopupWindow;
import com.giiisp.giiisp.widget.recording.Util;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.shuyu.waveview.AudioPlayer;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import razerdp.basepopup.BasePopupWindow;

/**
 * 录音的activity的基类
 * Created by Thinkpad on 2017/6/15.
 */
//@RuntimePermissions
public class DubbingPermissionActivity extends BaseMvpActivity<BaseImpl, WholePresenter> implements BaseImpl, AudioManager.OnAudioFocusChangeListener {

    protected int recorderSecondsElapsed;
    protected Timer timer;
    protected String filePath;
    protected MP3Recorder mRecorder;
    protected boolean mIsRecord = false;
    protected boolean mIsPlay;
    protected AudioPlayer audioPlayer;
    protected int duration;
    protected int curPosition;
    protected boolean isPause;
    protected TextView tvTime;
    private boolean writePermission;
    private boolean recordPermission;
    private AudioManager mAudioManager;
    protected UploadManager uploadManager = new UploadManager();
    protected ProgressPopupWindow progressPopupWindow;
    protected ProgressWheel progressWheel;
    protected int type;
    protected boolean back;
//    protected String QnToken;

    //    @NeedsPermission({Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void resolveRecord() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Permission permission) throws Exception {
                        switch (permission.name) {
                            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                                if (permission.granted) {
                                    writePermission = true;
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // Denied permission without ask never again
                                    Utils.showToast(R.string.cancel_storage_permission);
                                    writePermission = false;
                                } else {
                                    // Denied permission with ask never again
                                    // Need to go to the
                                    writePermission = false;
                                    Utils.showToast(R.string.prohibit_storage_permission);
                                }
                                break;
                            case Manifest.permission.RECORD_AUDIO:
                                if (permission.granted) {
                                    recordPermission = true;
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // Denied permission without ask never again
                                    Utils.showToast(R.string.cancel_audio_permission);
                                    recordPermission = false;
                                } else {
                                    // Denied permission with ask never again
                                    // Need to go to the
                                    recordPermission = false;
                                    Utils.showToast(R.string.prohibit_audio_permission);
                                }
                                break;
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        Log.i("--->>", "onError", throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i("--->>", "OnComplete");
                        if (writePermission && recordPermission) {
                            filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/";
                            File file = new File(filePath);
                            if (!file.exists()) {
                                if (!file.mkdirs()) {
                                    Utils.showToast(R.string.failed_create_file);
                                    return;
                                }
                            }

                            filePath = filePath + UUID.randomUUID().toString() + ".mp3";
                            mRecorder = new MP3Recorder(new File(filePath));
                            try {
                                mRecorder.start();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Utils.showToast(R.string.audio_error);
                                resolveError();
                                return;
                            }
                            //                            mAudioManager.requestAudioFocus(DubbingPermissionActivity.this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                            mIsRecord = true;
                            recorderSecondsElapsed = 0;
                            startTimer();
                        }
                    }
                });

    }

    protected void initpaly() {
        audioPlayer = new AudioPlayer(this, new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case AudioPlayer.HANDLER_CUR_TIME://更新的时间
                        curPosition = (int) msg.obj / 1000;
                        break;
                    case AudioPlayer.HANDLER_COMPLETE://播放结束
                        mIsPlay = false;
                        break;
                    case AudioPlayer.HANDLER_PREPARED://播放开始
                        duration = (int) msg.obj;
                        break;
                    case AudioPlayer.HANDLER_ERROR://播放错误
                        //                        resolveResetPlay();
                        break;
                }
                return false;
            }
        }));
    }
/*

    @OnShowRationale({Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showrationaleforRecordReadWrite(PermissionRequest request) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        //        showRationaleDialog(R.string.permission_camera_rationale, request);
        request.proceed();
    }

    @OnPermissionDenied({Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onRecordReadWriteDenied() {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        // or disabling certain functionality
        Toast.makeText(this, "取消授权,不能录音", Toast.LENGTH_SHORT).show();
    }


    @OnNeverAskAgain({Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onRecordReadWriteNeverAskAgain() {
        Toast.makeText(this, "您已经禁止权限,请在设置中开启", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //     DubbingPermissionActivity
        // NOTE: delegate the permission handling to generated method
        DubbingPermissionActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
*/

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        progressPopupWindow = new ProgressPopupWindow(this);
        progressPopupWindow.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                switch (type) {
                    case 0:
                        back = true;
                        Utils.showToast("取消上传");
                        break;
                    case 1:
//                        Utils.showToast("上传成功");
                        break;
                }

            }
        });

    }

    public ProgressWheel getProgressWheel() {
        return progressWheel;
    }

    public void setProgressWheel(ProgressWheel progressWheel) {
        this.progressWheel = progressWheel;
    }

    public void toggleRecording(View v) {

    }


    public void togglePlaying(View v) {

    }

    public void restartRecording(View v) {
    }

    /**
     * 停止录音
     */
    protected void resolveStopRecord() {
        mIsRecord = false;

        if (mRecorder != null && mRecorder.isRecording()) {
            mRecorder.setPause(false);
            mRecorder.stop();
            //            mAudioManager.abandonAudioFocus(this);
        }
        stopTimer();

    }

    /**
     * 录音异常
     */
    protected void resolveError() {
        //        resolveNormalUI();
        Utils.deleteFile(filePath);
        filePath = "";
        if (mRecorder != null && mRecorder.isRecording()) {
            mRecorder.stop();
        }
        stopTimer();
    }

    /**
     * 播放
     */
    protected void resolvePlayRecord() {
        if (TextUtils.isEmpty(filePath) || !new File(filePath).exists()) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isPause) {
            mIsPlay = true;
            audioPlayer.play();
        } else {
            mIsPlay = true;
            audioPlayer.playUrl(filePath);
        }
    }

    protected void resolvePausePlayRecord() {
        if (TextUtils.isEmpty(filePath) || !new File(filePath).exists()) {
//            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        mIsPlay = false;
        isPause = true;
        audioPlayer.pause();
    }


    /**
     * 重置录制
     */
    protected void resolveResetPlay() {
        filePath = "";
        if (mIsPlay) {
            mIsPlay = false;
            audioPlayer.pause();
        }
    }

    /**
     * 暂停
     */
    protected void resolvePause() {
        if (!mIsRecord)
            return;
        if (mRecorder.isPause()) {
            mRecorder.setPause(false);
        } else {
            mRecorder.setPause(true);
        }
        //        mAudioManager.abandonAudioFocus(this);

    }

    protected void startTimer() {
        stopTimer();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTimer();
            }
        }, 0, 1000);
    }

    protected void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    protected void updateTimer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mIsRecord) {
                    tvTime.setText("00:00:00/" + Util.formatSecond(recorderSecondsElapsed++));
                }
            }
        });
    }

    protected String toTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String dateString = formatter.format(time);
        return dateString;
    }

    @Override
    public void onSuccess(BaseEntity entity) {

    }

    @Override
    public void onFailure(String msg, Exception ex) {

    }


    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                //                resolvePause();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:

            case AudioManager.AUDIOFOCUS_GAIN:
                break;
        }
    }

    @Override
    public void initData() {
        presenter.getQNTokenData(uid);
        super.initData();
    }

    protected void postDubbing() {
        Log.i("--->>", "postDubbing: filePath" + filePath + "  uid" + uid + " uploadManager" + uploadManager);
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(uid) || uploadManager == null)
            return;
        progressPopupWindow.showPopupWindow();
        String simpe = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String upkey = simpe + "_" + Utils.fileName(filePath);
        uploadManager.put(filePath, upkey, uid, new UpCompletionHandler() {
            public void complete(String key1, ResponseInfo rinfo, JSONObject response) {
                Log.i("qiniu", key1 + ",\r\n " + rinfo + ",\r\n " + response);
                if (response == null)
                    return;
                String key = response.optString("key");

                if (!TextUtils.isEmpty(key)){
                    keyCompete(key);
                }

            }


        }, new UploadOptions(null, null, false,
                new UpProgressHandler() {
                    public void progress(String key, double percent) {
                        if (progressWheel != null) {
                            progressWheel.setProgress((float) percent);
                        }
                        Log.i("qiniu", key + ": " + percent);
                        Log.i("--->>", "postPic: " + back);
                    }
                }, new UpCancellationSignal() {
            public boolean isCancelled() {
                return back;
            }
        }));

    }

    protected void keyCompete(String key) {
    }
}
