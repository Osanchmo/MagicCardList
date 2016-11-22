package com.example.a21753725a.magiccardlist;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class card_detailFragment extends Fragment {

    private View view;
    private TextView tvName;
    private TextView tvType;
    private TextView tvRarity;
    private TextView tvColors;
    private TextView tvStr;
    private TextView tvText;
    private ImageView imCard;

    public card_detailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_card_detail, container, false);

        Intent intent = getActivity().getIntent();

        if (intent != null){
            Card card = (Card) intent.getSerializableExtra("card");
            updateCard(card);
        }

        return view;
    }
    private void updateCard(Card card){
        tvName = (TextView) view.findViewById(R.id.tvText);
        tvType = (TextView) view.findViewById(R.id.tvType);
        tvRarity = (TextView) view.findViewById(R.id.tvRarity);

        tvName.setText(card.getName());
        tvType.setText(card.getType());
        tvRarity.setText(card.getRarity());

        if (card.getColores() != null){
            tvColors = (TextView) view.findViewById(R.id.tvColor);
            tvColors.setText(card.getColores());
        } else tvColors.setText("");

        if (card.getFuerza() != null || card.getResistencia() != null){
            tvStr = (TextView) view.findViewById(R.id.tvColor);
            tvStr.setText(card.getFuerza() + "/" + card.getResistencia());
        } else tvStr.setText("");

        if (card.getTexto() != null){
            tvText = (TextView) view.findViewById(R.id.tvText);
            tvText.setText(card.getColores());
        }
        else tvColors.setText("");

        imCard = (ImageView) view.findViewById(R.id.imCard);
        Glide.with(getContext()).load(card.getImagen()).into(imCard);
    }
}
