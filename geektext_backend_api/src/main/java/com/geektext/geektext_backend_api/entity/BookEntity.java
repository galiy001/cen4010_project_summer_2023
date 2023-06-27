package com.geektext.geektext_backend_api.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

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
    private int copiesSold;

    @Column(name = "discount_percent")
    private Double discountPercent;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherEntity publisher;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author_id;
    
    @OneToMany(mappedBy = "book")
    private Set<RatingsEntity> ratings;


    public BookEntity() {}

    public BookEntity(String isbn, String name, String description, String genre, Date datePublished,
                      Double price, int copiesSold, Double discountPercent, PublisherEntity publisher, AuthorEntity author_id, Set<RatingsEntity> ratings) {
        this.isbn = isbn;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.datePublished = datePublished;
        this.price = price;
        this.copiesSold = copiesSold;
        this.discountPercent = discountPercent;
        this.publisher = publisher;
        this.author_id = author_id;
        this.ratings = ratings;
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

    public PublisherEntity getPublisher_id() {
        return publisher;
    }

    public void setPublisher_id(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public AuthorEntity getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(AuthorEntity author_id) {
        this.author_id = author_id;
    }
    
    public void SetRating( Set<RatingsEntity> rating) {
    	this.ratings = rating;
    }
    
    public Set<RatingsEntity> getRatings() {
    	return ratings;
    }
}
