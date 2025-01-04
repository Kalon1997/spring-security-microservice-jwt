package com.askthem.posts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.askthem.posts.dto.ListPost;
import com.askthem.posts.dto.PostAddReq;
import com.askthem.posts.model.Posts;
import com.askthem.posts.repository.PostRepo;

@RestController
public class PostController {
	@Autowired
	PostRepo postRepo;
	
	@PostMapping("/addquestion")
	public Posts addQuestion(@RequestBody PostAddReq postAddReq) {
		System.out.println("clicked====="+postAddReq);
		Integer postCount = postRepo.findAll().size();
		Posts newPost = new Posts(postCount+1, postAddReq.getQuestion(), "", false);
		Posts p = postRepo.save(newPost);
		System.out.println(p);
		return newPost;
	}
	
	
	@PostMapping("/all")
	public List<Posts> postList(@RequestBody ListPost listPost){
		return postRepo.findAll();
	}
}
