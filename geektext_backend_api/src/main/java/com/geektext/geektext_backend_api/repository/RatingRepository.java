package com.geektext.geektext_backend_api.repository;

import com.geektext.geektext_backend_api.entity.RatingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<RatingsEntity, Long> {

    @Query("SELECT AVG(r.rating) FROM RatingsEntity r WHERE r.book.isbn = :isbn")
    Double findAverageRatingByBookId(String isbn);

    @Query("SELECT r FROM RatingsEntity r WHERE r.user.id = :userId")
    List<RatingsEntity> findRatingsByUserId(Long userId);

    @Query("SELECT r FROM RatingsEntity r WHERE r.book.isbn = :isbn")
    List<RatingsEntity> findRatingsByBookId(String isbn);
}
