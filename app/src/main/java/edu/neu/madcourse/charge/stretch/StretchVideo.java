package edu.neu.madcourse.charge.stretch;

public class StretchVideo {
    private String title;
    private String link;
    private String video;

    public StretchVideo() {

    }

    public StretchVideo(String title, String link, String video) {
        this.title = title;
        this.link = link;
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
