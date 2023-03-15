package com.springrest.springrest.repository;

import com.springrest.springrest.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post, Integer> {
}
