package com.debut.ellipsis.freehit.Matches.UpcomingMatches;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;


public class UpcomingMatchesItemAdapter extends PagerAdapter {

    private Context context;
    private List<UpcomingMatchCardItem> dataObjectList;
    private LayoutInflater layoutInflater;
    private String logo_string1;
    private String logo_string2;
    private ImageView imageViewTeam1Logo;
    private ImageView imageViewTeam2Logo;

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
        View view = this.layoutInflater.inflate(R.layout.fragment_matches_upcoming_match_card, container, false);

        TextView textViewMatchName = (TextView) view.findViewById(R.id.match_name_upcoming);
        textViewMatchName.setText(this.dataObjectList.get(position).getMatch());

        TextView textViewSeriesName = (TextView) view.findViewById(R.id.series_name_upcoming);
        textViewSeriesName.setText(this.dataObjectList.get(position).getTour());

        TextView textViewStadiumName = (TextView) view.findViewById(R.id.stadium_upcoming);
        textViewStadiumName.setText("( "+this.dataObjectList.get(position).getStadium()+" )");

        imageViewTeam1Logo = (ImageView) view.findViewById(R.id.team_logo_1_upcoming);

        imageViewTeam2Logo = (ImageView) view.findViewById(R.id.team_logo_2_upcoming);


        TextView shortName1 = (TextView) view.findViewById(R.id.sn_team_1_upcoming);
        shortName1.setText(this.dataObjectList.get(position).getTeam1().getSn());


        TextView shortName2 = (TextView) view.findViewById(R.id.sn_team_2_upcoming);
        shortName2.setText(this.dataObjectList.get(position).getTeam2().getSn());


        TextView MatchDate = (TextView) view.findViewById(R.id.match_date_upcoming);
        MatchDate.setText(this.dataObjectList.get(position).getDate().getFinaldate());

        TextView ViewMore = (TextView) view.findViewById(R.id.upcoming_view_more);
        ViewMore.setText(R.string.matches_view_more);
        ViewMore.setVisibility(View.INVISIBLE);

        final CardView cardView = (CardView) view.findViewById(R.id.card_view);

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

        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 5) {
                    // Intent to move to list view for Click to view more
                    // Create a new intent to open the {@link UpcomingMatchesActivity}
                    Intent UpcomingIntent = new Intent(context, UpcomingMatchesActivity.class);
                    // Start the new activity
                    context.startActivity(UpcomingIntent);
                } else {
                    /*Intent UpcomingMatchScoreCardIntent = new Intent(context, UpcomingMatchScoreCard.class);
                    UpcomingMatchScoreCardIntent.putExtra("match_id", dataObjectList.get(position).getNdid());
                    UpcomingMatchScoreCardIntent.putExtra("match_name", dataObjectList.get(position).getMatch() + "(" + dataObjectList.get(position).getTeam1().getSn() + " vs " + dataObjectList.get(position).getTeam2().getSn() + ")");
                    context.startActivity(UpcomingMatchScoreCardIntent);*/
                    Toast.makeText(context,"Coming Soon !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        logo_string1 = this.dataObjectList.get(position).getTeam1().getImage();
        logo_string2 = this.dataObjectList.get(position).getTeam2().getImage();



        if (position < 5) {

            MainActivity.requestBuilder = GlideApp.with(context).load(logo_string1).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);

            MainActivity.requestBuilder.into(imageViewTeam1Logo);

            MainActivity.requestBuilder = GlideApp.with(context).load(logo_string2).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);

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