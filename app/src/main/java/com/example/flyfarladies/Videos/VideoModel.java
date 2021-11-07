package com.example.flyfarladies.Videos;

public class VideoModel {
    String videoId;
    String videoThumbnail;
    String videoTitle;
    String videoDescription;
    String detailsLink;
    String videoStatus;

    public VideoModel() {
    }

    public VideoModel(String videoId, String videoThumbnail, String videoTitle, String videoDescription, String detailsLink, String videoStatus) {
        this.videoId = videoId;
        this.videoThumbnail = videoThumbnail;
        this.videoTitle = videoTitle;
        this.videoDescription = videoDescription;
        this.detailsLink = detailsLink;
        this.videoStatus = videoStatus;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(String detailsLink) {
        this.detailsLink = detailsLink;
    }

    public String getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(String videoStatus) {
        this.videoStatus = videoStatus;
    }
}
