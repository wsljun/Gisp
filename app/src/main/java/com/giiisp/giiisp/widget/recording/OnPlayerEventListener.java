package com.giiisp.giiisp.widget.recording;

import android.media.MediaPlayer;

import com.giiisp.giiisp.entity.Song;

/**
 * 播放进度监听器
 * Created by hzwangchenyan on 2015/12/17.
 */
public interface OnPlayerEventListener {
    /**
     * 更新进度
     */
    void onPublish(int progress);

    /**
     * 设置进度大小
     */
    void onDuration(int duration);

    /**
     * 播放完成
     */
    void onCompletion(MediaPlayer mp);

    /**
     * 缓冲百分比
     */
    void onBufferingUpdate(int percent);

    /**
     * 切换歌曲
     */
    void onChange(Song music);
    /**
     * 开始播放
     */
    void onPrepared(MediaPlayer mp);

    /**
     * 暂停播放
     */
    void onPlayerPause();

    /**
     * 继续播放
     */
    void onPlayerResume();

    /**
     * 更新定时停止播放时间
     */
    void onTimer(long remain);

    void onMusicListUpdate();
}
