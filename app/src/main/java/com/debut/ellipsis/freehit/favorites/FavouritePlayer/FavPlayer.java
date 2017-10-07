package com.debut.ellipsis.freehit.favorites.FavouritePlayer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavPlayer extends Fragment {


    public FavPlayer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("COMING SOON");
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(35);
        textView.setTextColor(getResources().getColor(R.color.black));
        return textView;
    }

}
