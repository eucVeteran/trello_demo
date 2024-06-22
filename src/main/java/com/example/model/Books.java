package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Azizbek Toshpulatov
 */
@Entity
@Table
public class Books {
    @Id
    @Column
    private int bookID;
    @Column
    private String bookName;
    @Column
    private String author;
    @Column
    private int price;

    public int getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
