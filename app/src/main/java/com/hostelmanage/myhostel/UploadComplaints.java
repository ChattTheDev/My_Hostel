package com.hostelmanage.myhostel;

public class UploadComplaints {

    private String StudentName, StudentRoll, StudentHostelRoom, StudentHostelName, StudentComplaint;
    private String ImageUrl1;

    public UploadComplaints(String studentName, String studentRoll, String studentHostelRoom, String studentComplaint, String imageUrl, String studentHostelName) {
        StudentName = studentName;
        StudentRoll = studentRoll;
        StudentHostelRoom = studentHostelRoom;
        StudentComplaint = studentComplaint;
        ImageUrl1 = imageUrl;
        StudentHostelName = studentHostelName;
    }

    public String getStudentHostelName() {
        return StudentHostelName;
    }

    public void setStudentHostelName(String studentHostelName) {
        StudentHostelName = studentHostelName;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentRoll() {
        return StudentRoll;
    }

    public void setStudentRoll(String studentRoll) {
        StudentRoll = studentRoll;
    }

    public String getStudentHostelRoom() {
        return StudentHostelRoom;
    }

    public void setStudentHostelRoom(String studentHostelRoom) {
        StudentHostelRoom = studentHostelRoom;
    }

    public String getStudentComplaint() {
        return StudentComplaint;
    }

    public void setStudentComplaint(String studentComplaint) {
        StudentComplaint = studentComplaint;
    }

    public String getImageUrl() {
        return ImageUrl1;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl1 = imageUrl;
    }
}
