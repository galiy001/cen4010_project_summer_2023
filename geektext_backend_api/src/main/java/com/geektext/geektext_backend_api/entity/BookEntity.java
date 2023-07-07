package com.geektext.geektext_backend_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherEntity publisher;
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "rating_id")
    private RatingsEntity rating;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuthorBookEntity> authorBooks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartBookEntity> shoppingCartBooks = new ArrayList<>();

    public BookEntity() {}

    public BookEntity(String isbn, String name, String description, String genre, Date datePublished,
                      Double price, Integer copiesSold, Double discountPercent, PublisherEntity publisher, AuthorEntity author, RatingsEntity rating ) {
        this.isbn = isbn;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.datePublished = datePublished;
        this.price = price;
        this.copiesSold = copiesSold;
        this.discountPercent = discountPercent;
        this.publisher = publisher;
        this.author = author;
        this.rating = rating;
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

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public void setRating(RatingsEntity rating) {
        this.rating = rating;
    }

    public RatingsEntity getRating() {
        return rating;
    }
}
