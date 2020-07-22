package com.hostelmanage.myhostel;

public class Uplaodfiles {

    private String NoticeImage;
    private String ImageUrl;

    public Uplaodfiles(String noticeImage, String imageUrl) {
        NoticeImage = noticeImage;
        ImageUrl = imageUrl;
    }

    public Uplaodfiles() {
    }

    public String getNoticeImage() {
        return NoticeImage;
    }

    public void setNoticeImage(String noticeImage) {
        NoticeImage = noticeImage;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
