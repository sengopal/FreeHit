package com.debut.ellipsis.freehit.Stats.Team;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchScoreCard;


public class Schedule extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String strtext = getArguments().getString("p2");
        Intent i =new Intent(getContext(), LiveMatchScoreCard.class);
        i.putExtra("pos",strtext);
        startActivity(i);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
