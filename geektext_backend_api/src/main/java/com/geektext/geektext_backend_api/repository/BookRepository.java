package com.geektext.geektext_backend_api.repository;

import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.PublisherEntity;
import com.geektext.geektext_backend_api.entity.RatingsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    Optional<BookEntity> findByIsbn(String isbn);

    List<BookEntity> findByGenre(String genre);

    @Query("SELECT b FROM BookEntity b ORDER BY b.copiesSold DESC")
    List<BookEntity> findTopSellers(Pageable pageable);

    @Query("SELECT b FROM BookEntity b JOIN b.ratings r WHERE r.rating >= :rating")
    List<BookEntity> findByRatingOrHigher(@Param("rating") Long rating);

    List<BookEntity> findByPublisher(PublisherEntity publisher);

    List<BookEntity> findByRating_RatingValueGreaterThanEqual(Long ratingValue);
    
}
