package com.example.a21753725a.magiccardlist;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class MainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView cardList = (ListView) view.findViewById(R.id.listViewCards);

        String[] data = {
                "Los 400 golpes",
                "El odio",
                "El padrino",
                "El padrino. Parte II",
                "Ocurrió cerca de su casa",
                "Infiltrados",
                "Umberto D."
        };

        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lista_cartas,
                R.id.titolCards,
                items
        );
        cardList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
                inflater.inflate(R.menu.refresh_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void refresh() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Card>> {

        @Override
        protected ArrayList<Card> doInBackground(Void... voids) {

            CardListAPI api = new CardListAPI();
            ArrayList<Card> result;

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String color = preferences.getString("color","None");
            String rarity = preferences.getString("rarity","None");

            if (color.equalsIgnoreCase("None") && rarity.equalsIgnoreCase("None"))
            {
                result = api.getCards();
            }
            else if (!color.equalsIgnoreCase("None") && !color.equalsIgnoreCase("None"))
            {
                result = api.filterCards(color,rarity);
            }
            else if (!color.equalsIgnoreCase("")){
                result = api.filterCards(color,"");
            }
            else
            {
                result = api.filterCards("",rarity);
            }

            Gson JSON = new GsonBuilder()
                    .disableHtmlEscaping()
                    .setPrettyPrinting()
                    .create();

            //Log.d("DEBUG", result.toString());
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Card> cards) {
            adapter.clear();
            for (Card card : cards) {
                adapter.add(card.getName());
            }
        }
    }
}