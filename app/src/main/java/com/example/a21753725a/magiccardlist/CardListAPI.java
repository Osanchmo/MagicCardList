package com.example.a21753725a.magiccardlist;
import android.net.Uri;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CardListAPI {


    private final String BASE_URL = "https://api.magicthegathering.io/v1/cards";

    ArrayList<Card> getCards() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                //.appendPath("lists")
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    ArrayList<Card> filterCards(String filtro1, String filtro2) {
        Uri uri = null;

        if(!filtro1.equalsIgnoreCase("None") && (!filtro2.equalsIgnoreCase("None"))){
            uri = uri.parse(BASE_URL);
            uri.buildUpon()
                .appendQueryParameter("colors",filtro1)
                .appendQueryParameter("rarity",filtro2)
            .build();

        }
        else {

        if(!filtro1.equalsIgnoreCase("None")){
            uri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .appendQueryParameter("colors",filtro1)
                    .build();

        }

        if(!filtro2.equalsIgnoreCase("None")){
            uri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .appendQueryParameter("colors",filtro2)
                    .build();

        }
    }
        return doCall(uri.toString());
    }

    @Nullable
    private ArrayList<Card> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Recoge las cartas de una API y almacena datos en un array de objetos carta
     * con los datos que queremos guardar
     * @param jsonResponse
     * @return
     */
    private ArrayList<Card> processJson(String jsonResponse) {
        ArrayList<Card> cartas = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCards = data.getJSONArray("cards");

            for (int i = 0; i < jsonCards.length(); i++) {

                JSONObject jsonCard = jsonCards.getJSONObject(i);

                Card carta = new Card();

                carta.setName(jsonCard.getString("name"));
                carta.setRarity(jsonCard.getString("rarity"));
                carta.setRarity(jsonCard.getString("type"));
                carta.setRarity(jsonCard.getString("imageUrl"));
                carta.setId(jsonCard.getString("id"));

                if(jsonCard.has("text")) carta.setTexto(jsonCard.getString("text"));
                if(jsonCard.has("toughness")) carta.setFuerza(jsonCard.getString("toughness"));
                if(jsonCard.has("power")) carta.setTexto(jsonCard.getString("power"));
                if(jsonCard.has("colors")) {
                    String [] colors = new String[jsonCard.getJSONArray("colors").length()];
                    for (int j = 0; j <jsonCard.getJSONArray("colors").length() ; j++) {
                        colors[i] = jsonCard.getJSONArray("colors").get(i).toString();
                    }
                    carta.setColores(colors);
                }

                cartas.add(carta);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;
    }
}