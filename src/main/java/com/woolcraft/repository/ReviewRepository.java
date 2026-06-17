package com.woolcraft.repository;

import com.woolcraft.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductIdAndApprovedTrue(Long productId);
    List<Review> findByApprovedFalse();
}
