package com.rohitdaf.teacher;

import com.google.firebase.database.DatabaseReference;

public class DataRefFetch {

        public String name;
        public String roll;
        public String codename;
        public String reason;
        public String time,email;
        public String lectruAssigned;
        public Boolean acceptance;



        public DataRefFetch(){
        }
        public DataRefFetch(String name, String roll, String codename, String reason, String time ,Boolean acceptance , String email) {
            this.name = name;
            this.roll = roll;
            this.codename = codename;
            this.reason = reason;
            this.time = time;
            this.email = email;
            this.acceptance = acceptance;
        }


        public DataRefFetch(String name, String roll, String codename,String email) {
            this.name = name;
            this.roll = roll;
            this.codename = codename;
            this.email = email;
        }

        public DataRefFetch(Boolean acceptance){
            this.acceptance = acceptance;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }



    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLectruAssigned() {
        return lectruAssigned;
    }

    public void setLectruAssigned(String lectruAssigned) {
        this.lectruAssigned = lectruAssigned;
    }

    public Boolean getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(Boolean acceptance) {
        this.acceptance = acceptance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
