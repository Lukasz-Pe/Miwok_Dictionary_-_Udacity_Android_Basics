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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        int bg_color=getResources().getColor(R.color.category_colors);
        ArrayList<Word> words = new ArrayList<>(Arrays.asList(new Word("red", "czerwony", bg_color, R.drawable.color_red),
                new Word("green", "zielony", bg_color, R.drawable.color_green),
                new Word("mustard yellow", "musztardowy", bg_color, R.drawable.color_mustard_yellow),
                new Word("yellow", "żółty", bg_color, R.drawable.color_dusty_yellow),
                new Word("black", "czarny", bg_color, R.drawable.color_black),
                new Word("gray", "szary", bg_color, R.drawable.color_gray),
                new Word("brown", "brązowy", bg_color, R.drawable.color_brown),
                new Word("white", "biały", bg_color, R.drawable.color_white)));

        WordAdapter wordsArray = new WordAdapter(this, words);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(wordsArray);
    }
}
