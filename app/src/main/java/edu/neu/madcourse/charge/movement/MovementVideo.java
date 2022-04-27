package edu.neu.madcourse.charge.movement;

public class MovementVideo {
    private final String title;
    private final String link;
    private final String video;

    public MovementVideo(String title, String link, String video) {
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
