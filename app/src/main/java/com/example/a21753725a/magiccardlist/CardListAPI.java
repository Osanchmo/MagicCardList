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

    public ArrayList<Card> getCards(String rarity, String color) {

        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("rarity",rarity)
                .appendQueryParameter("colors",color)
                .build();
        String url = builtUri.toString();

        return doCall(url);
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
                System.out.println(jsonCard.get("name"));
                carta.setRarity(jsonCard.getString("rarity"));
                carta.setType(jsonCard.getString("type"));
                carta.setImagen(jsonCard.getString("imageUrl"));

                if(jsonCard.has("text")) carta.setTexto(jsonCard.getString("text"));
                if(jsonCard.has("toughness")) carta.setFuerza(jsonCard.getString("toughness"));
                if(jsonCard.has("power")) carta.setTexto(jsonCard.getString("power"));
                if(jsonCard.has("colors")) carta.setColores(jsonCard.getString("colors"));

                cartas.add(carta);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;
    }
}