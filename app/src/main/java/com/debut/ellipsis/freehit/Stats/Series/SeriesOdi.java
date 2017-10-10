package com.debut.ellipsis.freehit.Stats.Series;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.debut.ellipsis.freehit.R;

/**
 * Created by Jayanth on 07-10-2017.
 */

public class SeriesOdi extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_more_perfo_fragment, container, false);
       /* Intent i = getActivity().getIntent();
        int Team = i.getIntExtra("CountryName", 0);
        String tempTeamName = this.getContext().getString(Team);*/
        return rootView;
    }

    }
