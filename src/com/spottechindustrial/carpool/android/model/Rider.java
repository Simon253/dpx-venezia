package com.spottechindustrial.carpool.android.model;

public class Rider {
    // required fields
    private String mUid;
    private String mEmail;
    private String mPassword;
    private String mUsername;
    private boolean mCanDrive;

    // optional fields
    private char mGender;
    private String mMajor;
    private int mSchoolClass;

    public void setUid(final String uId) {
        mUid = uId;
    }

    public String getUid() {
        return mUid;
    }

    public void setEmail(final String email) {
        mEmail = email;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setPassword(final String password) {
        mPassword = password;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setUsername(final String username) {
        mUsername = username;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setCanDrive(final boolean canDrive) {
        mCanDrive = canDrive;
    }

    public boolean getCanDrive() {
        return mCanDrive;
    }

    public void setGender(final char gender) {
        mGender = gender;
    }

    public char getGender() {
        return mGender;
    }

    public void setMajor(final String major) {
        mMajor = major;
    }

    public String getMajor() {
        return mMajor;
    }

    public void setSchoolClass(final int schoolClass) {
        mSchoolClass = schoolClass;
    }

    public int getSchoolClass() {
        return mSchoolClass;
    }

    private Rider(final Builder builder) {
        mUid = builder.bUid;
        mEmail = builder.bEmail;
        mPassword = builder.bPassword;
        mUsername = builder.bUsername;
        mCanDrive = builder.bCanDrive;
    }

    public static class Builder{
        private String bUid;
        private String bEmail;
        private String bPassword;
        private String bUsername;
        private boolean bCanDrive;

        public Rider build(){
            return new Rider(this);
        }

        public Builder setUid(String uid) {
            bUid = uid;
            return this;
        }

        public Builder setEmail(String email) {
            bEmail = email;
            return this;
        }

        public Builder setPassword(String password) {
            bPassword = password;
            return this;
        }

        public Builder setUsername(final String username) {
            bUsername = username;
            return this;
        }

        public Builder setCanDrive(final boolean canDrive) {
            bCanDrive = canDrive;
            return this;
        }
    }
}
