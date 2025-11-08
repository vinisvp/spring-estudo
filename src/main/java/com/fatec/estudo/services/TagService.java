package com.fatec.estudo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.estudo.dtos.tag.TagRequest;
import com.fatec.estudo.dtos.tag.TagResponse;
import com.fatec.estudo.entities.Tag;
import com.fatec.estudo.mappers.TagMapper;
import com.fatec.estudo.repositories.TagRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<TagResponse> getTags() {
        return tagRepository.findAll()
                                .stream()
                                .map(TagMapper::toDto)
                                .toList();
    }

    public TagResponse getTagById(long id) {
        return tagRepository.findById(id)
                            .map(TagMapper::toDto)
                            .orElseThrow(() -> new EntityNotFoundException("Tag not found"));
    }

    public TagResponse saveTag(TagRequest tag){
        Tag entity = TagMapper.toEntity(tag);
        entity = tagRepository.save(entity);
        return TagMapper.toDto(entity);
    }

    public void updateTag(TagRequest tag, long id){
        Tag aux = tagRepository.getReferenceById(id);
        
        aux.setName(tag.name());

        tagRepository.save(aux);
    }

    public void deleteTag(long id){
        if(tagRepository.existsById(id)){
            tagRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Tag not found!");
        }
    }
}
