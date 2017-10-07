package com.debut.ellipsis.freehit.Stats.Player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CareerFragment extends Fragment {

    private String player_url;
    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent i = getActivity().getIntent();
        player_url = i.getStringExtra("player_url");
        System.out.println(player_url);

        final View rootView = inflater.inflate(R.layout.fragment_stats_player_career, container, false);

        mProgressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);

        Call<CareerItem> call = apiInterface.doGetCareerInfo(player_url);
        call.enqueue(new Callback<CareerItem>() {
            @Override
            public void onResponse(Call<CareerItem> call, Response<CareerItem> response) {
                mProgressBar.setVisibility(View.INVISIBLE);
                CareerItem info = response.body();
                TextView des=(TextView)rootView.findViewById((R.id.PlayerInfo));
                des.setText(info.getDesc());
           }


            @Override
            public void onFailure(Call<CareerItem> call, Throwable t) {
                Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT).show();
                call.cancel();

            }

            });



        return rootView;
    }
}
