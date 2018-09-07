package com.example.sony.tes.Model;

/**
 * Created by Wahyu ACC on 29/03/2018.
 */
public class LoginGuru {
    private boolean status;
    private String message;
    private DataMrd data_guru;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataMrd getData_Guru() {
        return data_guru;
    }

    public class DataMrd {
        private String id;
        private String fullname;
        private String email;
        private String password;
        private String code;
        private String birthdate;
        private String birthplace;
        private String phone;
        private String address;
        private String lulusan;
        private String hobby;
        private String images;
        private String certificate;
        private String gender;

        public String getId() {
            return id;
        }

        public String getFullname() {
            return fullname;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getCode() {
            return code;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public String getPhone() {
            return phone;
        }

        public String getAddress() {
            return address;
        }

        public String getLulusan() {
            return lulusan;
        }

        public String getHobby() {
            return hobby;
        }

        public String getImages() {
            return images;
        }

        public String getBirthplace() {
            return birthplace;
        }

        public String getCertificate() {
            return certificate;
        }

        public String getGender() {
            return gender;
        }
    }
}
