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
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.miwok.Word;

import java.util.ArrayList;
import java.util.Arrays;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        int bg_color=getResources().getColor(R.color.category_family);
        ArrayList<Word> words = new ArrayList<>(Arrays.asList(new Word("daughter", "córka", bg_color, R.drawable.family_daughter),
                new Word("father", "tata", bg_color, R.drawable.family_father),
                new Word("grandfather", "dziadek", bg_color, R.drawable.family_grandfather),
                new Word("grandmother", "babcia", bg_color, R.drawable.family_grandmother),
                new Word("mother", "mama", bg_color, R.drawable.family_mother),
                new Word("older brother", "starszy brat", bg_color, R.drawable.family_older_brother),
                new Word("older sister", "starsza siostra", bg_color, R.drawable.family_older_sister),
                new Word("son", "syn", bg_color, R.drawable.family_son),
                new Word("younger brother", "młodszy brat", bg_color, R.drawable.family_younger_brother),
                new Word("younger sister", "młodsza siostra", bg_color, R.drawable.family_younger_sister)));

        WordAdapter wordsArray = new WordAdapter(this, words);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(wordsArray);
    }

}
