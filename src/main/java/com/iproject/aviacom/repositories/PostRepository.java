package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
