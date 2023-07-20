package com.geektext.geektext_backend_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

/*
This class represents the Rating entity in the system. It captures a user's rating for a particular book they have purchased.
Each rating is a score on a 5-star scale.
*/
@Entity
@Table(name = "ratings")
public class RatingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;

    @CreatedDate
    @Column(name = "datestamp")
    private LocalDateTime datestamp;
    @Column(name = "rating")
    private Long rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "isbn")
    private BookEntity book;

    public RatingsEntity() {
    }

    public RatingsEntity(Long rating, BookEntity book, UserEntity user) {
        this.book = book;
        this.user = user;
        this.rating = rating;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
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

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
    public LocalDateTime getDatestamp() {return datestamp;}

    public void setDatestamp(LocalDateTime datestamp) {
        this.datestamp = datestamp;
    }

}
