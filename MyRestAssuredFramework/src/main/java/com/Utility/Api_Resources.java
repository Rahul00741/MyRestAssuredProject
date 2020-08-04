package com.Utility;

public enum Api_Resources {
	PostResource("/api/insert.php"),
	GetResource("/api/read.php"),
	PutResource("/api/update.php"),
	DeleteResource("/api/delete.php");
	
	private String Resource;
	
	Api_Resources (String resource)
	{
		this.Resource = resource;
	}
	
	public String getresource()
	{
		return Resource;
	}
	
}
