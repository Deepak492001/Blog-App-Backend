package com.example.controller;

import java.io.IOException;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Category;
import com.example.entity.Post;
import com.example.repositary.CategoryRepositary;
import com.example.repositary.PostRepositary;
import com.example.service.PostService;



@RestController
@CrossOrigin("*")
//@CrossOrigin("http://localhost:5173")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private PostRepositary postRepositary;

//
	@PostMapping("/add-post")
	public Post addPost(@RequestParam("postTitle") String postTitle, @RequestParam("postContent") String postContent,
			@RequestParam("postCategory") String postCategory, @RequestParam("postUser") String postUser,
			@RequestParam("postImage") MultipartFile file) {
 
		try {
			
			return this.postService.savePostData(postTitle, postContent, postCategory, postUser, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	@GetMapping("/posts")
	public List<Post> getPosts(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "3", required = false) int size) {

		return this.postService.getAllPosts();
	}

	@DeleteMapping("/delete-post/{postId}")
	public void deletePost(@PathVariable("postId") int postId) {
		this.postRepositary.deleteById(postId);
	}

	@PutMapping("/update-post/{postId}")
	public Post updatePost(@RequestBody Post post, @PathVariable("postId") int postId) {
		Post previousPost = this.postRepositary.findById(postId);
		previousPost.setPostCategory(post.getPostCategory());
		previousPost.setPostUser(post.getPostUser());
		previousPost.setPostContent(post.getPostContent());
		previousPost.setPostLastmodified(post.getPostLastmodified());
		previousPost.setPostTitle(post.getPostTitle());
		return this.postRepositary.save(previousPost);
	}

	@GetMapping("/post-by-postId/{postId}")
	public Post getPostByPostId(@PathVariable int postId) {
		return this.postService.getPostById(postId);
	}

	@GetMapping("/post-by-user/{user}")
	public List<Post> getPostsByUser(@PathVariable("user") String user) {
		return this.postRepositary.findByPostUser(user);
	}

}
