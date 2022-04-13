package edu.neu.madcourse.charge;

import android.os.Parcel;
import android.os.Parcelable;

public class Quote implements Parcelable {
    private String quotation;
    private String author;

    public Quote() {}

    public Quote(String quotation, String author) {
        this.quotation = quotation;
        this.author = author;
    }

    protected Quote(Parcel in) {
        quotation = in.readString();
        author = in.readString();
    }

    public static final Creator<Quote> CREATOR = new Creator<Quote>() {
        @Override
        public Quote createFromParcel(Parcel in) {
            return new Quote(in);
        }

        @Override
        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };

    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(String quotation) {
        this.quotation = quotation;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(quotation);
        parcel.writeString(author);
    }
}
