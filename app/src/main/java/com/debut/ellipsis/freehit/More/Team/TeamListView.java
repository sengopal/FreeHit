package com.debut.ellipsis.freehit.More.Team;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;


public class TeamListView extends AppCompatActivity {
    TeamListAdapter adapter;
    ListView listView;


    public TeamListView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_team_list);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        final ArrayList<TeamListItem> TeamItem = new ArrayList<TeamListItem>();

        View viewToolbar = findViewById(R.id.team_list_toolbar);
        Toolbar toolbar = (Toolbar) viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Teams");

        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("AFGHANISTAN"), R.string.settings_team_Afghanistan_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("AUSTRALIA"), R.string.settings_team_Australia_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("BANGLADESH"), R.string.settings_team_Bangladesh_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("CANADA"), R.string.settings_team_Canada_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("ENGLAND"), R.string.settings_team_England_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("INDIA"), R.string.settings_team_India_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("IRELAND"), R.string.settings_team_Ireland_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("NETHERLANDS"), R.string.settings_team_Netherlands_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("NEW ZEALAND"), R.string.settings_team_NewZealand_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("PAKISTAN"), R.string.settings_team_Pakistan_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("SOUTH AFRICA"), R.string.settings_team_SouthAfrica_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("SRI LANKA"), R.string.settings_team_SriLanka_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("UNITED ARAB EMIRATES"), R.string.settings_team_UnitedArabEmirates_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("WEST INDIES"), R.string.settings_team_WestIndies_label));
        TeamItem.add(new TeamListItem(WelcomeActivity.countryHash.getCountryFlag("ZIMBABWE"), R.string.settings_team_Zimbabwe_label));

        View view = findViewById(R.id.team_list);
        listView = (ListView) view.findViewById(R.id.list);

        adapter = new TeamListAdapter(this, TeamItem);
        listView.setAdapter(adapter);


        listView.setClickable(true);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), TeamActivity.class);
                i.putExtra("CountryName", TeamItem.get(position).getmTeamName());
                startActivity(i);
                setIntent(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(0, R.anim.exit_to_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        TeamListView.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }


}