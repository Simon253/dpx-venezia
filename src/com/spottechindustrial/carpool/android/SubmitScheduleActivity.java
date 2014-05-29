package com.spottechindustrial.carpool.android;

import java.util.Calendar;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.spottechindustrial.carpool.android.utils.Constants;
import com.spottechindustrial.carpool.android.utils.Utils;

public class SubmitScheduleActivity extends Activity {
    public static final String TAG = SubmitScheduleActivity.class.getSimpleName();

    public static final int PICK_DATE_REQUEST_GO = 1;
    public static final int PICK_DATE_REQUEST_BACK = 2;
    private int month = 00, date = 00, year = 0000;

    DialogFragment timeFragment;

    private static Handler mMainHandler;

    private EditText editStartName;
    private TextView startStreetAddress;

    private Button buttonEditStartTimeDate;
    private Button buttonEditStartTimeHM;
    private Button buttonEditStartTimeRange;

    private EditText editBackName;
    private TextView backStreetAddress;

    private Button buttonEditBackTimeDate;
    private Button buttonEditBackTimeHM;
    private Button buttonEditBackTimeRange;

    private static Calendar mCalendar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_schedule);
        getActionBar().hide();

        editStartName = (EditText) findViewById(R.id.editStartName);
        startStreetAddress = (TextView) findViewById(R.id.startStreetAddress);
        buttonEditStartTimeDate = (Button) findViewById(R.id.buttonEditStartTimeDate);
        buttonEditStartTimeHM = (Button) findViewById(R.id.buttonEditStartTimeHM);
        buttonEditStartTimeRange = (Button) findViewById(R.id.buttonEditStartTimeRange);

        editBackName = (EditText) findViewById(R.id.editBackName);
        backStreetAddress = (TextView) findViewById(R.id.backStreetAddress);
        buttonEditBackTimeDate = (Button) findViewById(R.id.buttonEditBackTimeDate);
        buttonEditBackTimeHM = (Button) findViewById(R.id.buttonEditBackTimeHM);
        buttonEditBackTimeRange = (Button) findViewById(R.id.buttonEditBackTimeRange);

        mCalendar = Calendar.getInstance();
        final int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        final int minute = mCalendar.get(Calendar.MINUTE);

        buttonEditStartTimeHM.setText(buildTimeHMString(hour, minute));
        buttonEditBackTimeHM.setText(buildTimeHMString(hour, minute));

        mMainHandler = new TimePickerHandler(buttonEditStartTimeHM, buttonEditBackTimeHM);
    }

    static class TimePickerHandler extends Handler {
        int mStartHour;
        int mStartMinute;
        int mBackHour;
        int mBackMinute;
        int mCallerId;

        private Button mButtonEditStartHM;
        private Button mButtonEditBackHM;

        TimePickerHandler(Button buttonEditStartHM, Button buttonEditBackHM) {
            mButtonEditStartHM = buttonEditStartHM;
            mButtonEditBackHM = buttonEditBackHM;
        }

        @Override
        public void handleMessage(Message m) {
            final Bundle b = m.getData();
            final int hour = b.getInt(Constants.TIME_PICKER_SET_HOUR);
            final int minute = b.getInt(Constants.TIME_PICKER_SET_MINUTE);
            mCallerId = b.getInt(Constants.TIME_PICKER_CALLER_ID);
            if (1 == mCallerId) {
                mStartHour = hour;
                mStartMinute = minute;
                mButtonEditStartHM.setText(buildTimeHMString(mStartHour, mStartMinute));
            } else if (2 == mCallerId) {
                mBackHour = hour;
                mBackMinute = minute;
                mButtonEditBackHM.setText(buildTimeHMString(mBackHour, mBackMinute));
            }
        }
    }

    private static String buildTimeHMString(final int hour, final int minute) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.padString(hour));
        stringBuilder.append(":");
        stringBuilder.append(Utils.padString(minute));
        return stringBuilder.toString();
    }

    private static Bundle buildTimeHMBundle(final int callId) {
    	final int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        final int minute = mCalendar.get(Calendar.MINUTE);

        final Bundle b = new Bundle();
        b.putInt(Constants.TIME_PICKER_SET_HOUR, hour);
        b.putInt(Constants.TIME_PICKER_SET_MINUTE, minute);
        b.putInt(Constants.TIME_PICKER_CALLER_ID, callId);

        return b;
    }

    public void onClickEditStartTimeHM(View v) {
        TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
        timePickerDialogFragment.setMainHandler(mMainHandler);
        timePickerDialogFragment.setArguments(buildTimeHMBundle(1));

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(timePickerDialogFragment, TimePickerDialogFragment.class.getSimpleName());
        ft.commit();
    }

    public void onClickEditBackTimeHM(View v) {
        TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
        timePickerDialogFragment.setMainHandler(mMainHandler);
        timePickerDialogFragment.setArguments(buildTimeHMBundle(2));

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(timePickerDialogFragment, TimePickerDialogFragment.class.getSimpleName());
        ft.commit();
    }
}
