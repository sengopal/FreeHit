package com.debut.ellipsis.freehit.Home;

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
import com.debut.ellipsis.freehit.CountryHash;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchScoreCard;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchScoreCard;
import com.debut.ellipsis.freehit.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class RecentMatchAdapter extends PagerAdapter {

    private static final String DATE_SEPARATOR = "T";

    private static final String MATCH_SERIES_SEPARATOR = ",";

    private String match_type;
    private Context context;
    private List<RecentItem> dataObjectList;
    private LayoutInflater layoutInflater;
    private String logo_string1;
    private String logo_string2;
    private ImageView imageViewTeam1Logo;
    private ImageView imageViewTeam2Logo;

    public RecentMatchAdapter(Context context, List<RecentItem> dataObjectList) {
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

        View view = null;

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
        view = this.layoutInflater.inflate(R.layout.fragment_home_recent_match_card_dark, container, false);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            view = this.layoutInflater.inflate(R.layout.fragment_home_recent_match_card, container, false);
        }

        CardView cardView = view.findViewById(R.id.card_view);
        TextView textViewMatchName = view.findViewById(R.id.match_name_recent);
        TextView textViewSeriesName = view.findViewById(R.id.series_name_recent);
        TextView textViewStadiumName = view.findViewById(R.id.stadium_recent);
        imageViewTeam1Logo = view.findViewById(R.id.team_logo_1_recent);

        imageViewTeam2Logo = view.findViewById(R.id.team_logo_2_recent);

        TextView shortName1 = view.findViewById(R.id.sn_team_1_recent);

        TextView shortName2 = view.findViewById(R.id.sn_team_2_recent);

        TextView MatchTime = view.findViewById(R.id.match_date_time_recent);

        TextView MatchDate = view.findViewById(R.id.match_target_trail_leadBy_date_recent);

        TextView team1Innings1 = view.findViewById(R.id.innings1_team1_recent);

        TextView team1Innings2 = view.findViewById(R.id.innings2_team1_recent);

        TextView team2Innings1 = view.findViewById(R.id.innings1_team2_recent);

        TextView team2Innings2 = view.findViewById(R.id.innings2_team2_recent);

            if (this.dataObjectList.get(position).getTitle() != null && this.dataObjectList.get(position).getType().equals("upcoming")) {

                String originalMatchTime = this.dataObjectList.get(position).getTime();


                Date time = null;
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

                textViewMatchName.setText(this.dataObjectList.get(position).getMatch());

                textViewSeriesName.setText(this.dataObjectList.get(position).getTour());

                textViewStadiumName.setText("( " + this.dataObjectList.get(position).getStadium() + " )");

                shortName1.setText(this.dataObjectList.get(position).getTeam1().getSn());


                shortName2.setText(this.dataObjectList.get(position).getTeam2().getSn());


                MatchTime.setText(formattedTime);

                MatchDate.setText(formattedDate);

                logo_string1 = WelcomeActivity.countryHash.getCountryFlag(this.dataObjectList.get(position).getTeam1().getName().toUpperCase());
                logo_string2 = WelcomeActivity.countryHash.getCountryFlag(this.dataObjectList.get(position).getTeam2().getName().toUpperCase());

                switch (AppCompatDelegate.getDefaultNightMode()) {
                    case AppCompatDelegate.MODE_NIGHT_YES:
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string1).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam1Logo);
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string2).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam2Logo);
                        break;
                    default:
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string1).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam1Logo);
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string2).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam2Logo);
                        break;
                }


                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "\t \t \t \t Match Starts on \n \t \t \t" + formattedDate + "\n" + " At " + formattedTime + " Local Time", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            if (this.dataObjectList.get(position).getTitle() != null && this.dataObjectList.get(position).getType().equals("past")) {

                String originalMatchTime = this.dataObjectList.get(position).getDate().getFinaldate();


                Date time = null;
                try {
                    time = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).parse(originalMatchTime.replaceAll("Z$", "+0000"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                SimpleDateFormat date_format = new SimpleDateFormat("dd MMM yyyy , E", Locale.ENGLISH);
                date_format.setTimeZone(TimeZone.getTimeZone("IND"));
                date_format.setTimeZone(TimeZone.getDefault());
                String formattedDate = date_format.format(time);

                textViewMatchName.setText(this.dataObjectList.get(position).getTitle());

                textViewSeriesName.setText(this.dataObjectList.get(position).getTour());

                textViewStadiumName.setText("( " + this.dataObjectList.get(position).getStadium() + " )");


                shortName1.setText(this.dataObjectList.get(position).getTeam1().getSn());

                team1Innings1.setText(this.dataObjectList.get(position).getTeam1().getInn1());

                team1Innings2.setText(this.dataObjectList.get(position).getTeam1().getInn2());

                shortName2.setText(this.dataObjectList.get(position).getTeam2().getSn());

                team2Innings1.setText(this.dataObjectList.get(position).getTeam2().getInn1());

                team2Innings2.setText(this.dataObjectList.get(position).getTeam2().getInn2());

                MatchTime.setText(this.dataObjectList.get(position).getTime());

                MatchDate.setText(formattedDate);

                String logo_string1 = WelcomeActivity.countryHash.getCountryFlag(this.dataObjectList.get(position).getTeam1().getName().toUpperCase());
                String logo_string2 = WelcomeActivity.countryHash.getCountryFlag(this.dataObjectList.get(position).getTeam2().getName().toUpperCase());

                switch (AppCompatDelegate.getDefaultNightMode()) {
                    case AppCompatDelegate.MODE_NIGHT_YES:
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string1).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam1Logo);
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string2).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam2Logo);
                        break;
                    default:
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string1).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam1Logo);
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string2).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam2Logo);
                        break;
                }


                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dataObjectList.get(position).getTeam1().getInn1().equals("") && dataObjectList.get(position).getTeam2().getInn1().equals("")) {
                            Toast.makeText(context, "Match was Abandoned !", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent PastMatchScoreCardIntent = new Intent(context, PastMatchScoreCard.class);
                            PastMatchScoreCardIntent.putExtra("match_id", dataObjectList.get(position).getNdid());
                            PastMatchScoreCardIntent.putExtra("match_name", dataObjectList.get(position).getTitle());
                            PastMatchScoreCardIntent.putExtra("match_type", "PAST");
                            PastMatchScoreCardIntent.putExtra("team1", dataObjectList.get(position).getTeam1().getName());
                            PastMatchScoreCardIntent.putExtra("team2", dataObjectList.get(position).getTeam2().getName());
                            context.startActivity(PastMatchScoreCardIntent);
                        }

                    }
                });

            }

            if (this.dataObjectList.get(position).getTitle() != null && this.dataObjectList.get(position).getType().equals("live")) {
                String originalMatchName = this.dataObjectList.get(position).getTour();

                String match_name = null;
                String series_name = null;
                // Check whether the originalLocation string contains the " of " text
                if (originalMatchName.contains(MATCH_SERIES_SEPARATOR)) {
                    // Split the string into different parts (as an array of Strings)
                    // based on the "," text. We expect an array of 4 Strings, where
                    // the first String will be "2nd Test" , second String will be " Australia in Bangladesh", third String will be " 2 Test Series", fourth String will be " 2017".
                    String[] parts = originalMatchName.split(MATCH_SERIES_SEPARATOR);
                    // match_name should be "2nd Test"
                    // series_name should be " Australia in Bangladesh, 2 Test Series, 2017"
                    match_name = parts[0];
                    series_name = parts[1] + MATCH_SERIES_SEPARATOR;
                    for (int i = 2; i < parts.length; i++) {
                        series_name += parts[i];
                    }
                }
                textViewMatchName.setText(match_name);

                textViewSeriesName.setText(series_name);

                textViewStadiumName.setText("( " + this.dataObjectList.get(position).getStadium() + " )");


                CountryHash countryHash = new CountryHash();

                final String ShortNameTeam1 = countryHash.getCountrySN((this.dataObjectList.get(position).getTeam1().getName()).toUpperCase());

                shortName1.setText(ShortNameTeam1);

                team1Innings1.setText(this.dataObjectList.get(position).getTeam1().getInn1());

                team1Innings2.setText(this.dataObjectList.get(position).getTeam1().getInn2());

                final String ShortNameTeam2 = countryHash.getCountrySN((this.dataObjectList.get(position).getTeam2().getName()).toUpperCase());


                shortName2.setText(ShortNameTeam2);

                team2Innings1.setText(this.dataObjectList.get(position).getTeam2().getInn1());

                team2Innings2.setText(this.dataObjectList.get(position).getTeam2().getInn2());

                MatchDate.setText(this.dataObjectList.get(position).getMresult());


                String originalMatchDate = this.dataObjectList.get(position).getDate().getFinaldate();

                // Check whether the originalLocation string contains the " of " text
                if (originalMatchDate.contains(DATE_SEPARATOR)) {
                    // Split the string into different parts (as an array of Strings)
                    // based on the "T" text. We expect an array of 2 Strings, where
                    // the first String will be "2017-09-04" and the second String will be "04:00:00.000Z".
                    String[] parts = originalMatchDate.split(DATE_SEPARATOR);
                    // originalMatchDate should be "2017-09-04"--> "04 Sep 2017"
                    originalMatchDate = parts[0];

                }

                final String finalMatch_name = match_name;
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent LiveMatchScoreCardIntent = new Intent(context, LiveMatchScoreCard.class);
                        LiveMatchScoreCardIntent.putExtra("match_id", dataObjectList.get(position).getNdid());
                        LiveMatchScoreCardIntent.putExtra("match_name", finalMatch_name + "( " + ShortNameTeam1 + " vs " + ShortNameTeam2 + " )");
                        LiveMatchScoreCardIntent.putExtra("team1", WelcomeActivity.countryHash.getCountryName(ShortNameTeam1));
                        LiveMatchScoreCardIntent.putExtra("team2", WelcomeActivity.countryHash.getCountryName(ShortNameTeam2));
                        LiveMatchScoreCardIntent.putExtra("match_type", "LIVE");
                        context.startActivity(LiveMatchScoreCardIntent);

                    }
                });

                //converting "2017-09-04" to "04 Sep 2017"
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy , E");
                Date date = null;
                try {
                    date = inputFormat.parse(originalMatchDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String outputDateStr = outputFormat.format(date);

                MatchTime.setText(outputDateStr);

                logo_string1 = WelcomeActivity.countryHash.getCountryFlag(this.dataObjectList.get(position).getTeam1().getName().toUpperCase());
                logo_string2 = WelcomeActivity.countryHash.getCountryFlag(this.dataObjectList.get(position).getTeam2().getName().toUpperCase());

                switch (AppCompatDelegate.getDefaultNightMode()) {
                    case AppCompatDelegate.MODE_NIGHT_YES:
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string1).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam1Logo);
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string2).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam2Logo);
                        break;
                    default:
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string1).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam1Logo);
                        MainActivity.requestBuilder = GlideApp.with(context).load(logo_string2).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);
                        MainActivity.requestBuilder.into(imageViewTeam2Logo);
                        break;
                }

            }

        container.addView(view);
        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}