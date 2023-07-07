package com.geektext.geektext_backend_api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ratings")
public class RatingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;

    @Column(name = "rating")
    private Long rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "isbn", cascade = CascadeType.ALL)
    private List<BookEntity> books;


    public RatingsEntity() {}

    public RatingsEntity(Long rating, UserEntity user) {
        this.rating = rating;
        this.user = user;
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

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
}
