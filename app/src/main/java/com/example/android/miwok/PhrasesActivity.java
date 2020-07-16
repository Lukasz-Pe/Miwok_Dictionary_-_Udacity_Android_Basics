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

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mp;
    private MediaPlayer.OnCompletionListener mpCompletionListener= new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener amListener;
    ArrayList<Word> words= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        int[] resIDs={R.raw.phrase_come_here,R.raw.phrase_what_is_your_name,
                R.raw.phrase_are_you_coming, R.raw.phrase_how_are_you_feeling,
        R.raw.phrase_im_coming, R.raw.phrase_im_feeling_good,
        R.raw.phrase_lets_go, R.raw.phrase_my_name_is,
        R.raw.phrase_where_are_you_going, R.raw.phrase_yes_im_coming,
        };
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
                int afResult = audioManager.requestAudioFocus(amListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                onAudioFocusChange(afResult,i,words);
                audioManager.abandonAudioFocus(amListener);
            }
        });
        listView.setAdapter(wordsArray);
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    void onAudioFocusChange(int state, int num, ArrayList<Word> wrds){
        switch(state){
            case AudioManager.AUDIOFOCUS_GAIN:{
                Word wrd = wrds.get(num);
                releaseMediaPlayer();
                mp=MediaPlayer.create(PhrasesActivity.this, wrd.getAudioID());
                mp.start();
                mp.setOnCompletionListener(mpCompletionListener);
                break;
            }
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: {
                    releaseMediaPlayer();
                break;
            }
        }
    }

    void releaseMediaPlayer(){
        if(mp!=null){
            mp.release();
            mp=null;
        }
    }
}
