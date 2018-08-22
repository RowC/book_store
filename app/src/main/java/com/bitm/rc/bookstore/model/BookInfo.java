package com.bitm.rc.bookstore.model;

import java.util.ArrayList;

public class BookInfo {
    private Integer id;
    private String bookName;
    private String author;
    private String edition;
    private String price;
    private Integer quantity;
    private String  publisherInfoList;
    private String country;
    private String language;

    public BookInfo() {
    }

    public BookInfo(Integer id,String bookName, String author, String edition,String publisherInfoList, Integer quantity, String price,String language) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.edition = edition;
        this.publisherInfoList = publisherInfoList;
        this.quantity = quantity;
        this.price = price;
        this.language = language;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getPublisherInfoList() {
        return publisherInfoList;
    }

    public void setPublisherInfoList(String publisherInfoList) {
        this.publisherInfoList = publisherInfoList;
    }

//    https://tausiq.wordpress.com/2013/01/19/android-input-field-validation/
}
