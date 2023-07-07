package com.geektext.geektext_backend_api.service.implementation;

import com.geektext.geektext_backend_api.entity.UserEntity;
import com.geektext.geektext_backend_api.entity.CreditCardsEntity;
import com.geektext.geektext_backend_api.repository.UserRepository;
import com.geektext.geektext_backend_api.repository.CreditCardRepository;
import com.geektext.geektext_backend_api.service.CreditCardService;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImplementation implements CreditCardService {
    private final UserRepository userRepository;
    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImplementation(UserRepository userRepository, CreditCardRepository creditCardRepository) {
        this.userRepository = userRepository;
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public void createCreditCardForUser(String username, CreditCardsEntity creditCardsEntity) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        creditCardsEntity.setUserId(userEntity);
        creditCardRepository.save(creditCardsEntity);
    }
}
