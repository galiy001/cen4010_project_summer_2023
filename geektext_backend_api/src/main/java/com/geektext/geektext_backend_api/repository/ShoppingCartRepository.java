package com.geektext.geektext_backend_api.repository;

import com.geektext.geektext_backend_api.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    @Query("SELECT SUM(b.price) FROM ShoppingCartEntity s JOIN s.books b WHERE s.user.userId = :userId")
    Double calculateCartSubtotalByUserId(@Param("userId") Long userId);

    @Query("SELECT s FROM ShoppingCartEntity s JOIN s.books b WHERE b.isbn = :isbn AND s.user.userId = :userId")
    Optional<ShoppingCartEntity> findByIsbnAndUserId(@Param("isbn") String isbn, @Param("userId") Long userId);

    List<ShoppingCartEntity> findByUser_UserId(Long userId);

    @Modifying
    @Query("DELETE FROM ShoppingCartEntity s WHERE s.user.userId = :userId AND :isbn MEMBER OF s.books")
    void deleteByIsbnAndUserId(@Param("isbn") String isbn, @Param("userId") Long userId);

}