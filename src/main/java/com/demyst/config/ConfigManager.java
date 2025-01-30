package com.demyst.config;

public class ConfigManager {
    private static final String BASE_URL = "https://demyst.com";
    private static final String LOGIN_ENDPOINT = "/console/users/sign_in";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getLoginEndpoint() {
        return LOGIN_ENDPOINT;
    }

    // Additional configuration properties can be added here
    public static int getDefaultTimeout() {
        return 2000; // 2 seconds
    }

    public static String getDefaultContentType() {
        return "application/json";
    }
}