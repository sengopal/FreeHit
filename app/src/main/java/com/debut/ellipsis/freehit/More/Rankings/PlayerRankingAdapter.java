package com.debut.ellipsis.freehit.More.Rankings;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.debut.ellipsis.freehit.More.Player.PlayerActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

public class PlayerRankingAdapter extends RecyclerView.Adapter<PlayerRankingAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView playerRank;
        TextView playerName;
        TextView playerRatings;
        public RelativeLayout rlcontainer;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            playerRank = (TextView) itemView.findViewById(R.id.rank);
            playerName = (TextView) itemView.findViewById(R.id.team);
            playerRatings = (TextView) itemView.findViewById(R.id.ratings);
            rlcontainer = (RelativeLayout) itemView.findViewById(R.id.rlcontainer);


        }
    }

    // Store a member variable for the contacts
    private List<RankingItem.AllRounder> mPlayerList;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public PlayerRankingAdapter(Context context, List<RankingItem.AllRounder> PlayerList) {
        mPlayerList = PlayerList;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public PlayerRankingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.fragment_more_ranking_batting_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PlayerRankingAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final RankingItem.AllRounder PlayerItem = mPlayerList.get(position);


        // Set item views based on your views and data model

        TextView teamName = viewHolder.playerName;
        teamName.setText(PlayerItem.getName() + " (" + PlayerItem.getCountry() + ')');

        TextView rank = viewHolder.playerRank;
        rank.setText(PlayerItem.getPos());

        TextView ratings = viewHolder.playerRatings;
        ratings.setText(PlayerItem.getRating());

        RelativeLayout RLContainer = viewHolder.rlcontainer;

        View.OnClickListener mClickListener;

        if (!PlayerItem.getLink().equals("")) {

            mClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent teamIntent = new Intent(mContext, PlayerActivity.class);
                    teamIntent.putExtra("player_name", PlayerItem.getName());
                    teamIntent.putExtra("player_url", PlayerItem.getLink());
                    teamIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(teamIntent);

                }
            };
            RLContainer.setOnClickListener(mClickListener);
        }

    }

    @Override
    public int getItemCount() {
        return mPlayerList.size();
    }
}
