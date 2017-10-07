package com.debut.ellipsis.freehit.favorites.FavouriteTeam;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.debut.ellipsis.freehit.PlayerCountryItem;
import com.debut.ellipsis.freehit.R;
import com.debut.ellipsis.freehit.Stats.Player.PlayerActivity;

import java.util.List;

public class FavTeamPlayersAdapter extends RecyclerView.Adapter<FavTeamPlayersAdapter.FavTeamPlayerViewHolder> {

    private List<PlayerCountryItem> playerCountryItems;
    private int rowLayout;
    private Context context;


    public static class FavTeamPlayerViewHolder extends RecyclerView.ViewHolder {
        LinearLayout playerLayout;
        ImageView PlayerImage;
        TextView PlayerName;
        LinearLayout rlcontainer;


        public FavTeamPlayerViewHolder(View v) {
            super(v);
            playerLayout = (LinearLayout) v.findViewById(R.id.row_layout);
            PlayerImage = (ImageView) v.findViewById(R.id.row_icon);
            PlayerName = (TextView) v.findViewById(R.id.row_title);

            rlcontainer = (LinearLayout) v.findViewById(R.id.row_layout);
        }
    }

    public FavTeamPlayersAdapter(List<PlayerCountryItem> playerCountryItems, int rowLayout, Context context) {
        this.playerCountryItems = playerCountryItems;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public FavTeamPlayersAdapter.FavTeamPlayerViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        }
        return new FavTeamPlayersAdapter.FavTeamPlayerViewHolder(view);

    }


    @Override
    public void onBindViewHolder(FavTeamPlayersAdapter.FavTeamPlayerViewHolder holder, final int position) {
        holder.PlayerName.setText(playerCountryItems.get(position).getName());
        Glide.with(context).load(playerCountryItems.get(position).getImage()).centerCrop().placeholder(R.drawable.matches).into(holder.PlayerImage);

        LinearLayout RLcontainer = holder.rlcontainer;

        View.OnClickListener mClickListener;


        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent NewsArticleIntent = new Intent(context, PlayerActivity.class);

                String pos = String.valueOf(position);
                NewsArticleIntent.putExtra("match_id", playerCountryItems.get(position).getLink());
                    /*ActivityOptions.makeCustomAnimation(context,R.anim.animation_entry,R.anim.animation_exit);*/
                NewsArticleIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(NewsArticleIntent);

            }
        };
        RLcontainer.setOnClickListener(mClickListener);


    }

    @Override
    public int getItemCount() {
        return playerCountryItems.size();
    }
}
