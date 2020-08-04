package com.UsingPojoClasses;

import com.Pojo.Post_Pojo;

public class PostUsing_PostPojo {
	
	// Use to get post method caliing getters & setters
	
	public Post_Pojo PostMethod(String body, String title, String author)
	{
		Post_Pojo post = new Post_Pojo();
		post.setAuthor(body);
		post.setBody(title);
		post.setTitle(author);
		
		return post;
		
		
	}

}
