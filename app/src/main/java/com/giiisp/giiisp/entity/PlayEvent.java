package com.giiisp.giiisp.entity;

import android.os.Handler;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by MASAILA on 16/5/13.
 */
public class PlayEvent {

    public enum Action {
        PLAY, STOP, RESUME, NEXT, PREVIOES, SEEK, HANDLER, SETTING, DUBBING, DUBBING_PLAY, DUBBING_STOP
    }

    private int max;
    private int dest;
    private ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    private Action mAction;
    private Song mSong;
    private List<Song> mQueue;
    private int seekTo;
    private Handler handler;
    private int paperId;

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Song getSong() {
        return mSong;
    }

    public void setSong(Song song) {
        mSong = song;
    }

    public Action getAction() {
        return mAction;
    }

    public void setAction(Action action) {
        mAction = action;
    }

    public List<Song> getQueue() {
        return mQueue;
    }

    public void setQueue(List<Song> queue) {
        mQueue = queue;
    }

    public int getSeekTo() {
        return seekTo;
    }

    public void setSeekTo(int seekTo) {
        this.seekTo = seekTo;
    }
}
