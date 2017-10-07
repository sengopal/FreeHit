package com.debut.ellipsis.freehit.Matches.PastMatches;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.debut.ellipsis.freehit.R;

import java.util.List;


public class PastMatchesListAdapter extends RecyclerView.Adapter<PastMatchesListAdapter.PastViewHolder> {
    private List<PastMatchCardItem> pastMatchCardItems;
    private Context context;


    public static class PastViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout pastLayout;
        ImageView team1image;
        ImageView team2image;
        TextView title;
        TextView series;
        TextView stadium;
        TextView sn1;
        TextView t1inn1;
        TextView t1inn2;
        TextView sn2;
        TextView t2inn1;
        TextView t2inn2;
        TextView result;
        TextView date;
        RelativeLayout rlcontainer;
//        RelativeLayout rlcontainer;

        public PastViewHolder(View v) {
            super(v);
            pastLayout = (RelativeLayout) v.findViewById(R.id.rlcontainer);
            team1image = (ImageView) v.findViewById(R.id.team_logo_1_past);
            team2image = (ImageView) v.findViewById(R.id.team_logo_2_past);
            title = (TextView) v.findViewById(R.id.match_name_past);
            series = (TextView) v.findViewById(R.id.series_name_past);
            stadium = (TextView) v.findViewById(R.id.stadium_past);
            sn1 = (TextView) v.findViewById(R.id.sn_team_1_past);
            t1inn1 = (TextView) v.findViewById(R.id.innings1_team1_past);
            t1inn2 = (TextView) v.findViewById(R.id.innings2_team1_past);
            sn2 = (TextView) v.findViewById(R.id.sn_team_2_past);
            t2inn1 = (TextView) v.findViewById(R.id.innings1_team2_past);
            t2inn2 = (TextView) v.findViewById(R.id.innings2_team2_past);
            result = (TextView) v.findViewById(R.id.match_result_past);
            date = (TextView) v.findViewById(R.id.match_date_past);
            rlcontainer = (RelativeLayout) itemView.findViewById(R.id.rlcontainer);
        }
    }

    public PastMatchesListAdapter(List<PastMatchCardItem> past, Context context) {
        this.pastMatchCardItems = past;
        this.context = context;
    }

    @Override
    public PastMatchesListAdapter.PastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.fragment_matches_past_match_list_item, parent, false);

        PastViewHolder viewHolder = new PastViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PastMatchesListAdapter.PastViewHolder holder, final int position) {
        holder.date.setText(pastMatchCardItems.get(position).getDate());
        holder.result.setText(pastMatchCardItems.get(position).getResult());
        holder.series.setText(pastMatchCardItems.get(position).getTour());
        holder.sn1.setText(pastMatchCardItems.get(position).getTeam1Info().getSn());
        holder.t1inn1.setText(pastMatchCardItems.get(position).getTeam1Info().getInn1());
        holder.t1inn2.setText(pastMatchCardItems.get(position).getTeam1Info().getInn2());
        holder.sn2.setText(pastMatchCardItems.get(position).getTeam2Info().getSn());
        holder.t2inn1.setText(pastMatchCardItems.get(position).getTeam2Info().getInn1());
        holder.t2inn2.setText(pastMatchCardItems.get(position).getTeam2Info().getInn2());
        holder.stadium.setText(pastMatchCardItems.get(position).getStadium());
        holder.title.setText(pastMatchCardItems.get(position).getTitle());
        RelativeLayout relativeLayout = holder.pastLayout;
        Glide.with(context).load(pastMatchCardItems.get(position).getTeam1Info().getImage()).placeholder(R.drawable.matches).into(holder.team1image);
        Glide.with(context).load(pastMatchCardItems.get(position).getTeam2Info().getImage()).placeholder(R.drawable.matches).into(holder.team2image);

        RelativeLayout RLcontainer = holder.rlcontainer;

        View.OnClickListener mClickListener;


        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent UpcomingMatchScoreCardIntent = new Intent(context, PastMatchScoreCard.class);
                UpcomingMatchScoreCardIntent.putExtra("match_id", pastMatchCardItems.get(position).getNdid());
                UpcomingMatchScoreCardIntent.putExtra("match_name", pastMatchCardItems.get(position).getTitle());
                    /*ActivityOptions.makeCustomAnimation(context,R.anim.animation_entry,R.anim.animation_exit);*/
                UpcomingMatchScoreCardIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(UpcomingMatchScoreCardIntent);

            }
        };
        RLcontainer.setOnClickListener(mClickListener);


    }

    @Override
    public int getItemCount() {
        return pastMatchCardItems.size();
    }
}