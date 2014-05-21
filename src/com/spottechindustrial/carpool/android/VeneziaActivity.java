package com.spottechindustrial.carpool.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class VeneziaActivity extends Activity {
    private static final String TAG = VeneziaActivity.class.getSimpleName();

    private static final int RQS_GooglePlayServices = 1;
    private static final int LIST_FRAGMENT = 1;
    private static final int MAP_FRAGMENT = 2;

    private int currentFragment;
    private GoogleMap veneziaMap;

    private VeneziaListFragment veneziaListFragment;
    private MapFragment mapFragment;

    private Button buttonMapListSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venezia);

        buttonMapListSwitch = (Button) findViewById(R.id.buttonMapListSwitch);

        currentFragment = LIST_FRAGMENT;
        veneziaListFragment = new VeneziaListFragment();
        mapFragment = new MapFragment();

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.veneziaFragmentContainer, veneziaListFragment).commit();
        }
    }

    public void onClickMapListSwitch(View v) {
        switch (currentFragment) {
            case LIST_FRAGMENT:
                Log.v(TAG, "switching to map fragment...");
                currentFragment = MAP_FRAGMENT;
                buttonMapListSwitch.setText(R.string.button_list);
                getFragmentManager().beginTransaction()
                        .replace(R.id.veneziaFragmentContainer, mapFragment).commit();
                break;
            case MAP_FRAGMENT:
                Log.v(TAG, "switching to list fragment...");
                currentFragment = LIST_FRAGMENT;
                buttonMapListSwitch.setText(R.string.button_map);
                getFragmentManager().beginTransaction()
                .replace(R.id.veneziaFragmentContainer, veneziaListFragment).commit();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
