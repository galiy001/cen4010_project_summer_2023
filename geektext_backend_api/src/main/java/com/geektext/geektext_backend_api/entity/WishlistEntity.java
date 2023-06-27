package com.geektext.geektext_backend_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlists")
public class WishlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long wishlist_id;

    @Column(name = "wishlist_num")
    private int wishlist_num;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user_id;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private BookEntity isbn;

    public WishlistEntity() {

    }

    public WishlistEntity(int wishlist_num, UserEntity user_id, BookEntity isbn) {
        this.wishlist_num = wishlist_num;
        this.user_id = user_id;
        this.isbn = isbn;
    }

    public Long getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(Long wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public int getWishlist_num() {
        return wishlist_num;
    }

    public void setWishlist_num(int wishlist_num) {
        this.wishlist_num = wishlist_num;
    }

    public UserEntity getUser_id() {
        return user_id;
    }

    public void setUser_id(UserEntity user_id) {
        this.user_id = user_id;
    }

    public BookEntity getIsbn() {
        return isbn;
    }

    public void setIsbn(BookEntity isbn) {
        this.isbn = isbn;
    }
}