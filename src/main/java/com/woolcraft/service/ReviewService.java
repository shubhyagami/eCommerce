package com.woolcraft.service;

import com.woolcraft.dto.ReviewDTO;
import com.woolcraft.entity.*;
import com.woolcraft.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final FileStorageService fileStorageService;

    @Transactional
    public Review submitReview(Long userId, Long productId, int rating, String comment, MultipartFile image) {
        Product product = productRepository.findById(productId).orElseThrow();
        Review r = Review.builder().user(User.builder().id(userId).build()).product(product)
                .rating(rating).comment(comment).approved(false).createdAt(java.time.LocalDateTime.now()).build();
        if (image != null && !image.isEmpty()) r.setImage(fileStorageService.storeFile(image));
        r = reviewRepository.save(r);
        updateProductRating(productId);
        return r;
    }

    public List<ReviewDTO> getApprovedReviews(Long productId) {
        return reviewRepository.findByProductIdAndApprovedTrue(productId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ReviewDTO> getPendingReviews() {
        return reviewRepository.findByApprovedFalse().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public void approveReview(Long reviewId) {
        Review r = reviewRepository.findById(reviewId).orElseThrow();
        r.setApproved(true);
        reviewRepository.save(r);
        updateProductRating(r.getProduct().getId());
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    private void updateProductRating(Long productId) {
        List<Review> approved = reviewRepository.findByProductIdAndApprovedTrue(productId);
        double avg = approved.stream().mapToInt(Review::getRating).average().orElse(0);
        Product p = productRepository.findById(productId).orElse(null);
        if (p != null) { p.setRating(avg); productRepository.save(p); }
    }

    private ReviewDTO toDTO(Review r) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(r.getId()); dto.setUserId(r.getUser().getId()); dto.setUserName(r.getUser().getName());
        dto.setProductId(r.getProduct().getId()); dto.setRating(r.getRating());
        dto.setComment(r.getComment()); dto.setImage(r.getImage());
        dto.setApproved(r.isApproved()); dto.setCreatedAt(r.getCreatedAt().toString());
        return dto;
    }
}
