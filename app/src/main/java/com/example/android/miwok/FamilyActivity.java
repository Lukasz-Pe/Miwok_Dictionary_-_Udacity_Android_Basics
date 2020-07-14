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

        ArrayList<Word> words = new ArrayList<>(Arrays.asList(new Word("me", "ja"),
                new Word("brother", "brat"),
                new Word("sister", "siostra"),
                new Word("mom", "mama"),
                new Word("dad", "tata"),
                new Word("wife", "żona"),
                new Word("husband", "mąż"),
                new Word("grandfather", "dziadek"),
                new Word("grandmother", "babcia"),
                new Word("cousin", "kuzyn")));

        WordAdapter wordsArray = new WordAdapter(this, words);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(wordsArray);
    }

}
