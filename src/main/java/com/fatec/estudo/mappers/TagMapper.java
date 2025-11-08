package com.fatec.estudo.mappers;

import com.fatec.estudo.dtos.tag.TagRequest;
import com.fatec.estudo.dtos.tag.TagResponse;
import com.fatec.estudo.entities.Tag;

public class TagMapper {
    public static Tag toEntity(TagRequest request){
        Tag tag = new Tag();

        tag.setName(request.name());

        return tag;
    }

    public static TagResponse toDto(Tag entity){
        return new TagResponse(
            entity.getId(),
            entity.getName()
        );
    }
}
