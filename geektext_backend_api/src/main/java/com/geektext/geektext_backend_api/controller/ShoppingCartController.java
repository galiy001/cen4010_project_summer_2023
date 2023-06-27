package com.geektext.geektext_backend_api.controller;

import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping_cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<BookEntity>> getUserCart(@PathVariable("user_id") Long userId) {
        return shoppingCartService.getCartByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{user_id}/{isbn}")
    public void addBookToUserCart(@PathVariable("user_id") Long userId, @PathVariable("isbn") String isbn) {
        shoppingCartService.addBookToCart(userId, isbn);
    }

    @DeleteMapping("/{user_id}/{isbn}")
    public void removeBookFromUserCart(@PathVariable("user_id") Long userId, @PathVariable("isbn") String isbn) {
        shoppingCartService.removeBookFromCart(userId, isbn);
    }

    @GetMapping("/{user_id}/subtotal")
    public double getSubtotal(@PathVariable("user_id") Long userId) {
        return shoppingCartService.calculateSubtotal(userId);
    }
}

