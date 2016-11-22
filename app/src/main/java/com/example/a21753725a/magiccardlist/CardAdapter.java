package com.example.a21753725a.magiccardlist;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class CardAdapter extends ArrayAdapter<Card>{
    public CardAdapter(Context context, int resource, List<Card> objects) {
                super(context, resource, objects);
            }
    
}
