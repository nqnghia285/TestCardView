package com.nqnghia.testcardview;

import android.widget.CheckBox;

public class ItemCardView {
    private int mImageResource;
    private String mTitle;
    private String mSubtitle;
    private Boolean mChecked;

    public ItemCardView(int imageResource, String title, String subtitle) {
        mImageResource = imageResource;
        mTitle = title;
        mSubtitle = subtitle;
        mChecked = false;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public Boolean getmCheckBox() { return mChecked; }

    public void setmImageResource(int imageResource) { mImageResource = imageResource; }

    public void setmTitle(String title) { mTitle = title; }

    public void setmSubtitle(String subtitle) { mSubtitle = subtitle; }

    public void setmCheckBox(Boolean checked) { mChecked = checked; }
}
