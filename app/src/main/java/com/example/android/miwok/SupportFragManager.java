package com.example.android.miwok;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SupportFragManager extends FragmentPagerAdapter {
    private String tabTitles[];
    private Context context;
    public SupportFragManager(FragmentManager fm, Context current){
        super (fm);
        this.context=current;
        tabTitles=new String[] {context.getResources().getString(R.string.category_numbers),
                                context.getResources().getString(R.string.category_family),
                                context.getResources().getString(R.string.category_colors),
                                context.getResources().getString(R.string.category_phrases)};
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return new NumbersFragment();
            }
            case 1: {
                return new FamilyFragment();
            }
            case 2: {
                return new ColorsFragment();
            }
            case 3: {
                return new PhrasesFragment();
            }
            default: {
                if (position < 0) {
                    return new PhrasesFragment();
                }
                if (position > 3) {
                    return new NumbersFragment();
                }
                return new NumbersFragment();
            }
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return 4;
    }
}
