package com.hostelmanage.myhostel;

public class UserAdapter {

    public String name, email, mobileno, stdbranch, yearstd, hostelroom, hostelname, collegeRoll;


    public UserAdapter(String name, String email, String mobileno, String stdbranch, String yearstd, String hostelroom, String hostelname, String collegeRoll) {
        this.name = name;
        this.email = email;
        this.mobileno = mobileno;
        this.stdbranch = stdbranch;
        this.yearstd = yearstd;
        this.hostelroom = hostelroom;
        this.hostelname = hostelname;
        this.collegeRoll = collegeRoll;
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

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }


    public String getStdbranch() {
        return stdbranch;
    }

    public void setStdbranch(String stdbranch) {
        this.stdbranch = stdbranch;
    }

    public String getYearstd() {
        return yearstd;
    }

    public void setYearstd(String yearstd) {
        this.yearstd = yearstd;
    }

    public String getHostelroom() {
        return hostelroom;
    }

    public void setHostelroom(String hostelroom) {
        this.hostelroom = hostelroom;
    }

    public String getHostelname() {
        return hostelname;
    }

    public void setHostelname(String hostelname) {
        this.hostelname = hostelname;
    }

    public String getCollegeRoll() {
        return collegeRoll;
    }

    public void setCollegeRoll(String collegeRoll) {
        this.collegeRoll = collegeRoll;
    }
}

