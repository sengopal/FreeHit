package com.debut.ellipsis.freehit.Matches.PastMatches;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.debut.ellipsis.freehit.Glide.CustomImageSizeModel;
import com.debut.ellipsis.freehit.Glide.CustomImageSizeModelFutureStudio;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.R;

import java.util.List;

public class PastMatchCardItemAdapter extends PagerAdapter {
    int cardLayout;
    private Context context;
    private List<PastMatchCardItem> dataObjectList;
    private LayoutInflater layoutInflater;
    public String logo_string1;
    public String logo_string2;
    public ImageView imageViewTeam1Logo;
    public ImageView imageViewTeam2Logo;

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
        View view = this.layoutInflater.inflate(R.layout.fragment_matches_past_match_card, container, false);

        TextView textViewMatchName = (TextView) view.findViewById(R.id.match_name_past);
        textViewMatchName.setText(this.dataObjectList.get(position).getTitle());

        TextView textViewSeriesName = (TextView) view.findViewById(R.id.series_name_past);
        textViewSeriesName.setText(this.dataObjectList.get(position).getTour());

        TextView textViewStadiumName = (TextView) view.findViewById(R.id.stadium_past);
        textViewStadiumName.setText("( "+this.dataObjectList.get(position).getStadium()+" )");


        imageViewTeam1Logo = (ImageView) view.findViewById(R.id.team_logo_1_past);

        imageViewTeam2Logo = (ImageView) view.findViewById(R.id.team_logo_2_past);


        TextView shortName1 = (TextView) view.findViewById(R.id.sn_team_1_past);
        shortName1.setText(this.dataObjectList.get(position).getTeam1Info().getSn());

        TextView team1Innings1 = (TextView) view.findViewById(R.id.innings1_team1_past);
        team1Innings1.setText(this.dataObjectList.get(position).getTeam1Info().getInn1());

        TextView team1Innings2 = (TextView) view.findViewById(R.id.innings2_team1_past);
        team1Innings2.setText(this.dataObjectList.get(position).getTeam1Info().getInn2());


        TextView shortName2 = (TextView) view.findViewById(R.id.sn_team_2_past);
        shortName2.setText(this.dataObjectList.get(position).getTeam2Info().getSn());

        TextView team2Innings1 = (TextView) view.findViewById(R.id.innings1_team2_past);
        team2Innings1.setText(this.dataObjectList.get(position).getTeam2Info().getInn1());

        TextView team2Innings2 = (TextView) view.findViewById(R.id.innings2_team2_past);
        team2Innings2.setText(this.dataObjectList.get(position).getTeam2Info().getInn2());


        TextView MatchResult = (TextView) view.findViewById(R.id.match_result_past);
        MatchResult.setText(this.dataObjectList.get(position).getResult());

        TextView viewMore = (TextView) view.findViewById(R.id.past_view_more);


        TextView MatchDate = (TextView) view.findViewById(R.id.match_date_past);
        MatchDate.setText(this.dataObjectList.get(position).getTime());

        final CardView cardView = (CardView) view.findViewById(R.id.card_view);
        TextView ViewMore = (TextView) view.findViewById(R.id.past_view_more);
        ViewMore.setText(R.string.matches_view_more);
        ViewMore.setVisibility(View.INVISIBLE);


        // Initializing Logo URLS
        logo_string1 = this.dataObjectList.get(position).getTeam1Info().getImage();
        logo_string2 = this.dataObjectList.get(position).getTeam2Info().getImage();



        if (position < 5) {

            CustomImageSizeModel Logo1 = new CustomImageSizeModelFutureStudio(logo_string1);
            CustomImageSizeModel Logo2 = new CustomImageSizeModelFutureStudio(logo_string2);

            RequestBuilder requestBuilder = GlideApp.with(context).load(Logo1).placeholder(R.drawable.matches);

            requestBuilder.into(imageViewTeam1Logo);

            RequestBuilder requestBuilder1 = GlideApp.with(context).load(Logo2).placeholder(R.drawable.matches);

            requestBuilder1.into(imageViewTeam2Logo);

            /*GlideApp.with(context).load(Logo1).apply(new RequestOptions().placeholder(R.drawable.matches)).into(imageViewTeam1Logo);
            GlideApp.with(context).load(Logo2).apply(new RequestOptions().placeholder(R.drawable.matches)).into(imageViewTeam2Logo);*/
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
                    Intent UpcomingIntent = new Intent(context, PastMatchesActivity.class);
                    // Start the new activity
                    context.startActivity(UpcomingIntent);
                } else {
                    Intent PastMatchScoreCardIntent = new Intent(context, PastMatchScoreCard.class);
                    PastMatchScoreCardIntent.putExtra("match_id", dataObjectList.get(position).getNdid());
                    PastMatchScoreCardIntent.putExtra("match_name", dataObjectList.get(position).getTitle());
                    context.startActivity(PastMatchScoreCardIntent);
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