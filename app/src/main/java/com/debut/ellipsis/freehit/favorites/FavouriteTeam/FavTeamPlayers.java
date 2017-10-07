package com.debut.ellipsis.freehit.favorites.FavouriteTeam;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.CountryItem;
import com.debut.ellipsis.freehit.PlayerCountryItem;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavTeamPlayers extends AppCompatActivity {

    APIInterface apiInterface;
    private String TeamID;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news_list);

        Intent i = getIntent();
        final String Team = i.getStringExtra("CountryName");
        System.out.println("COUNTRY : "+ Team);

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        Call<CountryItem> call = apiInterface.doGetCountryResources();
        call.enqueue(new Callback<CountryItem>() {
            @Override
            public void onResponse(Call<CountryItem> call, Response<CountryItem> response) {

                List<CountryItem> countries = response.body().getResults();
                mProgressBar.setVisibility(View.INVISIBLE);
                for(int i=0;i<countries.size();i++)
                {
                    if(countries.get(i).getTitle().equals(Team))
                    {
//GITPUSH
                        int Teamid=countries.get(i).getId();
                        TeamID = String.valueOf(Teamid);
                        Call<PlayerCountryItem> call1 = apiInterface.doGetFavTeamPlayers(TeamID);
                        call1.enqueue(new Callback<PlayerCountryItem>() {
                            @Override
                            public void onResponse(Call<PlayerCountryItem> call, Response<PlayerCountryItem> response) {

                                List<PlayerCountryItem> playerCountryItems = response.body().getResults();
                                recyclerView.setAdapter(new FavTeamPlayersAdapter(playerCountryItems, R.layout.country_picker_row, getApplicationContext()));

                            }

                            @Override
                            public void onFailure(Call<PlayerCountryItem> call, Throwable t) {
                                Toast toast=Toast.makeText(getApplicationContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
                                toast.show();
                                call.cancel();
                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<CountryItem> call, Throwable t) {
                Toast toast=Toast.makeText(getApplicationContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
                toast.show();
                call.cancel();
            }
        });




    }

}
