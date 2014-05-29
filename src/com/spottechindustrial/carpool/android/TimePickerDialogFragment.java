package com.spottechindustrial.carpool.android;

import com.spottechindustrial.carpool.android.utils.Constants;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TimePicker;

public class TimePickerDialogFragment extends DialogFragment {
    private int mHour;
    private int mMinute;
    private int mCallerId; // 1 for start time, 2 for back time

    private Handler mMainHandler;

    public void setMainHandler(final Handler mainHandler) {
    	mMainHandler = mainHandler;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        /** Creating a bundle object to pass currently set time to the fragment */
        Bundle b = getArguments();

        mHour = b.getInt(Constants.TIME_PICKER_SET_HOUR);
        mMinute = b.getInt(Constants.TIME_PICKER_SET_MINUTE);
        mCallerId = b.getInt(Constants.TIME_PICKER_CALLER_ID);

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                mHour = hourOfDay;
                mMinute = minute;

                /** Creating a bundle object to pass currently set time to the fragment */
                Bundle b = new Bundle();

                /** Adding time and callerId to bundle object */
                b.putInt(Constants.TIME_PICKER_SET_HOUR, mHour);
                b.putInt(Constants.TIME_PICKER_SET_MINUTE, mMinute);
                b.putInt(Constants.TIME_PICKER_CALLER_ID, mCallerId);

                /** Creating an instance of Message */
                Message m = new Message();
                m.setData(b);

                /** Message m is sending using the message handler instantiated in MainActivity class */
                mMainHandler.sendMessage(m);
            }
        };
 
        /** Opening the TimePickerDialog window */
        return new TimePickerDialog(getActivity(), listener, mHour, mMinute, false);
    }
}
