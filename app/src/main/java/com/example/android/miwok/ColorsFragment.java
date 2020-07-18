package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ColorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ColorsFragment extends Fragment {
    //Fragment vars
    private MediaPlayer mp;
    private MediaPlayer.OnCompletionListener mpCompletionListener= new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener amListener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ColorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ColorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ColorsFragment newInstance(String param1, String param2) {
        ColorsFragment fragment = new ColorsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        int bg_color=getResources().getColor(R.color.category_colors);
        final ArrayList<Word> words = new ArrayList<>(Arrays.asList(new Word("red", "czerwony", R.raw.color_red, R.drawable.color_red),
                new Word("green", "zielony", R.raw.color_green, R.drawable.color_green),
                new Word("mustard yellow", "musztardowy", R.raw.color_mustard_yellow, R.drawable.color_mustard_yellow),
                new Word("yellow", "żółty", R.raw.color_dusty_yellow, R.drawable.color_dusty_yellow),
                new Word("black", "czarny", R.raw.color_black, R.drawable.color_black),
                new Word("gray", "szary", R.raw.color_gray, R.drawable.color_gray),
                new Word("brown", "brązowy", R.raw.color_brown, R.drawable.color_brown),
                new Word("white", "biały", R.raw.color_white, R.drawable.color_white)));

        final WordAdapter wordsArray = new WordAdapter(getActivity(), words, bg_color);
        ListView listView=(ListView) rootView.findViewById(R.id.list);
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
        return rootView;
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    void onAudioFocusChange(int state, int num, ArrayList<Word> wrds){
        switch(state){
            case AudioManager.AUDIOFOCUS_GAIN:{
                Word wrd = wrds.get(num);
                releaseMediaPlayer();
                mp=MediaPlayer.create(getActivity(), wrd.getAudioID());
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