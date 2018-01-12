package com.giiisp.giiisp.widget.recording;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.Log;

import com.giiisp.giiisp.entity.PaperDatailEntity;
import com.giiisp.giiisp.entity.Song;
import com.giiisp.giiisp.utils.FileUtils;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.PaperDetailsActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 音乐播放后台服务
 * Created by wcy on 2015/11/27.
 */
public class PlayService extends Service implements MediaPlayer.OnCompletionListener, AudioManager.OnAudioFocusChangeListener {
    private static final String TAG = "Service";
    private static final long TIME_UPDATE = 100L;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PREPARING = 1;
    private static final int STATE_PLAYING = 2;
    private static final int STATE_PAUSE = 3;

    private List<Song> mMusicList;
    private List<Song> mMusicListEN;
    private MediaPlayer mPlayer = new MediaPlayer();
    private IntentFilter mNoisyFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
    private NoisyAudioStreamReceiver mNoisyReceiver = new NoisyAudioStreamReceiver();
    private Handler mHandler = new Handler();
    private AudioManager mAudioManager;
    private OnPlayerEventListener mListener;
    // 正在播放的歌曲[本地|网络]
    private Song mPlayingMusic;
    // 正在播放的本地歌曲的序号
    private int mPlayingPosition;
    private long quitTimerRemain;
    private int playState = STATE_IDLE;
    private List<String> imageList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: " + getClass().getSimpleName());
        mMusicList = AppCache.getMusicList();
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mPlayer.setOnCompletionListener(this);
        //        Notifier.init(this);
    }

    public MediaPlayer getmPlayer() {
        return mPlayer;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayBinder();
    }

    public static void startCommand(Context context, String action) {
        Intent intent = new Intent(context, PlayService.class);
        intent.setAction(action);
        context.startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case Actions.ACTION_MEDIA_PLAY_PAUSE:
                    playPause();
                    break;
                case Actions.ACTION_MEDIA_NEXT:
                    next();
                    break;
                case Actions.ACTION_MEDIA_PREVIOUS:
                    prev();
                    break;
            }
        }
        return START_NOT_STICKY;
    }

    public List<Song> getmMusicListEN() {
        return mMusicListEN;
    }

    public void setmMusicListEN(List<Song> mMusicListEN) {
        this.mMusicListEN = mMusicListEN;
    }

    public List<Song> getmMusicList() {
        return mMusicList;
    }

    public void setmMusicList(List<Song> mMusicList) {
        this.mMusicList = mMusicList;
    }

    /**
     * 扫描音乐
     */
    public void updateMusicList() {
        //        MusicUtils.scanMusic(this, mMusicList);
        if (!mMusicList.isEmpty()) {
            updatePlayingPosition();
            mPlayingMusic = (mPlayingMusic == null) ? mMusicList.get(mPlayingPosition) : mPlayingMusic;
        }
    }

    public void selectMusicList() {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mListener != null)
            mListener.onCompletion(mp);
        next();
    }

    public OnPlayerEventListener getOnPlayEventListener() {
        return mListener;
    }

    public void setOnPlayEventListener(OnPlayerEventListener listener) {
        mListener = listener;
    }

    public void play(int position) {
        if (mMusicList.isEmpty()) {
            return;
        }

        if (position < 0) {
            position = mMusicList.size() - 1;
        } else if (position >= mMusicList.size()) {
            position = 0;
        }

        mPlayingPosition = position;
        Song music = mMusicList.get(mPlayingPosition);
        //        Preferences.saveCurrentSongId(music.getId());
        play(music);
    }

    public void playOrder(int position) {
        if (mMusicList.isEmpty()) {
            return;
        }

        if (position < 0) {
            Utils.showToast("已是第一张");
        } else if (position >= mMusicList.size()) {
            Utils.showToast("已是最后一张");
        } else {
            mPlayingPosition = position;
            Song music = mMusicList.get(mPlayingPosition);
            //        Preferences.saveCurrentSongId(music.getId());
            play(music);
        }

    }

    public void play(Song music) {
        mPlayingMusic = music;
        if("mp4".equals(FileUtils.parseSuffix(mPlayingMusic.getPath()))) {
            return;
        }
        try {
            mPlayer.reset();
            mPlayer.setDataSource(music.getPath());
            mPlayer.prepareAsync();
            playState = STATE_PREPARING;
            mPlayer.setOnPreparedListener(mPreparedListener);
            mPlayer.setOnBufferingUpdateListener(mBufferingUpdateListener);
            if (mListener != null) {
                mListener.onChange(music);
            }
            //            Notifier.showPlay(music);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            if (mListener!=null){
                mListener.onPrepared(mp);
            }
            start();
        }
    };

    private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            if (mListener != null) {
                mListener.onBufferingUpdate(percent);
            }
        }
    };

    public void playPause() {
        if (isPreparing()) {
            return;
        }

        if (isPlaying()) {
            pause();
        } else if (isPausing()) {
            resume();
        } else {
            play(getPlayingPosition());
        }
    }

    private boolean start() {
        mPlayer.start();
        if (mPlayer.isPlaying()) {
            playState = STATE_PLAYING;
            mHandler.post(mPublishRunnable);
            //            Notifier.showPlay(mPlayingMusic);
            mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            registerReceiver(mNoisyReceiver, mNoisyFilter);
        }
        return mPlayer.isPlaying();
    }

    private void pause() {
        if (!isPlaying()) {
            return;
        }

        mPlayer.pause();
        playState = STATE_PAUSE;
        mHandler.removeCallbacks(mPublishRunnable);
        //        Notifier.showPause(mPlayingMusic);
        mAudioManager.abandonAudioFocus(this);
        unregisterReceiver(mNoisyReceiver);
        if (mListener != null) {
            mListener.onPlayerPause();
        }
    }

    private void resume() {
        if (!isPausing()) {
            return;
        }

        if (start()) {
            if (mListener != null) {
                mListener.onPlayerResume();
            }
        }
    }

    public void next() {
        if (mMusicList.isEmpty()) {
            return;
        }

        PlayModeEnum mode = PlayModeEnum.valueOf(0);
        switch (mode) {
            case SHUFFLE:
                mPlayingPosition = new Random().nextInt(mMusicList.size());
                play(mPlayingPosition);
                break;
            case SINGLE:
                play(mPlayingPosition);
                break;
            case LOOP:
            default:
                playOrder(mPlayingPosition + 1);
                break;
        }
    }

    public void prev() {
        if (mMusicList.isEmpty()) {
            return;
        }

        PlayModeEnum mode = PlayModeEnum.valueOf(0);
        switch (mode) {
            case SHUFFLE:
                mPlayingPosition = new Random().nextInt(mMusicList.size());
                play(mPlayingPosition);
                break;
            case SINGLE:
                play(mPlayingPosition);
                break;
            case LOOP:

            default:
                playOrder(mPlayingPosition - 1);
                break;
        }
    }

    /**
     * 跳转到指定的时间位置
     *
     * @param msec 时间
     */
    public void seekTo(int msec) {
        if (isPlaying() || isPausing()) {
            mPlayer.seekTo(msec);
            if (mListener != null) {
                mListener.onPublish(msec);
            }
        }
    }

    public boolean isPlaying() {
        return playState == STATE_PLAYING;
    }

    public boolean isPausing() {
        return playState == STATE_PAUSE;
    }

    public boolean isPreparing() {
        return playState == STATE_PREPARING;
    }

    /**
     * 获取正在播放的本地歌曲的序号
     */
    public int getPlayingPosition() {
        return mPlayingPosition;
    }

    /**
     * 获取正在播放的歌曲[本地|网络]
     */
    public Song getPlayingMusic() {
        return mPlayingMusic;
    }

    /**
     * 删除或下载歌曲后刷新正在播放的本地歌曲的序号
     */
    public void updatePlayingPosition() {
      /*  int position = 0;
        long id = Preferences.getCurrentSongId();
        for (int i = 0; i < mMusicList.size(); i++) {
            if (mMusicList.get(i).getId() == id) {
                position = i;
                break;
            }
        }
        mPlayingPosition = position;
        Preferences.saveCurrentSongId(mMusicList.get(mPlayingPosition).getId());*/
    }

    private Runnable mPublishRunnable = new Runnable() {
        @Override
        public void run() {
            if (isPlaying() && mListener != null) {
                mListener.onPublish(mPlayer.getCurrentPosition());
            }
            mHandler.postDelayed(this, TIME_UPDATE);
        }
    };

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_LOSS:
                pause();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                pause();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:

            case AudioManager.AUDIOFOCUS_GAIN:
                resume();
                break;
        }
    }

    public void startQuitTimer(long milli) {
        stopQuitTimer();
        if (milli > 0) {
            quitTimerRemain = milli + DateUtils.SECOND_IN_MILLIS;
            mHandler.post(mQuitRunnable);
        } else {
            quitTimerRemain = 0;
            if (mListener != null) {
                mListener.onTimer(quitTimerRemain);
            }
        }
    }

    private void stopQuitTimer() {
        mHandler.removeCallbacks(mQuitRunnable);
    }

    private Runnable mQuitRunnable = new Runnable() {
        @Override
        public void run() {
            quitTimerRemain -= DateUtils.SECOND_IN_MILLIS;
            if (quitTimerRemain > 0) {
                if (mListener != null) {
                    mListener.onTimer(quitTimerRemain);
                }
                mHandler.postDelayed(this, DateUtils.SECOND_IN_MILLIS);
            } else {
                AppCache.clearStack();
                stop();
            }
        }
    };

    @Override
    public void onDestroy() {
        AppCache.setPlayService(null);
        super.onDestroy();
        Log.i(TAG, "onDestroy: " + getClass().getSimpleName());
    }

    public void stop() {
        pause();
        stopQuitTimer();
        mPlayer.reset();
        mPlayer.release();
        mPlayer = null;
        //        Notifier.cancelAll();
        AppCache.setPlayService(null);
        stopSelf();
    }

    public class PlayBinder extends Binder {
        public PlayService getService() {
            return PlayService.this;
        }
    }
}
