package com.example.a21753725a.magiccardlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CardAdapter extends ArrayAdapter<Card>{
    public CardAdapter(Context context, int resource, List<Card> objects) {
                super(context, resource, objects);
            }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {


        Card card = getItem(pos);
        Log.w("XXXX", card.toString());

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.card_info, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvType = (TextView) convertView.findViewById(R.id.tvType);
        ImageView imCard = (ImageView) convertView.findViewById(R.id.imCard);

        tvName.setText(card.getName());
        tvType.setText(card.getType() + " - " + card.getRarity());

        return convertView;
    }
}
