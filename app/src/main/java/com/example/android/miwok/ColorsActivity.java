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

import java.util.ArrayList;
import java.util.Arrays;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        int bg_color=getResources().getColor(R.color.category_colors);
        final ArrayList<Word> words = new ArrayList<>(Arrays.asList(new Word("red", "czerwony", R.drawable.color_red, R.raw.color_red),
                new Word("green", "zielony", R.drawable.color_green, R.raw.color_green),
                new Word("mustard yellow", "musztardowy", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow),
                new Word("yellow", "żółty", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow),
                new Word("black", "czarny", R.drawable.color_black, R.raw.color_black),
                new Word("gray", "szary", R.drawable.color_gray, R.raw.color_gray),
                new Word("brown", "brązowy", R.drawable.color_brown, R.raw.color_brown),
                new Word("white", "biały", R.drawable.color_white, R.raw.color_white)));

        WordAdapter wordsArray = new WordAdapter(this, words, bg_color);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(wordsArray);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word wrd = words.get(i);
                mp= MediaPlayer.create(ColorsActivity.this, wrd.getAudioID());
                mp.start();
            }
        });
        listView.setAdapter(wordsArray);
    }
}
