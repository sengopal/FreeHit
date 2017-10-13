package com.debut.ellipsis.freehit.Matches.UpcomingMatches;


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
import com.bumptech.glide.request.RequestOptions;
import com.debut.ellipsis.freehit.Glide.CustomImageSizeModel;
import com.debut.ellipsis.freehit.Glide.CustomImageSizeModelFutureStudio;
import com.debut.ellipsis.freehit.R;

import java.util.List;

public class UpcomingMatchListAdapter extends RecyclerView.Adapter<UpcomingMatchListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView textViewMatchName;
        public TextView textViewSeriesName;
        public TextView textViewStadiumName;
        public String logo_string1;
        public String logo_string2;
        public ImageView imageViewTeam1Logo;
        public ImageView imageViewTeam2Logo;
        public TextView shortName1;
        public TextView shortName2;
        public TextView MatchDate;
        public RelativeLayout rlcontainer;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            textViewMatchName = (TextView) itemView.findViewById(R.id.match_name_upcoming);
            textViewSeriesName = (TextView) itemView.findViewById(R.id.series_name_upcoming);
            textViewStadiumName = (TextView) itemView.findViewById(R.id.stadium_upcoming);
            imageViewTeam1Logo = (ImageView) itemView.findViewById(R.id.team_logo_1_upcoming);
            imageViewTeam2Logo = (ImageView) itemView.findViewById(R.id.team_logo_2_upcoming);
            shortName1 = (TextView) itemView.findViewById(R.id.sn_team_1_upcoming);
            shortName2 = (TextView) itemView.findViewById(R.id.sn_team_2_upcoming);
            MatchDate = (TextView) itemView.findViewById(R.id.match_date_upcoming);
            rlcontainer = (RelativeLayout) itemView.findViewById(R.id.rlcontainer);


        }
    }

    // Store a member variable for the contacts
    private List<UpcomingMatchCardItem> mUpcomingMatchCards;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public UpcomingMatchListAdapter(Context context, List<UpcomingMatchCardItem> UpcomingMatchCards) {
        mUpcomingMatchCards = UpcomingMatchCards;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public UpcomingMatchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.fragment_matches_upcoming_match_list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(UpcomingMatchListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final UpcomingMatchCardItem upcomingMatchCards = mUpcomingMatchCards.get(position);

        // Set item views based on your views and data model

        TextView textViewMatchName = viewHolder.textViewMatchName;
        textViewMatchName.setText(upcomingMatchCards.getMatch());

        TextView textViewSeriesName = viewHolder.textViewSeriesName;
        textViewSeriesName.setText(upcomingMatchCards.getTour());

        TextView textViewStadiumName = viewHolder.textViewStadiumName;
        textViewStadiumName.setText("( "+upcomingMatchCards.getStadium()+" )");

        String logo_string1 = upcomingMatchCards.getTeam1().getImage();

        String logo_string2 = upcomingMatchCards.getTeam2().getImage();

        ImageView imageViewTeam1Logo = viewHolder.imageViewTeam1Logo;

        ImageView imageViewTeam2Logo = viewHolder.imageViewTeam2Logo;

        TextView shortName1 = viewHolder.shortName1;
        shortName1.setText(upcomingMatchCards.getTeam1().getSn());

        TextView shortName2 = viewHolder.shortName2;
        shortName2.setText(upcomingMatchCards.getTeam2().getSn());

        TextView MatchDate = viewHolder.MatchDate;
        MatchDate.setText(upcomingMatchCards.getDate().getFinaldate());

        CustomImageSizeModel Logo1 = new CustomImageSizeModelFutureStudio(logo_string1);
        CustomImageSizeModel Logo2 = new CustomImageSizeModelFutureStudio(logo_string2);


        Glide.with(getContext()).load(Logo1).apply(new RequestOptions().placeholder(R.drawable.matches)).into(imageViewTeam1Logo);
        Glide.with(getContext()).load(Logo2).apply(new RequestOptions().placeholder(R.drawable.matches)).into(imageViewTeam2Logo);

        RelativeLayout RLcontainer = viewHolder.rlcontainer;

        View.OnClickListener mClickListener;


        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent UpcomingMatchScoreCardIntent = new Intent(getContext(), UpcomingMatchScoreCard.class);
                UpcomingMatchScoreCardIntent.putExtra("match_id", upcomingMatchCards.getNdid());
                UpcomingMatchScoreCardIntent.putExtra("match_name",  upcomingMatchCards.getMatch() + "(" + upcomingMatchCards.getTeam1().getSn() + " vs " + upcomingMatchCards.getTeam2().getSn() + ")");

                UpcomingMatchScoreCardIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(UpcomingMatchScoreCardIntent);

            }
        };
        RLcontainer.setOnClickListener(mClickListener);


    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mUpcomingMatchCards.size() - 1;
    }
}
