package com.mysampleapp.util;

import android.media.MediaPlayer;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minjungkim on 7/18/17.
 */

public class AudioPlayer extends MediaPlayer {
    private String url;

    private AudioPlayer() {
        super();
    }

    private static volatile AudioPlayer audioPlayer;

    public static AudioPlayer INSTANCE() {
        if  (audioPlayer == null) {
            synchronized (AudioPlayer.class) {
                if (audioPlayer == null) {
                    audioPlayer = new AudioPlayer();
                }
            }
        }
        return audioPlayer;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean setUrl(String url) {
        if (this.url == null || !this.url.equals(url)) {
            this.url = url;
            super.reset();
            stop();
            try {
                this.setDataSource("http://bitly.com/" + url);
                this.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public int getTotalTime() {
        return this.getDuration();
    }

    public int getCurrentTime() {
        return this.getCurrentPosition();
    }

    public void reset() {
        this.seekTo(0);
        pause();
    }

    public void changeTime(int time) {
        this.seekTo(time);
    }
}
