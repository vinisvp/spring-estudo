package com.fatec.estudo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.estudo.dtos.category.CategoryRequest;
import com.fatec.estudo.dtos.category.CategoryResponse;
import com.fatec.estudo.entities.Category;
import com.fatec.estudo.entities.Contact;
import com.fatec.estudo.mappers.CategoryMapper;
import com.fatec.estudo.repositories.CategoryRepository;
import com.fatec.estudo.repositories.ContactRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ContactRepository contactRepository;

    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll()
                                .stream()
                                .map(CategoryMapper::toDto)
                                .toList();
    }

    public CategoryResponse getCategoryById(long id) {
        return categoryRepository.findById(id)
                            .map(CategoryMapper::toDto)
                            .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    public CategoryResponse saveCategory(CategoryRequest category){
        Category entity = CategoryMapper.toEntity(category);
        entity = categoryRepository.save(entity);
        return CategoryMapper.toDto(entity);
    }

    public void updateCategory(CategoryRequest category, long id){
        Category aux = categoryRepository.getReferenceById(id);
        
        aux.setName(category.name());

        categoryRepository.save(aux);
    }

    public void deleteCategory(long id){
        if(categoryRepository.existsById(id)){
            Category category = categoryRepository.getReferenceById(id);
            for(Contact contact : category.getContacts()){
                contact.setCategory(null);
            }

            contactRepository.saveAll(category.getContacts());

            categoryRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Category not found!");
        }
    }
}
