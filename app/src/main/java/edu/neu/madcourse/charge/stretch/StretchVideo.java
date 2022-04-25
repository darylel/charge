package edu.neu.madcourse.charge.stretch;

public class StretchVideo {
    private final String title;
    private final String link;
    private final String video;

    public StretchVideo(String title, String link, String video) {
        this.title = title;
        this.link = link;
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getVideo() {
        return video;
    }
}
