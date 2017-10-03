package com.debut.ellipsis.freehit.Stats.Team;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchScoreCard;
import com.debut.ellipsis.freehit.News.NewsFragment;

/**
 * Created by Jayanth on 03-10-2017.
 */

public class Home extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String strtext = getArguments().getString("p1");
        Intent i =new Intent(getContext(), NewsFragment.class);
        i.putExtra("pos",strtext);
        startActivity(i);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
