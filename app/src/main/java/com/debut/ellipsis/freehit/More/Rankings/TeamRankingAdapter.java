package com.debut.ellipsis.freehit.More.Rankings;


import android.content.Context;
import android.content.Intent;
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
import com.debut.ellipsis.freehit.More.Team.TeamActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

public class TeamRankingAdapter extends RecyclerView.Adapter<TeamRankingAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView teamRank;
        ImageView teamFlag;
        TextView teamName;
        TextView teamRatings;
        public RelativeLayout rlcontainer;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            teamRank = (TextView) itemView.findViewById(R.id.rank);
            teamFlag = (ImageView) itemView.findViewById(R.id.team_flag);
            teamName = (TextView) itemView.findViewById(R.id.team);
            teamRatings = (TextView) itemView.findViewById(R.id.ratings);
            rlcontainer = (RelativeLayout) itemView.findViewById(R.id.rlcontainer);



        }
    }

    // Store a member variable for the contacts
    private List<RankingItem.Team> mTeamList;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public TeamRankingAdapter(Context context, List<RankingItem.Team> TeamList) {
        mTeamList = TeamList;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public TeamRankingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.fragment_more_ranking_team_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TeamRankingAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final RankingItem.Team TeamItem = mTeamList.get(position);

        // Set item views based on your views and data model

        TextView teamName = viewHolder.teamName;
        teamName.setText(TeamItem.getTeam());

        TextView rank = viewHolder.teamRank;
        rank.setText(TeamItem.getPos());

        TextView ratings = viewHolder.teamRatings;
        ratings.setText(TeamItem.getRating());

        String team_logo_url = WelcomeActivity.countryHash.getCountryFlag(TeamItem.getTeam().toUpperCase());

        ImageView imageViewTeamLogo = viewHolder.teamFlag;

        MainActivity.requestBuilder = GlideApp.with(mContext).load(team_logo_url).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);

        MainActivity.requestBuilder.into(imageViewTeamLogo);

        RelativeLayout RLContainer = viewHolder.rlcontainer;

        View.OnClickListener mClickListener;


        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent teamIntent = new Intent(mContext, TeamActivity.class);
                teamIntent.putExtra("fav_country", TeamItem.getTeam());

                teamIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(teamIntent);

            }
        };
        RLContainer.setOnClickListener(mClickListener);


    }

    @Override
    public int getItemCount() {
        return mTeamList.size();
    }
}
