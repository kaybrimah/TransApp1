package com.khadeeja.sapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner G on 15/08/2017.
 */

public class UserInfo {

    @SerializedName("firebase_uid")
    String firebase_uid;

    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    @SerializedName("contact")
    String contact;


    @SerializedName("gender")
    String gender;

    @SerializedName("image_url")
    String image_url;

    @SerializedName("device")
     Device device;

    @SerializedName("session")
    Session session;


    public UserInfo() {

    }

    public UserInfo(String firebase_uid, String name, String email, String contact,
                    String gender, String image_url,Device device, Session session) {
        this.firebase_uid = firebase_uid;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.gender = gender;
        this.image_url = image_url;
        this.device = device;
        this.session = session;
    }

    public String getFirebase_uid() {
        return firebase_uid;
    }

    public void setFirebase_uid(String firebase_uid) {
        this.firebase_uid = firebase_uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
