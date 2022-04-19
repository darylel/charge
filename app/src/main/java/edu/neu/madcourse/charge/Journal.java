package edu.neu.madcourse.charge;

/**
 * The Journal class represents a journal entry object
 */
public class Journal {
    private String journalTitle;
    private String journalDate;

    public Journal() {}

    public Journal(String journalTitle, String journalDate) {
        this.journalTitle = journalTitle;

    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getJournalDate() { return journalDate; }

    public void setJournalDate(String journalDate) { this.journalDate = journalDate; }
}
