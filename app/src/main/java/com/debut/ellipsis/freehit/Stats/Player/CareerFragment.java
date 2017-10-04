package com.debut.ellipsis.freehit.Stats.Player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CareerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_stats_player_career, container, false);

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);



        Call<Career_Item> call = apiInterface.doGetCareerInfo();
        call.enqueue(new Callback<Career_Item>() {
            @Override
            public void onResponse(Call<Career_Item> call, Response<Career_Item> response) {

                Career_Item info = response.body();
                final ImageView articleImage = (ImageView) rootView.findViewById(R.id.Player_image);

                final String ImageURL = info.getImg();

                Glide.with(getContext()).load(ImageURL).centerCrop().placeholder(R.drawable.matches).into(articleImage);
                TextView des=(TextView)rootView.findViewById((R.id.PlayerInfo));
                des.setText(info.getDesc());
           }


            @Override
            public void onFailure(Call<Career_Item> call, Throwable t) {

            }

            });



        return rootView;
    }
}
