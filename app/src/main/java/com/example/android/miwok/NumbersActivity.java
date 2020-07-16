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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {
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
        int bg_color=getResources().getColor(R.color.category_numbers);
        final ArrayList<Word> words = new ArrayList<>(Arrays.asList(new Word("one", "jeden", R.raw.number_one, R.drawable.number_one),
                new Word("two", "dwa", R.raw.number_two, R.drawable.number_two),
                new Word("three", "trzy", R.raw.number_three, R.drawable.number_three),
                new Word("four", "cztery", R.raw.number_four, R.drawable.number_four),
                new Word("five", "pięć", R.raw.number_five, R.drawable.number_five),
                new Word("six", "sześć", R.raw.number_six, R.drawable.number_six),
                new Word("seven", "siedem", R.raw.number_seven, R.drawable.number_seven),
                new Word("eight", "osiem", R.raw.number_eight, R.drawable.number_eight),
                new Word("nine", "dziewięć", R.raw.number_nine, R.drawable.number_nine),
                new Word("ten", "dziesięć", R.raw.number_ten, R.drawable.number_ten)));



        final WordAdapter wordsArray = new WordAdapter(this, words, bg_color);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(wordsArray);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word wrd = words.get(i);
                releaseMediaPlayer();
                mp=MediaPlayer.create(NumbersActivity.this, wrd.getAudioID());
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