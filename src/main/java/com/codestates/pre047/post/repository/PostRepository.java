package com.codestates.pre047.post.repository;

import com.codestates.pre047.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

}
