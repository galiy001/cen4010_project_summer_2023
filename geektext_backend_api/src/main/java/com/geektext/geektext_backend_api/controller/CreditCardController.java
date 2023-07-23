package com.geektext.geektext_backend_api.controller;

import com.geektext.geektext_backend_api.service.CreditCardService;
import com.geektext.geektext_backend_api.entity.CreditCardsEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit_cards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("/{username}")
    public ResponseEntity<String> createCreditCardForUser(@PathVariable("username") String username, @RequestBody CreditCardsEntity creditCardsEntity) {
        creditCardService.createCreditCardForUser(username, creditCardsEntity);

        String responseMessage = "Credit card successfully added to user: " + username;
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
