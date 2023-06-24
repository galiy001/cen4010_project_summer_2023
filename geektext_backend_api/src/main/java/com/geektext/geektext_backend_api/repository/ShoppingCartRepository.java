package com.geektext.geektext_backend_api.repository;

import com.geektext.geektext_backend_api.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

}