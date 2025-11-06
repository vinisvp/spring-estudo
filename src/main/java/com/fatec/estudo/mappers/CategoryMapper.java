package com.fatec.estudo.mappers;

import com.fatec.estudo.dtos.category.CategoryRequest;
import com.fatec.estudo.dtos.category.CategoryResponse;
import com.fatec.estudo.entities.Category;

public class CategoryMapper {
    public static Category toEntity(CategoryRequest request){
        Category category = new Category();

        category.setName(request.name());

        return category;
    }

    public static CategoryResponse toDto(Category entity){
        return new CategoryResponse(
            entity.getId(),
            entity.getName()
        );
    }
}
