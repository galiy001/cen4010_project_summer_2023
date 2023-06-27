package com.geektext.geektext_backend_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "shopping_cart_books")
public class ShoppingCartBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private ShoppingCartEntity cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn")
    @JsonIgnore
    private BookEntity book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingCartEntity getCart() {
        return cart;
    }

    public void setCart(ShoppingCartEntity cart) {
        this.cart = cart;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

}

