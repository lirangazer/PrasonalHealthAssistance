package com.example.prasonalhealthassistance;

public class RecyclerUtils {

    private String titleName;
    private String description;
    private int imageProfile;

    public RecyclerUtils(String titleName, String description, int imageProfile) {
        this.titleName = titleName;
        this.description = description;
        this.imageProfile = imageProfile;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageProfile() { return imageProfile; }
}