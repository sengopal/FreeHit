package com.debut.ellipsis.freehit.Stats.Team;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.debut.ellipsis.freehit.R;
import com.debut.ellipsis.freehit.Stats.Player.PlayerActivity;
import com.debut.ellipsis.freehit.Stats.Rankings.RankingActivity;
import com.debut.ellipsis.freehit.Stats.Records.RecordsActivity;
import com.debut.ellipsis.freehit.Stats.Series.SeriesActivity;
import com.debut.ellipsis.freehit.Stats.StatsMain.StatsAdapter;
import com.debut.ellipsis.freehit.Stats.StatsMain.StatsItem;

import java.util.ArrayList;

/**
 * Created by Jayanth on 03-10-2017.
 */

public class TeamActivityListView extends AppCompatActivity {
    public TeamActivityListView(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_team_list);
        final ArrayList<StatsItem> statsItem = new ArrayList<StatsItem>();

        statsItem.add(new StatsItem(R.drawable.flag_afg, R.string.afg));
        statsItem.add(new StatsItem(R.drawable.flag_aus, R.string.aus));
        statsItem.add(new StatsItem(R.drawable.flag_ban, R.string.ban));
        statsItem.add(new StatsItem(R.drawable.flag_eng, R.string.eng));
        statsItem.add(new StatsItem(R.drawable.flag_ind, R.string.ind));
        statsItem.add(new StatsItem(R.drawable.flag_ire, R.string.ire));
        statsItem.add(new StatsItem(R.drawable.flag_nz, R.string.nz));
        statsItem.add(new StatsItem(R.drawable.flag_pak, R.string.pak));
        statsItem.add(new StatsItem(R.drawable.flag_sa, R.string.sa));
        statsItem.add(new StatsItem(R.drawable.flag_sl, R.string.sl));
        statsItem.add(new StatsItem(R.drawable.flag_wi, R.string.wi));
        statsItem.add(new StatsItem(R.drawable.flag_zw, R.string.zw));


        StatsAdapter adapter = new StatsAdapter(this, statsItem);
        final ListView listView = (ListView) findViewById(R.id.stats_list);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), TeamActivity.class);
                i.putExtra("statname",statsItem.get(position).getmStatsName());
                startActivity(i);
                setIntent(i);
            }
        });
 }
}
