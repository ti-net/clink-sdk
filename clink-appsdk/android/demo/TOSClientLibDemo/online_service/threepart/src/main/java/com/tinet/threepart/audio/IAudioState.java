package com.tinet.threepart.audio;

public abstract class IAudioState {
    public IAudioState() {
    }

    /**
     * 进入当前状态
     */
    void enter() {
    }

    abstract void handleMessage(AudioStateMessage var1);
}