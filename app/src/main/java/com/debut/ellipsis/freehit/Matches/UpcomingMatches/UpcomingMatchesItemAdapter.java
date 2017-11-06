package com.debut.ellipsis.freehit.Matches.UpcomingMatches;

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


public class UpcomingMatchesItemAdapter extends PagerAdapter {

    private Context context;
    private List<UpcomingMatchCardItem> dataObjectList;
    private LayoutInflater layoutInflater;
    private String logo_string1;
    private String logo_string2;
    private ImageView imageViewTeam1Logo;
    private ImageView imageViewTeam2Logo;

    private static final String TIME_SEPARATOR = "T";

    public UpcomingMatchesItemAdapter(Context context, List<UpcomingMatchCardItem> dataObjectList) {
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
            view = this.layoutInflater.inflate(R.layout.fragment_matches_upcoming_match_card_dark, container, false);
        }
        else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            view = this.layoutInflater.inflate(R.layout.fragment_matches_upcoming_match_card, container, false);
        }

        TextView textViewMatchName = view.findViewById(R.id.match_name_upcoming);
        textViewMatchName.setText(this.dataObjectList.get(position).getMatch());

        TextView textViewSeriesName = view.findViewById(R.id.series_name_upcoming);
        textViewSeriesName.setText(this.dataObjectList.get(position).getTour());

        TextView textViewStadiumName = view.findViewById(R.id.stadium_upcoming);
        textViewStadiumName.setText("( "+this.dataObjectList.get(position).getStadium()+" )");

        imageViewTeam1Logo = view.findViewById(R.id.team_logo_1_upcoming);

        imageViewTeam2Logo = view.findViewById(R.id.team_logo_2_upcoming);

        String originalMatchTime = this.dataObjectList.get(position).getTime();


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

        TextView shortName1 = view.findViewById(R.id.sn_team_1_upcoming);
        shortName1.setText(this.dataObjectList.get(position).getTeam1().getSn());

        TextView shortName2 = view.findViewById(R.id.sn_team_2_upcoming);
        shortName2.setText(this.dataObjectList.get(position).getTeam2().getSn());

        TextView MatchTime = view.findViewById(R.id.match_time_upcoming);
        MatchTime.setText(formattedTime);

        TextView MatchDate = view.findViewById(R.id.match_date_upcoming);
        MatchDate.setText(formattedDate);

        TextView ViewMore = view.findViewById(R.id.upcoming_view_more);
        ViewMore.setText(R.string.matches_view_more);
        ViewMore.setVisibility(View.INVISIBLE);

        final CardView cardView = view.findViewById(R.id.card_view);

        if (position == 5) {
            ViewMore.setVisibility(View.VISIBLE);
            textViewMatchName.setVisibility(View.INVISIBLE);
            textViewSeriesName.setVisibility(View.INVISIBLE);
            textViewStadiumName.setVisibility(View.INVISIBLE);
            imageViewTeam1Logo.setVisibility(View.INVISIBLE);
            imageViewTeam2Logo.setVisibility(View.INVISIBLE);
            shortName1.setVisibility(View.INVISIBLE);
            shortName2.setVisibility(View.INVISIBLE);
            MatchDate.setVisibility(View.INVISIBLE);
            MatchTime.setVisibility(View.INVISIBLE);

        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 5) {
                    // Intent to move to list view for Click to view more
                    // Create a new intent to open the {@link UpcomingMatchesActivity}
                    Intent UpcomingIntent = new Intent(context, MatchesActivity.class);
                    UpcomingIntent.putExtra("match_format","UPCOMING");
                    // Start the new activity
                    context.startActivity(UpcomingIntent);
                } else {

                    Toast.makeText(context,"\t \t \t \t Match Stars on \n \t \t \t"+formattedDate+"\n"+" At "+formattedTime+" Local Time",Toast.LENGTH_SHORT).show();
                }
            }
        });

        logo_string1 =  WelcomeActivity.countryHash.getCountryFlag(this.dataObjectList.get(position).getTeam1().getName().toUpperCase());
        logo_string2 =  WelcomeActivity.countryHash.getCountryFlag(this.dataObjectList.get(position).getTeam2().getName().toUpperCase());



        if (position < 5) {

            MainActivity.requestBuilder = GlideApp.with(context).load(logo_string1).placeholder(R.drawable.matches_vector).format(DecodeFormat.PREFER_RGB_565);

            MainActivity.requestBuilder.into(imageViewTeam1Logo);

            MainActivity.requestBuilder = GlideApp.with(context).load(logo_string2).placeholder(R.drawable.matches_vector).format(DecodeFormat.PREFER_RGB_565);

            MainActivity.requestBuilder.into(imageViewTeam2Logo);

        }
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}