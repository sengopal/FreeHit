package com.debut.ellipsis.freehit.More.Series;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.List;

/**
 * Created by Jayanth on 02-11-2017.
 */

public class SeriesBowlingAdapter extends RecyclerView.Adapter<SeriesBowlingAdapter.SeriesViewHolder> {
    private List<PerformanceItem> seriesItems;
    private int rowLayout;
    private Context context;


    public static class SeriesViewHolder extends RecyclerView.ViewHolder {
        TextView wickets;
        TextView balls;
        TextView name;
        TextView slno;
        LinearLayout RlContainer;
        public SeriesViewHolder(View itemView) {
            super(itemView);
            System.out.println("finding views");

            wickets=itemView.findViewById(R.id.wickets);
            balls=itemView.findViewById(R.id.balls);
            name=itemView.findViewById(R.id.name);
            slno=itemView.findViewById(R.id.pos);

        }
    }


    public SeriesBowlingAdapter(List<PerformanceItem> seriesInfo, int rowLayout, Context context) {
        System.out.println("Going into the adapter");
        this.seriesItems=seriesInfo;
        this.rowLayout=rowLayout;
        this.context=context;

    }





    @Override
    public SeriesBowlingAdapter.SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            //update
            view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        }
        return new SeriesBowlingAdapter.SeriesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(SeriesBowlingAdapter.SeriesViewHolder holder, int position) {

        holder.name.setText(seriesItems.get(0).getBowling().get(position).getName());
        holder.balls.setText(seriesItems.get(0).getBowling().get(position).getBalls());
        holder.slno.setText(seriesItems.get(0).getBowling().get(position).getPosition());
        holder.wickets.setText(seriesItems.get(0).getBowling().get(position).getwkts());

    }

    @Override
    public int getItemCount() {
        return seriesItems.get(0).getBowling().size();
    }
}
