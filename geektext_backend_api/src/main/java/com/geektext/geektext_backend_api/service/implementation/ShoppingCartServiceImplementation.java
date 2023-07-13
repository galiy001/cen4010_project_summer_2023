package com.geektext.geektext_backend_api.service.implementation;

import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.ShoppingCartEntity;
import com.geektext.geektext_backend_api.entity.UserEntity;
import com.geektext.geektext_backend_api.repository.ShoppingCartRepository;
import com.geektext.geektext_backend_api.service.BookService;
import com.geektext.geektext_backend_api.service.ShoppingCartService;
import com.geektext.geektext_backend_api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImplementation implements ShoppingCartService {

    private final UserService userService;
    private final BookService bookService;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImplementation(UserService userService, BookService bookService, ShoppingCartRepository shoppingCartRepository) {
        this.userService = userService;
        this.bookService = bookService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public Optional<List<BookEntity>> getCartByUserId(Long userId) {
        Optional<UserEntity> user = userService.searchByUserId(userId);
        if (user.isPresent()) {
            return Optional.ofNullable(user.get().getShoppingCart().getBooks());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void addBookToCart(Long userId, String isbn) {
        Optional<UserEntity> user = userService.searchByUserId(userId);
        Optional<BookEntity> book = bookService.findBookByIsbn(isbn);
        if (user.isPresent() && book.isPresent()) {
            user.get().getShoppingCart().getBooks().add(book.get());
            userService.updateUser(user.get());
        } else {
            System.out.println("User or Book not found");
        }
    }

    @Override
    public void removeBookFromCart(Long userId, String isbn) {
        Optional<UserEntity> user = userService.searchByUserId(userId);
        Optional<BookEntity> book = bookService.findBookByIsbn(isbn);
        if (user.isPresent() && book.isPresent()) {
            user.get().getShoppingCart().getBooks().remove(book.get());
            userService.updateUser(user.get());
        }
    }

    @Override
    public double calculateSubtotal(Long userId) {
        Optional<List<BookEntity>> booksInCart = getCartByUserId(userId);
        if (booksInCart.isPresent()) {
            return booksInCart.get().stream().mapToDouble(BookEntity::getPrice).sum();
        } else {
            return 0;
        }
    }
}