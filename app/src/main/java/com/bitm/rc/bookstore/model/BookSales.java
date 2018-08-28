package com.bitm.rc.bookstore.model;

public class BookSales {
    private Integer id;
    private Integer salesQty;
    private String salesPrice;
    private String salesDate;
    private String bookInfoId;

    public BookSales() {
    }

    public BookSales(Integer id, Integer salesQty, String salesPrice, String salesDate, String bookInfoId) {
        this.id = id;
        this.bookInfoId = bookInfoId;
        this.salesQty = salesQty;
        this.salesPrice = salesPrice;
        this.salesDate = salesDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(String bookInfoId) {
        this.bookInfoId = bookInfoId;
    }

    public Integer getSalesQty() {
        return salesQty;
    }

    public void setSalesQty(Integer salesQty) {
        this.salesQty = salesQty;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }
    public String getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }
}
