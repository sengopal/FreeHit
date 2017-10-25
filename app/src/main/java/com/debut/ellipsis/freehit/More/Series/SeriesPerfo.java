package com.debut.ellipsis.freehit.More.Series;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

public class SeriesPerfo extends Fragment {

    public ViewPager viewPager;
    private TabLayout tabLayout;

    public SeriesPerfo() {
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
        textView.setTextColor(getResources().getColor(R.color.black));
        return textView;

    }
}
