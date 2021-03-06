package com.example.a19518901_hocongviet_t3_10_12;

public class Book {
    private String bookName;
    private String author;
    private String price;
    private String desc;

    public Book(String bookName, String author, String price, String desc) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.desc = desc;
    }
    public  Book(){

    }
    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", desc='" + desc + '\'' +
                '}';
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
