package com.debut.ellipsis.freehit.Matches.PastMatches;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.MatchesActivity;
import com.debut.ellipsis.freehit.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class PastMatchCardItemAdapter extends PagerAdapter {
    private Context context;
    private List<PastMatchCardItem> dataObjectList;
    private LayoutInflater layoutInflater;

    public PastMatchCardItemAdapter(Context context, List<PastMatchCardItem> dataObjectList) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataObjectList = dataObjectList;
    }

    @Override
    public int getCount() {
        return dataObjectList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = null ;

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            view = this.layoutInflater.inflate(R.layout.fragment_matches_past_match_card_dark, container, false);
        }
        else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            view = this.layoutInflater.inflate(R.layout.fragment_matches_past_match_card, container, false);
        }

        String originalMatchTime = this.dataObjectList.get(position).getDate().getFinaldatetime();


        Date time = null ;
        try {
            time = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).parse(originalMatchTime.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat date_format = new SimpleDateFormat("dd MMM yyyy , E", Locale.ENGLISH);
        date_format.setTimeZone(TimeZone.getTimeZone("IND"));
        date_format.setTimeZone(TimeZone.getDefault());
        String formattedDate = date_format.format(time);

        TextView textViewMatchName = view.findViewById(R.id.match_name_past);
        textViewMatchName.setText(this.dataObjectList.get(position).getTitle());

        TextView textViewSeriesName = view.findViewById(R.id.series_name_past);
        textViewSeriesName.setText(this.dataObjectList.get(position).getTour());

        TextView textViewStadiumName = view.findViewById(R.id.stadium_past);
        textViewStadiumName.setText("( "+this.dataObjectList.get(position).getStadium()+" )");


        ImageView imageViewTeam1Logo = view.findViewById(R.id.team_logo_1_past);

        ImageView imageViewTeam2Logo = view.findViewById(R.id.team_logo_2_past);


        TextView shortName1 = view.findViewById(R.id.sn_team_1_past);
        shortName1.setText(this.dataObjectList.get(position).getTeam1Info().getSn());

        TextView team1Innings1 = view.findViewById(R.id.innings1_team1_past);
        team1Innings1.setText(this.dataObjectList.get(position).getTeam1Info().getInn1());

        TextView team1Innings2 = view.findViewById(R.id.innings2_team1_past);
        team1Innings2.setText(this.dataObjectList.get(position).getTeam1Info().getInn2());


        TextView shortName2 = view.findViewById(R.id.sn_team_2_past);
        shortName2.setText(this.dataObjectList.get(position).getTeam2Info().getSn());

        TextView team2Innings1 = view.findViewById(R.id.innings1_team2_past);
        team2Innings1.setText(this.dataObjectList.get(position).getTeam2Info().getInn1());

        TextView team2Innings2 = view.findViewById(R.id.innings2_team2_past);
        team2Innings2.setText(this.dataObjectList.get(position).getTeam2Info().getInn2());


        TextView MatchResult = view.findViewById(R.id.match_result_past);
        MatchResult.setText(this.dataObjectList.get(position).getResult());

        TextView viewMore = view.findViewById(R.id.past_view_more);


        TextView MatchDate = view.findViewById(R.id.match_date_past);
        MatchDate.setText(formattedDate);

        final CardView cardView = view.findViewById(R.id.card_view);
        TextView ViewMore = view.findViewById(R.id.past_view_more);
        ViewMore.setText(R.string.matches_view_more);
        ViewMore.setVisibility(View.INVISIBLE);

        
        String logo_string1 = WelcomeActivity.countryHash.getCountryFlag(WelcomeActivity.countryHash.getCountryName(this.dataObjectList.get(position).getTeam1Info().getSn()).toUpperCase());
        String logo_string2 = WelcomeActivity.countryHash.getCountryFlag(WelcomeActivity.countryHash.getCountryName(this.dataObjectList.get(position).getTeam2Info().getSn()).toUpperCase());



        if (position < 5) {

            MainActivity.requestBuilder = GlideApp.with(context).load(logo_string1).placeholder(R.drawable.matches_vector).format(DecodeFormat.PREFER_RGB_565);

            MainActivity.requestBuilder.into(imageViewTeam1Logo);

            MainActivity.requestBuilder = GlideApp.with(context).load(logo_string2).placeholder(R.drawable.matches_vector).format(DecodeFormat.PREFER_RGB_565);

            MainActivity.requestBuilder.into(imageViewTeam2Logo);

        }
        if (position == 5) {
            viewMore.setVisibility(View.VISIBLE);
            textViewMatchName.setVisibility(View.INVISIBLE);
            textViewSeriesName.setVisibility(View.INVISIBLE);
            textViewStadiumName.setVisibility(View.INVISIBLE);
            imageViewTeam1Logo.setVisibility(View.INVISIBLE);
            imageViewTeam2Logo.setVisibility(View.INVISIBLE);
            shortName1.setVisibility(View.INVISIBLE);
            shortName2.setVisibility(View.INVISIBLE);
            team1Innings1.setVisibility(View.INVISIBLE);
            team1Innings2.setVisibility(View.INVISIBLE);
            team2Innings1.setVisibility(View.INVISIBLE);
            team2Innings2.setVisibility(View.INVISIBLE);
            MatchResult.setVisibility(View.INVISIBLE);
            MatchDate.setVisibility(View.INVISIBLE);

        }



            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 5) {
                        // Intent to move to list view for Click to view more
                        // Create a new intent to open the {@link UpcomingMatchesActivity}
                        Intent UpcomingIntent = new Intent(context, MatchesActivity.class);
                        UpcomingIntent.putExtra("match_format","PAST");
                        // Start the new activity
                        context.startActivity(UpcomingIntent);
                    } else {

                        if (dataObjectList.get(position).getTeam2Info().getInn1().equals("")&&dataObjectList.get(position).getTeam2Info().getInn1().equals("")){
                            Toast.makeText(context, "Match was Abandoned !", Toast.LENGTH_SHORT).show();
                        } else
                        {
                            Intent PastMatchScoreCardIntent = new Intent(context, PastMatchScoreCard.class);
                        PastMatchScoreCardIntent.putExtra("match_id", dataObjectList.get(position).getNdid());
                        PastMatchScoreCardIntent.putExtra("match_name", dataObjectList.get(position).getTitle());
                        PastMatchScoreCardIntent.putExtra("match_type", "PAST");
                        PastMatchScoreCardIntent.putExtra("team1", WelcomeActivity.countryHash.getCountryName(dataObjectList.get(position).getTeam1Info().getSn()));
                        PastMatchScoreCardIntent.putExtra("team2", WelcomeActivity.countryHash.getCountryName(dataObjectList.get(position).getTeam2Info().getSn()));
                        context.startActivity(PastMatchScoreCardIntent);
                    }

                    }
                }
            });


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}