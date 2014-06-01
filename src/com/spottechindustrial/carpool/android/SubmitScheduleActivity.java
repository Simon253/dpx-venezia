package com.spottechindustrial.carpool.android;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.spottechindustrial.carpool.android.utils.Constants;
import com.spottechindustrial.carpool.android.utils.Utils;

public class SubmitScheduleActivity extends Activity {
    public static final String TAG = SubmitScheduleActivity.class.getSimpleName();

    public static final int PICK_DATE_REQUEST_GO = 1;
    public static final int PICK_DATE_REQUEST_BACK = 2;

    DialogFragment timeFragment;

    private static Handler mMainHandler;

    private EditText editStartName;
    private TextView startStreetAddress;

    private Date mTmpDate;

    private Button buttonEditStartTimeDate;
    private Date mStartDate;

    private Button buttonEditStartTimeHM;

    private Button buttonEditStartTimeRange;
    private int startTimeRange;

    private EditText editBackName;
    private TextView backStreetAddress;

    private Button buttonEditBackTimeDate;
    private Date mBackDate;

    private Button buttonEditBackTimeHM;

    private Button buttonEditBackTimeRange;
    private int backTimeRange;

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
        final int year = mCalendar.get(Calendar.YEAR);
        final int month = mCalendar.get(Calendar.MONTH) + 1;
        final int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        final String currentDateString = Utils.buildShortDateString(year, month, day);

        mStartDate = mCalendar.getTime();
        buttonEditStartTimeDate.setText(currentDateString);

        buttonEditStartTimeHM.setText(buildTimeHMString(hour, minute));

        startTimeRange = 30;
        buttonEditStartTimeRange.setText(buildTimeRangeString(startTimeRange));

        mBackDate = mCalendar.getTime();
        buttonEditBackTimeDate.setText(currentDateString);

        buttonEditBackTimeHM.setText(buildTimeHMString(hour, minute));

        backTimeRange = 30;
        buttonEditBackTimeRange.setText(buildTimeRangeString(backTimeRange));

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

    private String buildTimeRangeString(final int mins) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getResources().getString(R.string.plus_minus));
        stringBuilder.append(String.valueOf(mins));
        stringBuilder.append(getResources().getString(R.string.mins));
        return stringBuilder.toString();
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

    private AlertDialog rangeDialogBox(final int callerId) {
        final LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_fragment_number_picker, null);
        final NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);

        final String[] nums = new String[11];

        for(int i=0; i < nums.length; i++) {
           nums[i] = Integer.toString((i + 1) * 5);
        }
        numberPicker.setMaxValue(nums.length-1);
        numberPicker.setMinValue(0);

        final int range = (1 == callerId) ? startTimeRange : backTimeRange;
        numberPicker.setValue(range / 5 - 1);

        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDisplayedValues(nums);

        final AlertDialog dateDialog = new AlertDialog.Builder(this)
        .setTitle(null)
        .setView(view)
        .setPositiveButton(getResources().getString(R.string.confirm),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        final int range = Integer.parseInt(nums[numberPicker.getValue()]);;
                        if (1 == callerId) {
                            startTimeRange = range;
                            buttonEditStartTimeRange.setText(buildTimeRangeString(range));
                        } else if (2 == callerId) {
                            backTimeRange = range;
                            buttonEditBackTimeRange.setText(buildTimeRangeString(range));
                        }
                    }
                })
        .setNegativeButton(getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                })
        .create();
        return dateDialog;
    }

    private AlertDialog dateDialogBox(final int callerId) {
        final LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_fragment_calendar_view, null);
        final CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);

        String titleString = "";
        if (1 == callerId) {
            calendarView.setDate(mStartDate.getTime());
            titleString = Utils.getDateFormat(getApplicationContext()).format(mStartDate);
        } else if (2 == callerId) {
            calendarView.setDate(mBackDate.getTime());
            titleString = Utils.getDateFormat(getApplicationContext()).format(mBackDate);
        }
        final AlertDialog dateDialog = new AlertDialog.Builder(this)
                .setTitle(titleString)
                .setView(view)
                .setPositiveButton(getResources().getString(R.string.confirm),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                mCalendar = Calendar.getInstance();
                                if (mTmpDate.getTime() < mCalendar.getTimeInMillis()) {
                                    Toast.makeText(getApplicationContext(), R.string.invalid_date, Toast.LENGTH_LONG).show();
                                } else {
                                    mCalendar.setTime(mTmpDate);
                                    final int year = mCalendar.get(Calendar.YEAR);
                                    final int month = mCalendar.get(Calendar.MONTH) + 1;
                                    final int day = mCalendar.get(Calendar.DAY_OF_MONTH);
                                    if (1 == callerId) {
                                        mStartDate = mTmpDate;
                                        buttonEditStartTimeDate.setText(Utils.buildShortDateString(year, month, day));
                                    } else if (2 == callerId) {
                                        mBackDate = mTmpDate;
                                        buttonEditBackTimeDate.setText(Utils.buildShortDateString(year, month, day));
                                    }
                                }
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        })
                .create();

        calendarView.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                final Date selectedDate = Utils.convertYearMonthDayToDate(year, month, dayOfMonth);
                dateDialog.setTitle(Utils.getDateFormat(getApplicationContext()).format(selectedDate));
                mCalendar.set(year, month, dayOfMonth);
                mTmpDate = mCalendar.getTime();
            }
        });
        return dateDialog;
}

    public void onClickEditStartTimeDate(View v) {
        final AlertDialog diaBox = dateDialogBox(1);
        diaBox.show();
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

    public void onClickEditStartTimeRange(View v) {
        final AlertDialog dialogBox = rangeDialogBox(1);
        dialogBox.show();
    }

    public void onClickEditBackTimeDate(View v) {
        final AlertDialog diaBox = dateDialogBox(2);
        diaBox.show();
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

    public void onClickEditBackTimeRange(View v) {
        final AlertDialog dialogBox = rangeDialogBox(2);
        dialogBox.show();
    }
}
