package com.spottechindustrial.carpool.android.model;


public class Schedule {
    // required fields
    private String mStartName;
    private float mStartLocationX;
    private float mStartLocationY;
    private long mStartTime;
    private int mStartTimeRange; //mins

    private String mBackName;
    private float mBackLocationX;
    private float mBackLocationY;
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

    public void setStartLocationX(final float startLocationX) {
        mStartLocationX = startLocationX;
    }

    public float getStartLocationX() {
        return mStartLocationX;
    }

    public void setStartLocationY(final float startLocationY) {
        mStartLocationY = startLocationY;
    }

    public float getStartLocationY() {
        return mStartLocationY;
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

    public void setBackLocationX(final float backLocationX) {
        mBackLocationX = backLocationX;
    }

    public float getBackLocationX() {
        return mBackLocationX;
    }

    public void setBackLocationY(final float backLocationY) {
        mBackLocationY = backLocationY;
    }

    public float getBackLocationY() {
        return mBackLocationY;
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
        mStartLocationX = builder.bStartLocationX;
        mStartLocationY = builder.bStartLocationY;
        mStartTime = builder.bStartTime;
        mStartTimeRange = builder.bStartTimeRange;

        mBackName = builder.bBackName;
        mBackLocationX = builder.bBackLocationX;
        mBackLocationY = builder.bBackLocationY;
        mBackTime = builder.bBackTime;
        mBackTimeRange = builder.bBackTimeRange;
    }

    public static class Builder{
        private String bStartName;
        private float bStartLocationX;
        private float bStartLocationY;
        private long bStartTime;
        private int bStartTimeRange; //mins

        private String bBackName;
        private float bBackLocationX;
        private float bBackLocationY;
        private long bBackTime;
        private int bBackTimeRange; //mins

        public Schedule build(){
            return new Schedule(this);
        }

        public Builder setStartName(String startName) {
            bStartName = startName;
            return this;
        }

        public Builder setStartLocationX(float startLocationX) {
            bStartLocationX = startLocationX;
            return this;
        }

        public Builder setStartLocationY(float startLocationY) {
            bStartLocationY = startLocationY;
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

        public Builder setBackLocationX(float backLocationX) {
            bBackLocationX = backLocationX;
            return this;
        }

        public Builder setBackLocationY(float backLocationY) {
            bBackLocationY = backLocationY;
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
