package com.bitm.rc.bookstore.model;

import java.util.ArrayList;

public class BookInfo {
    private String bookName;
    private String author;
    private String edition;
    private String country;
    private String language;
    private String price;
    private Integer quantity;
    private ArrayList<PublisherInfo> publisherInfoList;


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ArrayList<PublisherInfo> getPublisherInfoList() {
        return publisherInfoList;
    }

    public void setPublisherInfoList(ArrayList<PublisherInfo> publisherInfoList) {
        this.publisherInfoList = publisherInfoList;
    }
}
