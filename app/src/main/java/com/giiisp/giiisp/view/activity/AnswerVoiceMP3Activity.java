package com.giiisp.giiisp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.api.UrlConstants;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.utils.DensityUtils;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.widget.recording.Util;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 语音回答页面
 * Created by Thinkpad on 2017/5/24.
 */
public class AnswerVoiceMP3Activity extends DubbingPermissionActivity implements View.OnClickListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_answer)
    TextView tvAnswer;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.tv_start_hint)
    TextView tvStartHint;


    @BindView(R.id.tv_record)
    ImageView tvRecord;

    private Dialog dialog;
    private String id;
    private String qid;
    private String answer;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_qarecording;
    }

    public static void actionActivity(Activity context, String answer, String qid, String id) {
        Intent sIntent = new Intent(context, AnswerVoiceMP3Activity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        sIntent.putExtra("Answer", answer);
        sIntent.putExtra("qid", qid);
        sIntent.putExtra("id", id);
        context.startActivityForResult(sIntent,2000);
    }

    @Override
    public void initView() {
        super.initView();
        initDialog();
        answer = getIntent().getStringExtra("Answer");
        qid = getIntent().getStringExtra("qid");
        id = getIntent().getStringExtra("id");
        //        setupRecorder();
        tvAnswer.setText(answer);
        tvAnswer.setMovementMethod(new ScrollingMovementMethod());
        initpaly();

    }

    public File getFilePath() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp", System.currentTimeMillis() + "ecorded_audio.wav");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        return file;

    }

    @Override
    public void toggleRecording(View v) {
        Util.wait(100, new Runnable() {
            @Override
            public void run() {
                if (mIsRecord) {
                    resolveStopRecord();
                    showDialog();
                    //                    pauseRecording();
                } else {
                    tvStartHint.setText(R.string.dubbing_consistent_with_text);
                    resolveRecord();
                    //                    DubbingPermissionActivityPermissionsDispatcher.resolveRecordWithCheck(AnswerVoiceMP3Activity.this);
                }
            }
        });
    }

    @Override
    protected void resolveStopRecord() {
        super.resolveStopRecord();
        tvRecord.setImageResource(R.mipmap.recording_icon);
    }

    @Override
    public void togglePlaying(View v) {
        Util.wait(100, new Runnable() {
            @Override
            public void run() {
                if (mIsPlay) {
                    resolvePausePlayRecord();
                } else {
                    resolvePlayRecord();
                }
            }
        });
    }

    public void restartRecording(View v) {
        resolveResetPlay();
    }

    public void showDialog() {
        dialog.show();
    }

    @Override
    protected void startTimer() {
        tvRecord.setImageResource(R.mipmap.answer_dubbing);
        super.startTimer();
    }

    @OnClick({R.id.tv_back, R.id.tv_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.tv_record:
                toggleRecording(view);
                break;
            case R.id.tv_audition:
                break;
            case R.id.tv_reset:
                dialog.dismiss();
                recorderSecondsElapsed = 0;
                break;
            case R.id.tv_publish:
                finish();
                break;
        }
    }


    private void initDialog() {
        View view = getLayoutInflater().inflate(R.layout.answer_recorder_dialog, null);
        TextView backEdit = (TextView) view.findViewById(R.id.tv_back_edit);
        tvTime = (TextView) view.findViewById(R.id.tv_recording);
        TextView audition = (TextView) view.findViewById(R.id.tv_audition);
        TextView reset = (TextView) view.findViewById(R.id.tv_reset);
        TextView publish = (TextView) view.findViewById(R.id.tv_publish);
        backEdit.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        audition.setOnClickListener(this);
        reset.setOnClickListener(this);
        publish.setOnClickListener(this);
        dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        if (window != null) {
            window.setWindowAnimations(R.style.main_menu_animstyle);
            WindowManager.LayoutParams wl = window.getAttributes();
            wl.x = 0;
            wl.y = DensityUtils.dp2px(this, 214f);
            // 以下这两句是为了保证按钮可以水平满屏
            wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
            wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            // 设置显示位置
            dialog.onWindowAttributesChanged(wl);
        }
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(false);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back_edit:

                finish();
                break;
            case R.id.tv_recording:
                togglePlaying(v);
                break;
            case R.id.tv_audition:
                break;
            case R.id.tv_reset:
                restartRecording(v);
                isPause = false;
                dialog.dismiss();
                break;
            case R.id.tv_publish:
                postDubbing();
                break;

        }
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        super.onSuccess(entity);
        if (!TextUtils.isEmpty(entity.getUid())) {
//            QnToken = entity.getToken();
//            Log.i("--->>", "onSuccess: " + QnToken);
        } else {
            Utils.showToast(entity.getInfo());
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected void keyCompete(String key) {
        super.keyCompete(key);
        ArrayMap<String, Object> map = new ArrayMap<>();
        map.put("qid", qid);
        map.put("uid", id);
        map.put("content", answer);
        map.put("record", UrlConstants.RequestUrl.MP3_URL + key);
        map.put("isRecord", 1);
        map.put("timing", recorderSecondsElapsed);
        presenter.getSaveAnswerData(map);
    }

    @Override
    protected void postDubbing() {
        super.postDubbing();
    }
}
