package com.fastag.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fastag.common.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String>{

}
