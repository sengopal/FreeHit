package com.debut.ellipsis.freehit.Matches.UpcomingMatches;


import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class UpcomingMatchListAdapter extends RecyclerView.Adapter<UpcomingMatchListAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView textViewMatchName;
        TextView textViewSeriesName;
        TextView textViewStadiumName;
        ImageView imageViewTeam1Logo;
        ImageView imageViewTeam2Logo;
        TextView shortName1;
        TextView shortName2;
        TextView MatchDate;
        CardView cardView;
        TextView MatchTime;
        public RelativeLayout rlcontainer;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            textViewMatchName = itemView.findViewById(R.id.match_name_upcoming);
            textViewSeriesName = itemView.findViewById(R.id.series_name_upcoming);
            textViewStadiumName = itemView.findViewById(R.id.stadium_upcoming);
            imageViewTeam1Logo = itemView.findViewById(R.id.team_logo_1_upcoming);
            imageViewTeam2Logo = itemView.findViewById(R.id.team_logo_2_upcoming);
            shortName1 = itemView.findViewById(R.id.sn_team_1_upcoming);
            shortName2 = itemView.findViewById(R.id.sn_team_2_upcoming);
            MatchTime = itemView.findViewById(R.id.match_time_upcoming);
            MatchDate = itemView.findViewById(R.id.match_date_upcoming);
            cardView = itemView.findViewById(R.id.card_view);
            rlcontainer = itemView.findViewById(R.id.rlcontainer);


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
        View contactView = null ;

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            contactView = inflater.inflate(R.layout.fragment_matches_upcoming_match_card_dark, parent, false);
        }
        else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            contactView = inflater.inflate(R.layout.fragment_matches_upcoming_match_card, parent, false);
        }

        // Return a new holder instance
        return new ViewHolder(contactView);
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

        String logo_string1 = WelcomeActivity.countryHash.getCountryFlag(upcomingMatchCards.getTeam1().getName().toUpperCase());

        String logo_string2 = WelcomeActivity.countryHash.getCountryFlag(upcomingMatchCards.getTeam2().getName().toUpperCase());

        ImageView imageViewTeam1Logo = viewHolder.imageViewTeam1Logo;

        ImageView imageViewTeam2Logo = viewHolder.imageViewTeam2Logo;

        String originalMatchTime = upcomingMatchCards.getTime();

        Date time = null ;
        try {
            time = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).parse(originalMatchTime.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat date_format = new SimpleDateFormat("dd MMM yyyy , E", Locale.ENGLISH);
        date_format.setTimeZone(TimeZone.getTimeZone("IND"));
        date_format.setTimeZone(TimeZone.getDefault());
        final String formattedDate = date_format.format(time);


        SimpleDateFormat time_format = new SimpleDateFormat(" hh:mm a", Locale.ENGLISH);
        time_format.setTimeZone(TimeZone.getTimeZone("IND"));
        time_format.setTimeZone(TimeZone.getDefault());
        final String formattedTime = time_format.format(time);

        TextView shortName1 = viewHolder.shortName1;
        shortName1.setText(upcomingMatchCards.getTeam1().getSn());

        TextView shortName2 = viewHolder.shortName2;
        shortName2.setText(upcomingMatchCards.getTeam2().getSn());

        TextView MatchDate = viewHolder.MatchDate;
        MatchDate.setText(formattedDate);

        viewHolder.MatchTime.setText(formattedTime);

        MainActivity.requestBuilder = GlideApp.with(mContext).load(logo_string1).placeholder(R.drawable.matches_vector).format(DecodeFormat.PREFER_RGB_565);

        MainActivity.requestBuilder.into(imageViewTeam1Logo);

        MainActivity.requestBuilder = GlideApp.with(mContext).load(logo_string2).placeholder(R.drawable.matches_vector).format(DecodeFormat.PREFER_RGB_565);

        MainActivity.requestBuilder.into(imageViewTeam2Logo);


        RelativeLayout RLContainer = viewHolder.rlcontainer;

        View.OnClickListener mClickListener;

        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext,"\t \t \t \t Match Stars on \n \t \t \t"+formattedDate+"\n"+" At "+formattedTime+" Local Time",Toast.LENGTH_SHORT).show();

            }
        };
        RLContainer.setOnClickListener(mClickListener);

    }

    @Override
    public int getItemCount() {
        return mUpcomingMatchCards.size();
    }
}
