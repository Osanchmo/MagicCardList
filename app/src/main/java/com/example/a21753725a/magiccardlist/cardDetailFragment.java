package com.example.a21753725a.magiccardlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CardDetailFragment extends Fragment {

    private View view;
    private TextView frName;
    private TextView frType;
    private TextView frRarity;
    private TextView frColors;
    private TextView frStr;
    private TextView frText;
    private ImageView frCard;

    public CardDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_carddetail, container, false);

        Intent intent = getActivity().getIntent();

        if (intent != null){
            Card card = (Card) intent.getSerializableExtra("card");
            updateCard(card);
        }

        return view;
    }
    private void updateCard(Card card){

        Log.d("Card",card.getName());

        frName = (TextView) view.findViewById(R.id.frName);
        frType = (TextView) view.findViewById(R.id.frType);
        frRarity = (TextView) view.findViewById(R.id.frRarity);
        frColors = (TextView) view.findViewById(R.id.frColor);
        frStr = (TextView) view.findViewById(R.id.frStr);
        frText = (TextView) view.findViewById(R.id.frText);

        frName.setText(card.getName());
        frType.setText(card.getType());
        frRarity.setText(card.getRarity());

        if (card.getColores() != null){
            frColors.setText(card.getColores());
        } else frColors.setText("");

        if (card.getFuerza() != null || card.getResistencia() != null){
            frStr.setText(card.getFuerza() + "/" + card.getResistencia());
        } else frStr.setText("");

        if (card.getTexto() != null){
            frText.setText(card.getTexto());
        }
        else frText.setText("");

        frCard = (ImageView) view.findViewById(R.id.frCard);
        Glide.with(getContext()).load(card.getImagen()).into(frCard);
    }
}
