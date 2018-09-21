package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Wahyu ACC on 29/03/2018.
 */
public class LoginGuru {

    private boolean status;

    @SerializedName("id") private String id;
    @SerializedName("email") private String email;
    @SerializedName("password") private String password;
    @SerializedName("fullname") private String fullname;
    @SerializedName("birthdate") private String birthdate;
    @SerializedName("birthplace") private String birthplace;
    @SerializedName("lulusan") private String lulusan;
    @SerializedName("phone") private String phone;
    @SerializedName("address") private String address;
    @SerializedName("gender") private String gender;
    @SerializedName("hobby") private String hobby;
    @SerializedName("images") private String images;
    @SerializedName("pelajaran") private String pelajaran;
    @SerializedName("jadwal") private List<Jadwal> jadwal;



    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public String getLulusan() {
        return lulusan;
    }

    public void setLulusan(String lulusan) {
        this.lulusan = lulusan;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public List<Jadwal> getJadwal() {
        return jadwal;
    }

    public void setJadwal(List<Jadwal> jadwal) {
        this.jadwal = jadwal;
    }

//    public DataMrd getData_Guru() {
//        return data_guru;
//    }
//
//    public class DataMrd {
//        private String id;
//        private String fullname;
//        private String email;
//        private String password;
//        private String code;
//        private String birthdate;
//        private String birthplace;
//        private String phone;
//        private String address;
//        private String lulusan;
//        private String hobby;
//        private String images;
//        private String certificate;
//        private String gender;
//
//        public String getId() {
//            return id;
//        }
//
//        public String getFullname() {
//            return fullname;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public String getCode() {
//            return code;
//        }
//
//        public String getBirthdate() {
//            return birthdate;
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public String getLulusan() {
//            return lulusan;
//        }
//
//        public String getHobby() {
//            return hobby;
//        }
//
//        public String getImages() {
//            return images;
//        }
//
//        public String getBirthplace() {
//            return birthplace;
//        }
//
//        public String getCertificate() {
//            return certificate;
//        }
//
//        public String getGender() {
//            return gender;
//        }
//    }
}
