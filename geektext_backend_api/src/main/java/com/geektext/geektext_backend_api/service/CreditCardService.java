package com.geektext.geektext_backend_api.service;

import com.geektext.geektext_backend_api.entity.CreditCardsEntity;

public interface CreditCardService {
    void createCreditCardForUser(String username, CreditCardsEntity creditCardsEntity);
}
