package com.geektext.geektext_backend_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity
@Table(name = "ratings")
public class RatingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long rating_id;

    @Column(name = "rating")
    private Long rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private BookEntity isbn;

    public RatingsEntity() {}

    public RatingsEntity(Long rating, BookEntity isbn, UserEntity user) {
        this.rating = rating;
        this.isbn = isbn;
        this.user = user;
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

    public BookEntity getIsbn() {
        return isbn;
    }

    public void setIsbn(BookEntity isbn) {
        this.isbn = isbn;
    }

    public void setUser_id(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser_id() {
        return user;
    }
}

