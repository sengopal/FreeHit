package com.debut.ellipsis.freehit.More.Player;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class PlayerSearch_Fragment extends Activity {

    ArrayList<String> datasource;

    ArrayList<String> results;
    ArrayAdapter<String> adapter;
    final ArrayList<Player_search_item> PlayerItem = new ArrayList<Player_search_item>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_player_search_fragment);
        // Get the intent, verify the action and get the query
        final ListView players = (ListView) findViewById(R.id.player_list_search);
        final Player_search_adapter adapt = new Player_search_adapter(getApplicationContext(),PlayerItem);


        EditText e = (EditText) findViewById(R.id.editText_player);
        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
                    final Call<player_parsing> playerInfo =  apiInterface.doGetPlayerList(s.toString());
                    playerInfo.enqueue(new Callback<player_parsing>() {
                        @Override
                        public void onResponse(Call<player_parsing> call, Response<player_parsing> response) {
                            player_parsing info = response.body();
                            List<player_parsing> var = info.getResult();
                            for (int i = 0; i < var.size(); i++) {
                                String name = var.get(i).getName();
                                String link = var.get(i).getLink();
                               
                                PlayerItem.add(new Player_search_item(name));
                                adapt.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<player_parsing> call, Throwable t) {

                        }
                    });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





    }



}
