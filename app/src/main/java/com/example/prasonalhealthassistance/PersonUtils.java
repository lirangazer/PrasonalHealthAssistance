package com.example.prasonalhealthassistance;

public class PersonUtils {

    private String personName;
    private String jobProfile;
    private String imageProfile;

    public PersonUtils(String personName, String jobProfile,String imageProfile) {
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


    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }
}