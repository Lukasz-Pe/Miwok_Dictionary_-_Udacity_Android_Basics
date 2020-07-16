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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.miwok.Word;

import java.util.ArrayList;
import java.util.Arrays;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mp;
    private MediaPlayer.OnCompletionListener mpCompletionListener= new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        int bg_color=getResources().getColor(R.color.category_family);
        final ArrayList<Word> words = new ArrayList<>(Arrays.asList(new Word("daughter", "córka", R.raw.family_daughter, R.drawable.family_daughter),
                new Word("father", "tata", R.raw.family_father, R.drawable.family_father),
                new Word("grandfather", "dziadek", R.raw.family_grandfather, R.drawable.family_grandfather),
                new Word("grandmother", "babcia", R.raw.family_grandmother, R.drawable.family_grandmother),
                new Word("mother", "mama", R.raw.family_mother, R.drawable.family_mother),
                new Word("older brother", "starszy brat", R.raw.family_older_brother, R.drawable.family_older_brother),
                new Word("older sister", "starsza siostra", R.raw.family_older_sister, R.drawable.family_older_sister),
                new Word("son", "syn", R.raw.family_son, R.drawable.family_son),
                new Word("younger brother", "młodszy brat", R.raw.family_younger_brother, R.drawable.family_younger_brother),
                new Word("younger sister", "młodsza siostra", R.raw.family_younger_sister, R.drawable.family_younger_sister)));

        WordAdapter wordsArray = new WordAdapter(this, words, bg_color);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(wordsArray);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word wrd = words.get(i);
                releaseMediaPlayer();
                mp= MediaPlayer.create(FamilyActivity.this, wrd.getAudioID());
                mp.start();
                mp.setOnCompletionListener(mpCompletionListener);
            }
        });
        listView.setAdapter(wordsArray);
    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    void releaseMediaPlayer(){
        if(mp!=null){
            mp.release();
            mp=null;
        }
    }
}
