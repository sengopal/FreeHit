package com.debut.ellipsis.freehit.Matches.LiveMatches;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.debut.ellipsis.freehit.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LiveMatchCardAdapter extends PagerAdapter {

    private static final String DATE_SEPARATOR = "T";

    private static final String MATCH_SERIES_SEPARATOR = ",";

    public static final String LOG_TAG = com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchCardAdapter.class.getSimpleName();


    private Context context;
    private List<LiveMatchCardItem> dataObjectList;
    private LayoutInflater layoutInflater;
    public String logo_string1;
    public String logo_string2;
    public ImageView imageViewTeam1Logo;
    public ImageView imageViewTeam2Logo;

    public LiveMatchCardAdapter(Context context, List<LiveMatchCardItem> dataObjectList) {
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
        View view = this.layoutInflater.inflate(R.layout.fragment_matches_live_match_card, container, false);
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

        TextView textViewMatchName = (TextView) view.findViewById(R.id.match_name_live);
        textViewMatchName.setText(match_name);

        TextView textViewSeriesName = (TextView) view.findViewById(R.id.series_name_live);
        textViewSeriesName.setText(series_name);

        TextView textViewStadiumName = (TextView) view.findViewById(R.id.stadium_live);
        textViewStadiumName.setText(this.dataObjectList.get(position).getStadium());

        imageViewTeam1Logo = (ImageView) view.findViewById(R.id.team_logo_1_live);


        imageViewTeam2Logo = (ImageView) view.findViewById(R.id.team_logo_2_live);


        TextView shortName1 = (TextView) view.findViewById(R.id.sn_team_1_live);
        shortName1.setText(this.dataObjectList.get(position).getTeam1().getSn());

        TextView team1Innings1 = (TextView) view.findViewById(R.id.innings1_team1_live);
        team1Innings1.setText(this.dataObjectList.get(position).getTeam1().getInn1());

        TextView team1Innings2 = (TextView) view.findViewById(R.id.innings2_team1_live);
        team1Innings2.setText(this.dataObjectList.get(position).getTeam1().getInn2());


        TextView shortName2 = (TextView) view.findViewById(R.id.sn_team_2_live);
        shortName2.setText(this.dataObjectList.get(position).getTeam2().getSn());

        TextView team2Innings1 = (TextView) view.findViewById(R.id.innings1_team2_live);
        team2Innings1.setText(this.dataObjectList.get(position).getTeam2().getInn1());

        TextView team2Innings2 = (TextView) view.findViewById(R.id.innings2_team2_live);
        team2Innings2.setText(this.dataObjectList.get(position).getTeam2().getInn2());


        TextView MatchResult = (TextView) view.findViewById(R.id.match_target_trail_leadBy_live);
        MatchResult.setText(this.dataObjectList.get(position).getMresult());


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

        final CardView cardView = (CardView) view.findViewById(R.id.card_view);

        final String finalMatch_name = match_name;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LiveMatchScoreCardIntent = new Intent(context, LiveMatchScoreCard.class);
                LiveMatchScoreCardIntent.putExtra("match_id", dataObjectList.get(position).getId());
                LiveMatchScoreCardIntent.putExtra("match_name", finalMatch_name + "(" + dataObjectList.get(position).getTeam1().getSn() + " vs " + dataObjectList.get(position).getTeam2().getSn() + ")");
                LiveMatchScoreCardIntent.putExtra("Team1Name", dataObjectList.get(position).getTeam1().getSn());
                LiveMatchScoreCardIntent.putExtra("Team2Name", dataObjectList.get(position).getTeam2().getSn());
                /*ActivityOptions.makeCustomAnimation(context,R.anim.enter_from_right,R.anim.exit_to_right);*/
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


        TextView MatchDate = (TextView) view.findViewById(R.id.match_date_live);
        MatchDate.setText(outputDateStr);


        // Initializing Logo URLS
        logo_string1 = this.dataObjectList.get(position).getTeam1().getImage();
        logo_string2 = this.dataObjectList.get(position).getTeam2().getImage();

        Glide.with(context).load(logo_string1).placeholder(R.drawable.matches).into(imageViewTeam1Logo);
        Glide.with(context).load(logo_string2).placeholder(R.drawable.matches).into(imageViewTeam2Logo);

        /*setImage(logo_string1, imageViewTeam1Logo);
        setImage(logo_string2, imageViewTeam2Logo);*/
        container.addView(view);
        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
