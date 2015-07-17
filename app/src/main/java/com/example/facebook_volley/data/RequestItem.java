package com.example.facebook_volley.data;

/**
 * Created by Doan Vo Viet Hoai on 6/19/2015.
 */
public class RequestItem {
    private String name, profilePic;

    public RequestItem() {
    }

    public RequestItem(String name, String profilePic) {
        this.name = name;
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
