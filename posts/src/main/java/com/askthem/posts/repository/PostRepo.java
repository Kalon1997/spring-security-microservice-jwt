package com.askthem.posts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.askthem.posts.model.Posts;

public interface PostRepo extends MongoRepository<Posts, Integer> {

}
