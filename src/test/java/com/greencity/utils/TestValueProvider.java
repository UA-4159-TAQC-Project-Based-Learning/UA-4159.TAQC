package com.greencity.utils;

import com.greencity.data.MandatoryFieldsNewsData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TestValueProvider {
    Properties properties;

    public TestValueProvider() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException err) {
            System.out.println(err.getMessage());
            System.out.println("Use system env");
        }
    }

    public String getBaseUIUrl() {
        return properties != null ? properties.getProperty("base.ui.url") : System.getenv("BASE_UI_URL");
    }

    public String getBaseAPIUrl() {
        return properties != null ? properties.getProperty("base.api.url") : System.getenv("BASE_API_URL");
    }

    public int getImplicitlyWait() {
        return properties != null ? Integer.parseInt(properties.getProperty("implicitlyWait")) : Integer.parseInt(System.getenv("IMPLICITLY_WAIT"));
    }


    public String getUserEmail() {
        //return properties != null ? properties.getProperty("user.email") : System.getenv("USER_EMAIL");
        String env = System.getenv("USER_EMAIL");
        if (env != null) return env;

        String property = properties.getProperty("user.email");
        if (property != null && property.startsWith("${") && property.endsWith("}")) {
            String envVar = property.substring(2, property.length() - 1);
            return System.getenv(envVar);
        }
        return property;
    }

    public String getUserName() {
        return properties != null ? properties.getProperty("user.name") : System.getenv("USER_NAME");
    }

    public String getUserPassword() {
        //return properties != null ? properties.getProperty("user.password") : System.getenv("USER_PASSWORD");
        String env = System.getenv("USER_PASSWORD");
        if (env != null) return env;

        String property = properties.getProperty("user.password");
        if (property != null && property.startsWith("${") && property.endsWith("}")) {
            String envVar = property.substring(2, property.length() - 1);
            return System.getenv(envVar);
        }
        return property;
    }

    public Integer getUserNewsId() {
        return properties != null ? Integer.parseInt(properties.getProperty("user.news.id")) : Integer.parseInt(System.getenv("USER_NEWS_ID"));
    }

    public String getAdminEmail() {
        return properties != null ? properties.getProperty("admin.email") : System.getenv("ADMIN_EMAIL");
    }

    public String getAdminName() {
        return properties != null ? properties.getProperty("admin.name") : System.getenv("ADMIN_NAME");
    }

    public String getAdminPassword() {
        return properties != null ? properties.getProperty("admin.password") : System.getenv("ADMIN_PASSWORD");
    }

    public String getJDBCGreenCityUsername() {
        return properties != null ? properties.getProperty("JDBCGreenCityUsername") : System.getenv("JDBC_GREENCITY_USERNAME");
    }

    public String getJDBCGreenCityPassword() {
        return properties != null ? properties.getProperty("JDBCGreenCityPassword") : System.getenv("JDBC_GREENCITY_PASSWORD");
    }

    public String getJDBCGreenCityURL() {
        return properties != null ? properties.getProperty("JDBCGreenCityURL") : System.getenv("JDBC_GREENCITY_URL");
    }

    public String getLsUserAccessToken() {
        return properties != null ? properties.getProperty("ls.accessToken") : System.getenv("LS_ACCESS_TOKEN");
    }

    public String getLsUserRefreshToken() {
        return properties != null ? properties.getProperty("ls.refreshToken") : System.getenv("LS_REFRESH_TOKEN");
    }

    public String getLsUserId() {
        return properties != null ? properties.getProperty("ls.userId") : System.getenv("LS_USER_ID");
    }

    public String getLsUserName() {
        return properties != null ? properties.getProperty("ls.name") : System.getenv("LS_NAME");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getUserEmailFromEnv() {
        String env = System.getenv("USER_EMAIL");
        if (env != null) {
            return env;
        }
        throw new IllegalArgumentException("Environment variable USER_EMAIL is not set");
    }


    public MandatoryFieldsNewsData getValidMandatoryFieldsNewsData() {
        String title = getProperty("news.title.valid");
        String tagsString = getProperty("news.tags.valid");
        String contentText = getProperty("news.contentText.valid");

        List<String> tags = Arrays.stream(tagsString.split(","))
                .map(String::trim)
                .toList();
        return new MandatoryFieldsNewsData(title, tags, contentText);

    }
}