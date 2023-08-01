package com.tinet.threepart.keyboard;

/**
 * @ProjectName: TIMSDK
 * @ClassName: KeyBoardObserver
 * @Author: liuzr
 * @CreateDate: 2021-10-21 10:05
 * @Description: 软键盘 观察者
 */
public interface KeyBoardObserver {
    void update(boolean keyBoardVisibile, int keyBoardHeight);
}