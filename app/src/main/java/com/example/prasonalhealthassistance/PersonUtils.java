package com.example.prasonalhealthassistance;

public class PersonUtils {

    private String personName;
    private String jobProfile;
    private int imageProfile;

    public PersonUtils(String personName, String jobProfile,int imageProfile) {
        this.personName = personName;
        this.jobProfile = jobProfile;
        this.imageProfile = imageProfile;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(String jobProfile) {
        this.jobProfile = jobProfile;
    }

    public int getImageProfile() {
        return imageProfile;
    }
//
//    public void setImageProfile(String imageProfile) {
//        this.imageProfile = imageProfile;
//    }
}