package com.fatec.estudo.repositories;

import com.fatec.estudo.entities.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

}
