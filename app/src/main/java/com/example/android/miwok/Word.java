package com.example.android.miwok;

public class Word {

    public Word(String defaultTranslation, String miwokTranslation, int background_colors){
        mDefaultWord = defaultTranslation;
        mMiwokWord = miwokTranslation;
        mDrawableId=0;
        mBackgroundColor=background_colors;
    }

    public Word(String defaultTranslation, String miwokTranslation, int background_colors, int drawableId){
        mDefaultWord = defaultTranslation;
        mMiwokWord = miwokTranslation;
        mDrawableId = drawableId;
        mBackgroundColor=background_colors;
    }

    public String getDefaultTranslation(){
        return mDefaultWord;
    }
    public String getMiwokTranslation(){
        return mMiwokWord;
    }
    public int getDrawableId(){return mDrawableId;}
    public int getBackgroundColor(){return mBackgroundColor;}
    private String mMiwokWord;
    private String mDefaultWord;
    private int mDrawableId;
    private int mBackgroundColor;

}
