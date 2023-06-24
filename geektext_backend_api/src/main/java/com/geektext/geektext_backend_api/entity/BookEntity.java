package com.geektext.geektext_backend_api.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "genre")
    private String genre;

    @Column(name = "date_published")
    private Date datePublished;

    @Column(name = "price")
    private Double price;

    @Column(name = "copies_sold")
    private Integer copiesSold;

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "publisher_id")
    private Long publisherId;

    @Column(name = "author_id")
    private Long authorId;

    public BookEntity() {

    }

    public BookEntity(String isbn, String name, String description, String genre, Date datePublished,
                      Double price, Integer copiesSold, Double discountPercent, Long publisherId, Long authorId) {
        this.isbn = isbn;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.datePublished = datePublished;
        this.price = price;
        this.copiesSold = copiesSold;
        this.discountPercent = discountPercent;
        this.publisherId = publisherId;
        this.authorId = authorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(Integer copiesSold) {
        this.copiesSold = copiesSold;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}