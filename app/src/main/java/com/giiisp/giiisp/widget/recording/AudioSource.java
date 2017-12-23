package com.giiisp.giiisp.widget.recording;

import android.media.MediaRecorder;

public enum AudioSource {
    MIC,
    CAMCORDER;

    public int getSource(){
        switch (this){
            case CAMCORDER:
                return MediaRecorder.AudioSource.CAMCORDER;
            default:
                return MediaRecorder.AudioSource.MIC;
        }
    }
}