package com.bitm.rc.bookstore.model;

import java.util.Date;

public class PublisherInfo {
    private String name;
    private String address;
    private Date date;

    public PublisherInfo() {
    }

    public PublisherInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
