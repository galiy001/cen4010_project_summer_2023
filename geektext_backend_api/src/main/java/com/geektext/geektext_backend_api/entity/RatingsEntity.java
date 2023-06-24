package com.geektext.geektext_backend_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class RatingsEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long rating_id;


    @Column(name = "rating")
    private Long rating;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn", referencedColumnName = "isbn")
    private BookEntity book;


    @Column(name = "user_id")
    private int user_id;




    public RatingsEntity() {
    }


    public RatingsEntity(Long rating, BookEntity book, int user_id, long rating_id) {
        this.rating = rating;
        this.book = book;
        this.user_id = user_id;
        this.rating_id = rating_id;
    }


    public Long getRatingId() {
        return rating_id;
    }


    public void setRatingId(Long rating_id) {
        this.rating_id = rating_id;
    }


    public Long getRating() {
        return rating;
    }


    public void setRating(Long rating) {
        this.rating = rating;
    }


    public BookEntity getBook() {
        return book;
    }


    public void setBook(BookEntity book) {
        this.book = book;
    }


    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public int getUser_id() {
        return user_id;
    }
}

