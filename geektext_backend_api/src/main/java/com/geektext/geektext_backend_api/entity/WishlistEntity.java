package com.geektext.geektext_backend_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="wishlists")
public class WishlistEntity {

    @Column(name = "wishlist_id")
    private int wishlist_id;
    @Column(name = "wishlist_num")
    private int wishlist_num;

    @Column(name = "user_id")
    private int user_id;
    @Column(name = "isbn")
    private String isbn;


    public WishlistEntity(int wishlist_id, int wishlist_num, int user_id, String isbn) {
        this.wishlist_id = wishlist_id;
        this.wishlist_num = wishlist_num;
        this.user_id = user_id;
        this.isbn = isbn;
    }


    public int getWishlist_id() {
        return wishlist_id;
    }

    public int getWishlist_num() {
        return wishlist_num;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getIsbn() {
        return isbn;
    }


    public void setWishlist_id(int wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public void setWishlist_num(int wishlist_num) {
        this.wishlist_num = wishlist_num;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}