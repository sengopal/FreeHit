package com.debut.ellipsis.freehit.Stats.Player;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchCardItem;
import com.debut.ellipsis.freehit.R;

import java.util.List;


public class Info_Fragment extends Fragment implements LoaderManager.LoaderCallbacks<List<LiveMatchCardItem>> {
    //ProgressBar that is displayed before the earthquake list is generated
    private ProgressBar mProgressBar;



    private static final int PLAYER_LOADER_ID = 1;

    private static final String LOG_TAG = Info_Fragment.class.getName();



    private static final String PLAYER_BIO_REQUEST_URL =
            "http://freehit-api.herokuapp.com/playerbio?url=%20https://sports.ndtv.com/cricket/players/199-chris-gayle-playerprofile";

   public Info_Fragment() {

   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_stats_player_info, container, false);
        final ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
       // Log.i(LOG_TAG,"TEST:pLAYER bio onCreate() called");
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
           LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(PLAYER_LOADER_ID, null, this).forceLoad();


        }

        else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View progressBar = rootView.findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);

        }
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        int colorCodeDark = Color.parseColor("#F44336");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mProgressBar.setIndeterminateTintList(ColorStateList.valueOf(colorCodeDark));
        }





        return rootView;

        }


    @Override
    public android.support.v4.content.Loader<List<LiveMatchCardItem>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<LiveMatchCardItem>> loader, List<LiveMatchCardItem> data) {

    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<LiveMatchCardItem>> loader) {

    }
}



