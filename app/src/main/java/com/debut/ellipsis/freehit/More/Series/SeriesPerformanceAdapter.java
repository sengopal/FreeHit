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

public class SeriesPerformanceAdapter extends RecyclerView.Adapter<SeriesPerformanceAdapter.SeriesViewHolder> {
    private List<PerformanceItem> seriesItems;
    private int rowLayout;
    private Context context;


    public static class SeriesViewHolder extends RecyclerView.ViewHolder {
        TextView runs;
        TextView avg;
        TextView name;
        TextView slno;
        LinearLayout RlContainer;
        public SeriesViewHolder(View itemView) {
            super(itemView);
            System.out.println("finding views");

            runs=itemView.findViewById(R.id.runs);
            avg=itemView.findViewById(R.id.avg);
            name=itemView.findViewById(R.id.name);
            slno=itemView.findViewById(R.id.pos);

        }
    }


    public SeriesPerformanceAdapter(List<PerformanceItem> seriesInfo, int rowLayout, Context context) {
        System.out.println("Going into the adapter");
        this.seriesItems=seriesInfo;
        this.rowLayout=rowLayout;
        this.context=context;

    }





        @Override
    public SeriesPerformanceAdapter.SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            if (viewType == 0) {
                view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
            }
            return new SeriesPerformanceAdapter.SeriesViewHolder(view);

        }

    @Override
    public void onBindViewHolder(SeriesPerformanceAdapter.SeriesViewHolder holder, int position) {

       holder.name.setText(seriesItems.get(0).getBatting().get(position).getName());
       holder.avg.setText(seriesItems.get(0).getBatting().get(position).getAvg());
       holder.slno.setText(seriesItems.get(0).getBatting().get(position).getPosition());
       holder.runs.setText(seriesItems.get(0).getBatting().get(position).getRuns());

    }

    @Override
    public int getItemCount() {
        return seriesItems.get(0).getBatting().size();
    }
}
