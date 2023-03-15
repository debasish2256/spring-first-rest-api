package com.springrest.springrest.controller;

import java.util.List;

import com.springrest.springrest.model.Post;
import com.springrest.springrest.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/addPost")
    public Post addPost(@RequestBody Post post){
        return postService.addPost(post);
    }
    @GetMapping("/getPosts")
    public List<Post> getAllPosts(){
        return postService.getAll();
    }
}
