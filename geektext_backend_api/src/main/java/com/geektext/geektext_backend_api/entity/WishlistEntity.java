package com.geektext.geektext_backend_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlists")
public class WishlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long wishlistId;

    @Column(name = "wishlist_num")
    private int wishlistNum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private BookEntity book;

    public WishlistEntity() {

    }

    public WishlistEntity(int wishlistNum, UserEntity user, BookEntity book) {
        this.wishlistNum = wishlistNum;
        this.user = user;
        this.book = book;
    }

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getWishlistNum() {
        return wishlistNum;
    }

    public void setWishlistNum(int wishlistNum) {
        this.wishlistNum = wishlistNum;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}