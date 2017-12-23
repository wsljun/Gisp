package com.giiisp.giiisp.entity;

import android.widget.ImageView;
import android.widget.TextView;

import com.giiisp.giiisp.R;

import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/11/22
 * Time: 15:18
 * FIXME
 */
public class DownloadController {
    private TextView mStatus;
    private ImageView mAction;
    private DownloadState mState;

    public DownloadController(TextView status, ImageView action) {
        mStatus = status;
        mAction = action;
        setState(new Normal());
    }



    public void setState(DownloadState state) {
        mState = state;
        mState.setText(mStatus, mAction);
    }

    public void setEvent(DownloadEvent event) {
        int flag = event.getFlag();
        switch (flag) {
            case DownloadFlag.NORMAL:
                setState(new DownloadController.Normal());
                break;
            case DownloadFlag.WAITING:
                setState(new DownloadController.Waiting());
                break;
            case DownloadFlag.STARTED:
                setState(new DownloadController.Started());
                break;
            case DownloadFlag.PAUSED:
                setState(new DownloadController.Paused());
                break;
            case DownloadFlag.COMPLETED:
                setState(new DownloadController.Completed());
                break;
            case DownloadFlag.FAILED:
                setState(new DownloadController.Failed());
                break;
            case DownloadFlag.DELETED:
                setState(new DownloadController.Deleted());
                break;

        }
    }

    public void handleClick(Callback callback) {
        mState.handleClick(callback);
    }

    public interface Callback {
        void startDownload();

        void pauseDownload();

        void install();
    }

    static abstract class DownloadState {

        abstract void setText(TextView status, ImageView button);

        abstract void handleClick(Callback callback);
    }

    public static class Normal extends DownloadState {

        @Override
        void setText(TextView status, ImageView button) {
            button.setImageResource(R.mipmap.download_item_icon);
            status.setText("暂未下载");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Waiting extends DownloadState {
        @Override
        void setText(TextView status, ImageView button) {
            button.setImageResource(R.mipmap.download_item_stop);
            status.setText("等待下载");
        }

        @Override
        void handleClick(Callback callback) {
            callback.pauseDownload();
        }
    }

    public static class Started extends DownloadState {
        @Override
        void setText(TextView status, ImageView button) {
            button.setImageResource(R.mipmap.download_item_icon);
            status.setText("正在下载");
        }

        @Override
        void handleClick(Callback callback) {
            callback.pauseDownload();
        }
    }

    public static class Paused extends DownloadState {
        @Override
        void setText(TextView status, ImageView button) {
            button.setImageResource(R.mipmap.download_stop);
            status.setText("暂停下载");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Failed extends DownloadState {
        @Override
        void setText(TextView status, ImageView button) {
            button.setImageResource(R.mipmap.download_item_stop);
            status.setText("下载失败");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }


    public static class Completed extends DownloadState {
        @Override
        void setText(TextView status, ImageView button) {
            button.setImageResource(R.mipmap.download_item_icon);
            status.setText("下载完成");
        }

        @Override
        void handleClick(Callback callback) {
            callback.install();
        }
    }

    public static class Deleted extends DownloadState {

        @Override
        void setText(TextView status, ImageView button) {
            button.setImageResource(R.mipmap.download_item_icon);
            status.setText("取消下载");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }
}
