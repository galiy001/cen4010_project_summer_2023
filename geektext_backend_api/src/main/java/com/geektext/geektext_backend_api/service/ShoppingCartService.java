package com.geektext.geektext_backend_api.service;

import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    Optional<List<BookEntity>> getCartByUserId(Long userId); //Retrieve all books in the user's cart.

    void addBookToCart(Long userId, String isbn); //Add a book to the user's cart.

    void removeBookFromCart(Long userId, String isbn); //Remove a book from the user's cart.

    double calculateSubtotal(Long userId); //Calculate the subtotal of all items in the user's cart.

}
