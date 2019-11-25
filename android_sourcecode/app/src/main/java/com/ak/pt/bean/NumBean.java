package com.ak.pt.bean;

/**
 * Created by admin on 2019/1/21.
 */

public class NumBean {
    public NumBean(  String mState,String mKg, String mTIme, String mDown, String mAuto) {
        this.mKg = mKg;
        this.mTIme = mTIme;
        this.mDown = mDown;
        this.mAuto = mAuto;
        this.mState = mState;
    }

    private String mKg;
    private String mTIme;
    private String mDown;
    private String mAuto;
    private String mState;

    public String getmAuto() {
        return mAuto;
    }

    public void setmAuto(String mAuto) {
        this.mAuto = mAuto;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmKg() {
        return mKg;
    }

    public void setmKg(String mKg) {
        this.mKg = mKg;
    }

    public String getmTIme() {
        return mTIme;
    }

    public void setmTIme(String mTIme) {
        this.mTIme = mTIme;
    }

    public String getmDown() {
        return mDown;
    }

    public void setmDown(String mDown) {
        this.mDown = mDown;
    }
}
