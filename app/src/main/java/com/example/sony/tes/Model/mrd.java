package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

public class mrd {

    @SerializedName("id")
    public int id;

    @SerializedName("fullname")
    public String fullname;

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String Password;

    @SerializedName("birthday")
    public String birthday;

    @SerializedName("phone")
    public String phone;

    @SerializedName("address")
    public String address;

    @SerializedName("lulusan")
    public String lulusan;

    @SerializedName("hobby")
    public String hobby;

    @SerializedName("images")
    public String images;


    @SerializedName("data")
    public String data;

    @SerializedName("code")
    public String code;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("category_id")
    public int category_id;


    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public int getCategory_id() {
        return category_id;
    }



    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLulusan() {
        return lulusan;
    }

    public void setLulusan(String lulusan) {
        this.lulusan = lulusan;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setData(String data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


