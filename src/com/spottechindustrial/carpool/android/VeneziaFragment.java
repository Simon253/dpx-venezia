package com.spottechindustrial.carpool.android;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.MapFragment;

public class VeneziaFragment extends Fragment {
    private static final String TAG = VeneziaFragment.class.getSimpleName();

    // private static final int RQS_GooglePlayServices = 1;
    // private static final int LIST_FRAGMENT = 1;
    // private static final int MAP_FRAGMENT = 2;

    // private int currentFragment;
    // private GoogleMap veneziaMap;

    private VeneziaListFragment veneziaListFragment;
    private MapFragment mapFragment;

    // private Button buttonMapListSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_venezia_container, container, false);

        veneziaListFragment = new VeneziaListFragment();
        mapFragment = new MapFragment();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	getFragmentManager().beginTransaction().replace(R.id.veneziaFragmentContainer, veneziaListFragment).commit();
    }

    public void switchToList() {
        Log.d(TAG, "switching to list fragment...");
        getFragmentManager().beginTransaction().replace(R.id.veneziaFragmentContainer, veneziaListFragment).commit();
    }

    public void switchToMap() {
        Log.d(TAG, "switching to map fragment...");
        getFragmentManager().beginTransaction().replace(R.id.veneziaFragmentContainer, mapFragment).commit();
    }
    /*
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
    */
}
