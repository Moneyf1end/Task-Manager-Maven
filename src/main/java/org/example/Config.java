package org.example;

public class Config {
    public databaseParamOfConfig database;

    public static class databaseParamOfConfig {
        public String url;
        public String user;
        public String password;

        boolean isValid() {
            return (url != null && user != null);
        }
    }
}
