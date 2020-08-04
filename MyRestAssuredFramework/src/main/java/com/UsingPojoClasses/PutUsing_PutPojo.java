package com.UsingPojoClasses;

import com.Pojo.Put_Pojo;

public class PutUsing_PutPojo {

	public Put_Pojo PutMethod() {
		
	Put_Pojo put = new Put_Pojo();
	
	put.setId(32);
	put.setAuthor("Mark");
	
	return put;
	}
}
