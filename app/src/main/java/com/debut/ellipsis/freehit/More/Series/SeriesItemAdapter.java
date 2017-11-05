package com.debut.ellipsis.freehit.More.Series;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

public class SeriesItemAdapter extends RecyclerView.Adapter<SeriesItemAdapter.SeriesViewHolder> {

    private List<SeriesItem> seriesItems;
    private int rowLayout;
    private Context context;


    public static class SeriesViewHolder extends RecyclerView.ViewHolder {
        ImageView Team1Logo;
        ImageView Team2Logo;
        TextView SeriesTitle;
        RelativeLayout RlContainer;


        public SeriesViewHolder(View v) {
            super(v);
            Team1Logo = v.findViewById(R.id.Team1Logo);
            Team2Logo = v.findViewById(R.id.Team2Logo);
            SeriesTitle = v.findViewById(R.id.series_title);
            RlContainer = v.findViewById(R.id.series_layout);
        }
    }

    public SeriesItemAdapter(List<SeriesItem> series, int rowLayout, Context context) {
        this.seriesItems = series;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public SeriesItemAdapter.SeriesViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        }
        return new SeriesItemAdapter.SeriesViewHolder(view);

    }


    @Override
    public void onBindViewHolder(SeriesItemAdapter.SeriesViewHolder holder, final int position) {
        String Title = seriesItems.get(position).getTitle();

        Title = Title /*+ ","*/;
        String[] t = Title.split(",");
        String[] origTeam = new String[10];
        origTeam[0] = " ";
        origTeam[1] = " ";

        holder.SeriesTitle.setText(Title);
        String team1 = seriesItems.get(position).getTeam1();
        String team2 = seriesItems.get(position).getTeam2();

        final String date = seriesItems.get(position).getDate();
        origTeam = t[0].split(" ");
        origTeam[0] = origTeam[0].trim();
        origTeam[1] = origTeam[1].trim();
        origTeam[1] = origTeam[0] + " " + origTeam[1];


        if (team1.equalsIgnoreCase(origTeam[0])) {
            team1 = team1;
        } else if (WelcomeActivity.countryHash.getCountrySN(origTeam[0].toUpperCase()) != null) {
            team2 = team1;
            team1 = origTeam[0];

        } else if (WelcomeActivity.countryHash.getCountrySN(origTeam[1].toUpperCase()) != null) {
            team2 = team1;
            team1 = origTeam[1];

        }

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            holder.SeriesTitle.setTextColor(Color.WHITE);
        }


        final String Team1Name = WelcomeActivity.countryHash.getCountrySN(team1.toUpperCase());
        final String Team2Name = WelcomeActivity.countryHash.getCountrySN(team2.toUpperCase());

        String Team1LogoUrl = WelcomeActivity.countryHash.getCountryFlag(team1.toUpperCase());
        String Team2LogoUrl = WelcomeActivity.countryHash.getCountryFlag(team2.toUpperCase());

        MainActivity.requestBuilder = GlideApp.with(context).load(Team1LogoUrl).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);

        MainActivity.requestBuilder.into(holder.Team1Logo);

        MainActivity.requestBuilder = GlideApp.with(context).load(Team2LogoUrl).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);

        MainActivity.requestBuilder.into(holder.Team2Logo);

        RelativeLayout RLContainer = holder.RlContainer;

        View.OnClickListener mClickListener;

        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent SeriesActivityIntent = new Intent(context, SeriesActivity.class);
                SeriesActivityIntent.putExtra("Series_Name", seriesItems.get(position).getTitle());
                SeriesActivityIntent.putExtra("date", date);
                SeriesActivityIntent.putExtra("id", seriesItems.get(position).getId());
                SeriesActivityIntent.putExtra("Teams", Team1Name + "," + Team2Name);
                SeriesActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(SeriesActivityIntent);

            }
        };
        RLContainer.setOnClickListener(mClickListener);

    }

    @Override
    public int getItemCount() {
        return seriesItems.size();
    }
}
