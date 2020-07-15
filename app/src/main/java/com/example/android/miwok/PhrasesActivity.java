/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mp;
    ArrayList<Word> words= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        int[] resIDs={R.raw.phrase_come_here,R.raw.phrase_what_is_your_name,
                R.raw.phrase_are_you_coming, R.raw.phrase_how_are_you_feeling,
        R.raw.phrase_im_coming, R.raw.phrase_im_feeling_good,
        R.raw.phrase_lets_go, R.raw.phrase_my_name_is,
        R.raw.phrase_where_are_you_going, R.raw.phrase_yes_im_coming,
        };
/*        resIDs.add(R.raw.phrase_come_here);
        resIDs.add(R.raw.phrase_what_is_your_name);
        resIDs.add(R.raw.phrase_are_you_coming);
        resIDs.add(R.raw.phrase_how_are_you_feeling);
        resIDs.add(R.raw.phrase_im_coming);
        resIDs.add(R.raw.phrase_im_feeling_good);
        resIDs.add(R.raw.phrase_lets_go);
        resIDs.add(R.raw.phrase_my_name_is);
        resIDs.add(R.raw.phrase_where_are_you_going);
        resIDs.add(R.raw.phrase_yes_im_coming);*/
        int bg_color=getResources().getColor(R.color.category_phrases);
        for(int i=0;i<10;i++){
            words.add(new Word("phrase "+(i+1), "fraza "+(i+1), resIDs[i]));
        }

        WordAdapter wordsArray = new WordAdapter(this, words, bg_color);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(wordsArray);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word wrd = words.get(i);
                mp= MediaPlayer.create(PhrasesActivity.this, wrd.getAudioID());
                mp.start();
            }
        });
        listView.setAdapter(wordsArray);
    }
}
