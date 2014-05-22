package com.spottechindustrial.carpool.android.model;

import android.graphics.PointF;


public class Schedule {
    // required fields
    private String mStartName;
    private PointF mStartLocation;
    private long mStartTime;
    private int mStartTimeRange; //mins

    private String mBackName;
    private PointF mBackLocation;
    private long mBackTime;
    private int mBackTimeRange; //mins

    // optional fields
    private String mStartStreetAddress;
    private String mBackStreetAddress;

    public void setStartName(final String startName) {
        mStartName = startName;
    }

    public String getStartName() {
        return mStartName;
    }

    public void setStartLocationX(final PointF startLocation) {
        mStartLocation = startLocation;
    }

    public PointF getStartLocation() {
        return mStartLocation;
    }

    public void setStartTime(final long startTime) {
        mStartTime = startTime;
    }

    public long getStartTime() {
        return mStartTime;
    }

    public void setStartTimeRange(final int startTimeRange) {
        mStartTimeRange = startTimeRange;
    }

    public int getStartTimeRange() {
        return mStartTimeRange;
    }

    public void setBackName(final String backName) {
        mBackName = backName;
    }

    public String getBackName() {
        return mBackName;
    }

    public void setBackLocation(final PointF backLocation) {
        mBackLocation = backLocation;
    }

    public PointF getBackLocation() {
        return mBackLocation;
    }

    public void setBackTime(final long backTime) {
        mBackTime = backTime;
    }

    public long getBackTime() {
        return mBackTime;
    }

    public void setBackTimeRange(final int backTimeRange) {
        mBackTimeRange = backTimeRange;
    }

    public int getBackTimeRange() {
        return mBackTimeRange;
    }

    public void setStartStreetAddress(final String startStreetAddress) {
        mStartStreetAddress = startStreetAddress;
    }

    public String getStartStreetAddress() {
        return mStartStreetAddress;
    }

    public void setBackStreetAddress(final String backStreetAddress) {
        mBackStreetAddress = backStreetAddress;
    }

    public String getBackStreetAddress() {
        return mBackStreetAddress;
    }

    private Schedule(final Builder builder) {
        mStartName = builder.bStartName;
        mStartLocation = builder.bStartLocation;
        mStartTime = builder.bStartTime;
        mStartTimeRange = builder.bStartTimeRange;

        mBackName = builder.bBackName;
        mBackLocation = builder.bBackLocation;
        mBackTime = builder.bBackTime;
        mBackTimeRange = builder.bBackTimeRange;
    }

    public static class Builder{
        private String bStartName;
        private PointF bStartLocation;
        private long bStartTime;
        private int bStartTimeRange; //mins

        private String bBackName;
        private PointF bBackLocation;
        private long bBackTime;
        private int bBackTimeRange; //mins

        public Schedule build(){
            return new Schedule(this);
        }

        public Builder setStartName(String startName) {
            bStartName = startName;
            return this;
        }

        public Builder setStartLocation(PointF startLocation) {
            bStartLocation = startLocation;
            return this;
        }

        public Builder setStartTime(long startTime) {
            bStartTime = startTime;
            return this;
        }

        public Builder setStartTimeRange(int startTimeRange) {
            bStartTimeRange = startTimeRange;
            return this;
        }

        public Builder setBackName(String backName) {
            bBackName = backName;
            return this;
        }

        public Builder setBackLocation(PointF backLocation) {
            bBackLocation = backLocation;
            return this;
        }

        public Builder setBackTime(long backTime) {
            bBackTime = backTime;
            return this;
        }

        public Builder setBackTimeRange(int backTimeRange) {
            bBackTimeRange = backTimeRange;
            return this;
        }
    }
}
