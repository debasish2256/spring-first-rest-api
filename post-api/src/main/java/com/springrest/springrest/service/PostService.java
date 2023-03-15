package com.springrest.springrest.service;

import java.util.List;

import com.springrest.springrest.model.Post;
import com.springrest.springrest.repository.IPostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private IPostRepository iPostRepository;
    public Post addPost(Post post) {
        return iPostRepository.save(post);
    }

    public List<Post> getAll() {
        return iPostRepository.findAll();
    }
}
