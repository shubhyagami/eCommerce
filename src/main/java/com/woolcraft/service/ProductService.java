package com.woolcraft.service;

import com.woolcraft.dto.ProductDTO;
import com.woolcraft.entity.*;
import com.woolcraft.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;
    private final FileStorageService fileStorageService;

    public List<ProductDTO> findAllActive() {
        return productRepository.findByActiveTrueOrderByCreatedAtDesc().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> findFeatured() {
        return productRepository.findByFeaturedTrueAndActiveTrue().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> findNewArrivals() {
        return productRepository.findByActiveTrueOrderByCreatedAtDesc().stream().limit(8).map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> findBestSellers() {
        return productRepository.findBestSellers().stream().limit(8).map(this::toDTO).collect(Collectors.toList());
    }

    public ProductDTO findById(Long id) {
        return productRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public List<ProductDTO> search(String keyword) {
        return productRepository.search(keyword).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<String> autocomplete(String prefix) {
        return productRepository.autocomplete(prefix);
    }

    public List<ProductDTO> findByCategory(Long categoryId) {
        return productRepository.findByCategoryIdAndActiveTrue(categoryId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public long countActive() { return productRepository.countByActiveTrue(); }
    public long countTotal() { return productRepository.count(); }

    @Transactional
    public Product saveProduct(String name, String title, String description, BigDecimal price,
                                BigDecimal discountPrice, int stock, boolean featured,
                                Long categoryId, List<MultipartFile> images) {
        Category cat = categoryRepository.findById(categoryId).orElse(null);
        Product p = Product.builder().name(name).title(title).description(description)
                .price(price).discountPrice(discountPrice).stock(stock)
                .sku("WC-" + UUID.randomUUID().toString().substring(0,8).toUpperCase())
                .featured(featured).active(true).rating(0).category(cat)
                .createdAt(java.time.LocalDateTime.now()).build();
        p = productRepository.save(p);
        if (images != null) {
            List<ProductImage> imgs = new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                MultipartFile f = images.get(i);
                if (!f.isEmpty()) {
                    String path = fileStorageService.storeFile(f);
                    imgs.add(ProductImage.builder().product(p).imagePath(path).isPrimary(i == 0).build());
                }
            }
            productImageRepository.saveAll(imgs);
            p.setImages(imgs);
        }
        return p;
    }

    @Transactional
    public Product updateProduct(Long id, String name, String title, String description, BigDecimal price,
                                  BigDecimal discountPrice, int stock, boolean featured, boolean active,
                                  Long categoryId, List<MultipartFile> newImages, List<String> keepImages) {
        Product p = productRepository.findById(id).orElseThrow();
        p.setName(name); p.setTitle(title); p.setDescription(description);
        p.setPrice(price); p.setDiscountPrice(discountPrice); p.setStock(stock);
        p.setFeatured(featured); p.setActive(active);
        p.setCategory(categoryRepository.findById(categoryId).orElse(null));
        if (newImages != null && !newImages.isEmpty()) {
            List<ProductImage> current = new ArrayList<>(p.getImages());
            for (ProductImage img : current) {
                if (keepImages == null || !keepImages.contains(img.getImagePath())) {
                    fileStorageService.deleteFile(img.getImagePath());
                    productImageRepository.delete(img);
                }
            }
            p.getImages().removeIf(i -> keepImages == null || !keepImages.contains(i.getImagePath()));
            for (MultipartFile f : newImages) {
                if (!f.isEmpty()) {
                    String path = fileStorageService.storeFile(f);
                    productImageRepository.save(ProductImage.builder().product(p).imagePath(path).isPrimary(p.getImages().isEmpty()).build());
                }
            }
        }
        return productRepository.save(p);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product p = productRepository.findById(id).orElseThrow();
        p.getImages().forEach(i -> fileStorageService.deleteFile(i.getImagePath()));
        productRepository.delete(p);
    }

    @Transactional
    public void toggleActive(Long id) {
        Product p = productRepository.findById(id).orElseThrow();
        p.setActive(!p.isActive());
        productRepository.save(p);
    }

    public ProductDTO toDTO(Product p) {
        if (p == null) return null;
        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId()); dto.setName(p.getName()); dto.setTitle(p.getTitle());
        dto.setDescription(p.getDescription()); dto.setPrice(p.getPrice());
        dto.setDiscountPrice(p.getDiscountPrice()); dto.setStock(p.getStock());
        dto.setSku(p.getSku()); dto.setFeatured(p.isFeatured()); dto.setActive(p.isActive());
        dto.setRating(p.getRating());
        dto.setCategoryName(p.getCategory() != null ? p.getCategory().getName() : null);
        dto.setCategoryId(p.getCategory() != null ? p.getCategory().getId() : null);
        if (p.getImages() != null && !p.getImages().isEmpty()) {
            List<String> paths = p.getImages().stream().map(ProductImage::getImagePath).collect(Collectors.toList());
            dto.setImages(paths);
            dto.setPrimaryImage(p.getImages().stream().filter(ProductImage::isPrimary).findFirst().map(ProductImage::getImagePath).orElse(paths.get(0)));
        }
        return dto;
    }
}
