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

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        int bg_color=getResources().getColor(R.color.category_numbers);
        ArrayList<Word> words = new ArrayList<>(Arrays.asList(new Word("one", "jeden", bg_color, R.drawable.number_one),
                new Word("two", "dwa", bg_color, R.drawable.number_two),
                new Word("three", "trzy", bg_color, R.drawable.number_three),
                new Word("four", "cztery", bg_color, R.drawable.number_four),
                new Word("five", "pięć", bg_color, R.drawable.number_five),
                new Word("six", "sześć", bg_color, R.drawable.number_six),
                new Word("seven", "siedem", bg_color, R.drawable.number_seven),
                new Word("eight", "osiem", bg_color, R.drawable.number_eight),
                new Word("nine", "dziewięć", bg_color, R.drawable.number_nine),
                new Word("ten", "dziesięć", bg_color, R.drawable.number_ten)));

        WordAdapter wordsArray = new WordAdapter(this, words);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(wordsArray);

    }
}