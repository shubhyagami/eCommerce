package com.woolcraft.service;

import com.woolcraft.entity.Category;
import com.woolcraft.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAllActive() {
        return categoryRepository.findAll().stream().filter(Category::isActive).toList();
    }

    public List<Category> findAll() { return categoryRepository.findAll(); }
    public Category findById(Long id) { return categoryRepository.findById(id).orElse(null); }

    @Transactional
    public Category save(String name, String image) {
        return categoryRepository.save(Category.builder()
                .name(name).slug(name.toLowerCase().replaceAll("\\s+","-"))
                .image(image).active(true).build());
    }

    @Transactional
    public Category update(Long id, String name, String image, boolean active) {
        Category c = categoryRepository.findById(id).orElseThrow();
        c.setName(name); c.setSlug(name.toLowerCase().replaceAll("\\s+","-"));
        if (image != null) c.setImage(image); c.setActive(active);
        return categoryRepository.save(c);
    }

    @Transactional
    public void delete(Long id) { categoryRepository.deleteById(id); }
}
