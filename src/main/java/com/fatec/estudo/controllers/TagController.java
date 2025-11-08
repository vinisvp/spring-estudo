package com.fatec.estudo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.estudo.dtos.tag.TagRequest;
import com.fatec.estudo.dtos.tag.TagResponse;
import com.fatec.estudo.services.TagService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getTags() {
        return ResponseEntity.ok(tagService.getTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getTagById(@PathVariable long id){
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    @PostMapping
    public ResponseEntity<TagResponse> saveTag(@Valid @RequestBody TagRequest tag){
        TagResponse savedTag = tagService.saveTag(tag);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedTag.id())
                        .toUri();

        return ResponseEntity.created(location).body(savedTag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTag(@PathVariable long id, @Valid @RequestBody TagRequest tag){
        tagService.updateTag(tag, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTagById(@PathVariable long id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
