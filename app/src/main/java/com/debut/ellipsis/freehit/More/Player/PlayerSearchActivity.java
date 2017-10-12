package com.debut.ellipsis.freehit.More.Player;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.More.favorites.FavouriteTeam.FavTeamPlayersAdapter;
import com.debut.ellipsis.freehit.PlayerCountryItem;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerSearchActivity extends AppCompatActivity {

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_player_search_fragment);

        toolbar = (Toolbar) findViewById(R.id.toolbar_player_search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("PLAYERS");

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.player_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        EditText e = (EditText) findViewById(R.id.editText_player);
        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
                final Call<PlayerCountryItem> playerInfo = apiInterface.doGetPlayerList(s.toString());
                playerInfo.enqueue(new Callback<PlayerCountryItem>() {
                    @Override
                    public void onResponse(Call<PlayerCountryItem> call, Response<PlayerCountryItem> response) {

                        List<PlayerCountryItem> playerCountryItems = response.body().getResults();
                        for (int i = 0; i < playerCountryItems.size(); i++) {

                            recyclerView.setAdapter(new FavTeamPlayersAdapter(playerCountryItems, R.layout.country_picker_row, getApplicationContext()));
                        }
                    }

                    @Override
                    public void onFailure(Call<PlayerCountryItem> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(0, R.anim.exit_to_right);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        PlayerSearchActivity.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }


}
