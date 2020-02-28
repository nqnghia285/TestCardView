package com.nqnghia.testcardview;

public class ItemCardView {
    private int mImageResource;
    private String mTitle;
    private String mSubtitle;

    public ItemCardView(int imageResource, String title, String subtitle) {
        mImageResource = imageResource;
        mTitle = title;
        mSubtitle = subtitle;
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
}
