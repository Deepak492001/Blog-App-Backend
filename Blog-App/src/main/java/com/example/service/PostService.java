package com.example.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Category;

import com.example.entity.Post;
import com.example.entity.User;
import com.example.repositary.PostRepositary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class PostService {
  @Autowired
  private PostRepositary postRepositary;
  
  
  public Post savePostData (String postTitle,String  postContent,String  postCategory,String  postUser, MultipartFile file) throws IOException {
	  byte[] imageData = file.getBytes();
	  
	  LocalDateTime myDateObj = LocalDateTime.now();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		String formattedDate = myDateObj.format(myFormatObj);
		
		Post p=new Post(postTitle, postContent, formattedDate, postCategory, postUser, imageData);
    
      
//      ImageModel image = new ImageModel(imageData);
      return this.postRepositary.save(p);
  }
  
//  public Post savePostData (Post post, MultipartFile file) throws IOException {
//	  byte[] imageData = file.getBytes();
//	  
//	  LocalDateTime myDateObj = LocalDateTime.now();
//
//		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//		String formattedDate = myDateObj.format(myFormatObj);
//		
//		Post p=new Post(post.getPostTitle(), post.getPostContent(), formattedDate, post.getPostCategory(), post.getPostUser(), imageData);
//    
//      
////      ImageModel image = new ImageModel(imageData);
//      return this.postRepositary.save(p);
//  }
//  
  
  
  public List<Post> getAllPostsByUser(User user){
	  return this.postRepositary.findByPostUser(user);
  }
//  doubt
//  public List<Post> getAllPostsByCategory(Category category){
//	  return this.postRepositary.findByCategoryName(category);
//  }
  
  public List<Post> getAllPosts(){
	  return this.postRepositary.findALL();
  }
  
  public List<Post> getAllPosts(int pageNumber,int pageSize){
	  
	  Pageable pageable=PageRequest.of(pageNumber, pageSize);
	  
	   Page<Post> pagePost= this.postRepositary.findAll(pageable);

	   return pagePost.getContent();
  }
  
  public Post getPostById(int postId) {
	  return  this.postRepositary.findById(postId);
  }


  
  
}
