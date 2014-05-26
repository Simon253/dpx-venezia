package com.spottechindustrial.carpool.android.model;

public class Token {
    private String mUid;
    private String mAccessToken;
    private String mRefreshToken;
    private long mTimestamp;

    public void setUid(final String uid) {
        this.mUid = uid;
    }

    public String getUid() {
        return this.mUid;
    }

    public void setAccessToken(final String accessToken) {
        this.mAccessToken = accessToken;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.mRefreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public void setTimestamp(final long timestamp) {
        this.mTimestamp = timestamp;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    private Token(final Builder builder) {
        mUid = builder.bUid;
        mAccessToken = builder.bAccessToken;
        mRefreshToken = builder.bRefreshToken;
        mTimestamp = builder.bTimestamp;
    }

    public static class Builder{
        private String bUid;
        private String bAccessToken;
        private String bRefreshToken;
        private long bTimestamp;

        public Token build(){
            return new Token(this);
        }

        public Builder setUid(final String uid) {
            bUid = uid;
            return this;
        }

        public Builder setAccessToken(final String accessToken) {
            bAccessToken = accessToken;
            return this;
        }

        public Builder setRefreshToken(final String refreshToken) {
            bRefreshToken = refreshToken;
            return this;
        }

        public Builder setTimestamp(final long timestamp) {
            bTimestamp = timestamp;
            return this;
        }
    }
}
