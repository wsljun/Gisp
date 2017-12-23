package com.giiisp.giiisp.widget.recording;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.entity.Song;
import com.giiisp.giiisp.view.activity.WelcomeActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wcy on 2017/4/18.
 */
public class Notifier {
    private static final int NOTIFICATION_ID = 0x111;
    private static PlayService playService;
    private static NotificationManager notificationManager;

    public static void init(PlayService playService) {
        Notifier.playService = playService;
        notificationManager = (NotificationManager) playService.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static void showPlay(Song music) {
        playService.startForeground(NOTIFICATION_ID, buildNotification(playService, music, true));
    }

    public static void showPause(Song music) {
        playService.stopForeground(false);
        notificationManager.notify(NOTIFICATION_ID, buildNotification(playService, music, false));
    }

    public static void cancelAll() {
        notificationManager.cancelAll();
    }

    private static Notification buildNotification(Context context, Song music, boolean isPlaying) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.putExtra(Extras.EXTRA_NOTIFICATION, true);
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_notification3)
                .setCustomContentView(getRemoteViews(context, music, isPlaying));
        return builder.build();
    }

    private static RemoteViews getRemoteViews(Context context, Song music, boolean isPlaying) {
        String title = music.getTitle();
        String subtitle = "第" + (music.getPosition()+1) + "页";
        String path =  music.getPhotoPath();
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification);
        SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                remoteViews.setImageViewBitmap(R.id.iv_icon,resource);
            }
        };
        RequestBuilder<Bitmap> load = Glide.with(context)
                .asBitmap()
                .load(path);
        load.into(simpleTarget);

  /*      if (cover[0] != null) {
            remoteViews.setImageViewBitmap(R.id.iv_icon, cover[0]);
        } else {
            remoteViews.setImageViewResource(R.id.iv_icon, R.mipmap.ic_launcher);
        }*/
        remoteViews.setTextViewText(R.id.tv_title, title);
        remoteViews.setTextViewText(R.id.tv_subtitle, subtitle);

        boolean isLightNotificationTheme = isLightNotificationTheme(playService);
        Intent playIntent = new Intent(StatusBarReceiver.ACTION_STATUS_BAR);
        playIntent.putExtra(StatusBarReceiver.EXTRA, StatusBarReceiver.EXTRA_PLAY_PAUSE);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(context, 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setImageViewResource(R.id.iv_play_pause, getPlayIconRes(isLightNotificationTheme, isPlaying));
        remoteViews.setOnClickPendingIntent(R.id.iv_play_pause, playPendingIntent);

        Intent nextIntent = new Intent(StatusBarReceiver.ACTION_STATUS_BAR);
        nextIntent.putExtra(StatusBarReceiver.EXTRA, StatusBarReceiver.EXTRA_NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(context, 1, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setImageViewResource(R.id.iv_next, getNextIconRes(isLightNotificationTheme));
        remoteViews.setOnClickPendingIntent(R.id.iv_next, nextPendingIntent);

        Intent prevIntent = new Intent(StatusBarReceiver.ACTION_STATUS_BAR);
        prevIntent.putExtra(StatusBarReceiver.EXTRA, StatusBarReceiver.EXTRA_PREV);
        PendingIntent prevPendingIntent = PendingIntent.getBroadcast(context, 2, prevIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setImageViewResource(R.id.iv_prev, R.mipmap.back_off);
        remoteViews.setOnClickPendingIntent(R.id.iv_prev, prevPendingIntent);

        return remoteViews;
    }

    private static int getPlayIconRes(boolean isLightNotificationTheme, boolean isPlaying) {
        if (isPlaying) {
            return R.mipmap.demonstration_play;

        } else {
            return R.mipmap.main_stop;
        }
    }

    private static int getNextIconRes(boolean isLightNotificationTheme) {
        return isLightNotificationTheme
                ? R.mipmap.fast_forward
                :R.mipmap.fast_forward;
    }

    private static boolean isLightNotificationTheme(Context context) {
        int notificationTextColor = getNotificationTextColor(context);
        return isSimilarColor(Color.BLACK, notificationTextColor);
    }

    private static int getNotificationTextColor(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder.build();
        RemoteViews remoteViews = notification.contentView;
        if (remoteViews == null) {
            return Color.BLACK;
        }
        int layoutId = remoteViews.getLayoutId();
        ViewGroup notificationLayout = (ViewGroup) LayoutInflater.from(context).inflate(layoutId, null);
        TextView title = (TextView) notificationLayout.findViewById(android.R.id.title);
        if (title != null) {
            return title.getCurrentTextColor();
        } else {
            return findTextColor(notificationLayout);
        }
    }

    /**
     * 如果通过 android.R.id.title 无法获得 title ，
     * 则通过遍历 notification 布局找到 textSize 最大的 TextView ，应该就是 title 了。
     */
    private static int findTextColor(ViewGroup notificationLayout) {
        List<TextView> textViewList = new ArrayList<>();
        findTextView(notificationLayout, textViewList);

        float maxTextSize = -1;
        TextView maxTextView = null;
        for (TextView textView : textViewList) {
            if (textView.getTextSize() > maxTextSize) {
                maxTextView = textView;
            }
        }

        if (maxTextView != null) {
            return maxTextView.getCurrentTextColor();
        }

        return Color.BLACK;
    }

    private static void findTextView(View view, List<TextView> textViewList) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findTextView(viewGroup.getChildAt(i), textViewList);
            }
        } else if (view instanceof TextView) {
            textViewList.add((TextView) view);
        }
    }

    private static boolean isSimilarColor(int baseColor, int color) {
        int simpleBaseColor = baseColor | 0xff000000;
        int simpleColor = color | 0xff000000;
        int baseRed = Color.red(simpleBaseColor) - Color.red(simpleColor);
        int baseGreen = Color.green(simpleBaseColor) - Color.green(simpleColor);
        int baseBlue = Color.blue(simpleBaseColor) - Color.blue(simpleColor);
        double value = Math.sqrt(baseRed * baseRed + baseGreen * baseGreen + baseBlue * baseBlue);
        return value < 180.0;
    }
}
