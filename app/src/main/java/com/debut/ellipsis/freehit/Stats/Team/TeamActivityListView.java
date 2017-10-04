package com.debut.ellipsis.freehit.Stats.Team;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;


public class TeamActivityListView extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText searchEditText;
    TeamListAdapter adapter;



    public TeamActivityListView(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_team_list);
        final ArrayList<TeamActivityListItem> TeamItem = new ArrayList<TeamActivityListItem>();

        toolbar = (Toolbar) findViewById(R.id.toolbar_team);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("TEAMS");

        searchEditText = (EditText) findViewById(R.id.country_code_picker_search);



        TeamItem.add(new TeamActivityListItem(R.drawable.flag_afg, "Afghanistan"));
        TeamItem.add(new TeamActivityListItem(R.drawable.flag_aus, "Australia"));
       /* TeamItem.add(new TeamActivityListItem(R.drawable.flag_ban, R.string.settings_team_Bangladesh_label));
        TeamItem.add(new TeamActivityListItem(R.drawable.flag_eng, R.string.settings_team_England_label));
        TeamItem.add(new TeamActivityListItem(R.drawable.flag_ind, R.string.settings_team_India_label));
        TeamItem.add(new TeamActivityListItem(R.drawable.flag_ire, R.string.settings_team_Ireland_label));
        TeamItem.add(new TeamActivityListItem(R.drawable.flag_nz, R.string.settings_team_NewZealand_label));
        TeamItem.add(new TeamActivityListItem(R.drawable.flag_pak, R.string.settings_team_Pakistan_label));
        TeamItem.add(new TeamActivityListItem(R.drawable.flag_sa, R.string.settings_team_SouthAfrica_label));
        TeamItem.add(new TeamActivityListItem(R.drawable.flag_sl, R.string.settings_team_SriLanka_label));
        TeamItem.add(new TeamActivityListItem(R.drawable.flag_wi, R.string.settings_team_WestIndies_label));
        TeamItem.add(new TeamActivityListItem(R.drawable.flag_zw, R.string.settings_team_Zimbabwe_label));*/
        final ListView listView = (ListView) findViewById(R.id.stats_list);
       final TeamListAdapter adapter2 = new TeamListAdapter(getApplicationContext(), TeamItem);

        adapter = new TeamListAdapter(this, TeamItem);
        listView.setAdapter(adapter);



        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TeamActivityListView.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });




        listView.setClickable(true);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), TeamActivity.class);
                i.putExtra("CountryName",TeamItem.get(position).getmStatsName());
                startActivity(i);
                setIntent(i);
            }
        });
 }


}
