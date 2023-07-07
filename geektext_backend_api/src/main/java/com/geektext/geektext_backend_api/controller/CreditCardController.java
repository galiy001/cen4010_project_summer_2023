package com.geektext.geektext_backend_api.controller;

import com.geektext.geektext_backend_api.service.CreditCardService;
import com.geektext.geektext_backend_api.entity.CreditCardsEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit_cards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("/{username}")
    public void createCreditCardForUser(@PathVariable("username") String username, @RequestBody CreditCardsEntity creditCardsEntity) {
        creditCardService.createCreditCardForUser(username, creditCardsEntity);
    }
}
