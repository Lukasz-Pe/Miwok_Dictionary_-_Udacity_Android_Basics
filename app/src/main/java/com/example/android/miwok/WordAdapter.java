package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(Activity context, ArrayList<Word> words){
        super (context,0, words);
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Word currentWord = getItem(position);
        LinearLayout layout_color=(LinearLayout) listItemView.findViewById(R.id.word_layout_background_color);
        ImageView image=(ImageView)listItemView.findViewById(R.id.show_image);
        TextView miwokTextView = (TextView)listItemView.findViewById(R.id.miwok);
        TextView defaultTextView = (TextView)listItemView.findViewById(R.id.default_word);

        layout_color.setBackgroundColor(currentWord.getBackgroundColor());
        if(image.getDrawable()!=null) {
            image.setImageResource(currentWord.getDrawableId());
        }else{
            image.setVisibility(View.GONE);
        }
        miwokTextView.setText(currentWord.getMiwokTranslation());
        defaultTextView.setText(currentWord.getDefaultTranslation());


        return listItemView;
    }
}
