package edu.neu.madcourse.charge;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The Journal class represents a journal entry object
 */
public class Journal implements Parcelable {
    private String journalTitle;
    private String journalDate;
    private String journalID;
    private String journalDescription;

    public Journal() {
    }

    public Journal(String journalTitle, String journalDate, String journalID,
                   String journalDescription) {
        this.journalTitle = journalTitle;
        this.journalDate = journalDate;
        this.journalID = journalID;
        this.journalDescription = journalDescription;
    }

    protected Journal(Parcel in) {
        journalTitle = in.readString();
        journalDate = in.readString();
        journalID = in.readString();
        journalDescription = in.readString();
    }

    public static final Creator<Journal> CREATOR = new Creator<Journal>() {
        @Override
        public Journal createFromParcel(Parcel in) {
            return new Journal(in);
        }

        @Override
        public Journal[] newArray(int size) {
            return new Journal[size];
        }
    };

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getJournalDate() {
        return journalDate;
    }

    public void setJournalDate(String journalDate) {
        this.journalDate = journalDate;
    }

    public String getJournalID() {
        return journalID;
    }

    public void setJournalID(String journalID) {
        this.journalID = journalID;
    }

    public String getJournalDescription() {
        return journalDescription;
    }

    public void setJournalDescription(String journalDescription) {
        this.journalDescription = journalDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(journalTitle);
        parcel.writeString(journalDate);
        parcel.writeString(journalID);
        parcel.writeString(journalDescription);
    }
}
