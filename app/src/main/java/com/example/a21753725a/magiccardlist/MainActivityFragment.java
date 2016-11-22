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

    private ArrayList<Card> items;
    private CardAdapter adapter;

    public MainActivityFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView cardList = (ListView) view.findViewById(R.id.listViewCards);

        items = new ArrayList<>();
        adapter = new CardAdapter(
                getContext(),
                R.layout.lista_cartas,
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
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
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
            String color = preferences.getString("color","");
            String rarity = preferences.getString("rarity","");

            result = api.getCards(rarity,color);


            Log.d("DEBUG", result.toString());
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Card> cards) {
            adapter.clear();
            for (Card card : cards) {
                adapter.add(card);
            }
        }
    }
}