package com.appsdeveloper.resourceserver.response;

public class UserRest {
    private String userId;
    private String userFirstName;
    private String userLastName;

    public UserRest() {
    }

    public UserRest(String userId, String userFirstName, String userLastName) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
