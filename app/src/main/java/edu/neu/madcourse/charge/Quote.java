package edu.neu.madcourse.charge;

public class Quote {
    private String quotation;
    private String author;

    public Quote() {}

    public Quote(String quotation, String author) {
        this.quotation = quotation;
        this.author = author;
    }

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
}
