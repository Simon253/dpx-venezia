package com.spottechindustrial.carpool.android.utils;

public class Constants {
    private Constants(){}

    public static final String LAYOUT_ID = "layoutId";
    public static final String TIME_PICKER_SET_HOUR = "setHour";
    public static final String TIME_PICKER_SET_MINUTE = "setMinute";
    public static final String TIME_PICKER_CALLER_ID = "callerId";

    public static char MALE = 'M';
    public static char FEMALE = 'F';
    // API names
    public static final String REGISTER_API = "register";
    public static final String LOGIN_API = "login";
    public static final String REFRESH_ACCESS_TOKEN_API = "refreshAccessToken";
    public static final String SET_GENDER_API = "setGender";
    public static final String SET_USERNAME_API = "setUsername";
    public static final String GET_PROFILES_API = "getProfiles";
    public static final String FILE_UPLOAD_API = "fileUpload";
    public static final String GET_COUNTER_API = "getCounter";

    // Http Headers
    public final static String CAR_POOL_CALL_ID = "carPoolCallId";
    public final static String CAR_POOL_CALL_NAME = "carPoolCallName";

    // HTTP Request Params
    public static final String SERVICES_CONSOLE_URL = "http://192.241.202.169:8080/ServicesConsole/";
    // public static final String SERVICES_CONSOLE_URL = "http://192.168.0.18:8080/ServicesConsole/";

    public static final String UID = "uid";
    public static final String RECEIVER_UID = "receiverUid";
    public final static String UID_LIST = "uidList";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";
    public static final String TIMESTAMP = "timestamp";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String GENDER = "gender";
    public static final String USERNAME = "username";
    public static final String CAN_DRIVE = "canDrive";
    public static final String MAJOR = "major";
    public static final String SCHOOL_CLASS = "schoolClass";
    public static final String FILE = "file";
    public static final String FILENAME = "filename";
    public static final String COUNT = "count";

    // JSON Params
    public final static String USER_LIST = "userList";

    public static final String IMAGE_USAGE = "imageUsage";
    public static final int IMAGE_USAGE_FOR_NEWS = 3;
    public static final int IMAGE_USAGE_FOR_TALKS = 2;
    public static final int IMAGE_USAGE_FOR_SETTINGS = 1;

    // HTTP Response Params
    public static final String STATUS = "status";
    public static final String DATA = "data";

    // SharedPreferences
    public static final String TOKEN_PREFERENCE = "tokenPreference";
    public static final String USER_PREFERENCE = "userPreference";
    public static final String COUNTER_PREFERENCE = "counterPreference";
    public static final String LOGIN_STATUS = "loginStatus";

    // Intent Request Code
    public static int INTENT_CODE_REGISTER = 1;
    public static int INTENT_CODE_CREATE_PROFILE = 2;
    public static int INTENT_CODE_MAIN = 3;
    public static int INTENT_CODE_SUBMIT_SCHEDULE = 4;

    public static int INTENT_CODE_REQUEST_PICTURE = 5;
    public static int INTENT_CODE_CROP_PICTURE = 6;
    public static int INTENT_CODE_REQUEST_CAMERA = 7;

    // Intent Params
    public static String IS_FROM_VENEZIA = "isFromVenezia";

    // Tab Tags
    public static final String NEWS_TAG = "newsTabTag";
    public static final String TALKS_TAG = "talksTabTag";
    public static final String DISCOVERY_TAG = "discoveryTabTag";
    public static final String SETTINGS_TAG = "settingsTabTag";
}
