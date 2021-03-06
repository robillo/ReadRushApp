package com.robillo.readrush.ui.audio_play;

import com.robillo.readrush.ui.base.MvpView;

/**
 * Created by robinkamboj on 30/12/17.
 */

public interface AudioPlayMvpView extends MvpView {

    void setUp();

    void loadContents();

    void showAudioLayout();

    void initMediaPlayer(int index);

    void playNextRushAudio();

    void playPrevRushAudio();

    void preparePlayer(int index);
}
