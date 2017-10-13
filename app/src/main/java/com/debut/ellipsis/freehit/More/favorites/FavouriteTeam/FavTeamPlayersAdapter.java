package com.debut.ellipsis.freehit.More.favorites.FavouriteTeam;


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
import com.bumptech.glide.request.RequestOptions;
import com.debut.ellipsis.freehit.More.Player.PlayerActivity;
import com.debut.ellipsis.freehit.PlayerCountryItem;
import com.debut.ellipsis.freehit.R;

import java.util.List;

public class FavTeamPlayersAdapter extends RecyclerView.Adapter<FavTeamPlayersAdapter.FavTeamPlayerViewHolder> {

    private List<PlayerCountryItem> playerCountryItems;
    private int rowLayout;
    private Context context;


    public static class FavTeamPlayerViewHolder extends RecyclerView.ViewHolder {
        ImageView PlayerImage;
        TextView PlayerName;
        LinearLayout rlcontainer;


        public FavTeamPlayerViewHolder(View v) {
            super(v);
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
        Glide.with(context).load(playerCountryItems.get(position).getImage()).apply(new RequestOptions().placeholder(R.drawable.matches).centerCrop()).into(holder.PlayerImage);

        LinearLayout RLContainer = holder.rlcontainer;

        View.OnClickListener mClickListener;


        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent PlayerBioIntent = new Intent(context, PlayerActivity.class);
                PlayerBioIntent.putExtra("player_url", playerCountryItems.get(position).getLink());
                PlayerBioIntent.putExtra("player_name",playerCountryItems.get(position).getName());
                PlayerBioIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(PlayerBioIntent);

            }
        };
        RLContainer.setOnClickListener(mClickListener);


    }

    @Override
    public int getItemCount() {
        return playerCountryItems.size();
    }
}
