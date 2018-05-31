package com.ftech.savelock.data;

/**
 * Created by Frederick on 5/19/2018.
 */

public class ListData {
    String mTitle,mDesc,mLetter;
    public ListData(String title,String desc,String letter){
        mTitle = title;
        mDesc = desc;
        mLetter = letter;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDesc() {
        return mDesc;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmLetter(String mLetter) {
        this.mLetter = mLetter;
    }

    public String getmLetter() {
        return mLetter;
    }
}
