package com.springrest.springrest.service;

import java.util.List;

import com.springrest.springrest.model.Status;
import com.springrest.springrest.model.Users;
import com.springrest.springrest.repository.IStatusRepository;
import com.springrest.springrest.repository.IUserRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusService {

	@Autowired
	IStatusRepository statusRepository;
	@Autowired
	IUserRepository userRepository;
	
	public int saveStatus(Status status) {
		return statusRepository.save(status).getStatusId();
	}

	public JSONArray getPosts(Integer postId, Integer userId) throws Exception {
        JSONArray posts = new JSONArray();
        if(null!=postId ){

            Status p1 = statusRepository.findById(postId).get();
            JSONObject json = new JSONObject();
            json.put("post_id",p1.getStatusId());
            json.put("postData",p1.getPostData());
            json.put("createdDate",p1.getCreatedDate());
            json.put("updatedDate",p1.getUpdatedDate());
            JSONObject j2 = new JSONObject();
            Users user = p1.getUser();
            j2.put("user_id",user.getUserId());
            j2.put("first_name",user.getFirstName());
            j2.put("last_name",user.getLastName());
            j2.put("age",user.getAge());
            j2.put("email",user.getEmail());
            j2.put("phone_number",user.getPhoneNumber());
            json.put("user",j2);
            posts.put(json);

        }else if(null!=userId && userRepository.findById(userId).isPresent()){

            List<Status>allPosts = statusRepository.findAll();
            for(Status p1: allPosts){
                if(p1.getUser().getUserId()==userId){
                    JSONObject post = new JSONObject();
                    post.put("post_id",p1.getStatusId());
                    post.put("postData",p1.getPostData());
                    post.put("createdDate",p1.getCreatedDate());
                    post.put("updatedDate",p1.getUpdatedDate());
                    post.put("user_id",p1.getUser().getUserId());
                    JSONObject j2 = new JSONObject();
                    Users user = p1.getUser();
                    j2.put("user_id",user.getUserId());
                    j2.put("first_name",user.getFirstName());
                    j2.put("last_name",user.getLastName());
                    j2.put("age",user.getAge());
                    j2.put("email",user.getEmail());
                    j2.put("phone_number",user.getPhoneNumber());
                    post.put("user",j2);
                    posts.put(post);
                }
            }
        }
        return posts;
    }
	 public void deletePost(Integer id) {
		 statusRepository.deleteById(id);
	    }
}
