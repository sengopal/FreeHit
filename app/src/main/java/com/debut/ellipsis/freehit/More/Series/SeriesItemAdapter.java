package com.debut.ellipsis.freehit.More.Series;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.debut.ellipsis.freehit.CountryHash;
import com.debut.ellipsis.freehit.Glide.GlideApp;
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
            Team1Logo = (ImageView) v.findViewById(R.id.Team1Logo);
            Team2Logo = (ImageView) v.findViewById(R.id.Team2Logo);
            SeriesTitle = (TextView) v.findViewById(R.id.series_title);
            RlContainer = (RelativeLayout) v.findViewById(R.id.series_layout);
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
        String Title=seriesItems.get(position).getTitle();
        String[] t=Title.split(",");
        holder.SeriesTitle.setText(t[0]);
        final String team1 = seriesItems.get(position).getTeam1();
        final String team2 = seriesItems.get(position).getTeam2();
        final String date = seriesItems.get(position).getDate();

        CountryHash countryHash = new CountryHash();
        final String Team1Name = countryHash.getCountrySN(team1.toUpperCase());
        final String Team2Name = countryHash.getCountrySN(team2.toUpperCase());

        String Team1LogoUrl = countryHash.getCountryFlag(team1.toUpperCase());
        String Team2LogoUrl = countryHash.getCountryFlag(team2.toUpperCase());

        /*CustomImageSizeModel Team1Logo = new CustomImageSizeModelFutureStudio(Team1LogoUrl);
        CustomImageSizeModel Team2Logo = new CustomImageSizeModelFutureStudio(Team2LogoUrl);*/

        RequestBuilder requestBuilder = GlideApp.with(context).load(Team1LogoUrl).placeholder(R.drawable.matches);

        requestBuilder.into(holder.Team1Logo);

        RequestBuilder requestBuilder1 = GlideApp.with(context).load(Team2LogoUrl).placeholder(R.drawable.matches);

        requestBuilder1.into(holder.Team2Logo);

        /*GlideApp.with(context).load(Team1Logo).apply(new RequestOptions().placeholder(R.drawable.matches)).into(holder.Team1Logo);
        GlideApp.with(context).load(Team2Logo).apply(new RequestOptions().placeholder(R.drawable.matches)).into(holder.Team2Logo);*/

        RelativeLayout RLContainer = holder.RlContainer;

        View.OnClickListener mClickListener;

        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent SeriesActivityIntent = new Intent(context, SeriesActivity.class);
                SeriesActivityIntent.putExtra("Series_Name", seriesItems.get(position).getTitle());
                SeriesActivityIntent.putExtra("date", date);
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
