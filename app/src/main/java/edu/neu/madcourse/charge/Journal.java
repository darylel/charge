package edu.neu.madcourse.charge;

/**
 * The Journal class represents a journal entry object
 */
public class Journal {
    private String journalTitle;
    private String journalDate;
    private String journalID;

    public Journal() {}

    public Journal(String journalTitle, String journalDate, String journalID) {
        this.journalTitle = journalTitle;
        this.journalDate = journalDate;
        this.journalID = journalID;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getJournalDate() { return journalDate; }

    public void setJournalDate(String journalDate) { this.journalDate = journalDate; }

    public String getJournalID() { return journalID; }

    public void setJournalID(String journalID) { this.journalID = journalID; }
}
