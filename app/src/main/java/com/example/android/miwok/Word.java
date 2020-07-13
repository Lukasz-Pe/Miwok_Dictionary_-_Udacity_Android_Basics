package com.example.android.miwok;

public class Word {

    public Word(String defaultTranslation, String miwokTranslation){
        mDefaultWord =defaultTranslation;
        mMiwokWord =miwokTranslation;
    }

    public String getDefaultTranslation(){
        return mDefaultWord;
    }
    public String getMiwokTranslation(){
        return mMiwokWord;
    }
    private String mMiwokWord;
    private String mDefaultWord;

}
