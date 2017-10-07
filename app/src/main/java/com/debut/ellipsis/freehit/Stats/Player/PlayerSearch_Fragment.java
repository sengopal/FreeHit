package com.debut.ellipsis.freehit.Stats.Player;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.debut.ellipsis.freehit.R;

/**
 * Created by Jayanth on 29-09-2017.
 */

public class PlayerSearch_Fragment extends Activity implements AdapterView.OnItemSelectedListener {
    String[] country = {"England", "India", "Australia", "Bangladesh", "New Zeland", "Pakistan", "South Africa", "Sri Lanka", "West Indies", "Zimbabwe",
            "Canada", "Ireland", "Kenya", "Netherland", "scotland", "Hong Kong", "UAE", "USA", "Afganistan", "PAPUA NEW GUINE", "Nepal", "Oman", "World xi"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_player_search_fragment);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
