package edu.neu.madcourse.charge;

public class Journal {
    private String entry;
    private String keyJournal;

    public Journal() {}

    public Journal(String item) {
        this.entry = item;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getKeyJournal() { return keyJournal; }

    public void setKey(String keyJournal) { this.keyJournal = keyJournal; }
}
