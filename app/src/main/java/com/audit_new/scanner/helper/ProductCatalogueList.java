package com.audit_new.scanner.helper;

public class ProductCatalogueList {

    private int mImageResource;
    private String mText1;
    private String mText2;
    private int mCount;

    public ProductCatalogueList(int imageResource, String text1, String text2, int count){
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
        mCount = count;
    }

    public void changeCount(int count){
        mCount = count;
    }
    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    public int getmCount() {
        return mCount;
    }
}
