package com.example.android.miwok;

public class Word {

    public Word(String defaultTranslation, String miwokTranslation, int audioID){
        mDefaultWord = defaultTranslation;
        mMiwokWord = miwokTranslation;
        mDrawableId=0;
        mAudioID = audioID;
    }

    public Word(String defaultTranslation, String miwokTranslation, int audioID, int drawableId){
        mDefaultWord = defaultTranslation;
        mMiwokWord = miwokTranslation;
        mDrawableId = drawableId;
        mAudioID = audioID;
    }

    @Override
    public String toString() {
        return "Word{" +
            "mDefaultTranslation='" + mDefaultWord + '\'' +
            ", mMiwokTranslation='" + mMiwokWord + '\'' +
            ", mAudioResourceId=" + mAudioID +
            ", mImageResourceId=" + mDrawableId +
            '}';
    }

    public String getDefaultTranslation(){
        return mDefaultWord;
    }
    public String getMiwokTranslation(){
        return mMiwokWord;
    }
    public int getDrawableId(){return mDrawableId;}
    public int getAudioID(){return mAudioID;}
    private String mMiwokWord;
    private String mDefaultWord;
    private int mDrawableId;
    private int mAudioID;
}
