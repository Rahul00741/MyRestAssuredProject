package com.UsingPojoClasses;

import com.Pojo.Get_Pojo;
import com.Pojo.Post_Pojo;

public class GetUsing_GetPojo {

	public Get_Pojo PostMethod()
	{
		Get_Pojo post = new Get_Pojo();
		post.setAuthor("Rahul");
		post.setBody("API_Testing");
		post.setTitle("Rest_Assured");
		
		return post;
		
		
	}
}
