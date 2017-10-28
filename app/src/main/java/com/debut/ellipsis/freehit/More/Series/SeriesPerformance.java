package com.debut.ellipsis.freehit.More.Series;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SeriesPerformance extends Fragment {

    public SeriesPerformance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView textView = new TextView(getActivity());
        textView.setText("COMING SOON");
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(35);
        textView.setTextColor(Color.BLACK);
        return textView;

    }
}
