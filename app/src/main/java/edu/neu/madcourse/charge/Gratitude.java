package edu.neu.madcourse.charge;

public class Gratitude {
    private String item;
    private String keyGratitude;

    public Gratitude() {};

    public Gratitude(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getKeyGratitude() { return keyGratitude; }

    public void setKey(String keyGratitude) { this.keyGratitude = keyGratitude; }
}
