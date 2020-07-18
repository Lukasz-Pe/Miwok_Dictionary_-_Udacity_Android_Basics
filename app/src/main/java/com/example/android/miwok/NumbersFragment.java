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
 * Use the {@link NumbersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumbersFragment extends Fragment {
    //Internal vars
    private MediaPlayer mp;
    private MediaPlayer.OnCompletionListener mpCompletionListener= new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener amListener;

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NumbersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumbersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumbersFragment newInstance(String param1, String param2) {
        NumbersFragment fragment = new NumbersFragment();
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

    //My methods
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