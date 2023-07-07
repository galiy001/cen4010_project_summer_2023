package com.geektext.geektext_backend_api.repository;

import com.geektext.geektext_backend_api.entity.CreditCardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardsEntity, Long> {
}
