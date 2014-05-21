package com.spottechindustrial.carpool.android.model;

import java.util.List;

public class Pool {
    private String mPid;
    private Rider mDriver;
    private int mMaxSeats;
    private List<Rider> mPassengerList;
    private Schedule mSchedule;

    public void setPid(final String pid) {
        mPid = pid;
    }

    public String getPid() {
        return mPid;
    }

    public void setDriver(final Rider driver) {
        mDriver = driver;
    }

    public Rider getDriver() {
        return mDriver;
    }

    public void setMaxSeats(final int maxSeats) {
        mMaxSeats = maxSeats;
    }

    public int getMaxSeats() {
        return mMaxSeats;
    }

    public void setPassengerList(final List<Rider> passengerList) {
        mPassengerList = passengerList;
    }

    public List<Rider> getPassengerList() {
        return mPassengerList;
    }

    public void setSchedule(final Schedule schedule) {
        mSchedule = schedule;
    }

    public Schedule getSchedule() {
        return mSchedule;
    }

    private Pool(final Builder builder) {
        mPid = builder.bPid;
        mDriver = builder.bDriver;
        mMaxSeats = builder.bMaxSeats;
        mPassengerList = builder.bPassengerList;
        mSchedule = builder.bSchedule;
    }

    public static class Builder{
        private String bPid;
        private Rider bDriver;
        private int bMaxSeats;
        private List<Rider> bPassengerList;
        private Schedule bSchedule;

        public Pool build(){
            return new Pool(this);
        }

        public Builder setPid(String pid) {
            bPid = pid;
            return this;
        }

        public Builder setDriver(Rider driver) {
            bDriver = driver;
            return this;
        }

        public Builder setMaxSeats(int maxSeats) {
            bMaxSeats = maxSeats;
            return this;
        }

        public Builder setPassengerList(List<Rider> passengerList) {
            bPassengerList = passengerList;
            return this;
        }

        public Builder setSchedule(Schedule schedule) {
            bSchedule = schedule;
            return this;
        }
    }
}
